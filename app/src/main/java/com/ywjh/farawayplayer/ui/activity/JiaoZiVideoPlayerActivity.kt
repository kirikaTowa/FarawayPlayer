package com.ywjh.farawayplayer.ui.activity

import android.Manifest
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player.*

import android.R.attr.start
import android.content.pm.PackageManager

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.util.Log
import android.view.ViewParent
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import cn.jzvd.JzvdStd

import kotlinx.android.synthetic.main.activity_video_player_jiaozi.*
import cn.jzvd.Jzvd
import com.ywjh.farawayplayer.adapter.VideoPagerAdapter
import kotlinx.android.synthetic.main.fragment_mv.*
import org.jetbrains.anko.radioGroup


class JiaoZiVideoPlayerActivity:BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            initDate() // 初始化MediaPlayer
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initDate()
            } else {
                Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    override fun getLayoutid(): Int {
        return R.layout.activity_video_player_jiaozi
    }



    override fun initDate() {
        //手机外部广播通过data传递数据
        val data=intent.data
        if (data==null){
            jz_video.setUp(
                "http://47.98.147.90:8080/faraway.mp4",
                "遠空"
            )
        }else{
            if (data.toString().startsWith("http")){
                //应用外网络视频请求
                jz_video.setUp(
                    data.toString(),
                    data.toString()
                )
            }else{
                //外本地请求
                jz_video.setUp(
                    data.path,
                    data.toString()
                )
            }

        }


        val videoPlayerBean= intent.getParcelableExtra<VideoPlayBean>("item")


    }
    //点击后退
    override fun onBackPressed() {
        if (Jzvd.backPress()) {
            return
        }
        super.onBackPressed()//后退
    }

    override fun onPause() {
        super.onPause()
        Jzvd.releaseAllVideos()//释放资源
    }

    override fun initListener() {
        //适配viewpagert 需要adapter 创建ViewPagerAdapter
        viewpagerjz.adapter=VideoPagerAdapter(supportFragmentManager)
        rg.setOnCheckedChangeListener{
            radioGroup,i->
            when(i){
                R.id.rb1 ->viewpagerjz.setCurrentItem(0)
                R.id.rb2 ->viewpagerjz.setCurrentItem(1)
                R.id.rb3 ->viewpagerjz.setCurrentItem(2)
            }
        }
       //viewpager选中设置监听  有三个方法不能直接打开 内部类实现
        viewpagerjz.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

          //滑动(左右)状态 改变的回调
            override fun onPageScrollStateChanged(state: Int) {
            }
            //滑动的回调
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            //滑动状态改变 选中状态改变 做滑动下面 同步更改上面
            override fun onPageSelected(position: Int) {

                when(position){
                    0->rg.check(R.id.rb1)
                    1->rg.check(R.id.rb2)
                    2->rg.check(R.id.rb3)
                }
            }


        })
    }


}






