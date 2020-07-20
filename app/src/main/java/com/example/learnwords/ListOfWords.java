package com.example.learnwords;

import java.util.ArrayList;

/**
 * <p>Класс позволяет работать со списком слов. </p>
 * <p>Содержит методы для добавления , удаления, изменения и поиска слов.</p>
 * @author Alex Tereschenko
 *
 * */

public class ListOfWords {
    private ArrayList<Word> arrListItems;

    public ListOfWords() {
        arrListItems = new ArrayList<Word>();
    }

    /**
     * <p>Добавляем одно слово в словарь.</p>
     * @param id Номер слова в списке
     * @param word Слово
     * @param translation Перевод слова
     * */

    public void addWord(int id, String word, String translation) {
        arrListItems.add(new Word(id,word,translation));
    }

    /**
     * <p>Считает количество слов в словаре.</p>
     *
     * */
    public int countWord() {
        return arrListItems.size();
    }

    /**
     * <p>Удаляем слово из словаря.</p>
     * @param id Номер слова в списке, которое нужно удалить
     * */
    public void removeWord(int id) {
        arrListItems.remove(id);
    }

    /**
     * <p>Получаем словарь.</p>
     *
     * */
    public ArrayList<Word> getListWord() {
        return arrListItems;
    }

    /**
     * <p> Поиск слова в словаре.</p>
     * @param word Слово, перевод которого, нужно найти
     * */

    public String findWord(String word) {
        for(int i = 0; i < arrListItems.size(); i++){
            String wrd = arrListItems.get(i).getWord();
            if (wrd == word) {
                return arrListItems.get(i).getTranslation();
            }
        }
        return "Not found";
    }

    /**
     * <p>Обновление слова в словаре.</p>
     * @param word Слово, перевод которого, нужно обновит
     * @param new_translaton Новый перевод
     * */
    public void updateWord(String word, String new_translaton) {
        int id = 0;
        for(int i = 0; i < arrListItems.size(); i++){
            String wrd = arrListItems.get(i).getWord();
            if (wrd == word) {
                id = i;
            }
        }

        Word my_word = new Word(arrListItems.get(id).getId(), arrListItems.get(id).getWord(), new_translaton);
        arrListItems.remove(id);
        arrListItems.add(my_word);
    }
}
