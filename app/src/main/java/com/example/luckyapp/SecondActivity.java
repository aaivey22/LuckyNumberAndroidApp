package com.example.luckyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    TextView welcomeTxt, luckyNumTxt;
    Button share_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTxt = findViewById(R.id.textView2);
        luckyNumTxt = findViewById(R.id.lucky_num_text);
        share_btn = findViewById(R.id.share_btn);

        // Receiving the data from Main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");

        // Generating Random Numbers
        int randomNum = generateRandomNumber();
        luckyNumTxt.setText(""+randomNum);

        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(userName, randomNum);
            }
        });
    }

    public int generateRandomNumber() {
        Random random = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = random.nextInt(upper_limit);
        return randomNumberGenerated;
    }

    public void shareData(String userName, int randomNum) {
        // Implicit Intent
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT, userName + " are super lucky!");
        i.putExtra(Intent.EXTRA_TEXT, userName+"'s lucky number is: "+randomNum);

        startActivity(Intent.createChooser(i, "Choose a Platform"));
    }
}