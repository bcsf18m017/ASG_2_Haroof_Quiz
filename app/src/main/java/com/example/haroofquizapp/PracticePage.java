package com.example.haroofquizapp;

import static android.graphics.Color.GREEN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class PracticePage extends AppCompatActivity {


    String[]options;
    HashMap<String,Integer>haroof;
    Button option1,option2,option3,option4,next,finish;
    TextView haraf,question;
    int[]randomOptions;
    int questionCount=0,rightIndex=0;
    boolean isAnswered=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_page);
        initializeComponents();
        firstQuestion();
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishButtonClick();
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButtonClick();
            }

        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    optionClick(option1);
            }

        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClick(option2);
            }

        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClick(option3);
            }

        });option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionClick(option4);
            }

        });


    }

    public void optionClick(Button btn)
    {
        isAnswered=true;
        disableClick();
        Log.i("NOM","IN");
        String str=btn.getText().toString();
        if(answerCheck(str))
        {
            btn.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else
        {
            btn.setBackgroundColor(getResources().getColor(R.color.red));
            rightButton(str).setBackgroundColor(getResources().getColor(R.color.green));

        }
    }

    public boolean answerCheck(String str)
    {
        if(str.equals(options[rightIndex]))
        {
            return true;
        }
        return false;
    }
    public Button rightButton(String str)
    {
        if(options[rightIndex].equals(option1.getText().toString()))
        {
            return option1;
        }
        if(options[rightIndex].equals(option2.getText().toString()))
        {
            return option2;
        }
        if(options[rightIndex].equals(option3.getText().toString()))
        {
            return option3;
        }
        if(options[rightIndex].equals(option4.getText().toString()))
        {
            return option4;
        }
        return option1;
    }


    public int getValueFromIndex()
    {
        for(int i=0;i<4;i++)
        {
            if(randomOptions[i]==rightIndex)
            {
                return i;
            }
        }
        return -1;
    }


    public void enableClick()
    {
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        option4.setClickable(true);
    }
    public void disableClick()
    {
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }
    public void resetColor()
    {
        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));
        option4.setBackgroundColor(getResources().getColor(R.color.white));
    }


    private void firstQuestion() {
        generateRandomOptions();
        haraf.setText(haroof.keySet().toArray()[questionCount].toString());
        option1.setText(options[randomOptions[0]]);
        option2.setText(options[randomOptions[1]]);
        option3.setText(options[randomOptions[2]]);
        option4.setText(options[randomOptions[3]]);
        questionCount++;
    }


    private void nextButtonClick() {
       if(isAnswered)
       {
           if(questionCount==haroof.size())
           {
               Toast.makeText(PracticePage.this,"Questions Completed", Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(PracticePage.this,MainActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);
           }
           else
           {
               resetColor();;
               enableClick();
               generateRandomOptions();
               haraf.setText(haroof.keySet().toArray()[questionCount].toString());
               option1.setText(options[randomOptions[0]]);
               option2.setText(options[randomOptions[1]]);
               option3.setText(options[randomOptions[2]]);
               option4.setText(options[randomOptions[3]]);
               questionCount++;
               isAnswered=false;
           }
       }
       else
       {
           Toast.makeText(PracticePage.this, "Please Answer First", Toast.LENGTH_SHORT).show();
       }
    }
    private void finishButtonClick() {
        Intent intent=new Intent(PracticePage.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void generateRandomOptions()
    {
        int count=0;
        rightIndex=(Integer)haroof.values().toArray()[questionCount];
        while(count!=3) {
            Random rand = new Random();
            int random = rand.nextInt(17);
            boolean flag = true;
            for (int i = 0; i < 4; i++) {
                if (randomOptions[i] == random || random==rightIndex) {
                    flag = false;
                }
            }
            if (flag) {
                randomOptions[count] = random;
                count++;
            }
        }

        randomOptions[3]=rightIndex;
        shuffleArray(randomOptions);
    }

    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    private static void swap(int[] a, int i, int change) {
        int helper = a[i];
        a[i] = a[change];
        a[change] = helper;
    }

    private void initializeComponents() {
        option1=(Button) findViewById(R.id.option1);
        option2=(Button) findViewById(R.id.option2);
        option3=(Button) findViewById(R.id.option3);
        option4=(Button) findViewById(R.id.option4);
        next=(Button) findViewById(R.id.next);
        finish=(Button) findViewById(R.id.finish);
        haraf=(TextView) findViewById(R.id.haraf);
        question=(TextView) findViewById(R.id.question);
        randomOptions=new int[4];
        haroof=new HashMap<>();
        haroof.put("أ",0);
        haroof.put("ع",1);
        haroof.put("ث",11);
        haroof.put("ح",1);
        haroof.put("غ",2);
        haroof.put("ق",3);
        haroof.put("ک",4);
        haroof.put("ت",10);
        haroof.put("ج",5);
        haroof.put("ی",5);
        haroof.put("ض",6);
        haroof.put("ل",7);
        haroof.put("ن",8);
        haroof.put("ر",9);
        haroof.put("د",10);
        haroof.put("و",16);
        haroof.put("ظ",11);
        haroof.put("ص",12);
        haroof.put("ط",10);
        haroof.put("ہ",0);
        haroof.put("ز",12);
        haroof.put("ش",5);
        haroof.put("س",12);
        haroof.put("ذ",11);
        haroof.put("م",13);
        haroof.put("ف",14);
        haroof.put("خ",2);
        haroof.put("ب",15);

        options=new String[]{"End of Throat","Middle of Throat","Start of the Throat","Base of Tongue which is near Uvula touching the mouth roof",
        "Portion of Tongue near its base touching the roof of mouth","Tongue touching the center of the mouth roof",
        "One side of the tongue touching the molar teeth","Rounded tip of the tongue touching the base of the frontal 8 teeth",
        "Rounded tip of the tongue touching the base of the frontal 6 teeth","Rounded tip of the tongue and some portion near it touching the base of the frontal 4 teeth",
        "Tip of the tongue touching the base of the front 2 teeth","Tip of the tongue touching the tip of the frontal 2 teeth",
        "Tip of the tongue comes between the front top and bottom teeth","While pronouncing the ending sound of  م  or ن , bring the vibration to the nose",
        "Tip of the two upper jaw teeth touches the inner part of the lower lip","Inner part of the both lips touch each other",
        "Outer part of both lips touch each other","Rounding both lips and not closing the mouth"};
    }
}