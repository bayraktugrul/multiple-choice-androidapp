package com.parola.tugrulbayrak.lysedebiyatyazar_eser;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TableActivity extends AppCompatActivity {
    TextView yazarEserTable;
    Button backHome;
    private QuizLibrary mQuestionLibrary;
    boolean color=true;
    AdRequest adRequest;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        init();
        yazarEserTable.setText("\n----Aşağı Kaydırın!----\n");
        yazarEserTable.append("-----------------------------------\n");
        yazarEserTable.append(mQuestionLibrary.questions[0] +"\n");
        yazarEserTable.append("Yazar: " + mQuestionLibrary.mCorrectAnswers[0]);
        yazarEserTable.append("\n-----------------------------------\n");

       for(int i=1;i<184;i++){
           //yazarEserTable.append(mQuestionLibrary.getQuestion(i) + "\n Yazar:" + mQuestionLibrary.getCorrectAnswer(i) + " \n");
            yazarEserTable.append(mQuestionLibrary.questions[i]);
            yazarEserTable.append("\n");
            yazarEserTable.append("Yazar:" +mQuestionLibrary.mCorrectAnswers[i]);
           yazarEserTable.append("\n-----------------------------------\n");
        }
    }

    public void init() {
        yazarEserTable = (TextView)findViewById(R.id.tableTextView);
        mQuestionLibrary = new QuizLibrary();
        backHome =(Button)findViewById(R.id.backHomeButton);
        adView = (AdView) findViewById(R.id.reklam);
        adRequest = new AdRequest.Builder() //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // .addTestDevice("B041E6C01582277D0CAF3334DD2A2B5B")
                .build();
        adView.loadAd(adRequest);
    }

    public void backHomeClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }



}
