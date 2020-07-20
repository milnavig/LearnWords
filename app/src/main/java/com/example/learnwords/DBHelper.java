package com.example.learnwords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * <p>Класс для работы и настройки базы данных. </p>
 * <p>Содержит методы для работы с БД</p>
 * @author Alex Tereschenko
 *
 * */

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "wordDb";
    public static final String TABLE_WORDS = "words";

    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "word";
    public static final String KEY_TRANSLATION = "translation";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WORDS + "(" + KEY_ID
                + " integer primary key," + KEY_WORD + " text," + KEY_TRANSLATION + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_WORDS);

        onCreate(db);

    }

    public Word getWord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORDS, new String[] { KEY_ID,
                        KEY_WORD, KEY_TRANSLATION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Word my_word = new Word(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return my_word;
    }

    public void deleteWord(Word my_word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, KEY_ID + " = ?", new String[] { String.valueOf(my_word.getId()) });
        db.close();
    }

    public int updateWord(Word my_word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, my_word.getWord());
        values.put(KEY_TRANSLATION, my_word.getTranslation());

        return db.update(TABLE_WORDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(my_word.getId()) });
    }
}