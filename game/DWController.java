/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/

import java.util.*;
import org.w3c.dom.Document;


//Main Deadwood file: will run all a=other classes
public class DWController {
    private DWView view;
    private Board game;
    // private Player[] pList;

    DWController(DWView view) {
        this.view  = view;
        // this.game = game;

    }


    public void initGame() { //previously the main method
        int numDays = 0;             //of Deadwood.java
        int initCredits = 0;
        int initRank = 0;
        int numP = this.view.getNumP();
        //set up for different group sizes
        numDays = setDays(numP);

        initCredits = setCredits(numP);
        initRank = setRank(numP);

        //set up the game
        this.game = new Board(numP, 10, numDays, this.view);
        Document doc = null; //get the scene cards
        try {
            doc = game.getDocFromFile("cards.xml");
            game.makeCardPile(doc);
        }
        catch(Exception e) {
            System.out.println("Main role Error = "+ e);
        }
        doc = null; //get the extras
        try {
            doc = game.getDocFromFile("board.xml");
            game.getExtras(doc);
        }
        catch(Exception e) {
            System.out.println("Extra Role Error = "+ e);
        }

        game.shuffleScenes();
        game.initExtras();


        //get the players and play the game
        Player[] pList = makePList(game, numP, initRank, initCredits);
        game.playGame(pList);
    }

    //set up functions start here
    private int setDays(int numP) {
        if(numP == 2 || numP == 3) {
            return 3;
        }
        else if(numP == 1) {
            return 1;
        }
        else {
            return 4;
        }
    }

    private int setCredits(int numP) {
        if(numP == 5) {
            return 2;
        }
        else if(numP == 6) {
            return 4;
        }
        else {
            return 0;
        }
    }

    private int setRank(int numP) {
        if(numP == 7 || numP == 8) {
            return 2;
        }
        else {
            return 1;
        }
    }

    private static Player[] makePList(Board game, int numP, int initRank, int initCredits) {
        Player[] pList = new Player[numP];

        Player player1 = new Player("Player A", initRank, game.trailer, 0, 0, 0, 0, 1);
        pList[0] = player1;
        if(numP >= 2) {
            Player player2 = new Player("Player B", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[1] = player2;
        }
        if(numP >= 3) {
            Player player3 = new Player("Player C", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[2] = player3;
        }
        if(numP >= 4) {
            Player player4 = new Player("Player D", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[3] = player4;
        }
        if(numP >= 5) {
            Player player5 = new Player("Player E", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[4] = player5;
        }
        if(numP >= 6) {
            Player player6 = new Player("Player F", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[5] = player6;
        }
        if(numP >= 7) {
            Player player7 = new Player("Player G", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[6] = player7;
        }
        if(numP == 8) {
            Player player8 = new Player("Player H", initRank, game.trailer, 0, initCredits, 0, 0, 1);
            pList[7] = player8;
        }
        return pList;
    }

}
