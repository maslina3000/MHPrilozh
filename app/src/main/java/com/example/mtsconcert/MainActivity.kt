package com.example.mtsconcert

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
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
import android.support.v4.app.NotificationCompat


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

        val intent = Intent(this, HOMEpage::class.java).apply {

            //putExtra(AlarmClock.EXTRA_MESSAGE, "")

        }

        startActivityForResult(intent, 254)

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
                                //  Log.d("*","Ещё сообщение $result.id $result.msg")
                                if(tde.id != result.id && result.msg != "") {
                                    /*Toast.makeText(this, result.msg, 50000.toInt()).show()

                                    val tag = Toast.makeText(this, "$result.msg", Toast.LENGTH_SHORT)

                                    tag.show()

                                    object : CountDownTimer(9000, 1000) {

                                        override fun onTick(millisUntilFinished: Long) {
                                            tag.show()
                                        }

                                        override fun onFinish() {
                                            tag.show()
                                        }

//                                    }.start()*/
                                    tde.id = result.id
                                    Log.d("*","И снова Ещё сообщение${result.id}")
                                    createNotification(result.msg);


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

    private var notifManager: NotificationManager? = null
    fun createNotification(aMessage: String) {
        val NOTIFY_ID = 1 // ID of notification
        val id = 133;// default_channel_id
        val title = "MTSCONCERT"// Default Channel
        val intent: Intent
        val pendingIntent: PendingIntent
        val builder: NotificationCompat.Builder
        if (notifManager == null) {
            notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            var mChannel: NotificationChannel? = notifManager!!.getNotificationChannel(id.toString())
            if (mChannel == null) {
                mChannel = NotificationChannel(id.toString(), title, importance)
                mChannel.enableVibration(true)
                mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                notifManager!!.createNotificationChannel(mChannel)
            }
            builder = NotificationCompat.Builder(this, id.toString())
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            builder.setContentTitle(aMessage) // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400))
        } else {
            builder = NotificationCompat.Builder(this)
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            builder.setContentTitle(aMessage) // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(this.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)).priority = Notification.PRIORITY_HIGH
        }
        val notification = builder.build()
        notifManager!!.notify(NOTIFY_ID, notification)
//        createNotification("Data is processed")
    }

    fun BackButton(view: View?)
    {
        finish();
    }
}