package com.ci.act.fcm

import android.util.Log
import com.ci.act.data.AppDataManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("FCM TOKEN", p0)
        AppDataManager.getMyInstance().setFCMToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
//        sendNotification(
//            applicationContext,
//            remoteMessage.notification?.title ?: "No Message",
//            remoteMessage.notification?.body ?: "Not Message",
//            MainThread::class.java
//        )
    }

//    private fun sendNotification(
//        context: Context,
//        title: String,
//        description: String,
//        navigationClass: Class<*>
//    ) {
//        Log.e("Notification","Entered")
//        val mBuilder =
//            NotificationCompat.Builder(context, getString(R.string.default_notification_channel_id))
//        val intent = Intent(this,navigationClass)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        val pendingIntent = PendingIntent.getActivity(context, 0, intent,0)
//        mBuilder.setContentIntent(pendingIntent)
//        mBuilder.setSmallIcon(R.drawable.compindia_logo)
//        mBuilder.setContentTitle(title)
//        mBuilder.setContentText(description)
//        val mNotificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val notificationChannel =
//                NotificationChannel(
//                    getString(R.string.default_notification_channel_id),
//                    "Announcement",
//                    importance
//                )
//            notificationChannel.enableLights(true)
//            notificationChannel.lightColor = Color.RED
//            mBuilder.setChannelId(getString(R.string.default_notification_channel_id))
//            mNotificationManager?.createNotificationChannel(notificationChannel)
//        }
//        val notification = mBuilder.build()
//        notification.flags = Notification.FLAG_AUTO_CANCEL
//        mNotificationManager!!.notify(1, notification)
//    }
}