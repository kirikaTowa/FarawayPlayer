package com.ywjh.farawayplayer.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.ywjh.farawayplayer.adapter.MvListAdapter
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.base.BaseListFragment
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.model.MvPagerBean
import com.ywjh.farawayplayer.model.VideoPlayBean
import com.ywjh.farawayplayer.presenter.impl.MvListPresenterImpl
import com.ywjh.farawayplayer.presenter.impl.MvPresenterImpl
import com.ywjh.farawayplayer.ui.activity.IjkVideoPlayerActivity
import com.ywjh.farawayplayer.ui.activity.JiaoZiVideoPlayerActivity
import com.ywjh.farawayplayer.ui.activity.TextureVideoPlayerActivity
import com.ywjh.farawayplayer.ui.activity.VideoPlayerActivity
import com.ywjh.farawayplayer.view.MvListView
import com.ywjh.farawayplayer.widget.MvItemViw
import org.jetbrains.anko.support.v4.startActivity

//每个mv页面的fragment
//pagerfragment具有上拉刷新和下拉加载更多Base
class MvPagerFragment: BaseListFragment<MvPagerBean,MvPagerBean.DataBean.InfoBean,MvItemViw>(), MvListView {


    var precode:Int? = null
    var code:Int? = null
    override fun init() {
        precode = arguments?.getInt("args")?.rem(10)

        if (precode!! > 7 || precode!! <1 )
            precode=4

        code=precode
    }

    override fun getSpecialAdapter(): BaseListAdapter<MvPagerBean.DataBean.InfoBean, MvItemViw> {
        return MvListAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return MvListPresenterImpl(code!!,this)
    }

    override fun getList(response: MvPagerBean?): List<MvPagerBean.DataBean.InfoBean>? {
        return response?.data?.info
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destoryView()
    }

    override fun initListener() {
        //父类执行了很多操作，子类需要，不能删
        super.initListener()
        //设置条目点击事件监听函数 //传一个函数 括号可以去掉 而只有一个参数 这个参数也可以去掉
   /*     adapter.setMyListener ({
                a -> Unit
            }
        )*/
        adapter.setMyListener {
            //a->Unit
              //it//代表传递回来的data数据 打印是Infobean类 下面类型不匹配
            val videoPlayBean= VideoPlayBean(it.userid,it.title,it.album_cover)
            startActivity<JiaoZiVideoPlayerActivity>("item" to videoPlayBean)

        }

    }
}