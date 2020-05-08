package com.ywjh.farawayplayer.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import com.ywjh.farawayplayer.R
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.net.NetManager.Companion.manager
import com.ywjh.farawayplayer.ui.activity.AudioPlayerActivity
import com.ywjh.farawayplayer.ui.activity.MainActivity
import com.ywjh.farawayplayer.widget.PlayListPopWindow
import de.greenrobot.event.EventBus
import kotlin.random.Random
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T







class AudioService: Service() {
    var mediaPlayer :MediaPlayer?=null
    var manager: NotificationManager? = null
    var notification:Notification?=null
    var list:ArrayList<AudioBean>?=null
    var position:Int?=null//当前position
    //为了及时拿到Binder对象，惰性加载

    val FROM_PRE=1
    val FROM_NEXT=2
    val FROM_STATE=3
    val FROM_CONTENT=4
    val binder by lazy { AudioBinder() }
    companion object{//静态常量暴露
    val MODE_ALL=1
        val MODE_SINGLE=2
        val MODE_RANDOM=3
    }
    var mode=MODE_ALL
    val PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID"
    val PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME"

    val sp by lazy{getSharedPreferences("config",Context.MODE_PRIVATE)}
    override fun onCreate() {
        super.onCreate()
        //getintent发现不能直接获取 下面的方法有
        //创建sharepreference方式保存图标状态
        //获取并设置播放模式
        mode=sp.getInt("mode",1)

    }
    //在这获取集合，每次更新 目前发现onstartCommand开始执行了一次，杀死进程执行了一次，这是intent为空会报异常
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //判断是点击条目还是通知栏主体
        val from=intent?.getIntExtra("from",-1)
        when(from) {
            FROM_PRE -> {
                binder.playPre()
            }
            FROM_NEXT -> {
                binder.playNext()
            }
            FROM_CONTENT -> {
                binder.notifyUpdateUi()
            }
            FROM_STATE -> {
                binder.updatePlayState()
            }
            else -> {
                val pos = intent?.getIntExtra("position", -1) ?: -1//想要播放的position
                if (pos != position) {
                    position = pos
                    list = intent?.getParcelableArrayListExtra<AudioBean>("list")
                    //开始播放 Activity与service进行交互

                    binder.playItem()
                } else {
                    binder.notifyUpdateUi()
                }
            }

        }

        return START_NOT_STICKY
    }
    //多次绑定只会执行一次onBind方法 通过bind对象与medaiplayer对象进行交互
    override fun onBind(intent: Intent?): IBinder? {
        return binder//对返回的binder对象进行保存处理 其他对服务的操作
    }

    inner class AudioBinder:Binder(), Iservice,MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        override fun playPosition(position: Int) {
            this@AudioService.position=position//注解
            playItem()
        }

        override fun getPlayList(): List<AudioBean>? {
            return list
        }


        override fun playPre() {
            //获取播放的position
            list?.let{
                when(mode){
                    MODE_RANDOM->list?.let { position= Random.nextInt(it.size-1) }
                    else->{
                        if (position==0){
                            position= it.size-1
                        }else{
                            position= position?.minus(1)
                        }
                    }
                }
                playItem()
            }
        }

        override fun playNext() {
            list?.let {
                when(mode){
                    MODE_RANDOM->position=Random.nextInt(it.size-1)
                    else->position= (position?.plus(1))?.rem(it.size)
                }
            }
            playItem()
        }

        override fun getPlayMode(): Int {
            return mode
        }

        /*点击依次切换
        * 修改播放模式  MODE_ALL=1    MODE_SINGE=2  MODE_RANDOM=3 三模式依次切换
        * */
        override fun updatePlayMode() {
            when(mode){
                MODE_ALL -> mode = MODE_SINGLE
                MODE_SINGLE -> mode = MODE_RANDOM
                MODE_RANDOM -> mode = MODE_ALL
            }
            //保存播放模式
            sp.edit().putInt("mode",mode).commit()
        }


        //监听歌曲播放完成的回调
        override fun onCompletion(mp: MediaPlayer?) {
            //自动播放下一曲
            autoPlayNext()
        }
        //依据播放模式
        private fun autoPlayNext() {
            when(mode){
                MODE_ALL->{
                    // if (position==list?.size-1)
                    list?.let{

                        position=(position!!+1)%it.size

                    }
                }
                //MODE_SINGE->单曲循环不变
                MODE_RANDOM->list?.let {  position = java.util.Random().nextInt(it.size) }
            }
            playItem()
        }
        //跳转到当前位置播放
        override fun seekTo(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        /*
        * 获取当前播放进度
        * */
        override fun getProgress(): Int {
            return mediaPlayer?.currentPosition?:0
        }

        //获取总进度
        override fun getDuration(): Int {
            return mediaPlayer?.duration?:0
        }

        //更新播放状态
        override fun updatePlayState() {
            //获取当前状态
            val isPlaying=isPlaying()
            isPlaying?.let {
                //这里贼好用
                if (isPlaying){
                    pause()
                }else{
                    start()
                }
            }
            //切换状态
        }
        //暂停方法封装函数写法
        //private fun pause() = mediaPlayer?.pause()
        private fun pause() {
            mediaPlayer?.pause()
            EventBus.getDefault().post(position?.let { list?.get(it) })
            //更新图标
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_pause_normal)
            manager?.notify(1,notification)
        }
        //开始播放
        private fun start()
        {
            mediaPlayer?.start()
            EventBus.getDefault().post(position?.let { list?.get(it) })
            notification?.contentView?.setImageViewResource(R.id.state,R.mipmap.btn_audio_play_normal)
            manager?.notify(1,notification)
        }

        override  fun isPlaying():Boolean?{
            return mediaPlayer?.isPlaying
        }
        //start真正播放音乐
        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            //通知更新界面
            notifyUpdateUi()
            //显示通知栏
            showNotification()
        }



        private fun showNotification() {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notification=getNotification()
            manager?.notify(1,notification)
            //1.创建通知渠道
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW)
                manager?.createNotificationChannel(channel)
                channel.enableLights(false);
                channel.enableVibration(false);
                channel.setVibrationPattern(longArrayOf(0));
                channel.setSound(null, null);

            }
        }



        private fun getNotification(): Notification? {
            val bitmap = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            val notification = NotificationCompat.Builder(this@AudioService)
                .setTicker("正在播放歌曲${position?.let { list?.get(it)?.display_name }}")
                .setSmallIcon(R.drawable.ic_launchers)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launchers))
/*                .setLargeIcon(bitmap)
                .setContentTitle("北京")//通知标题
                .setContentText("汪峰")//通知内容*/
                //自定义通知view
                .setCustomContentView(getRemoteViews())
                .setWhen(System.currentTimeMillis())
                .setOngoing(true)//设置不能滑动删除通知
                .setContentIntent(getPendingIntent())//通知栏主体点击事件 延时加载
                .setChannelId(PUSH_CHANNEL_ID)
                .setSound(null)
                .setVibrate(longArrayOf(0))
                .setDefaults(NotificationCompat.FLAG_ONLY_ALERT_ONCE)
                .setOnlyAlertOnce(true)
                .build()

            return notification
        }




        private fun getRemoteViews(): RemoteViews? {



            val remoteViews = RemoteViews(packageName, R.layout.notification)
            //修改标题和内容
            //remoteViews.setImageViewBitmap(R.id.widget_album, bitmap)
            remoteViews.setTextViewText(R.id.title, position?.let { list?.get(it)?.display_name })
            remoteViews.setTextViewText(R.id.artist, position?.let { list?.get(it)?.artist })
            //处理上一曲 下一曲  状态点击事件
            remoteViews.setOnClickPendingIntent(R.id.pre, getPrePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.state, getStatePendingIntent())
            remoteViews.setOnClickPendingIntent(R.id.next, getNextPendingIntent())
            return remoteViews
        }

        /**
         * 下一曲点击事件
         */
        private fun getNextPendingIntent(): PendingIntent? {
            //这里的Intent操作，service，不需要操作界面
            val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_NEXT)
            val pendingIntent = PendingIntent.getService(this@AudioService,2,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 播放暂停按钮点击事件
         */
        private fun getStatePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_STATE)
            val pendingIntent = PendingIntent.getService(this@AudioService,3,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 上一曲点击事件
         */
        private fun getPrePendingIntent(): PendingIntent? {
            val intent = Intent(this@AudioService,AudioService::class.java)//点击主体进入当前界面中
            intent.putExtra("from",FROM_PRE)
            val pendingIntent = PendingIntent.getService(this@AudioService,4,intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 通知栏主体点击事件
         */
        private fun getPendingIntent(): PendingIntent? {
            val intentM = Intent(this@AudioService, MainActivity::class.java)//点击主体进入当前界面中
            val intentA = Intent(this@AudioService,AudioPlayerActivity::class.java)//点击主体进入当前界面中
            intentA.putExtra("from",FROM_CONTENT)
            val intents= arrayOf(intentM,intentA)
            val pendingIntent = PendingIntent.getActivities(this@AudioService,1,intents, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /*
        * 通知界面更新  发送端
        * */
        fun notifyUpdateUi(){
            //传递当前条目的bean给activity的bus

            EventBus.getDefault().post(position?.let { list?.get(it) })
        }
        //设置内部类就可以访问到list了
        fun playItem(){
            //1.创建进入idle状态
            //如果media已经存在就先释放掉
            if(mediaPlayer!=null){
                //和Video类似
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer=null
            }
            mediaPlayer= MediaPlayer() //放到外层大类惰性加载即可 一个service只用一个mediaplayer
            mediaPlayer?.let {

                //2.设置播放路径 。let配合lambda表达式简直美滋滋
                it.setDataSource(position?.let { list?.get(it)?.data })
                //3.异步准备
                it.prepareAsync()
                //3.监听准备
                it.setOnPreparedListener(this)//改写为this 让当前binder类实现 重要
                it.setOnCompletionListener(this)
            }

        }
    }



}