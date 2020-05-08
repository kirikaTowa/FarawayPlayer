package com.ywjh.farawayplayer.service

import com.ywjh.farawayplayer.model.AudioBean

//通过这个接口实现与service的交互
interface Iservice {
    fun updatePlayState()
    fun isPlaying():Boolean?
    abstract fun getDuration(): Int
    abstract fun getProgress(): Int
    abstract fun seekTo(progress: Int)
    fun updatePlayMode()
    abstract fun getPlayMode(): Int
    fun playPre()
    fun playNext()
    abstract fun getPlayList(): List<AudioBean>?
    abstract fun playPosition(position: Int)



}