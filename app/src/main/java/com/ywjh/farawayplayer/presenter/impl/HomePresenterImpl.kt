package com.ywjh.farawayplayer.presenter.impl

import com.google.gson.Gson
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.base.BaseView
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.net.HomeRequest
import com.ywjh.farawayplayer.net.NetManager
import com.ywjh.farawayplayer.net.ResponseHandler
import com.ywjh.farawayplayer.presenter.interf.HomePresenter

import com.ywjh.farawayplayer.ui.fragment.HomeFragment
import com.ywjh.farawayplayer.utils.ThreadUtil
import com.ywjh.farawayplayer.utils.YWJHURLProviderUtils
import com.ywjh.farawayplayer.view.HomeView

import okhttp3.*
import java.io.IOException
//实现类 P层中转站
/*通过view层接口双向绑定 构造时传入  使用var之后 内部其他方法就可以进行调用 通过谷歌的volley进行封装
* 通过View接口双向绑定*/
class HomePresenterImpl(var homeView: BaseView<List<HomeItemBeanSuccess.ResultBean>>?): HomePresenter, ResponseHandler<HomeItemBeanSuccess> {
//解绑View和presenter


    override  fun destoryView(){
        if(homeView!=null)
            homeView=null
    }

    override fun onError(flag:Int,msg: String?) {
        homeView?.onError(msg)
    }

    override fun onSuccess(flag:Int,result: HomeItemBeanSuccess) {
        //区分是初始化数据还是加载更多数据
        when(flag){
            1-> {
                homeView?.loadSuccess(result.getResult())

            }
            2->homeView?.loadMore(result.getResult())
        }
    }


    //初始化或刷新数据的操作  抽取相同的网络请求 放入model层（可通过Google volley框架）
    override fun loadDatas() {
        //封装泛型方法后实现网络请求的发送
        //定义request
        val request=HomeRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,8,this).execute()//匿名内部类
        //发送request
        //NetManager.manager.sendRequest(request)

    }

    override fun LoadMore(type: Int) {
        //封装泛型方法后实现网络请求的发送
        //定义request
        val request=HomeRequest(BaseListPresenter.Type_LOAD_MORE,type,this).execute()//匿名内部类

        //发送request
        //NetManager.manager.sendRequest(request)

    }
    }

