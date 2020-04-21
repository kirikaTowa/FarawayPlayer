package com.ywjh.farawayplayer.utils


import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.ui.activity.SettingActivity


/*ClassName:ToolBarManger
Decription：所有界面toolbar管理类*/
interface ToolBarManager {
    /*由于不知道是哪里传过来的TOOlBar
    所以将Class变为接口
    Kotlin中接口与Java不同，可以实现*/
    val toolbar: Toolbar

    //初始化主界面中的toolbar
    fun initMainToolBar(){
        toolbar.setTitle("风聆")
        //设置一个菜单解析菜单布局
        toolbar.inflateMenu(R.menu.main)
        //        setOnMenuItemClickListener两种处理方式


        //kotlin与java进行调用的特性
//如果java接口中只有一个未实现的方法 可以省略接口的对象 直接用{}表示未实现的方法
        toolbar.setOnMenuItemClickListener{
         /*也可自行设置a->
         再捕捉a
         内含一个it参数="设置"
           */ println("item=$it")
            toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
            true
        }
        //设置菜单点击事件 2方法
/*        toolbar.setOnMenuItemClickListener(object : Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.setting->{
//                    Toast.makeText(toolbar.context,"点击了设置按钮",Toast.LENGTH_LONG).show();
//                      成功，进行跳转至设置界面 intent构建对象  后面获取::class java类加(继承appcompent)class.java
                    toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
                }
            }

            return true;
            }

        })*/
    }
    //处理设置界面的Toolbar SettingActivity
    fun initSettingToolbar(){
        toolbar.setTitle("设置界面")
    }
}