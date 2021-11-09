package com.example.haroofquizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

public class QuizPage extends AppCompatActivity {

    String[]options;
    HashMap<String,Integer> haroof;
    Button option1,option2,option3,option4,next,finish;
    TextView haraf,question;
    int[]randomOptions,questionsAsked;
    int questionCount=0,rightIndex=0;
    boolean isAnswered=false;
    int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
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

    public void generateRandomQuestion()
    {
        Random rand =new Random();
        int random=rand.nextInt(haroof.size());
        boolean flag=true;
        while(flag)
        {

            boolean flag1=true;
            for(int i=0;i<10;i++)
            {
                if(questionsAsked[i]==random)
                {
                    flag1=false;
                }
            }
            if(flag1)
            {
                questionsAsked[questionCount]=random;
                Log.i("NOMAN",Integer.toString(random));
                flag=false;
            }
            random=rand.nextInt(haroof.size());
        }

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
            score++;
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
        haraf.setText(haroof.keySet().toArray()[questionsAsked[questionCount]].toString());
        option1.setText(options[randomOptions[0]]);
        option2.setText(options[randomOptions[1]]);
        option3.setText(options[randomOptions[2]]);
        option4.setText(options[randomOptions[3]]);
        questionCount++;
    }


    private void nextButtonClick() {
        if(isAnswered)
        {
            if(questionCount==10)
            {
                Toast.makeText(QuizPage.this,"Questions Completed", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(QuizPage.this,ResultPage.class);
                intent.putExtra("score",score);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else
            {
                resetColor();;
                enableClick();
                generateRandomOptions();
                haraf.setText(haroof.keySet().toArray()[questionsAsked[questionCount]].toString());
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
            Toast.makeText(QuizPage.this, "Please Answer First", Toast.LENGTH_SHORT).show();
        }
    }
    private void finishButtonClick() {
        if(questionCount<10)
        {
            new AlertDialog.Builder(QuizPage.this).setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle("You Have Not Answered All The Questions")
                    .setMessage("Do You Still Want To Proceed")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(QuizPage.this,ResultPage.class);
                            Log.i("NOMAN",Integer.toString(score));
                            intent.putExtra("score",score);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(QuizPage.this,"Press Yes to Exit Quiz",Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
        else
        {
            Intent intent=new Intent(QuizPage.this,ResultPage.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }
    }

    public void generateRandomOptions()
    {
        generateRandomQuestion();
        int count=0;
        rightIndex=(Integer)haroof.values().toArray()[questionsAsked[questionCount]];
        Log.i("NOMAN",Integer.toString(rightIndex));
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
        questionsAsked=new int[]{0,0,0,0,0,0,0,0,0,0};
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