package com.example.reqresapi;

import android.app.NotificationManager;
import android.app.Service;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        getFirebaseMessage(message.getNotification().getTitle(), message.getNotification().getBody());
    }

    public void getFirebaseMessage(String title, String msg){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myFirebase")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);


        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(101, builder.build());
    }
    }
