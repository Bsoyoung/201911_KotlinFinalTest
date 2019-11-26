package com.example.a201911_kotlinfinaltest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a201911_kotlinfinaltest.utils.ContextUtil
import com.example.a201911_kotlinfinaltest.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

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

        loginBtn.setOnClickListener {

            val userId = idEdt.text.toString()
            val userPw = pwEdt.text.toString()

            ServerUtil.postRequestLogin(mContext,userId,userPw,object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    val code = json.getInt("code")

                    if(code == 200){

                        ContextUtil.setId(mContext,idEdt.text.toString())
                        ContextUtil.setPw(mContext,pwEdt.text.toString())

                    }

                }


            })


        }


    }

    override fun setValues() {
        savedIdCheckBox.isChecked = ContextUtil.getSavedIdChecked(mContext)

        if(savedIdCheckBox.isChecked){
            idEdt.setText(ContextUtil.getId(mContext))
            pwEdt.setText(ContextUtil.getPw(mContext))
        }

    }

}
