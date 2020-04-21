package com.ywjh.farawayplayer.base

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.adapter.HomeAdapter
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.presenter.impl.HomePresenterImpl
import com.ywjh.farawayplayer.view.HomeView
import kotlinx.android.synthetic.main.fragment_list.*

//所有具有下拉刷新和上拉加载更多基类 HomeView不行
/*
*基类抽取 HomeView->BaseView
* Adapter->BaselistAdapter
* Presenter->BaselistPresenter
* */
abstract class BaseListFragment<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment(), BaseView<RESPONSE> {
    //传入一个string在将其toast即可
    override fun onError(message: String?) {
        refreshLayout.isRefreshing=false
        myToast("加载数据失败")
    }
    //默认情况下可能为空 封装后V层success与Error通过p层的数据来控制View界面
    override fun loadSuccess(response: RESPONSE?) {
        //隐藏刷新控价

        refreshLayout.isRefreshing=false
        //刷新列表 此时返回值可能直接就是个集合或者bean类
        //泛型返回值没方法  拿集合 核心就是基类不知道怎么实现就让子类实现
        adapter.updateList(getList(response))//获取显示的列表 让子类实现

    }



    override fun loadMore(response:RESPONSE?) {

        adapter.loadMore(getList(response))

    }



    //适配 调用HomeAdapter 基类又是写方法
    val adapter by lazy { getSpecialAdapter() }

//四.mvp重构适配


    //4.1传入View接口
    val presenter by lazy { getSpecialPresenter()}


    //1.初始化Layout
    override fun initView(): View? {
        return View.inflate(context, R.layout.fragment_list, null)
    }

    //2.初始化监听
    override fun initListener() {
        //2.1初始化layout的 recycler_view1
        recycler_view1.layoutManager = LinearLayoutManager(context)
        //适配 调用HomeAdapter
        recycler_view1.adapter = adapter
//        谷歌自带的刷新效果默认有点丑 初始化刷新控件
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //2.2设置刷新监听
//        可选1.内部只有一个方法 直接打开 2.和之前一样传一个匿名内部类
        refreshLayout.setOnRefreshListener {
            //加载刷新数据 重构后改为presenter
            presenter.loadDatas()
        }
        //2.3监听列表滑动 定义一个匿名内部类
        recycler_view1.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            //2.31 内含两个方法
            //滑动状态的改变 内含三种状态 监听idle状态滑动就可以了
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(newState== RecyclerView.SCROLL_STATE_IDLE)//空闲状态
                {
                    //是否最后一条显示 RecycleView只有列表新式才有最后一条这个概念
                    //判断LayoutManage
                    val layoutManager=recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val manager: LinearLayoutManager = layoutManager
                        val lastPosition=manager.findLastVisibleItemPosition()
                        if (lastPosition==adapter.itemCount-1){
                            // 最后一条已经显示了 进行下一页的地址寻找 重构后调用p层
                            //println("111111111222222222 "+(adapter.itemCount))
                            presenter.LoadMore((adapter.itemCount)-1)
                        }
                    }
                }

            }

            /*    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                }*/
        })
    }

    //3.初始化数据
    override fun initData() {
//        loadDatas()
        //加载刷新数据 重构后通过presenter加载
        presenter.loadDatas()

    }

//获取适配器adapter 返回的都是BaseListAsapter的子类
    abstract  fun getSpecialAdapter():BaseListAdapter<ITEMBEAN,ITEMVIEW>//自带getAdapter方法冲突
//获取presenter
    abstract  fun getSpecialPresenter():BaseListPresenter

    //获取列表数据集合
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>?
}
