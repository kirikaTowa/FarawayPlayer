package com.ywjh.farawayplayer.utils
import android.widget.Toast
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.ui.fragment.HomeFragment
import com.ywjh.farawayplayer.ui.fragment.MVFragment
import com.ywjh.farawayplayer.ui.fragment.VBangFragment
import com.ywjh.farawayplayer.ui.fragment.YueDanFragment
import org.jetbrains.anko.support.v4.toast

//管理Fragment的Util类
//单例
class FragmentUtil private constructor(){//私有化构造方法
    val homeFragment by lazy {HomeFragment()  }
    val mvFragment by lazy { MVFragment()  }
    val vbangFragment by lazy {VBangFragment()  }
    val yuedanFragment by lazy {YueDanFragment()  }

    companion object{//伴生对象
        val fragmentUtil by lazy { FragmentUtil() }
    }
//通过封装类选择进入那个 Fragment
    fun getFragment(tabId:Int): BaseFragment {//Base返回值默认不支持为空

        when(tabId){
            R.id.tab_home -> return  homeFragment
            R.id.tab_mv -> return  mvFragment
            R.id.tab_vbang -> return  vbangFragment
            R.id.tab_yuedan -> return  yuedanFragment
        }

        //都没有就加null 非空判断 BaseFragment不支持空 所以加个？ 或者默认给homeFragment
        return  homeFragment
    }
}