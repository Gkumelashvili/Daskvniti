package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Answers extends AppCompatActivity {

    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        textView = findViewById(R.id.answertext);

        String answertxt = getIntent().getStringExtra("answer");
        textView.setText(answertxt);
    }
}