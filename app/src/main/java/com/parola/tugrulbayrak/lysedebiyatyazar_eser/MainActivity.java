package com.parola.tugrulbayrak.lysedebiyatyazar_eser;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    Button testButton;
    Button tableButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        MobileAds.initialize(getApplicationContext(), "***");

    }

    void init() {
        testButton = (Button) findViewById(R.id.testButton);
        tableButton=(Button)findViewById(R.id.button2);
    }

    public void testClick(View v) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    public void tableClick(View v) {
        Intent intent = new Intent(this,TableActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
    }


}
