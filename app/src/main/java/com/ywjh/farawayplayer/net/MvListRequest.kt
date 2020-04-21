package com.ywjh.farawayplayer.net

import com.ywjh.farawayplayer.model.MvPagerBean
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils

class MvListRequest(flag:Int, code:Int,limit:Int, offset:Int, handler: ResponseHandler<MvPagerBean>)
    : MRequest<MvPagerBean>(flag,YWJHURLProviderUtils.getMVlistUrl(code,offset,limit),handler) {
}