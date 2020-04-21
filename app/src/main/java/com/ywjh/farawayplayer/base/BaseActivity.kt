package com.ywjh.farawayplayer.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ywjh.farawayplayer.ui.activity.MainActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

//所有Activity基类
abstract class BaseActivity: AppCompatActivity(), AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutid())//基类抽取界面组
        initListener()//子类中并非一定要实现，创建一个function即可
        initDate()//数据支持

        //anko log日志处理
/*        debug { "哈哈" }*/

    }

    //    初始化数据,非私有加入open关键字才能复写
    open protected fun initDate() {

    }


    //    adapter listener
    open protected fun initListener(){

    }

    //创建抽象类以供获取Layout，abstract不用加
    abstract fun getLayoutid():Int

    //在基类线中弹toast解决线的问题   1？
    protected fun myToast(msg:String){
        runOnUiThread{toast(msg)}
    }

    //开启activity并且finish当前界面,添加内联支持inline 与reified

    //进入看发现限制是Activity或Activity子类  reified用于找到传入泛型具体的类 下面内部就可以获取Tclass名（所以直接传泛型无法获取名称）
   inline  fun <reified T:BaseActivity>startActivityAndFinish(){
        startActivity<T>()
        finish()
    }
}