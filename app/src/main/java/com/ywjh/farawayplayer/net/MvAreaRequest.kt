package com.ywjh.farawayplayer.net

import com.ywjh.farawayplayer.model.MvAreaBean
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils

//这个没有上拉刷新和下拉加载更多 flag没啥用
class MvAreaRequest(handler: ResponseHandler<MvAreaBean>) :
    MRequest<MvAreaBean>(0, YWJHURLProviderUtils.getMVareaUrl(), handler) {
}