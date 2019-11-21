package com.example.a201911_kotlinfinaltest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvent()
        setValues()

    }

    override fun setupEvent() {

        lottoBtn.setOnClickListener {

            val intent = Intent(mContext,LottoActivity::class.java)
            startActivity(intent)

        }
    }

    override fun setValues() {
    }

}
