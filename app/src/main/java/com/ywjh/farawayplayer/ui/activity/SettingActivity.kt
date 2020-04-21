package com.ywjh.farawayplayer.ui.activity

import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceManager
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.utils.ToolBarManager
import org.jetbrains.anko.find

class SettingActivity : BaseActivity(),ToolBarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.tb_toolbar) }
//获取layout布局即activity_setting
    override fun getLayoutid(): Int {
        //初始化内部按钮布局 调用系统内部自带的 会默认保存按钮是否被选中 因为继承BaseActivity所以 继承prefragment
//        PreferenceFragment
//        PreferenceActivity
    return  R.layout.activity_setting
    }
//复写ToolManager内的Seeting方法
    override fun initDate() {
        initSettingToolbar()
    //获取推送通知是否选中 选中状态系统的SharePreference自动保存
    var sp=PreferenceManager.getDefaultSharedPreferences(this)
    var push=sp.getBoolean("push",false)//默认false
    println("push=$push")
    }
}