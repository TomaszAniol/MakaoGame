package com.player;

import com.card.Card;
import com.card.CardNames;
import com.card.CardSuits;
import com.deck.Deck;
import com.deck.DeckOfCards;
import com.game.GamePlay;

import java.util.Random;

public class NPCPlayer extends Player {

    private Random random;

    public NPCPlayer(DeckOfCards cardFromDeck, GamePlay gamePlay, Deck deck) {
        super(deck, gamePlay, cardFromDeck);
        //initTestCards();
        random = new Random();

    }

    @Override
    public void playRound() {
        if (isSpecialCardInHand()) {
            int isCard = haveCard();
            if (isCard > -1) {
                throwCardOnDeck(isCard);
            } else {
                pullCardFromDeck();
            }
        }
        safeCardOnDeck(getCardOnDeck());
        if (noCardsInHand()) {
            gamePlay.setEndOfGame(true);
        }
        //for (Card card : cardsInHand) {
            //System.out.println("Karta w ręku: " + card.getCardName() + " " + card.getCardSuit());
        //}
    }

    private boolean noCardsInHand() {
        return cardsInHand.size() == 0;
    }


    @Override
    protected void throwCardOnDeck(int i) {
        //System.out.println("Zrzucam kartę na stół");
        Card cardPlayed = cardsInHand.get(i);
        //System.out.println("Karta NPC: " + cardPlayed.getCardSuit() + " " + cardPlayed.getCardName());
        deck.putCardOnDeck(cardPlayed);
        cardsInHand.remove(i);
        if (isJack()) {
            demandCardName();
        } else if (isAce()) {
            demandCardSuit();
        }
    }

    private boolean isJack() {
        return getCardOnDeckName().equals(CardNames.jack.toString());
    }

    private void demandCardName() {
        int randomNumber = random.nextInt(CardNames.values().length);
        String cardName = CardNames.values()[randomNumber].toString();
        //System.out.println("Walet, żadam:" + cardName);
        gamePlay.setDemandCard(cardName);
    }

    private boolean isAce() {
        return getCardOnDeckName().equals(CardNames.ace.toString());
    }

    private void demandCardSuit() {
        int randomNumber = random.nextInt(CardSuits.values().length);
        String cardSuit = CardSuits.values()[randomNumber].toString();
        //System.out.println("As, żadam:" + cardSuit);
        gamePlay.setDemandCard(cardSuit);
    }


    @Override
    public void pullCardFromDeck() {
        if (tryToTakeCardFromDeck()) {
            //System.out.println("Ciągne karte ze stołu");
            Card pickCards = cardFromDeck.pickLastCardOnDeck();
            cardsInHand.add(pickCards);
        }
    }

    private boolean tryToTakeCardFromDeck() {
        return cardFromDeck.IsCardOnDeck();
    }

    private void initTestCards() {
//        cardsInHand.add(new Card("two", "spade"));
//        cardsInHand.add(new Card("three", "spade"));
//        cardsInHand.add(new Card("four", "spade"));
//        cardsInHand.add(new Card("two", "diamond"));
//        cardsInHand.add(new Card("three", "heart"));
//        cardsInHand.add(new Card("four", "diamond"));
//        cardsInHand.add(new Card("five", "spade"));
//        cardsInHand.add(new Card("six", "spade"));
//        cardsInHand.add(new Card("seven", "spade"));
//        cardsInHand.add(new Card("queen", "spade"));
//        cardsInHand.add(new Card("nine", "spade"));
//        cardsInHand.add(new Card("ten", "spade"));
        cardsInHand.add(new Card("jack", "spade"));
        cardsInHand.add(new Card("jack", "heart"));
        cardsInHand.add(new Card("jack", "diamond"));
        cardsInHand.add(new Card("jack", "club"));
//        cardsInHand.add(new Card("king", "heart"));
//        cardsInHand.add(new Card("king", "spade"));
//        cardsInHand.add(new Card("ace", "spade"));
    }

    private int haveCard() {
        for (int i = 0; i < cardsInHand.size(); i++) {
            if (isSpecialCard) {
                isSpecialCard = false;
                return specialCardPosition;

            }else if (isQueenOnDeck()) {
                //System.out.println("dama");
                return i;
            }else if (cardHasSameNameorSuit(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean cardHasSameNameorSuit(int i) {
        return cardsInHand.get(i).getCardSuit().equals(getCardOnDeckSuit()) ||
                cardsInHand.get(i).getCardName().equals(getCardOnDeckName());
    }

    private boolean isQueenOnDeck() {
        return getCardOnDeckName().equals(CardNames.queen.toString());
    }
}