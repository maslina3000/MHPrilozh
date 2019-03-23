package com.example.mtsconcert

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

object service {
    fun onGetScreen(i_context: Context):String{
        val URL="http://178.249.243.15:3313/getScreen"
        FuelManager.instance.baseParams = listOf("user_email" to "email","user_password" to ("password"))
        //println("Password md5"+sha256(password))
        val (request, response, result) = URL.httpPost().responseObject(Screen.Deserializer())

        Log.d("onLogin","$result")

        when (result) {

            is Result.Success -> {
                val (screen, err) = result

                screen?.forEach { screen ->
                    Log.d("onLogin", "token=$screen token.status=.status")
                    return (screen.color);
                }
            }
            is Result.Failure -> {
                Log.d("onLogin", "ERROR request_error")
                return "#FFFFFF"
            }
        }
        return "#000000"
    }

    fun onGetMessage(i_context: Context): Message? {
        val URL="http://178.249.243.15:3313/getMsg"
        FuelManager.instance.baseParams = listOf("user_email" to "email","user_password" to ("password"))
        //println("Password md5"+sha256(password))
        val (request, response, result) = URL.httpPost().responseObject(Message.Deserializer())

        Log.d("onLogin","$result")

        when (result) {

            is Result.Success -> {
                val (msg, err) = result

                msg?.forEach { msg ->
                    Log.d("onLogin", "token=$msg token.status=.status")
                    return (msg);
                }
            }
            is Result.Failure -> {
                Log.d("onLogin", "ERROR request_error")
                return null
            }
        }
        return null
    }
}
