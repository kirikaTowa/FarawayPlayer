package com.ywjh.farawayplayer.adapter

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ywjh.farawayplayer.model.MvAreaBean
import com.ywjh.farawayplayer.ui.fragment.MVFragment

import com.ywjh.farawayplayer.ui.fragment.MvPagerFragment

//这个FragmentPagerAdapter实现参数，复写两个方法
class MvPagerAdapter(val list: List<MvAreaBean.DataBean.ListBean>?, fm: FragmentManager?) :FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        //用于返回某个Fragment

       val fragment=MvPagerFragment()//fragment传参
        val bundle=Bundle()
        list?.get(position)?.channel_id?.let { bundle.putInt("args", it) }

        //bundle.putString("args",list?.get(position)?.name)
        fragment.arguments=bundle//arguments传一个bundle
      // val fragment=Fragment.instantiate(context,MvPagerFragment::class.java.name,bundle)
        return fragment
        //return MVFragment()
    }

    override fun getCount(): Int {
        return list?.size?:0

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list?.get(position)?.name
    }
}