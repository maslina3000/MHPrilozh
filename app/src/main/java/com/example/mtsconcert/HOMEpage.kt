package com.example.mtsconcert

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HOMEpage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
    }
    fun onGetScreen(view: View?) {
// Log.d("onLogin","onLogin")
      //  val e_email = findViewById<EditText>(R.id.e_email)


// Пример Асинхронного кода с слушателем
        Observable.create<Any> { emitter ->
            run {
                try {

                    emitter.onNext(service.onGetScreen(this))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }
        }.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    run {
                        println(" onGEtScreen:$result")


                    }

                },
                { error ->
                    run {
                        println(" onGEtScreen:$error")
                        Toast.makeText(this, "Ошибка сервера", Toast.LENGTH_SHORT).show()
                    }

                },
                {}
            )

    }

    fun ShowZazhigalka(view: View)
    {
        val intent = Intent(this, zazhigalka::class.java).apply {

            //putExtra(AlarmClock.EXTRA_MESSAGE, "")

        }

        startActivityForResult(intent, 250)
    }
}
