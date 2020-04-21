package com.ywjh.farawayplayer.presenter.impl

import com.ywjh.farawayplayer.base.BaseListFragment
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.model.MvPagerBean
import com.ywjh.farawayplayer.net.MvListRequest
import com.ywjh.farawayplayer.net.ResponseHandler
import com.ywjh.farawayplayer.presenter.interf.MvListPresenter
import com.ywjh.farawayplayer.view.MvListView

class MvListPresenterImpl(var code:Int, var mvListView: MvListView?): MvListPresenter, ResponseHandler<MvPagerBean> {
    override fun onError(flag: Int, msg: String?) {
       mvListView?.onError(msg)
    }

    override fun onSuccess(flag: Int, result: MvPagerBean) {
        if (flag==BaseListPresenter.TYPE_INIT_OR_REFRESH){
            mvListView?.loadSuccess(result)
        }else if (flag==BaseListPresenter.Type_LOAD_MORE){
            mvListView?.loadMore(result)
        }
    }

    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,code,1,20,this).execute()
    }

    override fun LoadMore(i: Int) {
        MvListRequest(BaseListPresenter.Type_LOAD_MORE,code,1,i,this).execute()
    }

    override fun destoryView() {
       if (mvListView!=null)
           mvListView=null
    }
}