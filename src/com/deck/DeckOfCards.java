package com.deck;

import com.card.Card;
import com.card.CardNames;
import com.card.CardSuits;

import java.util.*;

public class DeckOfCards {

    private List<Card> deckOfCards = new ArrayList<>();
    private List<PullCardListner> listners = new ArrayList<>();


    public DeckOfCards() {
        String[] listOfCardNames = getListOfCardNames();
        String[] listOfCardSuits = getListOfCardSuits();

        for (String cardSuit : listOfCardSuits) {
            for (String cardName : listOfCardNames) {
                deckOfCards.add(new Card(cardName, cardSuit));
            }
        }
        shuffle();
    }

    public void addListner(PullCardListner toAdd){
        listners.add(toAdd);
    }

    private String[] getListOfCardNames() {
        CardNames nameOfCard;
        String[] listOfNames = new String[CardNames.values().length];
        for (int i = 0; i < CardNames.values().length; i++) {
            nameOfCard = CardNames.values()[i];
            listOfNames[i] = nameOfCard.toString();
        }
        return listOfNames;
    }

    private String[] getListOfCardSuits() {
        CardSuits suitOfCard;
        String[] listOfSuits = new String[CardSuits.values().length];
        for (int i = 0; i < CardSuits.values().length; i++) {
            suitOfCard = CardSuits.values()[i];
            listOfSuits[i] = suitOfCard.toString();
        }
        return listOfSuits;
    }


    public boolean IsCardOnDeck() {
        return deckOfCards.size() > 0;

    }

    public Card pickLastCardOnDeck() {
        int lastCard = deckOfCards.size() - 1;
        Card card = deckOfCards.get(lastCard);
        removeCard(lastCard);
        notifyPulledCard();
        return card;
    }

    private void notifyPulledCard(){
        for (PullCardListner listner : listners){
            listner.cardPulled();
        }
    }


    private void removeCard(int numberOfCard) {
        deckOfCards.remove(numberOfCard);
    }

    private void shuffle() {
        int numberOfCardsInDeck = deckOfCards.size();
        List<Integer> listOfPossibleNumbers = createListOfPossibleNumbers(numberOfCardsInDeck);
        Card[] tempListOfCard = shuffleCards(numberOfCardsInDeck, listOfPossibleNumbers);
        Collections.addAll(deckOfCards, tempListOfCard);
    }

    private List<Integer> createListOfPossibleNumbers(int numberOfCardsInDeck) {
        List<Integer> listOfPossibleNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfCardsInDeck; i++) {
            listOfPossibleNumbers.add(i);
        }
        return listOfPossibleNumbers;
    }

    private Card[] shuffleCards(int numberOfCardsInDeck, List<Integer> listOfPossibleNumbers) {
        Card[] tempListOfCard = new Card[numberOfCardsInDeck];
        Random random = new Random();
        for (int i = 0; i < numberOfCardsInDeck; i++) {
            int randomNumber = random.nextInt(listOfPossibleNumbers.size());
            int positionOnList = listOfPossibleNumbers.get(randomNumber);
            listOfPossibleNumbers.remove(randomNumber);
            Card card = pickLastCardOnDeck();
            tempListOfCard[positionOnList] = card;
        }
        return tempListOfCard;
    }


}
