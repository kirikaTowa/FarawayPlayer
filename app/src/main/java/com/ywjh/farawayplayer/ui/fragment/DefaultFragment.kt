package com.ywjh.farawayplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.ywjh.farawayplayer.base.BaseFragment

class DefaultFragment: BaseFragment() {
    override fun initView(): View? {
        val tv= TextView(context)
        tv.gravity= Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.text=javaClass.simpleName//text显示的文字为类名
        return tv
    }
}