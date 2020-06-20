/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/

import java.util.*;
import org.w3c.dom.Document;


//Main Deadwood file: will run all other classes
public class Deadwood {
    public static void main(String[] args) {
        int numDays = 0;
        int initCredits = 0;
        int initRank = 0;
        System.out.println("Welcome to DEADWOOD, partner.");
        System.out.print("How many players will be playing?? (Choose from 2-8): ");
        int numP = getNumP();

        //set up for different group sizes
        numDays = setDays(numP);
        initCredits = setCredits(numP);
        initRank = setRank(numP);

        //set up the game
        Board game = new Board(numP, 10, numDays);
        Document doc = null; //get the scene cards
        try {
            doc = game.getDocFromFile("cards.xml");
            game.makeCardPile(doc);
        }
        catch(Exception e) {
            System.out.println("Error = "+ e);
        }
        doc = null; //get the extras
        try {
            doc = game.getDocFromFile("board.xml");
            game.getExtras(doc);
        }
        catch(Exception e) {
            System.out.println("Error = "+ e);
        }
        game.initExtras();
        game.shuffleScenes();

        //get the players and play the game
        Player[] pList = makePList(game, numP, initRank, initCredits);
        game.playGame(pList);
    }

    //gets the number of players
    public static int getNumP() {
        Scanner reader = new Scanner(System.in);
        int numP = 0;
        boolean goodNum = false;
        while(!goodNum) {
            if(!reader.hasNextInt()) {
                System.out.print("That ain't a number, buckaroo. (Choose from 2-8): ");
                reader.nextLine();
            }
            else {
                numP = reader.nextInt();
                if(numP < 1 || numP > 8) {
                    System.out.print("You chose the wrong number, buckaroo. (Choose from 2-8): ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        return numP;
    }

    //set up functions start here
    public static int setDays(int numP) {
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

    public static int setCredits(int numP) {
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

    public static int setRank(int numP) {
        if(numP == 7 || numP == 8) {
            return 2;
        }
        else {
            return 1;
        }
    }

    public static Player[] makePList(Board game, int numP, int initRank, int initCredits) {
        Player[] pList = new Player[numP];

        Player player1 = new Player("Player A", initRank, game.trailer, 0, 1000, 1000, 0);
        pList[0] = player1;
        if(numP >= 2) {
            Player player2 = new Player("Player B", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[1] = player2;
        }
        if(numP >= 3) {
            Player player3 = new Player("Player C", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[2] = player3;
        }
        if(numP >= 4) {
            Player player4 = new Player("Player D", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[3] = player4;
        }
        if(numP >= 5) {
            Player player5 = new Player("Player E", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[4] = player5;
        }
        if(numP >= 6) {
            Player player6 = new Player("Player F", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[5] = player6;
        }
        if(numP >= 7) {
            Player player7 = new Player("Player G", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[6] = player7;
        }
        if(numP == 8) {
            Player player8 = new Player("Player H", initRank, game.trailer, 0, initCredits, 0, 0);
            pList[7] = player8;
        }
        return pList;
    }

}
