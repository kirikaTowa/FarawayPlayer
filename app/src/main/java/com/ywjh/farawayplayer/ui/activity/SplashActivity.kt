package com.ywjh.farawayplayer.ui.activity

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {
    //监听this让当前Activity实现这个方法
    override fun onAnimationEnd(view: View?) {
    //动画结束 进入界面 startActivity 见anko简化
//        startActivity<MainActivity>()
//        finish()//结束当前Activity 这个地方可以抽取基类 以便后续使用
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutid(): Int {
        return R.layout.activity_splash
    }

    override fun initDate() {
        //对某个属性进行动画处理,通过anko库，不用拿到控件，直接传id名即可 2.加入Listener动画监听
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000)
    }



}