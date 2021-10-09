package com.tiooooo.gmtrading.util

import com.google.android.material.textfield.TextInputEditText
import com.tiooooo.gmtrading.model.Items
import java.util.*


object Helper {
    fun TextInputEditText.getPlainText() = this.text.toString().trim()

    fun getGreetingMessage():String{
        val c = Calendar.getInstance()

        return when (c.get(Calendar.HOUR_OF_DAY)) {
            in 5..12 -> "Hello, Good Morning"
            in 12..17 -> "Hello, Good Afternoon"
            in 18 downTo 5 -> "Hello, Good Evening"
            else -> "Hello"
        }
    }

    fun Float.getValue(): String {
        val value = this.toString().split(".")
        val back = value.last().toInt()
        return if (back == 0) this.toInt().toString()
        else this.toString()
    }

}