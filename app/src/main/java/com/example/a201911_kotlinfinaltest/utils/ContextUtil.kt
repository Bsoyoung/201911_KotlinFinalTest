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



    }

}