package com.ywjh.farawayplayer.ui.fragment

import com.ywjh.farawayplayer.R
import android.view.View
import android.widget.Adapter
import com.ywjh.farawayplayer.adapter.MvPagerAdapter
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.model.MvAreaBean
import com.ywjh.farawayplayer.presenter.impl.MvPresenterImpl
import com.ywjh.farawayplayer.view.MvView
import kotlinx.android.synthetic.main.fragment_mv.*


class MVFragment: BaseFragment(),MvView {
    override fun onSuccess(result: MvAreaBean) {

       val adapter=MvPagerAdapter(result.data?.list,childFragmentManager)
        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onError(msg: String?) {
        myToast("加载数据失败")
    }

   /* override fun onSuccess() {
        //适配Mv界面 通过tablelayout适配viewpager
        //mvfragment嵌套上下两层fragment
        //其中管理需要chidfragment 报错了是因为传入可能为空，修改为空返回0

    }*/

    val preseneter by lazy{MvPresenterImpl(this)}
    override fun initView(): View? {
        return View.inflate(context,R.layout.fragment_mv,null)
    }

    override fun initListener() {
        super.initListener()
    }
//上边浮动栏的条目由返回的网络数据条目决定，首先先获取网络请求数据
    override fun initData() {
        preseneter.loadDatas()

    }
}