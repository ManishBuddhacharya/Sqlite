package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etWord, etMeaning;
    private Button btnAddWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        setContentView(R.layout.activity_main);

        etWord = findViewById(R.id.etWord);
        etMeaning = findViewById(R.id.etMeaning);
        btnAddWord = findViewById(R.id.btnAddWords);

        btnAddWord.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();
        if(myHelper.InsertData(etWord.getText().toString(), etMeaning.getText().toString(), sqLiteDatabase)){
            Toast.makeText(MainActivity.this,"Successful", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
