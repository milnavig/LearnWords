package com.example.learnwords;

import java.io.Serializable;

/**
 * <p>Класс для роботы со словами. </p>
 * <p>Содержит методы добавление, редактирования и удаления слов</p>
 * @author Alex Tereschenko
 *
 * */
public class Word implements Serializable {//!!!

    private int id;
    private String word;
    private String translation;

    public Word(){}

    public Word(int id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getTranslation() {
        return translation;
    }
}