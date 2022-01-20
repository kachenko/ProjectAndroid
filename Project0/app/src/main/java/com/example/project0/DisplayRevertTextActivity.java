package com.example.project0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class DisplayRevertTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_revert_text);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESS);
        TextView textView = findViewById(R.id.resultText);
        textView.setText(message);

        createNotificationChannel();
        makeNotification("onCreate", 1);

    }

    @Override
    protected void onDestroy() {
        makeNotification("onDestroy", 2);
        super.onDestroy();
    }

    String getDateTime(){
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return String.format(Locale.getDefault(), "%04d-%02d-%02d %02d:%02d:%02d:%03d",
                year, month, day, hours, minutes, seconds,currentTimeMillis%1000);
    }

    NotificationManager notifyManager;
    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("channel_ID", getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getLocalClassName());
            notifyManager.createNotificationChannel(channel);
        }
    }

    protected void makeNotification(String methodName, int messID)
    {
            String stringTime = getDateTime();
            String stringClassName = getClass().getSimpleName();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(stringClassName)
                    .setContentText(methodName + " " + stringTime);
            Notification notify = builder.build();
            notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notifyManager.notify(++messID, notify);
    }

    public void goBack(View view) {
        this.finish();
    }
}
















