package com.adilashraf.loginsignup

 import android.app.NotificationChannel
 import android.app.NotificationManager
 import android.app.PendingIntent
 import android.content.Intent
 import android.graphics.Bitmap
 import android.graphics.drawable.BitmapDrawable
 import android.os.Build
 import android.os.Bundle
  import android.widget.Button
 import androidx.appcompat.app.AppCompatActivity
 import androidx.core.app.NotificationCompat
 import androidx.core.content.res.ResourcesCompat


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var notify: Button
    private val CHANNEL_ID: String  = "200"
    private val MESSAGE_ID: Int  = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notify = findViewById(R.id.notify)


        notify.setOnClickListener{
            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.logo, null)
            val bitmapDrawable = drawable as BitmapDrawable?
            assert(bitmapDrawable != null)
            val largetIcon: Bitmap? = bitmapDrawable!!.bitmap

           val notificationManager:NotificationManager =  getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            val i = Intent(this, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val pendingIntent =
                PendingIntent.getActivity(this, 10, i, PendingIntent.FLAG_UPDATE_CURRENT)

            val builder = NotificationCompat.Builder(this@MainActivity)
            builder.setLargeIcon(largetIcon)
            builder.setSmallIcon(R.drawable.logo)
            builder.setContentText("Hey there! \n" +
                    "Welcome to Mouna Get ready for some amazing   ")
            builder.setAutoCancel(true)
            builder.setSubText("Welcome")
             builder.setContentIntent(pendingIntent)
            builder.setChannelId(CHANNEL_ID)
            val notification = builder.build()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        CHANNEL_ID,
                        "New Message",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }
            notificationManager.notify(MESSAGE_ID, notification)

    }


    }
}