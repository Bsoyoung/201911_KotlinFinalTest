package com.example.a201911_kotlinfinaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        setupEvent()
        setValues()

    }

    override fun setupEvent() {
    }

    override fun setValues() {
    }

}
