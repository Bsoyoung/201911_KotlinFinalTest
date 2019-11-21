package com.example.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LottoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lotto)
        setupEvent()
        setValues()
    }

    override fun setupEvent() {
    }

    override fun setValues() {
    }

}
