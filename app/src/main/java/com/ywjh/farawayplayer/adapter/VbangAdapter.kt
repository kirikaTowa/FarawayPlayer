package com.ywjh.farawayplayer.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.widget.VbangItemView

//v榜界面列表适配器 我们发现CursorAdapter是继承BaseAdapter(有四个未实现方法)CurosrAdapter已主动实现
class VbangAdapter(context: Context?, c: Cursor?) : CursorAdapter(context, c) {//实现两参的构造方法 无参会标错
    /*我们发现他是没有VeiwHolder的 要实现还得创建ViewHolder进行Holder绑定
    * 创建条目View
    * */
    //报错了 getitemid是cursoradapter中的一个方法   获取long类型值_id字段 ，而我们偏偏又没查出这个字段
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return VbangItemView(context)
    }
    /*
    * view+data数据绑定
    * */
    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        //通过控件id直接操控 不需要控件持有 内部已经实现拿到View了
        val itemView=view as VbangItemView
        //拿到curser后每次都要让指针移到下一条  这里可以用bean类
        val itemBean= cursor?.let { AudioBean.getAudioBean(it) }//好用
        //view+data
        if (itemBean != null) {

            itemView.setData(itemBean)
        }



    }

}