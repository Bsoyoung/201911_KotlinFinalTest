package com.example.a201911_kotlinfinaltest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a201911_kotlinfinaltest.datas.UserData
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

                    Log.d("결과",code.toString())
                    if(code == 200){

                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val token = data.getString("token")

                        ContextUtil.setUserToken(mContext,token)

                        val userData = UserData.getUserDataFromJsonObject(user)

                        val intent = Intent(mContext,BoardActivity::class.java)
                        startActivity(intent)
                    }
                    else if (code==400){
                        runOnUiThread{
                            Toast.makeText(mContext,"로그인에 실패했습니다",Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        //500 => INTERNAL SERVER ERROR  => 서버 내부 에러 => 서버개발자의 실수
                        //404 NOT FOUND => 업는 주소에 요청
                        //403 => 권한이 없는 요청
                        runOnUiThread {
                            Toast.makeText(mContext,"서버에 문제가 있습니다",Toast.LENGTH_SHORT).show()
                        }
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
