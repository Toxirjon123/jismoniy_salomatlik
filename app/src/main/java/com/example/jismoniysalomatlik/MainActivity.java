package com.example.jismoniysalomatlik;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.jismoniysalomatlik.db.HistoryDatabase;
import com.example.jismoniysalomatlik.model.HistoryModel;
import com.example.jismoniysalomatlik.network.RestClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView txtName;
    LottieAnimationView lotti;
    MaterialAlertDialogBuilder builder;
    Dialog d;
    EditText puls, sisBosim, diaBosim, boy, vazn;
    MaterialCardView btnProfile;
    HistoryDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new HistoryDatabase(this);
        initViews();
        setDialog();
        builder = new MaterialAlertDialogBuilder(this, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered);
        builder.setMessage("Agar siz ushbu telefon orqali oilangizni boshqa vakillarini jismoniy salomatligini  miqdorini bilmoqchi bo’lsangiz quyida 'Yangi' tugmani bosib, ushbu shaxsni ma’lumotlarini qaytadan kiriting. Aks holda bekor qilish tugmasini bosing.");
        builder.setCancelable(false);
        builder.setNegativeButton("Yangi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                MainActivity.this.finish();
            }
        }).setPositiveButton("Bekor qilish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        setText();
        findViewById(R.id.calculate).setOnClickListener(this);
        btnProfile.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));


    }

    private void setDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.attention_item);
        d.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        d.getWindow().setGravity(Gravity.CENTER);
        d.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        d.setCancelable(false);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.show();
        MaterialButton btn = d.findViewById(R.id.btnTushunarli);
        btn.setOnClickListener(view -> d.dismiss());


    }

    private void setText() {
        txtName.setText(getName());
    }

    private void initViews() {
        puls = findViewById(R.id.puls);
        diaBosim = findViewById(R.id.disBosim);
        sisBosim = findViewById(R.id.sisBosim);
        boy = findViewById(R.id.boy);
        vazn = findViewById(R.id.vazn);
        lotti = findViewById(R.id.lotti);
        txtName = findViewById(R.id.txtName);
        btnProfile = findViewById(R.id.im1);
    }

    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        dialog.findViewById(R.id.xop).setOnClickListener(view1 -> {
            dialog.dismiss();
            builder.show();


        });
        TextView t = dialog.findViewById(R.id.dialText);
        ImageView imageView = dialog.findViewById(R.id.img);

        int dav_sis = Integer.parseInt(sisBosim.getText().toString());
        int pols = Integer.parseInt(puls.getText().toString());
        int dav_di = Integer.parseInt(diaBosim.getText().toString());
        int massa = Integer.parseInt(vazn.getText().toString());
        double vozrast = Double.parseDouble(boy.getText().toString());
        double r = getKOUFZ(getAP(pols,
                dav_sis,dav_di,
                Integer.parseInt(getAge(getYear(getBirth()), getMonth(getBirth()), getDay(getBirth()))),
                massa,
                vozrast));

        imageView.setImageResource(getIcon(resultAfterCheck(r)));
        String s = "Hurmatli " + getName() + "\n" + "Sizning shaxsiy jismoniy salomatligingiz darajasi " + String.format("%.2f", r) + "% ga teng, ya'ni\n ";
        String s2 = resultAfterCheck(r);
        String s1;

        if (getList(database.getData()).size() > 0) {
            if (checkResult(r).isEmpty()) {
                s1 = s + s2 + "\nbu so'ngi ko'rsatkichingiz bilan bir xil. " + "\n\n" + getResult(resultAfterCheck(r));
            } else {
                s1 = s + s2 + "\nbu so'ngi ko'rsatkichingizdan " + checkResult(r) + "\n\n" + getResult(resultAfterCheck(r));
            }
        } else {
            s1 = s + s2 + "\n\n" + getResult(resultAfterCheck(r));
        }
        writeDatabase(r);


        SpannableString ss1 = new SpannableString(s1);
        ss1.setSpan(new RelativeSizeSpan(1.5f), s.length(), s.length() + s2.length(), 0); // set size
        ss1.setSpan(new ForegroundColorSpan(Color.RED), s.length(), s.length() + s2.length(), 0);// set color

        t.setText(ss1);
    }

    private double getKof(String age) {
        if (Integer.parseInt(age) <= 34.9) {
            return 1;
        } else if (Integer.parseInt(age) > 35 && Integer.parseInt(age) <= 54.9) {
            return 1.1;
        } else {
            return 1.309;
        }
    }

    private void writeDatabase(double r) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String time = sdf.format(new Date());
        database.insertData(new HistoryModel(databaseName(), String.valueOf(r), time));

    }

    private double getLastResult() {
        return Double.parseDouble(getList(database.getData()).get(getList(database.getData()).size() - 1).getValue());
    }

    private String checkResult(double r) {
        String result = "";
        if (r > getLastResult()) {
            result = (String.format("%.2f", r - getLastResult())) + "% ga yaxshiroq.";
        } else if (r < getLastResult()) {
            result = String.format("%.2f", getLastResult() - r) + "% ga yomonroq.";
        } else {
            result = "";
        }
        return result;
    }

    private String getName() {
        SharedPreferences sh = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        return sh.getString("name", "") + " " + sh.getString("fam", "");
    }

    private String resultAfterCheck(double n) {
        String result;
        if (n > 15.00 && n < 36.45) {
            result = "JUDA YOMON";
        } else if (n > 36.46 && n < 57.64) {
            result = "YOMON";
        } else if (n > 57.65 && n < 70.74) {
            result = "O'RTACHADAN PASTROQ";
        } else if (n > 70.75 && n < 83.82) {
            result = "O'RTACHA";
        } else if (n > 83.83 && n < 91.90) {
            result = "YAXSHI";
        } else if (n > 91.91 && n < 100) {
            result = "A'LO";
        } else {
            result = "";
        }
        return result;
    }

    private double getKOUFZ(double ap) {
        return (100 - (((ap - 1) / 3.236) * 100) + (((ap / 4.236) * 25) - 5.9)) * getKof(getAge(getYear(getBirth()), getMonth(getBirth()), getDay(getBirth())));
    }

    private double getAP(int chp, int sad, int dad, int v, int mt, double r) {
        return (0.011 * chp) + (0.014 * sad) + (0.008 * dad) + (0.014 * v) + (0.009 * mt) - (0.009 * r) - 0.273;
    }

    private String getResult(String a) {
        String res;
        switch (a) {
            case "JUDA YOMON":
                res = "Zudlik bilan shifokorlarga tashxis qo'ydiring va ular nazoratida davolaning!";
                break;
            case "YOMON":
                res = "Tanangizni salbiy ta'sirlarga moslashishi qoniqarsiz holga kelib qolgan va o'ta zo'riqib ishlayapti. Natijada sizda kasallik keltirib chiqaruvchi jarayonlar boshlangan. Zudlik bilan shifokorlarga murokaat qiling va kasallikni oldini oling.";
                break;
            case "O'RTACHADAN PASTROQ":
                res = "Lekin amalda sog'siz. Tanangizdagi turli salbiy ta'sirlarga moslashuvchanlik jarayonlari (adaptatsiya) zo'riqib ishlab, " +
                        "sizni sog'ligingizni saqlayapti. Sizga dispanser tekshiruvidan o'tishni va uni tavsiyalariga amal qilib sog'lingizni yanada yaxshilashga erishishni tavsiya etamiz.";
                break;
            case "O'RTACHA":
                res = "Amalda sog'lomsiz. Ammo sizning turmush tarzingizda sog'ligingizga salbiy ta'sir qiluvchi ayrim odatlar, yoki " +
                        "holatlar yoki ba'zi bir omillar mavjud. Lekin tanangizni salbiy ta'sirlarga moslanuvchanligi(adaptatsiyasi) qoniqarli ishlayotgani " +
                        "uchun kasalliklarni rivojlanishiga yo'l qo'yilmayapti va sizni sog'lingiz o'rtacha darajada ta'minlayapti. Ushbu salbiy omillarni shifokorlar yordamida aniqlab bartaraf etsangiz sog'ligingiz yanada yaxshilandi.";
                break;
            case "YAXSHI":
                res = "Amaldagi sog'lom turmush tarzingizga rioya qiling";
                break;
            case "A'LO":
                res = "Amaldagi sog'lom turmush tarzingizni davom ettiring.";
                break;
            default:
                res = "";
                break;
        }
        return res;
    }

    private int getIcon(String a) {
        int res;
        switch (a) {
            case "JUDA YOMON":
                res = R.drawable.sadface;
                break;
            case "YOMON":
                res = R.drawable.bad_sad;
                break;
            case "O'RTACHADAN PASTROQ":
                res = R.drawable.sad;
                break;
            case "O'RTACHA":
                res = R.drawable.neutral;
                break;
            case "YAXSHI":
                res = R.drawable.happy;
                break;
            case "A'LO":
                res = R.drawable.smile;
                break;
            default:
                res = 0;
                break;
        }
        return res;
    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);

        return ageInt.toString();
    }

    private int getMonth(String year) {
        String[] a = year.split("/");
        return Integer.parseInt(a[0]);
    }

    private int getDay(String year) {
        String[] a = year.split("/");
        return Integer.parseInt(a[1]);
    }

    private int getYear(String year) {
        String[] a = year.split("/");
        return Integer.parseInt(a[2]);
    }

    private String getBirth() {
        SharedPreferences sh = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        return sh.getString("birth", "");
    }

    private String databaseName() {
        SharedPreferences sh = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        return sh.getString("name", "");
    }

    private List<HistoryModel> getList(List<HistoryModel> list) {
        List<HistoryModel> historyModelList = new ArrayList<>();
        for (HistoryModel model : list) {
            if (model.getName().equals(databaseName())) {
                historyModelList.add(model);
            }
        }
        return historyModelList;
    }


}