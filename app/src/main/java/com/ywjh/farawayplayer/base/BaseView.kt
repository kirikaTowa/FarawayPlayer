package com.ywjh.farawayplayer.base

import com.ywjh.farawayplayer.model.HomeItemBeanSuccess

//所有上拉刷新和下拉加载更多列表界面的基类
interface BaseView<RESPONSE> {
    fun onError(message:String?)
    //初始化数据或刷新数据成功
/*    fun loadSuccess(userBeanList: List<HomeItemBeanSuccess.ResultBean>?)
    fun loadMore(userBeanList: List<HomeItemBeanSuccess.ResultBean>?)*/
    fun loadSuccess(response: RESPONSE?)
    fun loadMore(response: RESPONSE?)
}