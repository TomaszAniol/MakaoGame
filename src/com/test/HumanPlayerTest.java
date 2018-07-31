package com.test;

import com.card.Card;
import com.deck.Deck;
import com.deck.DeckOfCards;
import com.describeGame.DescribeGameOfPlayers;
import com.game.GamePlay;
import com.player.HumanPlayer;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;
    private DescribeGameOfPlayers describeGameOfPlayers;

    @org.junit.Before
    public void setup(){
        DeckOfCards deckOfCards = new DeckOfCards();
        GamePlay gamePlay = new GamePlay();
        Deck deck = new Deck();
        humanPlayer = new HumanPlayer(deckOfCards, gamePlay, deck);
        describeGameOfPlayers = new DescribeGameOfPlayers(humanPlayer, deck);
    }

    @org.junit.Test
    public void writeCards() {

    }

    @org.junit.Test
    public void playRound() {
    }

    @org.junit.Test
    public void setChoosenCard() {
    }

    @org.junit.Test
    public void throwCardOnDeck() {
    }

    @org.junit.Test
    public void pullCardFromDeck() {
    }
}