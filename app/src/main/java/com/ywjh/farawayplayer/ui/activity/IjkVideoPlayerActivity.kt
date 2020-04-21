package com.ywjh.farawayplayer.ui.activity

import android.Manifest
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.VideoPlayBean


import android.R.attr.start
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_video_player_ijk.*


class IjkVideoPlayerActivity:BaseActivity(),View.OnClickListener {
    override fun getLayoutid(): Int {
        return R.layout.activity_video_player_ijk
    }



    override fun initDate() {
        val videoPlayerBean= intent.getParcelableExtra<VideoPlayBean>("item")
        //println("2222222222itemBean=${VideoPlayBean}")
        //异步的准备  如果没准备好就 直接start会爆炸的
        videoView.setVideoPath("http://vodkgeyttp8.vod.126.net/cloudmusic/MjUyNDMxNzEw/0645927d1d174202121abf592a085c45/2b0d0faaf2c6d0feccd40b31ac668586.mp4?wsSecret=0ab6b6be547292495d0e617ac48d77f5&wsTime=1587349836")//不用findbyid
        //videoView.start()
        videoView.setOnPreparedListener{
            videoView.start()
    }

        play.setOnClickListener(this)
        pause.setOnClickListener(this)
        replay.setOnClickListener(this)

    }
//生命周期与播放界面绑定
    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
    }

    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.play -> if (!videoView.isPlaying) {
                videoView.start() // 开始播放
            }
            R.id.pause -> if (videoView.isPlaying) {
                videoView.pause() // 暂停播放
            }
            R.id.replay -> if (videoView.isPlaying) {
                //videoView.resume() // 重新播放
            }
        }
    }
}


