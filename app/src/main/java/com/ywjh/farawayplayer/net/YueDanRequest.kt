package com.ywjh.farawayplayer.net

import com.ywjh.farawayplayer.model.YueDanBean
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils


class YueDanRequest(flag:Int, limit:Int, offset:Int, handler: ResponseHandler<YueDanBean>):MRequest<YueDanBean>(flag,

    YWJHURLProviderUtils.getYueDanUrl(limit,offset),handler) {

}