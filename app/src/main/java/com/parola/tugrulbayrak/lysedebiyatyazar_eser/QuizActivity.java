package com.parola.tugrulbayrak.lysedebiyatyazar_eser;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    TextView ScoreView;
    TextView FailView;
    TextView QuestionView;
    TextView numberQuestionText;
    Button choice1;
    Button choice2;
    Button choice3;
    Button nextButton;
    Button homeButton;
    Button volumeButton;
    boolean next=true;
    boolean volume=true;
    MediaPlayer mpFail;
    MediaPlayer mpWin;
    private QuizLibrary mQuestionLibrary = new QuizLibrary();
    String answer;
    int questionNumber=0;
    int score=0;
    int fail=0;
    int numberOfQuestion=0;
    InterstitialAd myInterstitialAd;
    AdRequest adRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        updateQuestion();


        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (choice1.getText() == answer){

                   if(next==true) {
                       score++;
                       numberOfQuestion++;
                       numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                       updateDogru();
                       //   Toast.makeText(QuizActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                       mpWin.start();
                       choice1.setBackgroundColor(Color.parseColor("#51A328"));
                       next=false;
                   }


                }
                else {

                   if(next ==true) {
                     //  Toast.makeText(QuizActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                       fail++;
                       numberOfQuestion++;
                       numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                       updateYanlis();
                       choice1.setBackgroundColor(Color.parseColor("#CC0000"));
                       mpFail.start();

                       if(choice2.getText() == answer) {
                           choice2.setBackgroundColor(Color.parseColor("#51A328"));
                           choice3.setBackgroundColor(Color.parseColor("#CC0000"));
                       }
                       else if(choice3.getText() == answer){
                           choice3.setBackgroundColor(Color.parseColor("#51A328"));
                           choice2.setBackgroundColor(Color.parseColor("#CC0000"));
                       }
                       next=false;
                   }


                }

            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (choice2.getText() == answer){
                    if(next==true) {
                        score++;
                        numberOfQuestion++;
                        numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                        updateDogru();
                        // Toast.makeText(QuizActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                        choice2.setBackgroundColor(Color.parseColor("#51A328"));
                        mpWin.start();
                        next=false;
                    }

                }
                else {
                    if(next==true) {
                      //  Toast.makeText(QuizActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                        fail++;
                        numberOfQuestion++;
                        numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                        mpFail.start();
                        updateYanlis();
                        choice2.setBackgroundColor(Color.parseColor("#CC0000"));
                        if(choice1.getText() == answer) {
                            choice1.setBackgroundColor(Color.parseColor("#51A328"));
                            choice3.setBackgroundColor(Color.parseColor("#CC0000"));
                        }
                        else if(choice3.getText() == answer){
                            choice3.setBackgroundColor(Color.parseColor("#51A328"));
                            choice1.setBackgroundColor(Color.parseColor("#CC0000"));
                        }

                        next=false;
                    }
                }

            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (choice3.getText() == answer){
                    if(next==true) {
                        score++;
                        numberOfQuestion++;
                        numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                        mpWin.start();
                        updateDogru();
                        // Toast.makeText(QuizActivity.this, "Doğru", Toast.LENGTH_SHORT).show();
                        choice3.setBackgroundColor(Color.parseColor("#51A328"));
                        next=false;
                    }

                }
                else {
                    if(next==true) {
                       // Toast.makeText(QuizActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();
                        fail++;
                        numberOfQuestion++;
                        numberQuestionText.setText("Soru Sayısı: " +numberOfQuestion);
                        updateYanlis();
                        mpFail.start();
                        choice3.setBackgroundColor(Color.parseColor("#CC0000"));
                        if(choice1.getText() == answer) {
                            choice1.setBackgroundColor(Color.parseColor("#51A328"));
                            choice2.setBackgroundColor(Color.parseColor("#CC0000"));
                        }
                        else if(choice2.getText() == answer){
                            choice2.setBackgroundColor(Color.parseColor("#51A328"));
                            choice1.setBackgroundColor(Color.parseColor("#CC0000"));
                        }
                        next=false;
                    }
                }

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
                adRequest = new AdRequest.Builder() //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        //.addTestDevice("B041E6C01582277D0CAF3334DD2A2B5B")
                        .build();
                myInterstitialAd.loadAd(adRequest);
                myInterstitialAd.setAdListener(new AdListener() {

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        myInterstitialAd.show();
                    }
                });
                next=true;
            }
        });

        volumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(volume){
                    mpWin.setVolume(0,0);
                    mpFail.setVolume(0,0);
                    volume=false;
                    volumeButton.setBackgroundResource(R.drawable.off);
                }
                else {
                    mpWin.setVolume(1,1);
                    mpFail.setVolume(1,1);
                    volume=true;
                    volumeButton.setBackgroundResource(R.drawable.on);
                }


            }
        });




    }

    void init() {
        QuestionView = (TextView)findViewById(R.id.questionView);
        FailView = (TextView) findViewById(R.id.failView);
        ScoreView = (TextView) findViewById(R.id.scoreView);
        choice1 = (Button)findViewById(R.id.choice1);
        choice2 = (Button)findViewById(R.id.choice2);
        choice3 = (Button)findViewById(R.id.choice3);
        nextButton = (Button) findViewById(R.id.nextButton);
        homeButton = (Button)findViewById(R.id.backHome);
        numberQuestionText=(TextView) findViewById(R.id.numberQuestionText);
        mpFail = MediaPlayer.create(this, R.raw.fail);
        mpWin = MediaPlayer.create(this, R.raw.win);
        volumeButton = (Button)findViewById(R.id.volumeButton);
        myInterstitialAd = new InterstitialAd(this);
        myInterstitialAd.setAdUnitId("ca-app-pub-5869261082618030/5112655106");



    }

    private void updateQuestion(){
        Random rand =  new Random();
        Random rand2= new Random();
        Random rand3= new Random();
        String secim1,secim2,secim3;

        int numb= rand.nextInt(186 - 14+ 1) + 14;
        int numb2= rand2.nextInt(8-2+1)+2;
        int numb3= rand3.nextInt(3)+0;
        questionNumber =numb;
        QuestionView.setText(mQuestionLibrary.getQuestion(questionNumber));
       /*
        choice1.setText(mQuestionLibrary.getChoice1(questionNumber));
        choice2.setText(mQuestionLibrary.getChoice2(questionNumber));
        choice3.setText(mQuestionLibrary.getChoice3(questionNumber));
       */
        secim1 = mQuestionLibrary.getCorrectAnswer(questionNumber);
        secim2 = mQuestionLibrary.getCorrectAnswer(questionNumber - numb2);
        secim3 = mQuestionLibrary.getCorrectAnswer(questionNumber - numb2 - 2);

       while(secim1==secim2 || secim2==secim3||secim1==secim3) {
           numb2=rand.nextInt(8-0+1)+0;
           secim1 = mQuestionLibrary.getCorrectAnswer(questionNumber);
           secim2 = mQuestionLibrary.getCorrectAnswer(questionNumber - numb2);
           secim3 = mQuestionLibrary.getCorrectAnswer(questionNumber - numb2 - 2);
       }

        String secim[]= {secim1,secim2,secim3};

        if(numb3==2){
            choice1.setText(secim[numb3]);
            choice2.setText(secim[numb3-1]);
            choice3.setText(secim[numb3-2]);
        }
        else if(numb3==1){
            choice1.setText(secim[numb3]);
            choice2.setText(secim[numb3-1]);
            choice3.setText(secim[numb3+1]);
        }
        else {
            choice1.setText(secim[numb3]);
            choice2.setText(secim[numb3+1]);
            choice3.setText(secim[numb3+2]);
        }

        /*
        choice1.setText(secim[numb3]);
        choice2.setText(secim2);
        choice3.setText(secim3);
        */
        choice1.setBackgroundColor(Color.parseColor("#47A3FF"));
        choice2.setBackgroundColor(Color.parseColor("#47A3FF"));
        choice3.setBackgroundColor(Color.parseColor("#47A3FF"));
        answer = mQuestionLibrary.getCorrectAnswer(questionNumber);
        questionNumber++;
    }

    private void updateDogru() {
        ScoreView.setText("Doğru: " +score);
    }

    private void updateYanlis() {

        FailView.setText("Yanlış: " +fail);
    }

    public void backHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }



}
