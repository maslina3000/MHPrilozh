package com.example.mtsconcert

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import android.os.CountDownTimer




class MainActivity : AppCompatActivity() {
    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            //СУДА?
        val timer = Timer()
        timer.scheduleAtFixedRate(
            object : TimerTask() {

                override fun run() {
                    // Magic here
                    Log.d("*","Строка")
                   onGetMessage()


                }
            },
            0, 2000
        ) // 1000 Millisecond = 1 second



    }

    fun login(view: View)
    {
        val intent = Intent(this, HOMEpage::class.java).apply {

            //putExtra(AlarmClock.EXTRA_MESSAGE, "")

        }

        startActivityForResult(intent, 254)
    }

    fun onGetMessage() {
        Log.d("onLogin","какой-то текст")
        //  val e_email = findViewById<EditText>(R.id.e_email)
       // val e_color = findViewById<FrameLayout>(R.id.onFrameLayout
        val tde = this

                //Пример Асинхронного кода с слушателем
                Observable.create<Message> { emitter ->
                    run {
                        try {

                            emitter.onNext(service.onGetMessage(this))
                        } catch (e: Exception) {
                            emitter.onError(e)
                        }
                    }
                }.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { result  ->
                            run {
                                println(" onGEtScreen:$result")
                                   // e_color.setBackgroundColor(Color.parse
                                Log.d("*","Ещё сообщение $result.id $result.msg")
                                if(tde.id != result.id && result.msg != "") {
                                    Toast.makeText(this, result.msg, 50000.toInt()).show()

                                    val tag = Toast.makeText(this, "$result.msg", Toast.LENGTH_SHORT)

                                    tag.show()

                                    object : CountDownTimer(9000, 1000) {

                                        override fun onTick(millisUntilFinished: Long) {
                                            tag.show()
                                        }

                                        override fun onFinish() {
                                            tag.show()
                                        }

                                    }.start()

                                    Log.d("*","И снова Ещё сообщение")
                                    tde.id = result.id


                                }



                            }

                        },
                        { error ->
                            run {
                                println("onGEtScreen:$error")
                                Toast.makeText(this, "Ошибка сервера", Toast.LENGTH_SHORT).show()
                            }

                        },
                        {}
                    )
    }
    fun BackButton(view: View?)
    {
        finish();
    }
}
