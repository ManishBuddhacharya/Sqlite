package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class SearchWordActivity extends AppCompatActivity {
    private EditText etSearch;
    private Button btnSearch;
    private ListView lstWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        lstWords = findViewById(R.id.lstWords);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSearchedWords(etSearch.getText().toString());
            }
        });
    }

    private void loadSearchedWords(String word) {
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List <Word> wordList = new ArrayList<>();
        wordList = myHelper.getSearchedWords(word, sqLiteDatabase);

        final HashMap<String, String> hashMap = new HashMap<>();
        for (int i =0; i<wordList.size(); i++){
            hashMap.put(wordList.get(i).getWord(), wordList.get(i).getMeaning());
        }

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(hashMap.keySet())
        );
        lstWords.setAdapter(stringArrayAdapter);

        lstWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = hashMap.get(key);
                Toast.makeText(SearchWordActivity.this, key + meaning,Toast.LENGTH_LONG).show();


                Intent intent = new Intent(SearchWordActivity.this, DetailWordViewActivity.class);
                intent.putExtra("message", meaning);
                startActivity(intent);

            }
        });
    }
}
