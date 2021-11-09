package com.example.haroofquizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class ResultPage extends AppCompatActivity {

    ProgressBar pb;
    TextView text;
    Button btn;
    ImageView share;
    ConstraintLayout cl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        pb=(ProgressBar) findViewById(R.id.progressBar);
        text=(TextView)findViewById(R.id.progressText);
        btn=(Button)findViewById(R.id.main);
        share=(ImageView)findViewById(R.id.share);
        cl=(ConstraintLayout)findViewById(R.id.myLayout);
        Intent intent=getIntent();
        int temp=intent.getIntExtra("score",0);
        pb.setProgress(temp);
        text.setText(Integer.toString(temp)+"/10");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultPage.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

    }
    private void share() {
        Bitmap bm=getBitmap(cl);
        try{
            File file=new File(this.getExternalCacheDir(),"background.png");
            FileOutputStream fout=new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG,100,fout);
            fout.flush();
            fout.close();
            file.setReadable(true,false);
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            String subject="Quiz App";
            intent.putExtra(Intent.EXTRA_TEXT,"Woo Hoo ! Here is my score\nCome Play With Me");
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            startActivity(Intent.createChooser(intent,"Share Via"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @SuppressLint("ResourceAsColor")
    private Bitmap getBitmap(View view)
    {
        Bitmap bm=Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas can=new Canvas(bm);
        Drawable bg=view.getBackground();
        if(bg!=null)
        {
            bg.draw(can);
        }
        else
        {
            can.drawColor(R.color.blue);
        }
        view.draw(can);
        return bm;
    }
}