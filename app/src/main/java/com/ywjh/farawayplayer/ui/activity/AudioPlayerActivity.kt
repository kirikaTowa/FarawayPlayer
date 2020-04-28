package com.ywjh.farawayplayer.ui.activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.IBinder
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.service.AudioService
import com.ywjh.farawayplayer.service.Iservice

class AudioPlayerActivity:BaseActivity(){
    override fun getLayoutid(): Int {
        return R.layout.activity_audio_player
    }

    val conn by lazy { AudioConnection()}
    override fun initDate() {
        /*
        * 1.和2.对应拿包裹和封装intent转发包裹，但是没必要
        * */
       /* 1.val list=intent.getParcelableArrayListExtra<AudioBean>("list")
        val position=intent.getIntExtra("position",-1)//-1代表没有获取到*/
        //println("111111111111list=$list 2222222222positin=$position") 队列与位置正确
        //-》通过AudioService播放
        var intent=intent//这是getintent 和1中一样取从VBangFragment中的数据
        //修改
        intent.setClass(this,AudioService::class.java)
        //val intent= Intent(this,AudioService::class.java)//對應發件人和收件人
      /*  2.//通过intent将list以及position传递过去
        intent.putExtra("list",list)
        intent.putExtra("position",position)*/

        //先开启
        startService(intent)
        //再绑定
        bindService(intent,conn, Context.BIND_AUTO_CREATE)//第二个参数是接口connection
        //回到AudioService进行播放


        /* //播放音乐
        //1.创建进入idle状态
        val mediaPlayer=MediaPlayer()
        //2.设置播放路径
        mediaPlayer.setDataSource(list.get(position).data)
        //3.异步准备
        mediaPlayer.prepareAsync()
        //3.监听准备
        mediaPlayer.setOnPreparedListener{
            mediaPlayer.start()
        }*/

    }
    var  iService:Iservice?=null
    inner class AudioConnection:ServiceConnection{
        /*
        * 意外断开连接时
        * */
        override fun onServiceDisconnected(name: ComponentName?) {

        }
        /*
        * service连接时
        * */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService=service as Iservice//报错了 AudioService没实现接口直接莽会报错
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)//手动解绑
    }
}