package com.ywjh.farawayplayer.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.AudioBean
import kotlinx.android.synthetic.main.item_vbang.view.*
import java.util.*

class VbangItemView: RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init{
        View.inflate(context,R.layout.item_vbang,this)
    }
    /*
    * View和数据的绑定
    *
    * */
    fun setData(itemBean: AudioBean){
        //歌曲名

        titlev.text=itemBean.display_name
        artistv.text=itemBean.artist//text下的Formatter 安卓api 格式化M，G。。。
        sizev.text=Formatter.formatFileSize(context,itemBean.size)
    }

}