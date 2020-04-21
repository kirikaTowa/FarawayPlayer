package com.ywjh.farawayplayer.widget


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

import com.squareup.picasso.Picasso

import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import kotlinx.android.synthetic.main.item_home.view.*


class HomeItemView : RelativeLayout{


    //复写三个次级构造方法
    //代码new相关
    constructor(context: Context?) : super(context)
    //清单文件相关
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    //主题相关
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
/*初始化方法 调用主，次构造方法都会执行
下面阶段的inflate方法传入一个xml文件进行调用
* */

    init {
    //this 当前的xml文件解析为view传过去 填null则应写addview
        View.inflate(context,R.layout.item_home,this)
    }
    /*刷新条目view数据*/
    fun setDate(data: HomeItemBeanSuccess.ResultBean) {
        //歌曲名称
        title.setText(data.title)
        //简介
        desc.setText(data.author)
        //处理背景图片 使用一个框架
        /*光调用获取的数据图片可能不能全覆盖src 应该找到显示控件 然后是培育新 item_home ImageView控件 id 为bg*/
       // Picasso.with(context).load(data.pic_radio).into(bg)
        Picasso.with(context).load("http://47.98.147.90:8080/img/%E9%A3%8E%E5%87%8C1.png").into(bg)

    }



}