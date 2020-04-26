package com.ywjh.farawayplayer.model

import android.database.Cursor
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

//音乐列表条目的bean类
data class AudioBean(var data:String?, var size:Long, var display_name:String, var artist: String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString()
    ) {
    }


//Parcelable N个方法 静态object可放一起
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeLong(size)
        parcel.writeString(display_name)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AudioBean> {
        override fun createFromParcel(parcel: Parcel): AudioBean {
            return AudioBean(parcel)
        }

        override fun newArray(size: Int): Array<AudioBean?> {
            return arrayOfNulls(size)
        }

        /*
       * 根据特定(游标)位置上的cursor获取bean
       * */
        fun getAudioBean(cursor:Cursor):AudioBean{
            //1.创建一个AudioBean对象，
            val audioBean=AudioBean("",0,"","")
            //2.再判断cursor是否为空
            cursor.let {
                audioBean.data=cursor.getString(cursor.getColumnIndex((MediaStore.Audio.Media.DATA)))
                audioBean.size=cursor.getLong(cursor.getColumnIndex((MediaStore.Audio.Media.SIZE)))
                audioBean.display_name=cursor.getString(cursor.getColumnIndex((MediaStore.Audio.Media.DISPLAY_NAME)))//带后缀名的
                audioBean.display_name=audioBean.display_name.substring(0,audioBean.display_name.lastIndexOf("."))//截取到 最后一个.结束如:夕日坂-初音.flac
                audioBean.artist=cursor.getString(cursor.getColumnIndex((MediaStore.Audio.Media.ARTIST)))
            }

            return audioBean
            //3.解析cursor并且设置到cursor对象中

        }
        /*
        *根据特定位置的cursor获取整个列表
        * */
        fun getAudioBeans(cursor: Cursor?): ArrayList<AudioBean> {
            //创建集合
            val list=ArrayList<AudioBean>()
            //cursor是否为空
            //不为空则解析cursor并添加到集合中
            cursor?.let {
                //游标当前在任何位置  都要访问到完整列表 可以先移动到-1位
                it.moveToPosition(-1)//指定cursor对象
                //解析cursor到集合中
                while (it.moveToNext()){
                    val audioBean = getAudioBean(it)
                    list.add(audioBean)
                }
            }

            return list
        }
    }
}