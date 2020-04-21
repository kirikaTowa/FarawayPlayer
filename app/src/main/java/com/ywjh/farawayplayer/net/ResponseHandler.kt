package com.ywjh.farawayplayer.net
/*
* Description:请求回调
* */
interface ResponseHandler<RESPONSE> {
    fun onError(flag:Int,msg:String?)
    //成功返回当前结果  结果不一定一样 可用泛型限定
    fun onSuccess(flag:Int,result:RESPONSE)
}