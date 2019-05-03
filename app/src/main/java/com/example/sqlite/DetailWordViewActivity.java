package com.example.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailWordViewActivity extends AppCompatActivity {
    private TextView tvWord,tvMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_word_view);

        tvWord= findViewById(R.id.tvWord);
        tvMeaning= findViewById(R.id.tvMeaning);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String meaning = bundle.getString("message");
            tvMeaning.setText(meaning);
        }
    }
}
