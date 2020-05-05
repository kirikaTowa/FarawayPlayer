package com.ywjh.farawayplayer.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.widget.PlayListPopWindow
import de.greenrobot.event.EventBus
import kotlin.random.Random

class AudioService: Service() {
    var mediaPlayer :MediaPlayer?=null

    var list:ArrayList<AudioBean>?=null
    var position:Int?=null//当前position
    //为了及时拿到Binder对象，惰性加载
    val binder by lazy { AudioBinder() }
    companion object{//静态常量暴露
        val MODE_ALL=1
        val MODE_SINGLE=2
        val MODE_RANDOM=3
    }
    var mode=MODE_ALL

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
        val pos=intent?.getIntExtra("position",-1)?:-1//想要播放的position
        if (pos !=position)
        {
            position=pos
            list=intent?.getParcelableArrayListExtra<AudioBean>("list")
            //开始播放 Activity与service进行交互

            binder.playItem()
        }else{
            binder.notifyUpdateUi()
        }


        return START_NOT_STICKY
    }
    //多次绑定只会执行一次onBind方法 通过bind对象与medaiplayer对象进行交互
    override fun onBind(intent: Intent?): IBinder? {
        return binder//对返回的binder对象进行保存处理 其他对服务的操作
    }

    inner class AudioBinder:Binder(), Iservice,MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {


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
        override fun updataPlayState() {
            //获取当前状态
           val isPlayer=isPlaying()
           isPlayer?.let {
               //这里贼好用
               if (isPlayer){
                   mediaPlayer?.pause()
               }else{
                   mediaPlayer?.start()
               }
           }
           //切换状态
        }
        override  fun isPlaying():Boolean?{
            return mediaPlayer?.isPlaying
        }
        //start真正播放音乐
        override fun onPrepared(mp: MediaPlayer?) {
            mediaPlayer?.start()
            //通知更新界面
            notifyUpdateUi()
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