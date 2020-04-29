package com.ywjh.farawayplayer.service
//通过这个接口实现与service的交互
interface Iservice {
    fun updataPlayState()
    fun isPlaying():Boolean?
    abstract fun getDuration(): Int
    abstract fun getProgress(): Int

}