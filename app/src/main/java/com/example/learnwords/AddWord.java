package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * <p>Класс Activity реализует страницу добавления слова. </p>
 * <p>Содержит обработчики кнопок и полей</p>
 * @author Alex Tereschenko
 *
 * */
public class AddWord extends AppCompatActivity {
    Button btnAdd;
    EditText word, translation;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        btnAdd = (Button) findViewById(R.id.button_add);
        word = (EditText) findViewById(R.id.wrd);
        translation = (EditText) findViewById(R.id.trsl);

        dbHelper = new DBHelper(this);

        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        String wrd = word.getText().toString();
                        String trsl = translation.getText().toString();

                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        ContentValues contentValues = new ContentValues();

                        contentValues.put(DBHelper.KEY_WORD, wrd);
                        contentValues.put(DBHelper.KEY_TRANSLATION, trsl);

                        database.insert(DBHelper.TABLE_WORDS, null, contentValues);

                        Intent intent = new Intent("com.example.learnwords.WordList");
                        startActivity(intent);
                    }
                }
        );
    }
}
