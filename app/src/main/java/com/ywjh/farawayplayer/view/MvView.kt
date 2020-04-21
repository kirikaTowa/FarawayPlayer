package com.ywjh.farawayplayer.view

import com.ywjh.farawayplayer.model.MvAreaBean

interface MvView {
    fun onError(msg:String?)

    fun onSuccess(result: MvAreaBean)
}