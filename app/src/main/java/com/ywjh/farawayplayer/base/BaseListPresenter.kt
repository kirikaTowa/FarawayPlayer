package com.ywjh.farawayplayer.base
//所有下拉刷新和上拉加载更多列表界面presenter基类
interface BaseListPresenter {
    companion object{
        val TYPE_INIT_OR_REFRESH=1
        val Type_LOAD_MORE=2
    }

    fun loadDatas()
    abstract fun LoadMore(i: Int)
    //解绑操作
    fun destoryView()
}