package com.example.course_work_zlat_stonks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    ListView userList;
    TextView header;
    DB_Helper db_helper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header = findViewById(R.id.header);
        userList = findViewById(R.id.list);

        db_helper = new DB_Helper(getApplicationContext());
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Site_Activity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() { super.onResume();

        try {
            db = db_helper.open();
            userCursor = db.rawQuery("SELECT * FROM " + DB_Helper.TABLE, null);

            String[] from = new String[] {DB_Helper.TIKER, DB_Helper.INN};
            int[] to = new int[] {android.R.id.text1, android.R.id.text2};
            userAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, userCursor, from, to, 0);
                header.setText("Найдено элементов: " + userCursor.getCount());
                userList.setAdapter(userAdapter);

        } catch (Exception e) {
            e.printStackTrace(); header.setText("Ошибка: " + e.getMessage());}
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userCursor != null && !userCursor.isClosed()) {
            userCursor.close();
        }
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}