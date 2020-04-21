package com.ywjh.farawayplayer.adapter

import android.content.Context
import com.ywjh.farawayplayer.base.BaseListAdapter
import com.ywjh.farawayplayer.base.BaseListPresenter
import com.ywjh.farawayplayer.model.MvPagerBean
import com.ywjh.farawayplayer.widget.MvItemViw

class MvListAdapter:BaseListAdapter<MvPagerBean.DataBean.InfoBean,MvItemViw>() {
    override fun refreshItemView(itemView: MvItemViw, data: MvPagerBean.DataBean.InfoBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): MvItemViw {
        return MvItemViw(context)
    }

}