package com.example.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DisplayWordActivity extends AppCompatActivity {
    private ListView lstWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word);

        lstWords = findViewById(R.id.lstWords);
        loadWord();

    }

    private void loadWord() {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqlLiteDatabase = myHelper.getWritableDatabase();

        List <Word> wordList = new ArrayList<>();
        wordList = myHelper.GetAlWords(sqlLiteDatabase);

        HashMap<String, String> hashMap = new HashMap<>();
        for (int i =0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );
        lstWords.setAdapter(stringArrayAdapter);
    }
}
