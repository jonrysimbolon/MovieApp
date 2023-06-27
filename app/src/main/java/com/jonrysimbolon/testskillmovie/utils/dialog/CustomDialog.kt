package com.jonrysimbolon.testskillmovie.utils.dialog

import android.content.Context

interface CustomDialog {
    fun init(context: Context)
    fun show(show: Boolean)
}