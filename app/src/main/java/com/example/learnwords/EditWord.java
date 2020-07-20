package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * <p>Класс Activity реализует страницу редактирования слова. </p>
 * <p>Содержит обработчики кнопок и полей</p>
 * @author Alex Tereschenko
 *
 * */
public class EditWord extends AppCompatActivity {
    EditText my_word, my_translation;
    Button save;
    Bundle arguments;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        my_word = (EditText)findViewById(R.id.word);
        my_translation = (EditText)findViewById(R.id.translation);
        save = (Button)findViewById(R.id.button);

        arguments = getIntent().getExtras();
        if(arguments!=null){
            int id = Integer.parseInt(arguments.get("id").toString());
            dbHelper = new DBHelper(this);
            SQLiteDatabase dbdata = dbHelper.getWritableDatabase();
            Word wrd = dbHelper.getWord(id);

            my_word.setText(wrd.getWord());
            my_translation.setText(wrd.getTranslation());
        }

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        Word edited_word = new Word(Integer.parseInt(arguments.get("id").toString()),my_word.getText().toString(),my_translation.getText().toString());
                        dbHelper.updateWord(edited_word);

                        Intent intent = new Intent("com.example.learnwords.WordList");
                        startActivity(intent);
                    }
                }
        );

    }
}
