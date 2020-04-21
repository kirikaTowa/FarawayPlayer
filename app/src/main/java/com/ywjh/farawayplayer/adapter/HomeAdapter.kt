package com.ywjh.farawayplayer.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.model.HomeItemBeanSuccess
import com.ywjh.farawayplayer.widget.HomeItemView
import com.ywjh.farawayplayer.widget.LoadMoreView


//创建Adapter进行处理   传入<homeholder>泛型
class HomeAdapter: BaseListAdapter<HomeItemBeanSuccess.ResultBean, HomeItemView>(){
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBeanSuccess.ResultBean) {
            itemView.setDate(data)
    }

    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

}