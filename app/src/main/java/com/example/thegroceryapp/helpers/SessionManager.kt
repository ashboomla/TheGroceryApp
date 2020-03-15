package com.example.thegroceryapp.helpers

import android.content.Context
import android.content.SharedPreferences

class SessionManager (var mContext: Context){

    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object{
        private const val FILE_NAME ="my_file"
        private const val KEY_EMAIL ="email"
        private const val KEY_PASSWORD ="password"
        private const val KEY_NAME = "name"
    private const val KEY_IS_LOGGED_IN ="isLoggedIn"
    }

    fun register(name:String,email:String,password:String)
    {
        editor.putString(KEY_NAME,name)
        editor.putString(KEY_EMAIL,email)
        editor.putString(KEY_PASSWORD,password)
        editor.commit()

    }
    fun login(email: String, password: String) : Boolean
    {    var saveEmail = sharedPreferences.getString(KEY_EMAIL,null)
        var savePassword = sharedPreferences.getString(KEY_PASSWORD, null)
        if(email.equals(saveEmail) && password.equals(savePassword))
        {
            editor.putBoolean(KEY_IS_LOGGED_IN,true)
            editor.commit()
            return true
        }
        else{return false }
    }

    fun checkLogin():Boolean
    {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN,false)
    }
    fun logout(){
        editor.clear()
        editor.commit()

    }
}