package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "My Channel";
    private static int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the drawable variable
        Drawable drawable = getResources().getDrawable(R.drawable.abc); // Replace with your actual drawable resource

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap largeIcon = bitmapDrawable.getBitmap();
            Notification notification = null;

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (nm != null) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.abc) // Ensure this drawable exists in your resources
                            .setContentText("New Message")
                            .setSubText("New Message from Shubham")
                            .setChannelId(CHANNEL_ID)
                            .build();
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH);
                    nm.createNotificationChannel(channel);
                } else {
                    notification = new Notification.Builder(this)
                            .setLargeIcon(largeIcon)
                            .setSmallIcon(R.drawable.abc) // Ensure this drawable exists in your resources
                            .setContentText("New Message")
                            .setSubText("New Message from Shubham")
                            .build();
                }

                // Only notify if the notification is not null
                if (notification != null) {
                    nm.notify(NOTIFICATION_ID, notification);
                }
            } else {
                // Log or handle the error if NotificationManager is null
                System.out.println("NotificationManager is null");
            }
        } else {
            // Handle the case where the drawable is not a BitmapDrawable
            System.out.println("Drawable is not a BitmapDrawable");
        }
    }
}
