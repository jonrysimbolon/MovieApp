package com.jonrysimbolon.testskillmovie.utils.dialog

import android.app.Dialog
import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout

abstract class CustomDialog {

    var dialog: Dialog? = null
    var constraintLayout: ConstraintLayout? = null
    var reloadAction: (() -> Unit)? = null
    open fun init(context: Context) {}
    open fun show(show: Boolean = false) {}
    open fun setDescription(description: String) {}
    open fun setReloadClickListener(function: (() -> Unit)?) {}
}