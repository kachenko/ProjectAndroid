package com.example.projectadd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            String a1 = b.getString("firstA");
            String a2 = b.getString("secondA");

            Toast.makeText(MainActivity.this, "Adding " + a1 + " to " + a2, Toast.LENGTH_SHORT).show();

            Integer result = Integer.parseInt(a1) + Integer.parseInt(a2);

            // =========

            Intent intentResult = new Intent();
            intentResult.putExtra("result", result);
            intentResult.putExtra("operation", "addition");
            setResult(Activity.RESULT_OK, intentResult);
            finish();

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Bad arguments", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
}