package com.ywjh.farawayplayer.ui.fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.Preference
import androidx.preference.PreferenceFragment
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.ui.activity.AboutActivity
import org.jetbrains.anko.toast


class SettingFragment : PreferenceFragment() {
         override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
             addPreferencesFromResource(R.xml.setting)
         }

         override fun onPreferenceTreeClick(preference: Preference?): Boolean {
             val key=preference?.key
             //ps清除缓存可以清除data下cache文件
             if("about".equals(key)){
                 toast("点击了关于")
                 //context.startActivity(Intent(context,AboutActivity::class.java))
                 //context低于23api  使用activity即可
                 activity.startActivity(Intent(activity,AboutActivity::class.java))
             }

             return super.onPreferenceTreeClick(preference)
         }


     }
