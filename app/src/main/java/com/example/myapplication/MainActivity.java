package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

 public class MainActivity extends AppCompatActivity {

    NotificationManagerCompat notificationManagerCompat;

    Broadcast broadcast;

    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcast = new Broadcast();
        notificationManagerCompat = NotificationManagerCompat.from(this);
        Channel channel = new Channel(this);
        channel.createChannel();

        displayNotification1();
        displayNotification2();
    }

    private void displayNotification1() {

        Notification notification = new NotificationCompat.Builder(this, Channel.channel_2)
                .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                .setContentTitle("High")
                .setContentText("asdasdad")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);


    }

    private void displayNotification2() {
        Notification notification = new NotificationCompat.Builder(this, Channel.channel_1)
                .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                .setContentTitle("LOW")
                .setContentText("asdasd")
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();

        notificationManagerCompat.notify(2, notification);

    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcast,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcast);
    }
}
