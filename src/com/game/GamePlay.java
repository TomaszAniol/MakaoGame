package com.game;


import com.player.Player;
import com.deck.Deck;
import com.deck.DeckOfCards;
import com.describeGame.DescribeGamePlay;
import com.player.HumanPlayer;
import com.player.NPCPlayer;

import java.util.ArrayList;
import java.util.List;

public class GamePlay {

    private DeckOfCards cardFromDeck;

    private List<Player> players;
    private Deck deck = new Deck();
    private boolean endOfGame;
    private DescribeGamePlay describeGamePlay;

    public GamePlay() {
        cardFromDeck = new DeckOfCards();
        //System.out.println("Karta na stole: " + deck.actualCardOnDeck().getCardSuit() + deck.actualCardOnDeck().getCardName());
       // System.out.println("Karta na stole: " + deck.actualCardOnDeck().getCardSuit() + deck.actualCardOnDeck().getCardName());
        createPlayers();
        describeGamePlay = new DescribeGamePlay();
    }

    private void createPlayers(){
        players = new ArrayList<>();
        players.add(new HumanPlayer(cardFromDeck, this, deck));
        for (int i = 0; i < 3; i++) {
            players.add(new NPCPlayer(cardFromDeck, this, deck));
        }
    }

    public void playRound() {
        while (!endOfGame){
            for (int i = 0; i < players.size(); i++){
                if (players.get(i) instanceof HumanPlayer){
                    describeGamePlay.humanPlayer();
                }
                else if(players.get(i) instanceof NPCPlayer){
                    describeGamePlay.npcPlayer(i);
                }
                players.get(i).playRound();
            }
        }
        describeGamePlay.gameOver();
    }

    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }

    public void setDemandCard(String demandCard){
        describeGamePlay.demandCard(demandCard);
        for (Player player : players){
            player.setDemandCard(demandCard);
        }
    }
}

