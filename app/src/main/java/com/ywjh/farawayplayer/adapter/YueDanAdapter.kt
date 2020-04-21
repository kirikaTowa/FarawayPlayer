package com.ywjh.farawayplayer.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.model.YueDanBean
import com.ywjh.farawayplayer.widget.HomeItemView
import com.ywjh.farawayplayer.widget.LoadMoreView
import com.ywjh.farawayplayer.widget.YueDanItemView

class YueDanAdapter:BaseListAdapter<YueDanBean.ResultBean.PlaylistsBean,YueDanItemView>() {
    override fun refreshItemView(itemView: YueDanItemView, data: YueDanBean.ResultBean.PlaylistsBean) {
        itemView.setDate(data)
    }

    override fun getItemView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

}