package com.example.a201911_kotlinfinaltest.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object{

        var Base_URL =  "http://192.168.0.26:5000"

        fun postRequestLogin(context:Context,userId:String,userPw:String,handler: JsonResponseHandler){

            var client = OkHttpClient()
            var url = "${Base_URL}/auth"

            var formBody = FormBody.Builder().add("login_id",userId).add("password",userPw).build()

            var request = Request.Builder().url(url).post(formBody).build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    var body = response.body()!!.string()
                    var json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })

        }
        fun getRequestMyInfo(context:Context,handler: JsonResponseHandler) {
            var client = OkHttpClient()

            var urlBuilder = HttpUrl.parse("${Base_URL}/my_info")!!.newBuilder()
//            GET 방식의 파라미터를 첨부하는 방법
            urlBuilder.addEncodedQueryParameter("device_token", "test")

//            url 최종 확정
            val requestUrl = urlBuilder.build().toString()
            Log.d("요청URL", requestUrl)

            val request = Request.Builder()
                .url(requestUrl)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("서버통신에러", e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {

                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)

                }


            })
            }

        }

}