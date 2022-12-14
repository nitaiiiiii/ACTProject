package com.ci.act.extensions

import android.content.SharedPreferences


inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = this.edit()
    editor.func()
    editor.apply()
}