package com.example.a201911_kotlinfinaltest

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a201911_kotlinfinaltest.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvent()
        setValues()
    }

    override fun setupEvent() {
        savedIdCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            ContextUtil.setSavedIdChecked(mContext,isChecked)
        }

    }

    override fun setValues() {
        savedIdCheckBox.isChecked = ContextUtil.getSavedIdChecked(mContext)


    }

}
