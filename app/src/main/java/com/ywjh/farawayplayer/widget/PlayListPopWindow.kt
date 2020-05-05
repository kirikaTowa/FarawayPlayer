package com.ywjh.farawayplayer.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import com.ywjh.farawayplayer.R
//不是布局而是窗体
class PlayListPopWindow(context:Context):PopupWindow(){
    init {
        //设置布局
        val view=LayoutInflater.from(context).inflate(R.layout.pop_playlist,null,false)//窗体无viewgroup
        contentView=view//展示出来
        //设置宽度和高度，不设置不显示出来
        //width=ViewGroup.LayoutParams.MATCH_PARENT
        //设置高度为3/5
        val manager=context.getSystemService(Context.WINDOW_SERVICE) as WindowManager//拿到windowmanager
        val point=Point()
        manager.defaultDisplay.getSize(point)
        val windowV:Int=point.x//设置宽度
        val windoeH=point.y
        width=(windowV*9)/10
        height=(windoeH*3)/5
        //设置获取焦点
        isFocusable=true
        //设置外部点击
        isOutsideTouchable=true
        //能够响应返回按钮（适配低版本点击返回按钮）
        setBackgroundDrawable(ColorDrawable())
    }
}
