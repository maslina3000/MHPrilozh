package com.example.mtsconcert

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View)
    {
        val intent = Intent(this, HOMEpage::class.java).apply {

            //putExtra(AlarmClock.EXTRA_MESSAGE, "")

        }

        startActivityForResult(intent, 254)
    }
}
