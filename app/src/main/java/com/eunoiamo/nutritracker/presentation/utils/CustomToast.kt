package com.eunoiamo.nutritracker.presentation.utils

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import android.util.TypedValue

object CustomToast {

    fun show(context: Context, message: String, isSuccess: Boolean = false) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)

        val view = toast.view
        view?.setBackgroundColor(if (isSuccess) Color.GREEN else Color.RED)

        val text = view?.findViewById<TextView>(android.R.id.message)
        text?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // Set text size
        text?.setTextColor(Color.WHITE) // Set text color

        toast.setGravity(Gravity.CENTER, 0, 0)

        toast.show()
    }
}
