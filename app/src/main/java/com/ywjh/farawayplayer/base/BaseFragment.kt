package com.ywjh.farawayplayer.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast


/*
* 继承Fragment 所有Fragment基类
* */
abstract class BaseFragment: Fragment(), AnkoLogger {
    //初始化
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()//此方法并非实例具体要实现
        debug { "哈哈" }
    }
//显示一个布局
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }
//获取布局view
    abstract fun initView(): View?



    //fragment初始化
    protected open fun init(){

    }
    //adapter与数据加载
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //adapter与 数据的加载
        initListener()
        initData()//        toast("哈哈")


    }
//数据的初始化
    protected open fun initData() {
    }

    protected open fun initListener() {
    }

    protected fun myToast(msg:String){
        context?.runOnUiThread{toast(msg)}
    }




}
