package com.card;


public class Card {

    private String cardName;
    private String cardSuit;

    public Card(String cardName, String cardSuit){
        this.cardName = cardName;
        this.cardSuit = cardSuit;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardSuit() {
        return cardSuit;
    }



}
