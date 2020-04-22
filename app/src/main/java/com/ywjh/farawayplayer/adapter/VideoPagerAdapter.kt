package com.ywjh.farawayplayer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ywjh.farawayplayer.ui.fragment.DefaultFragment

class VideoPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return DefaultFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}