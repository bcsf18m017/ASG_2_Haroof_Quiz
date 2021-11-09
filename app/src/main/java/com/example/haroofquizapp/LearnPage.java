package com.example.haroofquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnPage extends AppCompatActivity {



    Button halqiyah,lahatiyan,shajariyan,tarfiyan,niteeyah,lisaveyah,ghunna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_page);
        initializeComponents();
        halqiyah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,HalqiyahContent.class);
                startActivity(intent);
            }
        });
        lahatiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,LahatiyahContent.class);
                startActivity(intent);
            }
        });
        tarfiyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,TarfiyahContent.class);
                startActivity(intent);
            }
        });
        niteeyah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,Niteeyah.class);
                startActivity(intent);
            }
        });
        shajariyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,sharjariyahContent.class);
                startActivity(intent);
            }
        });
        lisaveyah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,LisaveyahContent.class);
                startActivity(intent);
            }
        });
        ghunna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LearnPage.this,GhunnaContent.class);
                startActivity(intent);
            }
        });

    }

    private void initializeComponents() {
        halqiyah=(Button) findViewById(R.id.halqiyah);
        lahatiyan=(Button) findViewById(R.id.lahatiyah);
        shajariyan=(Button) findViewById(R.id.shajariyahHaafiyah);
        tarfiyan=(Button) findViewById(R.id.tarfiyah);
        niteeyah=(Button) findViewById(R.id.nitEeyah);
        lisaveyah=(Button) findViewById(R.id.lisaveyah);
        ghunna=(Button) findViewById(R.id.ghunna);
    }
}