package com.player;

import com.card.Card;
import com.card.CardNames;
import com.deck.Deck;
import com.deck.DeckOfCards;
import com.describeGame.DescribeGameOfPlayers;
import com.game.GamePlay;

public class HumanPlayer extends Player {

    private int choosenCard;
    private DescribeGameOfPlayers describeGameOfPlayers;

    public HumanPlayer(DeckOfCards cardFromDeck, GamePlay gamePlay, Deck deck) {
        super(deck, gamePlay, cardFromDeck);
        this.describeGameOfPlayers = new DescribeGameOfPlayers(this, deck);
        listnerToAdd();
        //initTestCards();
    }

    public void writeCards() {
        describeGameOfPlayers.updatePlayerCards(cardsInHand);
    }

    private void listnerToAdd(){
        deck.addListner(describeGameOfPlayers);
        cardFromDeck.addListner(describeGameOfPlayers);
    }

    private void initTestCards() {
        cardsInHand.add(new Card("two", "spade"));
        cardsInHand.add(new Card("three", "spade"));
        cardsInHand.add(new Card("four", "spade"));
        cardsInHand.add(new Card("two", "diamond"));
        cardsInHand.add(new Card("three", "heart"));
        cardsInHand.add(new Card("four", "diamond"));
        cardsInHand.add(new Card("five", "spade"));
        cardsInHand.add(new Card("six", "spade"));
        cardsInHand.add(new Card("seven", "spade"));
        cardsInHand.add(new Card("queen", "spade"));
        cardsInHand.add(new Card("nine", "spade"));
        cardsInHand.add(new Card("ten", "spade"));
        cardsInHand.add(new Card("jack", "spade"));
        cardsInHand.add(new Card("king", "heart"));
        cardsInHand.add(new Card("king", "spade"));
//        cardsInHand.add(new Card("ace", "spade"));
    }

    @Override
    public void playRound() {
        writeCards();
        if (isSpecialCardInHand()) {
            if (isSpecialCard) {
                isSpecialCard = false;
                throwCardOnDeck(specialCardPosition);
//                System.out.println("Karta rzucona automatycznie na stół");
                describeGameOfPlayers.infoCardThrowedAutomatically();
            } else {
                describeGameOfPlayers.showMenu();
                if (isCardInRange(choosenCard)) {
                    if (isEqualToSuitOrName(choosenCard) || isQueenOnDeck(choosenCard)) {
                        throwCardOnDeck(choosenCard);

                    } else {
//                        System.out.println("Źle zagrana karta");
                        describeGameOfPlayers.infoWrongPlayedCard();
                    }
                } else {
//                    System.out.println("Karta poza zasięgiem");
                    describeGameOfPlayers.infoCardOutOfRange();
                }
            }
        } else {
//            System.out.println("Karta zagrana automatycznie");
            describeGameOfPlayers.infoCardThrowedAutomatically();
        }
        safeCardOnDeck(getCardOnDeck());
        if (noCardsInHand()) {
            gamePlay.setEndOfGame(true);
        }
    }


    public void setChoosenCard(int selectedNumber) {
        choosenCard = selectedNumber - 1;
    }

    private boolean isCardInRange(int i) {
        if (i >= 0 && i < cardsInHand.size()) {
            return true;
        }
        return false;
    }

    private boolean isEqualToSuitOrName(int i) {
        String cardPlayedName = getCardInHandName(i);
        String cardPlayedSuit = getCardInHandSuit(i);
        return (cardPlayedName.equals(getCardOnDeckName()) ||
                cardPlayedSuit.equals(getCardOnDeckSuit()));
    }

    private boolean isQueenOnDeck(int i) {
        String cardPlayedName = getCardInHandName(i);
        return cardPlayedName.equals(CardNames.queen.toString());
    }

    private boolean noCardsInHand() {
        return cardsInHand.size() == 0;
    }

    @Override
    protected void throwCardOnDeck(int i) {

        Card cardPlayed = cardsInHand.get(i);
        if (tryPlayCard(cardPlayed)) {
            cardsInHand.remove(i);
        } else {
            //System.out.println("Nie możesz zagrać tą kartą");
            describeGameOfPlayers.infoWrongPlayedCard();
        }

    }

    private boolean tryPlayCard(Card cardPlayed) {
        return deck.putCardOnDeck(cardPlayed);
    }


    @Override
    public void pullCardFromDeck() {
        if (tryToTakeCardFromDeck()) {
            Card pickCards = cardFromDeck.pickLastCardOnDeck();
            cardsInHand.add(pickCards);
        } else {
            //System.out.println("Koniec kart na stosie");
            describeGameOfPlayers.infoEndOfCardsOnDeck();
        }

    }

    private boolean tryToTakeCardFromDeck() {
        return cardFromDeck.IsCardOnDeck();
    }


}
