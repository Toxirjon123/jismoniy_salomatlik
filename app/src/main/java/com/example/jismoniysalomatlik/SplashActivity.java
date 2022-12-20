package com.example.jismoniysalomatlik;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jismoniysalomatlik.intro.IntroActivity;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
           if(isHave().isEmpty()){
               startActivity(new Intent(SplashActivity.this, IntroActivity.class));
           }else {
               startActivity(new Intent(SplashActivity.this, MainActivity.class));
           }
            finish();
        }, 2200);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private String isHave(){
        SharedPreferences sh = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        return sh.getString("name", "");
    }
}