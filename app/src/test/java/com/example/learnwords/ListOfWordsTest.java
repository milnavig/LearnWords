package com.example.learnwords;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ListOfWordsTest{
    public ListOfWords myWords;

    public ListOfWordsTest() {
        myWords = new ListOfWords();
    }
/*
    @Test
    public void testAddWord() {
        myWords.addWord(1,"football", "футбол");
        myWords.addWord(2,"sport", "спорт");
        myWords.addWord(3,"car", "машина");
        ArrayList<Word> list = myWords.getListWord();

        Assert.assertEquals(3, list.size());
        Assert.assertEquals("football", list.get(0).getWord());
    }
*/
/*
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWord() {
        myWords.addWord(4,"game", "игра");
        myWords.removeWord(1);
        System.out.println(myWords.countWord());
        //ArrayList<Word> list = myWords.getListWord();

        //Assert.assertEquals(0, list.size());
    }

 */
/*
    @Test
    public void testRemoveWordCorrect() {
        myWords.addWord(1,"game", "игра");
        myWords.addWord(2,"sport", "спорт");
        myWords.removeWord(1);
        System.out.println(myWords.countWord());
        ArrayList<Word> list = myWords.getListWord();

        Assert.assertEquals(1, list.size());
    }

 */
/*
    @Test
    public void testFindWord() {
        myWords.addWord(1,"game", "игра");
        myWords.addWord(2,"sport", "спорт");

        Assert.assertEquals("спорт", myWords.findWord("world"));
    }
*/
    @Test
    public void testUpdateWord() {
        myWords.addWord(1,"game", "игра");
        myWords.addWord(2,"sport", "спорт");
        myWords.addWord(3,"car", "машина");
        myWords.updateWord("game", "игра2");

        Assert.assertEquals("игра2", myWords.findWord("game"));
    }
}
