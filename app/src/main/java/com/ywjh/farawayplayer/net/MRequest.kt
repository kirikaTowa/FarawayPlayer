package com.ywjh.farawayplayer.net

import com.google.gson.Gson
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import java.lang.reflect.ParameterizedType

//创建所有请求的基类 供其他人调用 也不知道的会传入一个啥 后面咋调用 所以也传入一个泛型
//打开一下 传两个参数 1.传入数据地址 2.结果回调Handler
open class MRequest<RESPONSE>(
    val flag:Int,
    val url: String,
    val handler: ResponseHandler<RESPONSE>
) {
   //解析网络请求的结果
    fun parseResult(result: String?): RESPONSE {
       val gson = Gson()
       //获取泛型类型

       val type=(this.javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[0]

       val resultBean:RESPONSE = gson.fromJson(result, type)

       //fromJson是将数据转换为我们要的结果

/*       val resultBean:HomeItemBeanSuccess = gson.fromJson(result, HomeItemBeanSuccess::class.java!!)
       val userBeanList:List<HomeItemBeanSuccess.ResultBean> = resultBean.getResult()*/

       /*数组写法
       原val list=gson.fromJson<<ListBean>>(result,object:TypeToken<List<HomeItemBean>>(){}.type*/
        return resultBean
   }
//发送网络请求
fun execute(){
    NetManager.manager.sendRequest(this)
}

}