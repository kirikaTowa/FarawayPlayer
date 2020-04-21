package com.ywjh.farawayplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.MvPagerBean
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_mv.view.*
import kotlinx.android.synthetic.main.item_mv.view.bg
import kotlinx.android.synthetic.main.item_mv.view.title

class MvItemViw: RelativeLayout {
    init{
        View.inflate(context,R.layout.item_mv,this)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
//适配每个条目view
    fun setData(data: MvPagerBean.DataBean.InfoBean) {
        //歌手名称
    artist.text=data.singername
    title.text=data.videoname
    Picasso.with(context).load(data.img).into(bg)
    }
}