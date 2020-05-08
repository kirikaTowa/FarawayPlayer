package com.ywjh.farawayplayer.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.ywjh.farawayplayer.R
import org.jetbrains.anko.find

//不是布局而是窗体
class PlayListPopWindow(context:Context,adapter:BaseAdapter,listener:AdapterView.OnItemClickListener,var window: Window):PopupWindow(){
    var alpha:Float=0f
    init {
        //记录当前透明度

        alpha=window.attributes.alpha
        //设置布局
        val view=LayoutInflater.from(context).inflate(R.layout.pop_playlist,null,false)//窗体无viewgroup
        //获取listview
        val listView=view.find<ListView>(R.id.listView)
        //做适配
        listView.adapter=adapter//适配是可变的，让外部传递主构造器

        //设置条目点击事件
        listView.setOnItemClickListener(listener)

        contentView=view//展示出来
        //设置宽度和高度，不设置不显示出来
        width=ViewGroup.LayoutParams.MATCH_PARENT
        //设置高度为3/5
        val manager=context.getSystemService(Context.WINDOW_SERVICE) as WindowManager//拿到windowmanager
        val point=Point()
        manager.defaultDisplay.getSize(point)
        //val windowV:Int=point.x//设置宽度
        val windoeH=point.y
        //width=(windowV*9)/10
        height=(windoeH*3)/5
        //设置获取焦点
        isFocusable=true
        //设置外部点击
        isOutsideTouchable=true
        //能够响应返回按钮（适配低版本点击返回按钮）
        setBackgroundDrawable(ColorDrawable())
        //处理popwindow动画
        animationStyle =R.style.pop
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        //popwindow已经显示，通过window对象获取透明度
        val attribute= window.attributes
        attribute.alpha=0.4f//范围是1~0,1最透明
        //设置到窗体显示
        window.attributes=attribute
    }

    override fun dismiss() {
        super.dismiss()
        //popwindow已经隐藏 恢复透明度
        val attributes=window.attributes
        attributes.alpha=alpha
        window.attributes=attributes

    }
}
