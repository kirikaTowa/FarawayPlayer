package com.ywjh.farawayplayer.widget


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.squareup.picasso.Picasso

import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.YueDanBean
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_home.view.bg
import kotlinx.android.synthetic.main.item_yuedan.view.*


class YueDanItemView : RelativeLayout{


    //复写三个次级构造方法
    //代码new相关
    constructor(context: Context?) : super(context)
    //清单文件相关
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    //主题相关
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)
/*初始化方法 调用主，次构造方法都会执行
下面阶段的inflate方法传入一个xml文件进行调用
* */

    init {
        //this 当前的xml文件解析为view传过去 填null则应写addview
        View.inflate(context,R.layout.item_yuedan,this)
    }
    /*刷新条目view数据*/
    fun setDate(data: YueDanBean.ResultBean.PlaylistsBean) {
        //歌单名称

       author_title .setText(data.name)
        //创建者
       author_name.setText(data.creator?.nickname)
        //歌曲个数
       count.setText(data.trackCount.toString())
        //*光调用获取的数据图片可能不能全覆盖src 应该找到显示控件 然后是培育新 item_home ImageView控件 id 为bg*//*
        Picasso.with(context).load(R.drawable.mikutop).transform(CropCircleTransformation()).into(author_image)
        Picasso.with(context).load(data.coverImgUrl).into(bg)
    }



}