package com.player;

import com.card.Card;
import com.card.CardNames;
import com.card.CardSuits;
import com.deck.Deck;
import com.deck.DeckOfCards;
import com.game.GamePlay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Player {

    private String demandCard;
    private Card cardUsedInLastRound;
    protected List<Card> cardsInHand;
    protected DeckOfCards cardFromDeck;
    protected boolean isSpecialCard;
    protected int specialCardPosition;
    protected Deck deck;
    protected GamePlay gamePlay;

    public Player(Deck deck, GamePlay gamePlay, DeckOfCards cardFromDeck) {
        this.deck = deck;
        this.gamePlay = gamePlay;
        this.cardFromDeck = cardFromDeck;
        demandCard = "";
        cardUsedInLastRound = deck.actualCardOnDeck();
        cardsInHand = new ArrayList<>();
        isSpecialCard = false;
        specialCardPosition = 0;
        TakeFirstCards();
    }

    private void TakeFirstCards() {
        TakeCards(5);
    }

    private void TakeCards(int cardsToTaken) {
        for (int i = 0; i < cardsToTaken; i++) {
            pullCardFromDeck();
        }
    }

    public void setDemandCard(String cardDemand) {
        this.demandCard = cardDemand;
    }

    public String getDemandCard() {return demandCard;}

    protected Card getCardOnDeck() {
        return deck.actualCardOnDeck();
    }

    protected String getCardOnDeckName() {
        return deck.actualCardOnDeck().getCardName();
    }

    protected String getCardOnDeckSuit() {
        return deck.actualCardOnDeck().getCardSuit();
    }

    protected String getCardInHandName(int position) {
        return cardsInHand.get(position).getCardName();
    }

    protected String getCardInHandSuit(int position) {
        return cardsInHand.get(position).getCardSuit();
    }

    protected void safeCardOnDeck(Card cardToSafe) {
        cardUsedInLastRound = cardToSafe;
    }

    public abstract void playRound();

    protected abstract void throwCardOnDeck(int i);

    public abstract void pullCardFromDeck();

    protected boolean isSpecialCardInHand() {

        if (isCardThrowedInLastRound()) {
            return true;
        } else if (cardIsDemanded()) {
            return tryToFindDemandCard();
        } else if (isCardTwoOnDeck()) {
            return tryToFindTwo();
        } else if (isCardThreeOnDeck()) {
            return tryToFindThree();
        } else if (isCardFourOnDeck()) {
            return tryToFindFour();
        } else if (isCardKingOnDeck()) {
            return tryToFindKing();
        }
        return true;
    }

    private boolean cardIsDemanded() {
        return !demandCard.isEmpty();
    }

    private boolean tryToFindDemandCard() {
        if (isDemandCardInHand()) {
            demandCard = "";
            //System.out.println("Karta znaleziona");
            return true;
        } else {
            //System.out.println("Karta nie znaleziona");
            demandCard = "";
            pullCardFromDeck();
            return false;
        }
    }

    private boolean isCardTwoOnDeck() {
        return getCardOnDeckName().equals(CardNames.two.toString());
    }

    private boolean tryToFindTwo() {
        if (isCardTwoInHand()) {
            //System.out.println("Rzucam dwójkę");
            return true;
        } else {
            //System.out.println("Biore 2 karty");
            TakeCards(2);
            return false;
        }
    }

    private boolean isCardThreeOnDeck() {
        return getCardOnDeckName().equals(CardNames.three.toString());
    }

    private boolean tryToFindThree() {
        if (isCardThreeInHand()) {
          //  System.out.println("Rzucam trójkę");
            return true;
        } else {
            TakeCards(3);
            //System.out.println("Biore 3 karty");
            return false;
        }
    }

    private boolean isCardFourOnDeck() {
        return getCardOnDeckName().equals(CardNames.four.toString());
    }

    private boolean tryToFindFour() {
        if (isCardFourInHand()) {
          //  System.out.println("Rzucam czwórkę");
            return true;
        } else {
         //   System.out.println("Kolejna tura");
            // skip turn
            return false;
        }
    }

    private boolean isCardKingOnDeck() {
        return (getCardOnDeckName().equals(CardNames.king.toString()) &&
                (getCardOnDeckSuit().equals(CardSuits.spade.toString()) ||
                        getCardOnDeckSuit().equals(CardSuits.heart.toString())));
    }

    private boolean tryToFindKing() {
        if (isCardKingInHand()) {
          //  System.out.println("Rzucam Króla");
            return true;
        } else {
            //System.out.println("Biore 5 kart");
            TakeCards(5);
            return false;
        }
    }

    private boolean isCardThrowedInLastRound() {
        return cardUsedInLastRound.equals(getCardOnDeck());
    }

    private boolean isCardTwoInHand() {
        return findCard(card -> card.getCardName().equals(CardNames.two.toString()));
    }

    private boolean isCardThreeInHand() {
        return findCard(card -> card.getCardName().equals(CardNames.three.toString()));
    }

    private boolean isCardFourInHand() {
        return findCard(card -> card.getCardName().equals(CardNames.four.toString()));
    }

    private boolean isCardKingInHand() {
        return findCard(card -> (card.getCardName().equals(CardNames.king.toString())) &&
                (card.getCardSuit().equals(CardSuits.heart.toString())
                        || card.getCardSuit().equals(CardSuits.spade.toString())));
    }

    private boolean isDemandCardInHand() {
        return findCard(card -> card.getCardSuit().equals(demandCard) || card.getCardName().equals(demandCard));
    }

    private boolean findCard(Predicate<Card> card) {
        for (int i = 0; i < cardsInHand.size(); i++) {
            if (card.test(cardsInHand.get(i))) {
                specialCardPosition = i;
                isSpecialCard = true;
                return true;
            }
        }
        return false;
    }




}
