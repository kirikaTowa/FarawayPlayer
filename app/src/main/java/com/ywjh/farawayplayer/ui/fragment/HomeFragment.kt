package com.ywjh.farawayplayer.ui.fragment

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ywjh.farawayplayer.adapter.HomeAdapter
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.base.BaseListFragment
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.presenter.impl.HomePresenterImpl
import com.ywjh.farawayplayer.utils.ThreadUtil
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils
import com.ywjh.farawayplayer.view.HomeView
import com.ywjh.farawayplayer.widget.HomeItemView

import kotlinx.android.synthetic.main.fragment_list.*
import okhttp3.*
import java.io.IOException


class HomeFragment: BaseListFragment<List<HomeItemBeanSuccess.ResultBean>,HomeItemBeanSuccess.ResultBean,HomeItemView>() {
    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBeanSuccess.ResultBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter{
        return  HomePresenterImpl(this)//传this报错因为传homeview而原继承这个的改写继承Base。。。是BaesView
    }

    override fun getList(response: List<HomeItemBeanSuccess.ResultBean>?): List<HomeItemBeanSuccess.ResultBean>? {
        //println("22222222"+response)得到数据
        return response
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destoryView()
    }
}
