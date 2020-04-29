package com.ywjh.farawayplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.ywjh.farawayplayer.model.AudioBean
import de.greenrobot.event.EventBus

class AudioService: Service() {
    var mediaPlayer :MediaPlayer?=null

    var list:ArrayList<AudioBean>?=null
    var position:Int?=null
    //为了及时拿到Binder对象，惰性加载
    val binder by lazy { AudioBinder() }
    override fun onCreate() {
        super.onCreate()
        //getintent发现不能直接获取 下面的方法有

    }
    //在这获取集合，每次更新 目前发现onstartCommand开始执行了一次，杀死进程执行了一次，这是intent为空会报异常
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        list=intent?.getParcelableArrayListExtra<AudioBean>("list")
        position=intent?.getIntExtra("position",-1)?:-1
        //开始播放 Activity与service进行交互

        binder.playItem()
        return START_NOT_STICKY
    }
    //多次绑定只会执行一次onBind方法 通过bind对象与medaiplayer对象进行交互
    override fun onBind(intent: Intent?): IBinder? {
        return binder//对返回的binder对象进行保存处理 其他对服务的操作
    }

    inner class AudioBinder:Binder(), Iservice,MediaPlayer.OnPreparedListener {
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
        private  fun notifyUpdateUi(){
            //传递当前条目的bean给activity的bus
            EventBus.getDefault().post(position?.let { list?.get(it) })
        }
        //设置内部类就可以访问到list了
        fun playItem(){
            //1.创建进入idle状态
            mediaPlayer= MediaPlayer() //放到外层大类惰性加载即可 一个service只用一个mediaplayer
            mediaPlayer?.let {

                //2.设置播放路径 。let配合lambda表达式简直美滋滋
                it.setDataSource(position?.let { list?.get(it)?.data })
                //3.异步准备
                it.prepareAsync()
                //3.监听准备
                it.setOnPreparedListener(this)//改写为this 让当前binder类实现 重要
            }

        }
    }
}