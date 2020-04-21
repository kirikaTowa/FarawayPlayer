package com.ywjh.farawayplayer.presenter.impl

import com.ywjh.farawayplayer.model.MvAreaBean
import com.ywjh.farawayplayer.net.MvAreaRequest
import com.ywjh.farawayplayer.net.ResponseHandler
import com.ywjh.farawayplayer.presenter.interf.MvPresenter
import com.ywjh.farawayplayer.view.MvView


//p层view绑定 var一个
class MvPresenterImpl(var mvView: MvView): MvPresenter, ResponseHandler<MvAreaBean> {
    override fun onError(flag: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun onSuccess(flag: Int, result: MvAreaBean) {

        mvView.onSuccess(result)
    }

    override fun loadDatas() {
        MvAreaRequest(this).execute()
    }
}