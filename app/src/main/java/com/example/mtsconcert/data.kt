package com.example.mtsconcert

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


data class Tocken (
        val token:String,
        val message:String,
        val status :Boolean
)
{
    class Deserializer: ResponseDeserializable<Array<Tocken>> {
        override fun deserialize(content: String): Array<Tocken>? = Gson().fromJson(content, Array<Tocken>::class.java)
    }
}

data class Screen (
        val id:String,
        val color:String,
        val light:String,
        val frequency:String,
        val vibro :Boolean
){
    class Deserializer: ResponseDeserializable<Array<Screen>> {
        override fun deserialize(content: String): Array<Screen>? = Gson().fromJson(content, Array<Screen>::class.java)
    }
}

data class Message (
        val id:String,
        val from:String,
        val toAll:String,
        val comand:String,
        val msg :String
){
    class Deserializer: ResponseDeserializable<Array<Message>> {
        override fun deserialize(content: String): Array<Message>? = Gson().fromJson(content, Array<Message>::class.java)
    }
}