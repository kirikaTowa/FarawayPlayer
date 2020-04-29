package com.ywjh.farawayplayer.utils
//处理时间 毫秒变为时间字符串
object StringUtil {//静态类 不然会变暗色
    val Hour=60*60*1000
    val MIN=60*1000
    val SEC=1000
    var result:String=""
    fun parseDuration(progress: Int):String{
        val hour=progress/Hour
        val min=progress%Hour/MIN //小时求余数结果找分钟
        val sec=progress%MIN/SEC
        if (hour==0){
            result=String.format("%02d:%02d",min,sec)//不足两位以0补齐
        }else{
            result= String.format("%02d:%02d:%02d",hour,min,sec)
        }
        return result
    }
}