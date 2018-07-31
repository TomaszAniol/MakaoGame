package com.test;

import com.card.Card;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CardTest {

    private Card card;
    private String name;
    private String suit;

    public CardTest(String name, String suit) {
        this.name = name;
        this.suit = suit;
    }

    @org.junit.Before
    public void setup() {
        card = new Card(name, suit);
    }

    @org.junit.Test
    public void getCardName() {
        assertEquals(name, card.getCardName());
    }

    @org.junit.Test
    public void getCardSuit() {
        assertEquals(suit, card.getCardSuit());
    }

    @Parameterized.Parameters
    public static Collection<Object> TestConditions() {
        return Arrays.asList(new Object[][]{
                {"king", "spade"},
                {"four", "spade"},
                {"two", "diamond"},
                {"three", "heart"},
                {"four", "diamond"},
                {"five", "spade"},
                {"six", "club"},
                {"seven", "club"},
                {"queen", "spade"},
                {"nine", "diamond"},
                {"ten", "spade"},
                {"jack", "spade"},
                {"king", "heart"},
        });
    }
}