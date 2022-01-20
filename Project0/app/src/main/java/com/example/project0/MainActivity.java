package com.example.project0;

import static android.content.Intent.ACTION_SEND;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESS = "com.example.project0.MESSAGE";

    Chronometer chrono;
    double default_value = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chrono = (Chronometer) findViewById(R.id.chronometer);
        chrono.start();

        Intent intentResult = getIntent();
        TextView resultText = findViewById(R.id.resultTextView);
        resultText.setText(intentResult.getStringExtra("result"));
    }

    public void revertText(View view) {
        Intent intent = new Intent(this, DisplayRevertTextActivity.class);
        EditText editText = (EditText) findViewById(R.id.enterTextToRevert);
        String message = editText.getText().toString();
        StringBuffer buff = new StringBuffer(message);
        intent.putExtra(EXTRA_MESS, buff.reverse().toString());
        startActivity(intent);
    }

    public void onClick(View view) {
        EditText firstA = (EditText) findViewById(R.id.firstAText);
        EditText secondA = (EditText) findViewById(R.id.secondAText);

        String firstAText = firstA.getText().toString();
        String secondAText = secondA.getText().toString();

        if(firstAText.length() == 0 || secondAText.length() == 0) {
            Toast.makeText(MainActivity.this, "Bad arguments!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intentSend = new Intent(android.content.Intent.ACTION_SEND);
        intentSend.putExtra("firstA", firstAText);
        intentSend.putExtra("secondA", secondAText);
        intentSend.setType("text/*");
        startActivityForResult(intentSend, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                String operation = data.getStringExtra("operation");
                TextView resultText = findViewById(R.id.resultTextView);
                TextView operationText = findViewById(R.id.operationTextView);

                operationText.setText(operation);
                resultText.setText("" + result);
            }
        }
    }












}