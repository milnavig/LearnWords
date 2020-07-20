package com.example.learnwords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * <p>Класс Activity реализует главную страницу. </p>
 * <p>Содержит обработчики кнопок</p>
 * @author Alex Tereschenko
 *
 * */

public class MainActivity extends AppCompatActivity {
    private Button add_word;
    private Button show_list;
    private Button remind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton() {
        add_word = (Button)findViewById(R.id.add_word);
        show_list = (Button)findViewById(R.id.word_list);
        remind = (Button)findViewById(R.id.remind_word);


        add_word.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        Intent intent = new Intent("com.example.learnwords.AddWord");
                        startActivity(intent);
                    }
                }
        );

        show_list.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        Intent intent2 = new Intent("com.example.learnwords.WordList");
                        startActivity(intent2);
                    }
                }
        );
        remind.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        Intent intent2 = new Intent("com.example.learnwords.TestActivity");
                        startActivity(intent2);
                    }
                }
        );
    }
}
