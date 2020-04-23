package com.ywjh.farawayplayer.utils

import android.database.Cursor

object CursorUtil {
    fun logCursor(cursor: Cursor?){
        cursor?.let {
            //将游标复位
            //count是cursor含有的条目个数
            //columnCount是每个条目含有的字段
            it.moveToPosition(-1)
                while (it.moveToNext()){
                    for (index in 0 until it.columnCount)//每个字段对应的位置[)
                    {
                        println("key=${it.getColumnName(index)} --value=${it.getString(index)}")
                    }
                }
        }
    }
}