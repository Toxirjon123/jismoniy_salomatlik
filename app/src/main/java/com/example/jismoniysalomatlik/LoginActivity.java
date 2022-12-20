package com.example.jismoniysalomatlik;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout name, fam;
    MaterialButton kirish;
    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;
    String date = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, style, date, year, month, day);
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE,"Bekor qilish",datePickerDialog);
        editText.setOnClickListener(view -> datePickerDialog.show());
        kirish.setOnClickListener(this);

    }

    private void initViews() {
        name = findViewById(R.id.textInputLayout);
        fam = findViewById(R.id.textInputLayout2);
        kirish = findViewById(R.id.tasdiqlash);
        editText= findViewById(R.id.birt);
    }

    @Override
    public void onClick(View view) {
        String n = name.getEditText().getText().toString();
        String f = fam.getEditText().getText().toString();
        if(n.isEmpty()){
            Toast.makeText(this, "Ismingizni kiriting!", Toast.LENGTH_SHORT).show();
        }else if(f.isEmpty()){
            Toast.makeText(this, "Familyangizni kiriting!", Toast.LENGTH_SHORT).show();
        }else if(date.isEmpty()){
            Toast.makeText(this, "Tug'ilgan kuningizni kiriting!", Toast.LENGTH_SHORT).show();
        }else {
            saveShar(n,f,date);
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            LoginActivity.this.finish();
        }

    }

    private void saveShar(String name, String fam, String date){
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("name", name);
        editor.putString("fam",fam);
        editor.putString("birth",date);
        editor.apply();
    }
    private void updateLabel(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat,Locale.US);
        date = dateFormat.format(myCalendar.getTime());
        editText.setText(date);
    }

//    private String makeDateString(int day, int month, int year){
//
//    }



}