package com.example.a201911_kotlinfinaltest.utils

import android.content.Context

class ContextUtil {

    companion object{

        val prefName = "KotlinFinalTestPreference"
        val USER_ID = "USER_ID"
        val USER_PW = "USER_PW"
        val SAVE_ID_CHECKED = "SAVE_ID_CHECKED"

        val USER_TOKEN = "USER_TOKEN"
//
//        fun setUserId(context:Context,userId:String){
//
//            var pref = context.getSharedPreferences(prefName,Con)
//
//        }

        fun setSavedIdChecked(context:Context,isChecked:Boolean){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putBoolean(SAVE_ID_CHECKED,isChecked).apply()
        }

        fun getSavedIdChecked(context:Context):Boolean{
            val pref= context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getBoolean(SAVE_ID_CHECKED,false);
        }


        fun setId(context:Context,user_id:String){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(USER_ID,"").apply()
        }

        fun getId(context:Context):String{
            val pref= context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getString(USER_ID,"")!!;
        }


        fun setPw(context:Context,user_id:String){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(USER_PW,"").apply()
        }

        fun getPw(context:Context):String{
            val pref= context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getString(USER_PW,"")!!;
        }

        fun setUserToken(context:Context, userToken:String) {

            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, userToken).apply()
        }

        fun getUserToken(context: Context) : String {

            var pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!

        }

    }

}