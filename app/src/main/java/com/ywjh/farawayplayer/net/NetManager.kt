package com.ywjh.farawayplayer.net

import com.google.gson.Gson
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.utils.ThreadUtil
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils
import okhttp3.*
import java.io.IOException

//改装okhttp为      发送网络请求类 可以做成单例类每次请求都是Mrequest基类
class NetManager private constructor(){
    val client by lazy{OkHttpClient()}
    companion object{//创造伴生对象
        val manager by lazy{NetManager()}
    }
    //发送网络请求
    fun <RESPONSE> sendRequest(req:MRequest<RESPONSE>){
        //path url地址可通过req传入
//        val path = YWJHURLProviderUtils.getHomeUrl(type) 就不需要了

        val request = Request.Builder()
            .url(req.url).addHeader("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")//通过M基类的req对象拿到URl
            .get()//get方法
            .build()


        client.newCall(request).enqueue(object : Callback {


            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
//                        refreshLayout.isRefreshing=false
//                        homeView.onError(e?.message)
                        req.handler.onError(req.flag,e?.message)//通过handler回调给成功/失败接口实现类
                    }
                })

            }


            override fun onResponse(call: Call?, response: Response?) {
                //4.14查看结果集  根据数据结果集来编写Bean类(model->HomeItemBean)
                val result = response?.body()?.string()//.body()公开获取响应的流  .string()得到字符串
//    在这回调基类
                val parseResult=req.parseResult(result)
                //val userBeanList:List<RESPONSE.ResultBean>? = resultBean.getResult()

                ThreadUtil.runOnMainThread(object :Runnable{
                    override fun run() {
                        //loadmore成功回调不太一样
//                        if (userBeanList != null) {
//                            refreshLayout.isRefreshing=false
//                            adapter.loadMore(userBeanList)
//                        }
                        //都交给View层处理
                        //homeView.loadMore(userBeanList)
                        req.handler.onSuccess(req.flag,parseResult)
                    }

                })
            }
        })

    }
}