package com.ywjh.farawayplayer.ui.fragment
import android.graphics.Color
import com.ywjh.farawayplayer.R
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ywjh.farawayplayer.adapter.HomeAdapter
import com.ywjh.farawayplayer.adapter.YueDanAdapter
import com.ywjh.farawayplayer.base.BaseFragment
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.base.BaseListFragment
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.model.YueDanBean
import com.ywjh.farawayplayer.presenter.impl.HomePresenterImpl
import com.ywjh.farawayplayer.presenter.impl.YueDanPresenterImpl
import com.ywjh.farawayplayer.view.YueDanView
import com.ywjh.farawayplayer.widget.HomeItemView
import com.ywjh.farawayplayer.widget.YueDanItemView
import kotlinx.android.synthetic.main.fragment_list.*

//第二步开始写yuedan Fragment发现原homefragment的布局是普适用的 抽取为list 每个条目的bean类
class YueDanFragment: BaseListFragment<YueDanBean, YueDanBean.ResultBean.PlaylistsBean, YueDanItemView>() {
    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.ResultBean.PlaylistsBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenter {
        return YueDanPresenterImpl(this)
    }

    override fun getList(response: YueDanBean?): List<YueDanBean.ResultBean.PlaylistsBean>? {

        return response?.result?.playlists
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destoryView()
    }
}
