package com.ywjh.farawayplayer.view

import com.ywjh.farawayplayer.base.BaseView
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess

/*
*
* 负责home界面和presenter交互 接着应该在啊presenter层写一个借口控制数据流
* */
interface HomeView:BaseView<List<HomeItemBeanSuccess.ResultBean>> {
   /* fun onError(message:String?)
    //初始化数据或刷新数据成功
    fun loadSuccess(userBeanList: List<HomeItemBeanSuccess.ResultBean>?)
     fun loadMore(userBeanList: List<HomeItemBeanSuccess.ResultBean>?)*/
}