package com.ywjh.farawayplayer.net

import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils

//首页数据请求类
class HomeRequest(flag:Int,type:Int,handler: ResponseHandler<HomeItemBeanSuccess>)
    :MRequest<HomeItemBeanSuccess>(flag,YWJHURLProviderUtils.getHomeUrl(type),handler) {

}