package com.deck;

import com.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Deck {

    private Card cardOnDeck;
    private DeckOfCards deckOfCards;
    private List<DeckListner> listners = new ArrayList<>();

    public Deck() {
        deckOfCards = new DeckOfCards();
        cardOnDeck = deckOfCards.pickLastCardOnDeck();
    }

    public void addListner(DeckListner toAdd){
        listners.add(toAdd);
    }

    public Card actualCardOnDeck() {
        return cardOnDeck;
    }

    public boolean putCardOnDeck(Card playerCard) {
        cardOnDeck = playerCard;
        notifyListners();
        return true;
    }

    private void notifyListners(){
        for (DeckListner deckListner : listners){
            deckListner.cardThrown();
        }
    }
}
