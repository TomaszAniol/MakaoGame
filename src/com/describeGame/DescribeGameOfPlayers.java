package com.describeGame;

import com.card.Card;
import com.player.HumanPlayer;
import com.deck.Deck;
import com.deck.DeckListner;
import com.deck.PullCardListner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DescribeGameOfPlayers implements DeckListner, PullCardListner {
    private Scanner in = new Scanner(System.in);
    private HumanPlayer humanPlayer;
    private Deck deck;
    private List<Card> playerCards;
    private List<CardListWithID> playerCardsWithID;
    private List<CardListWithID> playersSortedCardsID;
    private boolean cardThrowedAutomatically;
    private boolean wrongPlayedCard;
    private boolean cardOutOfRange;
    private boolean endOfCardsOnDeck;


    public DescribeGameOfPlayers(HumanPlayer player, Deck deck){
        this.humanPlayer = player;
        this.deck = deck;
        playerCards = new ArrayList<>();
    }

    public void describeAfterAction(){
        if (cardThrowedAutomatically){
            System.out.println("Karta rzucona automatycznie na stół");
            cardThrowedAutomatically = false;
        }
        else if (wrongPlayedCard){
            System.out.println("Źle zagrana karta");
            wrongPlayedCard = false;
        }
        else if (cardOutOfRange){
            System.out.println("Karta poza zasięgiem");
            cardOutOfRange = false;
        }
        else if(endOfCardsOnDeck){
            System.out.println("Nie możesz zagrać tą kartą");
            endOfCardsOnDeck = false;
        }
    }

    public void infoCardThrowedAutomatically(){
        cardThrowedAutomatically = true;
    }

    public void infoWrongPlayedCard(){
        wrongPlayedCard = true;
    }

    public void infoCardOutOfRange(){
        cardOutOfRange = true;
    }

    public void infoEndOfCardsOnDeck() {endOfCardsOnDeck = true;}


    public void showMenu(){
        createListWithID();
        writeCards();
            System.out.println("Karta na stole: " + deck.actualCardOnDeck().getCardSuit() + " " + deck.actualCardOnDeck().getCardName());
            System.out.println("Wybierz co chcesz zrobić:");
            System.out.println("1 - Rzuć kartę na stół, 2 - Pobierz kartę ze stosu, 0 - Koniec");
            int choosen = in.nextInt();
            switch (choosen) {
                case 0:
                    //quite = true;
                    break;
                case 1:
                    System.out.println("Którą kartę rzucić");
                    int choosenCard = in.nextInt();
                    System.out.println("Wybrana karta menu: " + choosenCard);
                    humanPlayer.setChoosenCard(playersSortedCardsID.get(choosenCard - 1).id);
                    break;
                case 2:
                    System.out.println("Karta zabrana");
                    humanPlayer.pullCardFromDeck();
                    break;
            }
            describeAfterAction();

    }
    private void writeCards(){
        AtomicInteger atomicInteger = new AtomicInteger(1);
        playersSortedCardsID = new ArrayList<>();
        playersSortedCardsID = playerCardsWithID.stream()
                .sorted(Comparator.comparing((CardListWithID card) -> card.suit).thenComparing(card -> card.name))
                .collect(Collectors.toList());


        playersSortedCardsID.stream()
                .forEach(card -> {
                    System.out.print(atomicInteger.getAndIncrement());
                      System.out.println(" " + card.suit + " " + card.name);});
    }



    public void updatePlayerCards(List<Card> playerCards){
        this.playerCards = new ArrayList<>();
        this.playerCards.addAll(playerCards);
    }

    @Override
    public void cardThrown(){
        printCardThrown();
    }

    private void printCardThrown(){
        System.out.println("Rzucona karta: " + deck.actualCardOnDeck().getCardName() + " " + deck.actualCardOnDeck().getCardSuit());
    }

    @Override
    public void cardPulled(){
        printCardPulled();
    }

    private void printCardPulled(){
        System.out.println("Karta wzięta ze stosu");
    }

    private void createListWithID(){
        int id = 1;
        playerCardsWithID = new ArrayList<>();
        for(Card playerCard : playerCards){
            String cardName = playerCard.getCardName().toString();
            String cardSuit = playerCard.getCardSuit().toString();
            playerCardsWithID.add(new CardListWithID(cardName, cardSuit, id));
            id++;
        }
    }

    private class CardListWithID{
        private String name;
        private String suit;
        private int id;

        private CardListWithID(String name, String suit, int id){
            this.name = name;
            this.suit = suit;
            this.id = id;
        }
    }
}
