package com.example.a201911_kotlinfinaltest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvent()
        setValues()
    }

    override fun setupEvent() {
    }

    override fun setValues() {

        savedIdCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                //체크박스가 체크 되는 상황
            }
            else{

            }



        }


    }

}
