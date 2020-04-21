package com.ywjh.farawayplayer.presenter.impl

import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.base.BaseView
import com.ywjh.farawayplayer.model.YueDanBean
import com.ywjh.farawayplayer.net.ResponseHandler
import com.ywjh.farawayplayer.net.YueDanRequest
import com.ywjh.farawayplayer.presenter.interf.YueDanPresenter
import com.ywjh.farawayplayer.view.YueDanView
//继承接口后传handler用this就很好用
class YueDanPresenterImpl (var yueDanView: BaseView<YueDanBean>?):YueDanPresenter, ResponseHandler<YueDanBean> {
    override fun destoryView() {
        if(yueDanView!=null)
            yueDanView=null
    }

    override fun onError(flag: Int, msg: String?) {
        yueDanView?.onError(msg)
    }

    override fun onSuccess(flag: Int, result: YueDanBean) {
        if (flag==BaseListPresenter.TYPE_INIT_OR_REFRESH){

            yueDanView?.loadSuccess(result)


        }else if(flag==BaseListPresenter.Type_LOAD_MORE){
            yueDanView?.loadMore(result)
        }

    }

    override fun loadDatas() {
       YueDanRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,3,0,this).execute()

    }

    override fun LoadMore(offset: Int) {
        YueDanRequest(BaseListPresenter.Type_LOAD_MORE,3,offset,this).execute()
    }

}