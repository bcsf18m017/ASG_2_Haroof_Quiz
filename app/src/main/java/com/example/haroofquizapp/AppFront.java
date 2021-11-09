package com.example.haroofquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppFront extends AppCompatActivity {

    Button learn,pratice,quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_front);
        initializeComponents();
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppFront.this,LearnPage.class);
                startActivity(intent);
            }
        });
        pratice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppFront.this,PracticePage.class);
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AppFront.this,QuizPage.class);
                startActivity(intent);
            }
        });
    }

    private void initializeComponents() {
        learn=(Button) findViewById(R.id.learn);
        pratice=(Button) findViewById(R.id.practice);
        quiz=(Button) findViewById(R.id.quiz);
    }
}