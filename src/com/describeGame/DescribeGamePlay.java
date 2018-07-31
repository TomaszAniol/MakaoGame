package com.describeGame;

public class DescribeGamePlay {

    public void humanPlayer(){
        System.out.println("****HUMAN***");
    }

    public void npcPlayer(int i){
        System.out.println("**NPC " + i + "**");
    }

    public void gameOver(){
        System.out.println("***************");
        System.out.println("***Game Over***");
    }

    public void demandCard(String demandCard){
        System.out.println("Żądam: " + demandCard);
    }
}
