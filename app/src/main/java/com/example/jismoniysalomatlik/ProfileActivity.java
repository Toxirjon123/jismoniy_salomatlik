package com.example.jismoniysalomatlik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.jismoniysalomatlik.adapter.HistoryAdapter;
import com.example.jismoniysalomatlik.db.HistoryDatabase;

public class ProfileActivity extends AppCompatActivity {
    RecyclerView hisRec;
    HistoryDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        database = new HistoryDatabase(this);
        hisRec.setAdapter(new HistoryAdapter(database.getData(),this));
    }

    private void initViews() {
        hisRec = findViewById(R.id.hisRec);
    }
}