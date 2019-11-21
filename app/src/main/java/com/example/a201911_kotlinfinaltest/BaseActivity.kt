package com.example.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var mContext = this
    abstract fun setupEvent()
    abstract fun setValues()
}