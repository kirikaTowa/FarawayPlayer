package com.ywjh.farawayplayer.ui.activity
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.model.AudioBean

class AudioPlayerActivity:BaseActivity(){
    override fun getLayoutid(): Int {
        return R.layout.activity_audio_player
    }

    override fun initDate() {
        val list=intent.getParcelableArrayListExtra<AudioBean>("list")
        val position=intent.getIntExtra("position",-1)//-1代表没有获取到
        //println("111111111111list=$list 2222222222positin=$position") 队列与位置正确
    }
}