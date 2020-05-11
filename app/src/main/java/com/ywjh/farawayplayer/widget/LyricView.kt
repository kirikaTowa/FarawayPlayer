package com.ywjh.farawayplayer.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.ywjh.farawayplayer.R
import com.ywjh.farawayplayer.model.LyricBean
import java.time.OffsetTime

//自定义歌词view
class LyricView: View {
    val paint by lazy{Paint(Paint.ANTI_ALIAS_FLAG)}//惰性加载创建抗锯齿画笔
    //多行歌词通过集合
    val list by lazy { ArrayList<LyricBean>() }
    var viewW:Int=0
    var viewH:Int=0
    var centerLine=10
    var bigSize=0f
    var smallSize=0f
    var white=0
    var green=0
    var lineHeight=0
    var duration=0
    var progress=0
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        bigSize=resources.getDimension(R.dimen.bigSize)
        smallSize=resources.getDimension(R.dimen.smallSize)
        white=resources.getColor(R.color.white)
        green=resources.getColor(R.color.green)
        //定义行高
        lineHeight = resources.getDimensionPixelOffset(R.dimen.lineHeight)

        //画笔
        paint.textAlign=Paint.Align.CENTER//在x方向确定位置是以中间确定位置
        //循环添加歌词的bean
        for(i in 0 until 20){
            list.add(LyricBean(2000*i,"正在播放第$i 行歌曲"))
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //drawSingleLine(canvas)
        drawMutieLine(canvas)
    }

    private fun drawMutieLine(canvas: Canvas?) {
        //求居中行偏移量y
        //1.行可用时间
        var lineTime=0
        //最后一行居中
        if (centerLine==list.size-1){
        //可用时间=duration-最后一行开始时间
            lineTime=duration-list.get(centerLine).startTime
        }else{
            //其他行居中
            val centerS =list.get(centerLine).startTime
            val nextS=list.get(centerLine+1).startTime
            lineTime=nextS-centerS
        }
        //2.求偏移时间=progress-居中
        val offsetTime=progress-list.get(centerLine).startTime
        //3.偏移百分比=偏移时间/行可用时间
        val offsetPercent:Float=offsetTime/(lineTime.toFloat())
        //4.偏移y
        val offsetY=offsetPercent*lineHeight

        val centerText=list.get(centerLine).content//拿到居中行文本
        val bounds = Rect()//传递指针，拿到数据
        paint.getTextBounds(centerText, 0, centerText.length, bounds)
        //求居中行y值
        val textH = bounds.height()
        val centerY=viewH/2+textH/2-offsetY
        for ((index,value)in list.withIndex()){
            if(index==centerLine){
                paint.color=green
                paint.textSize=bigSize
            }else{
                //其他行
                paint.color=white
                paint.textSize=smallSize
            }
            val curX=viewW/2
            val curY=centerY+(index-centerLine)*lineHeight
            //超出边界处理
            //处理上下边界歌词
            if(curY<0 ){
                continue
            }
            if(curY>viewH+lineHeight){
                break
            }


            val curText=list.get(index).content
            canvas?.drawText(curText,curX.toFloat(),curY.toFloat(),paint)
        }
    }

    private fun drawSingleLine(canvas: Canvas?) {
        //初始化paint的颜色和大小
        paint.textSize = bigSize
        paint.color = green

        val text = "MikuMikuMi"
        //求出文本的宽度和高度
        val bounds = Rect()//传递指针，拿到数据
        paint.getTextBounds(text, 0, text.length, bounds)
        val textW = bounds.width()
        val textH = bounds.height()
        val x = viewW / 2 - textW / 2
        val y = viewH / 2 - textH / 2
        //x=viewW/2-textW/2
        //y=viewH/2+textH/2
        canvas?.drawText(text, viewW / 2.toFloat(), y.toFloat(), paint)
    }

    //可以拿到View的宽和高 这个方法会在第二步 布局调用 ，layout布局完后执行
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewW=w
        viewH=h
    }

    fun updateProgress(progress:Int){
        this.progress=progress
        //获取居中行行号
        //进度大于最后一行的开始时间
        if(progress>=list.get(list.size-1).startTime){
            //最后一行居中
            centerLine=list.size-1
        }else{
            //其他行居中的话 循环遍历集合  找到居中行
            for(index in 0 until list.size-1){//最大到-2 看下until范围左闭有开
                //progress>=当前行开始时间&&<下一行开始时间
                val curStartTime=list.get(index).startTime
                val nextStartTime=list.get(index+1).startTime
                if (progress>=curStartTime && progress<nextStartTime){
                    centerLine=index
                    break
                }
            }
        }
        //找到居中行后，绘制多行歌词 重新调用ondraw方法
        /*
        * invalidate()执行ondraw，宽度变化不管   只会改变内容，条目控件的绘制
        *postInvalidate() ondraw方法，可在子线程刷新
        * requestLayout //view布局(宽度和高度)参数个改变时刷新 重新绘制
        * */
        invalidate()

    }
    //设置当前总时长

    fun setSongDuration(duration:Int){
        this.duration=duration
    }
}