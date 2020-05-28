package com.example.exampreparation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Start_Quiz extends AppCompatActivity {

    private Button start,bookmark;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__quiz);

        start=findViewById(R.id.startquiz);
        bookmark=findViewById(R.id.bookmark);

        MobileAds.initialize(this);

        //loadAds();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Start_Quiz.this,CategoriesActivity.class);
                startActivity(intent);
            }
        });


        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent book_intent=new Intent(".BookmarkActivity");
                startActivity(book_intent);
            }
        });
    }
//    private void loadAds(){
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

//    }
}
