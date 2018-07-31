package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        GamePlay gp = new GamePlay();
        gp.playRound();

        Random random = new Random();
        int wynik1 = random.nextInt(12) + 1;
        int wynik2 = random.nextInt(12) + 1;
        int wynik3 = random.nextInt(12) + 1;

        List<Integer> calkowityWynik = new ArrayList<>();
        calkowityWynik.add(wynik1);
        calkowityWynik.add(wynik2);
        calkowityWynik.add(wynik3);
        int losuj = random.nextInt(2);
        int wynik4 = calkowityWynik.get(losuj);
        System.out.println(wynik1);
        System.out.println(wynik2);
        System.out.println(wynik3);
        System.out.println("************");
        System.out.println(wynik4);
    }
}
