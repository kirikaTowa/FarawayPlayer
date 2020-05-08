package com.ywjh.farawayplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.AudioBean
import com.ywjh.farawayplayer.model.MvPagerBean
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_mv.view.*
import kotlinx.android.synthetic.main.item_mv.view.bg
import kotlinx.android.synthetic.main.item_mv.view.title

class PopListItemView: RelativeLayout {
    init {
        View.inflate(context, R.layout.item_pop, this)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //适配每个条目view
    fun setData(data: AudioBean) {
        //歌手名称
        artist.text = data.artist
        title.text = data.display_name

    }
}