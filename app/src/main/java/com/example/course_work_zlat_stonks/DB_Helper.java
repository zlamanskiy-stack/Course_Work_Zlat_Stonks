package com.example.course_work_zlat_stonks;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class DB_Helper extends SQLiteOpenHelper {
    private static String DB_PATH;
    private static final String DB_NAME = "Database_Course.db";
    private static final int SCHEMA = 1;
    static final String TABLE = "course_database";
    static final String INN = "field7", TIKER = "field5";
    private Context myContext;

    DB_Helper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;
        DB_PATH = context.getDatabasePath(DB_NAME).getAbsolutePath();
        Log.d("DB_Helper", "Путь к БД: " + DB_PATH);
    }

    void create_db() {
        File dbFile = new File(DB_PATH);
        if (!dbFile.exists()) {
            Log.d("DB_Helper", "Создаем директорию для БД");
            dbFile.getParentFile().mkdirs();

            Log.d("DB_Helper", "Копируем БД из assets");
            try (InputStream is = myContext.getAssets().open(DB_NAME);
                 OutputStream os = new FileOutputStream(dbFile)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) > 0) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();

                Log.d("DB_Helper", "БД скопирована успешно. Размер: " + dbFile.length() + " байт");

                // Проверяем, что это валидная SQLite база
                try (SQLiteDatabase testDB = SQLiteDatabase.openDatabase(DB_PATH, null,
                        SQLiteDatabase.OPEN_READONLY)) {
                    Log.d("DB_Helper", "БД валидна, версия: " + testDB.getVersion());
                }

            } catch (IOException e) {
                Log.e("DB_Helper", "Ошибка копирования: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Log.d("DB_Helper", "БД уже существует. Размер: " + dbFile.length() + " байт");
        }
    }

    SQLiteDatabase open() {
        create_db();
        return SQLiteDatabase.openDatabase(DB_PATH, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}