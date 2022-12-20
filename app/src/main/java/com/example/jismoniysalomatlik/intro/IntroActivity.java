package com.example.jismoniysalomatlik.intro;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jismoniysalomatlik.LoginActivity;
import com.example.jismoniysalomatlik.MainActivity;
import com.example.jismoniysalomatlik.R;

public class IntroActivity extends AppCompatActivity {
    boolean isFirst = true;
    FrameLayout btnNext;
    TextView introTxt;
    View v1, v2;
    Dialog d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
      initViews();
      setDialog();
      d.findViewById(R.id.frameLayout1).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
              IntroActivity.this.finish();
          }
      });
      isFirst = true;
      btnNext.setOnClickListener(view -> nextStep(isFirst));
    }

    private void setDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.graph_dialog);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        d.getWindow().setGravity(Gravity.CENTER|Gravity.TOP);
        d.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTop;
        d.setCancelable(false);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void nextStep(boolean first) {
        if(first){
            introTxt.setText("Shaxsning jismoniy salomatligi sifatini baholash Ibn Sino tasnifi asosida, ularni choralarini va umumiy " +
                    "miqdori ko'rsatkichlarini hisoblash esa professor M.Qoraboyev barpo etgan matematik model asosida amalga oshiriladi. \n Tananing jismoniy salomatligining miqdoriy o'zgarishlari va sifat o'zgarishlari o'rtasidagi bog'lanish navbatdagi sahifada keltirilgan.");
            v1.setBackgroundResource(R.drawable.inactive_line);
            v2.setBackgroundResource(R.drawable.active_line);
            isFirst = false;
        }else {
            d.show();

        }
    }

    private void initViews() {
        btnNext = findViewById(R.id.frameLayout);
        introTxt = findViewById(R.id.introTxt);
        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sh = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        if(!sh.getString("name", "").isEmpty()){
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            IntroActivity.this.finish();
        }
    }
}