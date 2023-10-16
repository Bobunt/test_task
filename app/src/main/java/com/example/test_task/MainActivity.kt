package com.example.test_task

import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.test_task.fragments.MainFragment


class MainActivity : ComponentActivity() {

    private lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"
    private val title = "Инфо."
    private val text = "Внесены изменения"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            MainFragment(onNotification = {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_dialog_info))

                } else {
                    builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_dialog_info))

                }
                notificationManager.notify(1234, builder.build())
            })
        }
    }

}