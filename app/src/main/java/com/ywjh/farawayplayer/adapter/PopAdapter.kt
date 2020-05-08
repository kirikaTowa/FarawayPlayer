package com.ywjh.farawayplayer.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ywjh.farawayplayer.base.BaseActivity
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.widget.PlayListPopWindow
import com.ywjh.farawayplayer.widget.PopListItemView

//播放列表PopWindowadapter
class PopAdapter(var list:List<AudioBean>):BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView:View?=null
        if (convertView==null){//kotlin形参不能修改
            itemView= PopListItemView(parent?.context)
        }else{
            itemView=convertView as PopListItemView
        }
        itemView.setData(list.get(position))
        return itemView
    }

    override fun getItem(position: Int): Any {
        return  list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}