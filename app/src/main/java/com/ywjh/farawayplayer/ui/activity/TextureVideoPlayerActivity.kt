package com.ywjh.farawayplayer.ui.activity

import android.Manifest
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player.*

import android.R.attr.start
import android.graphics.SurfaceTexture
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView

import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_video_player_texture.*


class TextureVideoPlayerActivity:BaseActivity(), TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        //View大小发生变化的监听
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //视图更新
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
       //视图销毁 ，有报错，是因为 这个有bool类型返回值 true就不继续渲染 false调用release 销毁texture资源，且声明大多数app应返回true
       mediaPlayer?.let {
           mediaPlayer.stop()
           mediaPlayer.release()
       }
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        //加非空校验很重要的

        videoPlayBean?.let {
            //视图可用显示界面 但还需要解码 显示视频画面的界面

            // mediaPlayer.setDataSource(videoPlayBean.url)
            mediaPlayer.setDataSource("http://vodkgeyttp8.vod.126.net/cloudmusic/MjUyNDMxNzEw/0645927d1d174202121abf592a085c45/2b0d0faaf2c6d0feccd40b31ac668586.mp4?wsSecret=78f06a28f908083a31966b1b59664f8f&wsTime=1587039496")
            mediaPlayer.setSurface(Surface(surface))//设置显示画面
            mediaPlayer.prepareAsync()//异步准备
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
                //旋转画面
                textureView.rotation=100f
            }

        }
    }
    override fun getLayoutid(): Int {
        return R.layout.activity_video_player_texture
    }


    var videoPlayBean: VideoPlayBean? = null
    val mediaPlayer by lazy { MediaPlayer() }
    override fun initDate() {


        videoPlayBean= intent.getParcelableExtra<VideoPlayBean>("item")

       /* //异步的准备  如果没准备好就 直接start会爆炸的
        videoView.setVideoPath("http://vodkgeyttp8.vod.126.net/cloudmusic/MjUyNDMxNzEw/0645927d1d174202121abf592a085c45/2b0d0faaf2c6d0feccd40b31ac668586.mp4?wsSecret=2a9be61ff562356af134a092589fb74f&wsTime=1586910734")//不用findbyid
        //videoView.start()
        videoView.setOnPreparedListener{
            videoView.start()
        textureView.surfaceTextureListener = this//当前类
    }*/
       textureView.surfaceTextureListener = this//设置时间回调 当前类


    }



}


