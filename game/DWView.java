/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/


import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class DWView extends JFrame {

    int numP; //number of players to initialize die

    //labels needed
    JLabel boardlabel;
    JLabel cardlabel;
    JLabel label;
    JLabel player1;
    JLabel player2;
    JLabel player3;
    JLabel player4;
    JLabel player5;
    JLabel player6;
    JLabel player7;
    JLabel player8;
    JTextArea stats;

    //statistics
    JLabel pName;
    JLabel location;
    JLabel rank;
    JLabel roleRank;
    JLabel cash;
    JLabel credits;
    JLabel rTokens;

    //back of scenecards for intitalization
    JLabel mainStBack = new JLabel();
    JLabel saloonBack = new JLabel();
    JLabel bankBack = new JLabel();
    JLabel hotelBack = new JLabel();
    JLabel churchBack = new JLabel();
    JLabel hideoutBack = new JLabel();
    JLabel ranchBack = new JLabel();
    JLabel trainStationBack = new JLabel();
    JLabel jailBack = new JLabel();
    JLabel storeBack = new JLabel();

    //front scene cards for flipping
    JLabel mainStFront = new JLabel();
    JLabel saloonFront = new JLabel();
    JLabel bankFront = new JLabel();
    JLabel hotelFront = new JLabel();
    JLabel churchFront = new JLabel();
    JLabel hideoutFront = new JLabel();
    JLabel ranchFront = new JLabel();
    JLabel trainStationFront = new JLabel();
    JLabel jailFront = new JLabel();
    JLabel storeFront = new JLabel();

    JLabel wrapped = new JLabel();


    // JLayered Pane
    JLayeredPane bPane;

    // Constructor
    public DWView() {
        // Set the title of the JFrame
        super("DEADWOOD 2.0");
        // Set the exit option for the JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the JLayeredPane to hold the display, cards, dice and buttons
        bPane = getLayeredPane();

        // Create the deadwood board
        boardlabel = new JLabel();
        ImageIcon icon =  new ImageIcon("media/board.jpg");
        boardlabel.setIcon(icon);
        boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

        // Add the board to the lowest layer
        bPane.add(boardlabel, new Integer(0));

        // Set the size of the GUI
        setSize(icon.getIconWidth()+200,icon.getIconHeight());
        // Create the Menu for action buttons
        stats = new JTextArea();
        stats.setBounds(icon.getIconWidth()+40, 10, 320, 180);
        stats.setEditable(false);
        // Border border = BorderFactory.createLineBorder(Color.BLACK);
        // stats.setBorder(BorderFactory.createCompundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        bPane.add(stats, new Integer(2));



    }

    // This class implements Mouse Events




    public int getNumP() {
        return this.numP;
    }

    private void addPDie() { //put the dice on the board
        player1 = new JLabel();
        ImageIcon pIcon1 = new ImageIcon("media/dice/A1.png");
        player1.setIcon(pIcon1);
        player1.setBounds(991,248,46,46);
        player1.setVisible(false);
        player1.setVisible(true);
        bPane.add(player1,new Integer(3));

        if(this.numP >= 2) {
            player2 = new JLabel();
            ImageIcon pIcon2 = new ImageIcon("media/dice/B1.png");
            player2.setIcon(pIcon2);
            player2.setBounds(1001,248,46,46);
            player2.setVisible(true);
            bPane.add(player2,new Integer(3));
        }

        if(this.numP >= 3) {
            player3 = new JLabel();
            ImageIcon pIcon3 = new ImageIcon("media/dice/C1.png");
            player3.setIcon(pIcon3);
            player3.setBounds(1011,248,46,46);
            player3.setVisible(true);
            bPane.add(player3,new Integer(3));

        }

        if(this.numP >= 4) {
            player4 = new JLabel();
            ImageIcon pIcon4 = new ImageIcon("media/dice/D1.png");
            player4.setIcon(pIcon4);
            player4.setBounds(1021,248,46,46);
            player4.setVisible(true);
            bPane.add(player4,new Integer(3));
        }

        if(this.numP >= 5) {
            player5 = new JLabel();
            ImageIcon pIcon5 = new ImageIcon("media/dice/E1.png");
            player5.setIcon(pIcon5);
            player5.setBounds(1031,248,46,46);
            player5.setVisible(true);
            bPane.add(player5,new Integer(3));
        }

        if(this.numP >= 6) {
            player6 = new JLabel();
            ImageIcon pIcon6 = new ImageIcon("media/dice/F1.png");
            player6.setIcon(pIcon6);
            player6.setBounds(1041,248,46,46);
            player6.setVisible(true);
            bPane.add(player6,new Integer(3));
        }

        if(this.numP >= 7) {
            player7 = new JLabel();
            ImageIcon pIcon7 = new ImageIcon("media/dice/G1.png");
            player7.setIcon(pIcon7);
            player7.setBounds(1051,248,46,46);
            player7.setVisible(true);
            bPane.add(player7,new Integer(3));
        }

        if(this.numP >= 8) {
            player8 = new JLabel();
            ImageIcon pIcon8 = new ImageIcon("media/dice/H1.png");
            player8.setIcon(pIcon8);
            player8.setBounds(1061,248,46,46);
            player8.setVisible(true);
            bPane.add(player8,new Integer(3));
        }

    }

    private void addCardBacks() { //add card backs for initialization
        ImageIcon mainStBIcon = new ImageIcon("media/cards/CardBack.jpg");
        mainStBack.setIcon(mainStBIcon);
        mainStBack.setBounds(969,28,205,115);
        mainStBack.setVisible(true);
        bPane.add(mainStBack,new Integer(1));

        ImageIcon saloonBIcon = new ImageIcon("media/cards/CardBack.jpg");
        saloonBack.setIcon(saloonBIcon);
        saloonBack.setBounds(632,280,205,115);
        saloonBack.setOpaque(true);
        bPane.add(saloonBack,new Integer(1));

        ImageIcon bankBIcon = new ImageIcon("media/cards/CardBack.jpg");
        bankBack.setIcon(bankBIcon);
        bankBack.setBounds(623,475,205,115);
        bankBack.setOpaque(true);
        bPane.add(bankBack,new Integer(1));

        ImageIcon hotelBIcon = new ImageIcon("media/cards/CardBack.jpg");
        hotelBack.setIcon(hotelBIcon);
        hotelBack.setBounds(969,740,205,115);
        hotelBack.setOpaque(true);;
        bPane.add(hotelBack,new Integer(1));

        ImageIcon churchBIcon = new ImageIcon("media/cards/CardBack.jpg");
        churchBack.setIcon(churchBIcon);
        churchBack.setBounds(623,734,205,115);
        churchBack.setOpaque(true);
        bPane.add(churchBack,new Integer(1));

        ImageIcon hideoutBIcon = new ImageIcon("media/cards/CardBack.jpg");
        hideoutBack.setIcon(hideoutBIcon);
        hideoutBack.setBounds(27,732,205,115);
        hideoutBack.setOpaque(true);
        bPane.add(hideoutBack,new Integer(1));

        ImageIcon ranchBIcon = new ImageIcon("media/cards/CardBack.jpg");
        ranchBack.setIcon(ranchBIcon);
        ranchBack.setBounds(252,478,205,115);
        ranchBack.setOpaque(true);
        bPane.add(ranchBack,new Integer(1));

        ImageIcon trainStationBIcon = new ImageIcon("media/cards/CardBack.jpg");
        trainStationBack.setIcon(trainStationBIcon);
        trainStationBack.setBounds(21,69,205,115);
        trainStationBack.setOpaque(true);
        bPane.add(trainStationBack,new Integer(1));

        ImageIcon jailBIcon = new ImageIcon("media/cards/CardBack.jpg");
        jailBack.setIcon(jailBIcon);
        jailBack.setBounds(281,27,205,115);
        jailBack.setOpaque(true);
        bPane.add(jailBack,new Integer(1));

        ImageIcon storeBIcon = new ImageIcon("media/cards/CardBack.jpg");
        storeBack.setIcon(storeBIcon);
        storeBack.setBounds(370,282,205,115);
        storeBack.setOpaque(true);
        bPane.add(storeBack,new Integer(1));
    }

    public void wrapUp(Player p) { //add card backs for initialization
        ImageIcon wrappedIcon = new ImageIcon("media/cards/CardBackWrapped.png");
        if(p.getCurrentLoc().getName().equals("Main Street")) {
            mainStBack.setIcon(wrappedIcon);
            mainStBack.setVisible(true);
            mainStFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Saloon")) {
        saloonBack.setIcon(wrappedIcon);
        saloonBack.setVisible(true);
        saloonFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Bank")) {
        bankBack.setIcon(wrappedIcon);
        bankBack.setVisible(true);
        bankFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Hotel")) {
        hotelBack.setIcon(wrappedIcon);
        hotelBack.setVisible(true);
        hotelFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Church")) {
        churchBack.setIcon(wrappedIcon);
        churchBack.setVisible(true);
        churchFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Secret Hideout")) {
        hideoutBack.setIcon(wrappedIcon);
        hideoutBack.setVisible(true);
        hideoutFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Secret Ranch")) {
        ranchBack.setIcon(wrappedIcon);
        ranchBack.setVisible(true);
        ranchFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Train Station")) {
        trainStationBack.setIcon(wrappedIcon);
        trainStationBack.setVisible(true);
        trainStationFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("Jail")) {
        jailBack.setIcon(wrappedIcon);
        jailBack.setVisible(true);
        jailFront.setVisible(false);
        }

        else if(p.getCurrentLoc().getName().equals("General Store")) {
        storeBack.setIcon(wrappedIcon);
        storeBack.setVisible(true);
        storeFront.setVisible(false);
        }
    }

    public void addSceneCards(int a, int b, int c, int d, int e,
    int f, int g, int h, int i, int j) { //add scene cards to board, and set them invisible
        String a1 = Integer.toString(a+1);
        String b1 = Integer.toString(b+1);
        String c1  = Integer.toString(c+1);
        String d1  = Integer.toString(d+1);
        String e1  = Integer.toString(e+1);
        String f1 = Integer.toString(f+1);
        String g1 = Integer.toString(g+1);
        String h1 = Integer.toString(h+1);
        String i1 = Integer.toString(i+1);
        String j1 = Integer.toString(j+1);



        ImageIcon mainStBIcon = new ImageIcon("media/cards/" + a1 + ".png");
        mainStFront.setIcon(mainStBIcon);
        mainStFront.setBounds(969,28,205,115);
        mainStFront.setVisible(false);
        bPane.add(mainStFront,new Integer(1));

        ImageIcon saloonBIcon = new ImageIcon("media/cards/" + b1 + ".png");
        saloonFront.setIcon(saloonBIcon);
        saloonFront.setBounds(632,280,205,115);;
        saloonFront.setOpaque(false);
        bPane.add(saloonFront,new Integer(1));

        ImageIcon bankBIcon = new ImageIcon("media/cards/" + c1 + ".png");
        bankFront.setIcon(bankBIcon);
        bankFront.setBounds(623,475,205,115);
        bankFront.setOpaque(false);
        bPane.add(bankFront,new Integer(1));

        ImageIcon hotelBIcon = new ImageIcon("media/cards/" + d1 + ".png");
        hotelFront.setIcon(hotelBIcon);
        hotelFront.setBounds(969,740,205,115);
        hotelFront.setOpaque(false);
        bPane.add(hotelFront,new Integer(1));

        ImageIcon churchBIcon = new ImageIcon("media/cards/" + e1 + ".png");
        churchFront.setIcon(churchBIcon);
        churchFront.setBounds(623,734,205,115);
        churchFront.setOpaque(false);
        bPane.add(churchFront,new Integer(1));

        ImageIcon hideoutBIcon = new ImageIcon("media/cards/" + f1 + ".png");
        hideoutFront.setIcon(hideoutBIcon);
        hideoutFront.setBounds(27,732,205,115);
        hideoutFront.setOpaque(false);
        bPane.add(hideoutFront,new Integer(1));

        ImageIcon ranchBIcon = new ImageIcon("media/cards/" + g1 + ".png");
        ranchFront.setIcon(ranchBIcon);
        ranchFront.setBounds(252,478,205,115);
        ranchFront.setOpaque(false);
        bPane.add(ranchFront,new Integer(1));

        ImageIcon trainStationBIcon = new ImageIcon("media/cards/" + h1 + ".png");
        trainStationFront.setIcon(trainStationBIcon);
        trainStationFront.setBounds(21,69,205,115);
        trainStationFront.setOpaque(false);
        bPane.add(trainStationFront,new Integer(1));

        ImageIcon jailBIcon = new ImageIcon("media/cards/" + i1 + ".png");
        jailFront.setIcon(jailBIcon);
        jailFront.setBounds(281,27,205,115);
        jailFront.setOpaque(false);
        bPane.add(jailFront,new Integer(1));

        ImageIcon storeBIcon = new ImageIcon("media/cards/" + j1 + ".png");
        storeFront.setIcon(storeBIcon);
        storeFront.setBounds(370,282,205,115);
        storeFront.setOpaque(false);
        bPane.add(storeFront,new Integer(1));
    }

    private void addShots() {

    }




    //starters
    public void dropNumP() { //display drop down menu at the beginning
        String[] choices = { "1", "2", "3", "4", "5", "6", "7", "8"}; // displays selection 2-8 players
        String input = (String) JOptionPane.showInputDialog(null,
        "How many players will be playing??\n" + "(Choose from 2-8): ",
        "Welcome to Deadwood, partner.", JOptionPane.QUESTION_MESSAGE, null,

        choices, // Array of choices
        choices[1]); // Initial choice
        if(input == null) {
            this.numP = -1;
        }
        else { //set input as the number of players
            this.numP = Integer.parseInt(input);
        }
    }

    public void setUpGUI() { //sets up the board, player dice, scene cards,
        if(this.numP == -1) {
            System.exit(0);
        }
        else {
            setVisible(true);
            addPDie();
            addCardBacks();

        }
    }

    public void displayStats(Player p) {
        stats.setText(p.displayStats());
    }

    public int primaryChoices(Player p) { //state machine
        displayStats(p);
        int choice = 0;
        boolean goodInput = false;
        if(p.getState() == 1) { //if in trailer
            while(!goodInput) {
                String[] choices = {"1.) Move into room"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner: \n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    goodInput = true;
                }
            }
            return choice;
        }
        else if(p.getState() == 2) { //if in casting office
            while(!goodInput) {
                String[] choices = {"1.) Move into room",
                "2.) Upgrade Rank"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner: \n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    goodInput = true;
                }
            }
            return choice;
        }
        else if(p.getState() == 3) { //if in an unwrapped scene

            while(!goodInput) {
                String[] choices = {"1.) Move into room",
                "2.) Take a role in your current room"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner. Press cancel to end turn.\n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    goodInput = true;
                }
            }
            return choice;
            //display new choices based on initial choice
            //make player choose again if choice leads to bad input
        }
        else if(p.getState() == 4) { //if in casting office
            while(!goodInput) {
                String[] choices = {"1.) Move into room and don't take a role"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner: \n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    goodInput = true;
                }
            }
            return choice;
            //display new choices based on initial choice
            //make player choose again if choice leads to bad input
        }
        else if(p.getState() == 5) { //if acting on a scene card role
            while(!goodInput) {
                String[] choices = {"1.) Act on your Main role",
                "2.) Rehearse"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner: \n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    if(choice == 2 && p.getRTokens() == 5) {
                        JOptionPane.showMessageDialog(null, "You can't rehearse anymore.",
                        "Wrong choice, buckaroo.",
                        JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        goodInput = true;
                    }
                }
            }
            return choice;
        }
        else { //if acting on an extra role
            while(!goodInput) {
                String[] choices = {"1.) Act Extra Role",
                "2.) Rehearse"};
                String input = (String) JOptionPane.showInputDialog(null,
                p.getName() + "'s turn. Make a choice, partner: \n",
                "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
                choices, // Array of choices
                choices[0]); // Initial choice
                if(input == null) {
                    choice = 9999;
                    goodInput = true;
                }
                else {
                    choice = Integer.parseInt(input.substring(0,1));
                    if(choice == 2 && p.getRTokens() == 5) {
                        JOptionPane.showMessageDialog(null, "You can't rehearse anymore.",
                        "Wrong choice, buckaroo.",
                        JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        goodInput = true;
                    }
                }
            }
            return choice;
        }
    }


    public Room move(Player p) { //move player dice into room
        int neighborNum = p.getCurrentLoc().getNeighbors().size();
        String[] choices = new String[neighborNum];

        //stores the current players neighboring rooms in choices[]
        for(int i = 0; i < neighborNum; i++) {
            choices[i] = p.getCurrentLoc().getNeighbors().get(i).getName();
        }

        //display the neighboring rooms
        String input = (String) JOptionPane.showInputDialog(null,
        p.getName() + ": Which room, partner??: \n",
        "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
        choices, // Array of choices
        choices[0]); // Initial choice

        if(p.getName().charAt(7) == 'A') { //player A
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player1.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX(),
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'B') { //player B
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player2.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+10,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'C') { //player C
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player3.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+20,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'D') { //player D
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player4.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+30,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'E') { //player E
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player5.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+40,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'F') {//player F
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player6.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+50,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else if(p.getName().charAt(7) == 'G') {//player G
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player7.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+60,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
        else { //player H
            int desiredRoomIndex = findRoomByName(input, p.getCurrentLoc().getNeighbors());
            player8.setBounds(p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getX()+70,
            p.getCurrentLoc().getNeighbors().get(desiredRoomIndex).getY(),46,46);
            return p.getCurrentLoc().getNeighbors().get(desiredRoomIndex);
        }
    }



    public void flip(Player p) { //flips scene cards when player enters a room. Call whenever a player moves into a room
        if(p.getCurrentLoc().getName().equals("Main Street")) {
            mainStBack.setVisible(false);
            mainStFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Saloon")) {
            saloonBack.setVisible(false);
            saloonFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Bank")) {
            bankBack.setVisible(false);
            bankFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Hotel")) {
            hotelBack.setVisible(false);
            hotelFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Church")) {
            churchBack.setVisible(false);
            churchFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Secret Hideout")) {
            hideoutBack.setVisible(false);
            hideoutFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Ranch")) {
            ranchBack.setVisible(false);
            ranchFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Train Station")) {
            trainStationBack.setVisible(false);
            trainStationFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("Jail")) {
            jailBack.setVisible(false);
            jailFront.setVisible(true);
        }
        else if(p.getCurrentLoc().getName().equals("General Store")) {
            storeBack.setVisible(false);
            storeFront.setVisible(true);
        }

    }

    public int askRoleType(Player p) { //ask player whether they want an extra role or a main role
        int choice = 0;
        String[] choices = {"1.) Main Role", "2.) Extra Role"};
        String input = (String) JOptionPane.showInputDialog(null,
        p.getName() + ": Take role? (Click Cancel if you don't want to take a role): \n",
        "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
        choices, // Array of choices
        choices[0]); // Initial choice
        if(input == null) {
            choice = 9999;
        }
        else {
            choice = Integer.parseInt(input.substring(0,1));
        }
        return choice;
    }

    public int selectMainRole(Player p) { //ask player which main role they want
        boolean goodInput = false;
        int choice = 0;
        while(!goodInput) { // gets the main roles of current players room and inserts them in choices[]
            int mRolesNum = p.getCurrentLoc().getSceneCard().getMainRoles().size(); //gets number of main roles in curr players room
            String[] choices = new String[mRolesNum]; //creates an array of choices for the player to select from
            for(int i = 0; i < mRolesNum; i++) {
                choices[i] = p.getCurrentLoc().getSceneCard().getMainRoles().get(i).getName(); //insert in choices[]
            }
            String input = (String) JOptionPane.showInputDialog(null,
            p.getName() + ": Which role, partner?? (Press Cancel to cancel choosing): \n",
            "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
            choices, // Array of choices
            choices[0]); // Initial choice

            if(input == null) {
                JOptionPane.showMessageDialog(null, "You cancelled choosing",
                "",
                JOptionPane.WARNING_MESSAGE);
                return 9999;
            }
            else if(p.getRank() < p.getCurrentLoc().getSceneCard().getMainRoles().get(findRoleByName(input, p.getCurrentLoc().getSceneCard().getMainRoles())).getRoleRank()) {
                JOptionPane.showMessageDialog(null, "Get your rank up, partner.",
                "",
                JOptionPane.WARNING_MESSAGE);
            }
            else {
                choice = findRoleByName(input, p.getCurrentLoc().getSceneCard().getMainRoles());
                JOptionPane.showMessageDialog(null, "You took a role.",
                "",
                JOptionPane.WARNING_MESSAGE);
                goodInput = true;
            }
        }


        if(p.getName().charAt(7) == 'A') {
            player1.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'B') {
            player2.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'C') {
            player3.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'D') {
            player4.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'E') {
            player5.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'F') {
            player6.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'G') {
            player7.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
        else {
            player8.setBounds(p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getX()+p.getCurrentLoc().getSceneX(),
            p.getCurrentLoc().getSceneCard().getMainRoles().get(choice).getY()+p.getCurrentLoc().getSceneY(),46,46);
            return choice;
        }
    }

    public int selectExtraRole(Player p) { //ask player which extra role they want
        boolean goodInput = false;
        int choice = 0;
        while(!goodInput) {
            int eRolesNum = p.getCurrentLoc().getOffCard().getExtraRoles().size();
            // int index =
            String[] choices = new String[eRolesNum];
            for(int i = 0; i < eRolesNum; i++) {
                choices[i] = p.getCurrentLoc().getOffCard().getExtraRoles().get(i).getName();
            }
            String input = (String) JOptionPane.showInputDialog(null,
            p.getName() + ": Which role, partner?? (Press Cancel to cancel choosing): \n",
            "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
            choices, // Array of choices
            choices[0]); // Initial choice

            if(input == null) {
                JOptionPane.showMessageDialog(null, "You cancelled choosing",
                "",
                JOptionPane.WARNING_MESSAGE);
                return 9999;
            }
            else if(p.getRank() < p.getCurrentLoc().getOffCard().getExtraRoles().get(findRoleByName(input, p.getCurrentLoc().getOffCard().getExtraRoles())).getRoleRank()) {
                JOptionPane.showMessageDialog(null, "Get your rank up, partner.",
                "",
                JOptionPane.WARNING_MESSAGE);
            }
            else {
                choice = findRoleByName(input, p.getCurrentLoc().getOffCard().getExtraRoles());
                goodInput = true;
            }
        }



        if(p.getName().charAt(7) == 'A') {
            player1.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'B') {
            player2.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'C') {
            player3.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'D') {
            player4.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'E') {
            player5.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'F') {
            player6.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else if(p.getName().charAt(7) == 'G') {
            player7.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
        else {
            player8.setBounds(p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getX(),
            p.getCurrentLoc().getOffCard().getExtraRoles().get(choice).getY(),46,46);
            return choice;
        }
    }

    public int chooseUpgrade(Player p) { //ask player what rank they want
        boolean goodInput = false;
        int choice = 0;
        while(!goodInput) {
            String[] choices = {"2", "3", "4", "5", "6"};
            String input = (String) JOptionPane.showInputDialog(null,
            p.getName() + ": Which rank would you like, buckaroo?? (Click Cancel to cancel choosing): \n",
            "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
            choices, // Array of choices
            choices[0]); // Initial choice

            if(input == null) {
                JOptionPane.showMessageDialog(null, "You cancelled choosing",
                "",
                JOptionPane.WARNING_MESSAGE);
                return 9999;
            }
            else {
                choice = Integer.parseInt(input);
                if(!canPayCash(p.getRank(), choice, p.getDollars()) &&
                !canPayCredits(p.getRank(), choice, p.getCredits())) {
                    JOptionPane.showMessageDialog(null, "Choose another rank, buckaroo.",
                    "",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(canPayCash(p.getRank(), choice, p.getDollars()) ||
                canPayCredits(p.getRank(), choice, p.getCredits())) {
                    dollarsOrCredits(p, choice);
                    goodInput = true;
                }
            }

        }
        return choice;
    }

    public int dollarsOrCredits(Player p, int desiredRank) { //asks player for payment method
        boolean goodInput = false;
        int choice = 0;
        while(!goodInput) {
            String[] choices = {"1.) Dollars", "2.) Credits"};
            String input = (String) JOptionPane.showInputDialog(null,
            p.getName() + ": Would you like to pay with Dollars or Credits?? (Click Cancel to cancel choosing): \n",
            "It's yeehaw time.", JOptionPane.QUESTION_MESSAGE, null,
            choices, // Array of choices
            choices[0]); // Initial choice

            if(input == null) {
                JOptionPane.showMessageDialog(null, "You cancelled choosing",
                "",
                JOptionPane.WARNING_MESSAGE);
                return 9999;
            }
            else {
                choice = Integer.parseInt(input);
                if(choice == 1 &&  !canPayCash(p.getRank(), desiredRank, p.getDollars())) {
                    JOptionPane.showMessageDialog(null, "Choose another payment method, buckaroo.",
                    "",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(choice == 2 &&  !canPayCredits(p.getRank(), desiredRank, p.getCredits())) {
                    JOptionPane.showMessageDialog(null, "Choose another payment method, buckaroo.",
                    "",
                    JOptionPane.WARNING_MESSAGE);
                }
                else if(choice == 1 && canPayCash(p.getRank(), desiredRank, p.getDollars())){

                    return 1;
                }
                else if(choice == 2 && canPayCredits(p.getRank(), desiredRank, p.getCredits())) {
                    return 2;
                }
            }
        }
        return -1;
    }


    private boolean canPayCash(int rank, int newRank, int dollars) { //checks if player has enough dollars
        if(newRank < rank) {
            JOptionPane.showMessageDialog(null, "You can't downgrade a rank partner!",
            "",
            JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(newRank == 2 && (dollars >= 4)) {
            return true;
        }
        else if(newRank == 3 && (dollars >= 10)) {
            return true;
        }
        else if(newRank == 4 && (dollars >= 18)) {
            return true;
        }
        else if(newRank == 5 && (dollars >= 28)) {
            return true;
        }
        else if(newRank == 6 && (dollars >= 40)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean canPayCredits(int rank, int newRank, int credits) { //checks if player has enough credits
        if(newRank < rank) {
            JOptionPane.showMessageDialog(null, "You can't downgrade a rank partner!",
            "",
            JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(newRank == 2 && (credits >= 5)) {
            return true;
        }
        else if(newRank == 3 && (credits >= 10)) {
            return true;
        }
        else if(newRank == 4 && (credits >= 15)) {
            return true;
        }
        else if(newRank == 5 && (credits >= 20)) {
            return true;
        }
        else if(newRank == 6 && (credits >= 25)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int updateWithCash(Player p, int desiredRank) {
        if(p.getName().charAt(7) == 'A') {
            ImageIcon pIcon1 = new ImageIcon("media/dice/A" + Integer.toString(desiredRank) + ".png");
            player1.setIcon(pIcon1);
        }
        else if(p.getName().charAt(7) == 'B') {
            ImageIcon pIcon2 = new ImageIcon("media/dice/B" + Integer.toString(desiredRank) + ".png");
            player2.setIcon(pIcon2);
        }
        else if(p.getName().charAt(7) == 'C') {
            ImageIcon pIcon3 = new ImageIcon("media/dice/C" + Integer.toString(desiredRank) + ".png");
            player3.setIcon(pIcon3);
        }
        else if(p.getName().charAt(7) == 'D') {
            ImageIcon pIcon4 = new ImageIcon("media/dice/D" + Integer.toString(desiredRank) + ".png");
            player4.setIcon(pIcon4);
        }
        else if(p.getName().charAt(7) == 'E') {
            ImageIcon pIcon5 = new ImageIcon("media/dice/E" + Integer.toString(desiredRank) + ".png");
            player5.setIcon(pIcon5);
        }
        else if(p.getName().charAt(7) == 'F') {
            ImageIcon pIcon6 = new ImageIcon("media/dice/F" + Integer.toString(desiredRank) + ".png");
            player6.setIcon(pIcon6);
        }
        else if(p.getName().charAt(7) == 'G') {
            ImageIcon pIcon7 = new ImageIcon("media/dice/G" + Integer.toString(desiredRank) + ".png");
            player7.setIcon(pIcon7);
        }
        else if(p.getName().charAt(7) == 'H') {
            ImageIcon pIcon8 = new ImageIcon("media/dice/H" + Integer.toString(desiredRank) + ".png");
            player8.setIcon(pIcon8);
        }
        if(desiredRank == 2) {
            return 4;
        }
        else if(desiredRank == 3) {
            return 10;
        }
        else if(desiredRank == 4) {
            return 18;
        }
        else if(desiredRank == 5) {
            return 28;
        }
        else if(desiredRank == 6) {
            return 40;
        }
        return -1;
    }

    public int updateWithCredits(Player p, int desiredRank) {
        if(p.getName().charAt(7) == 'A') {
            ImageIcon pIcon1 = new ImageIcon("media/dice/A" + Integer.toString(desiredRank) + ".png");
            player1.setIcon(pIcon1);
        }
        else if(p.getName().charAt(7) == 'B') {
            ImageIcon pIcon2 = new ImageIcon("media/dice/B" + Integer.toString(desiredRank) + ".png");
            player2.setIcon(pIcon2);
        }
        else if(p.getName().charAt(7) == 'C') {
            ImageIcon pIcon3 = new ImageIcon("media/dice/C" + Integer.toString(desiredRank) + ".png");
            player3.setIcon(pIcon3);
        }
        else if(p.getName().charAt(7) == 'D') {
            ImageIcon pIcon4 = new ImageIcon("media/dice/D" + Integer.toString(desiredRank) + ".png");
            player4.setIcon(pIcon4);
        }
        else if(p.getName().charAt(7) == 'E') {
            ImageIcon pIcon5 = new ImageIcon("media/dice/E" + Integer.toString(desiredRank) + ".png");
            player5.setIcon(pIcon5);
        }
        else if(p.getName().charAt(7) == 'F') {
            ImageIcon pIcon6 = new ImageIcon("media/dice/F" + Integer.toString(desiredRank) + ".png");
            player6.setIcon(pIcon6);
        }
        else if(p.getName().charAt(7) == 'G') {
            ImageIcon pIcon7 = new ImageIcon("media/dice/G" + Integer.toString(desiredRank) + ".png");
            player7.setIcon(pIcon7);
        }
        else if(p.getName().charAt(7) == 'H') {
            ImageIcon pIcon8 = new ImageIcon("media/dice/H" + Integer.toString(desiredRank) + ".png");
            player8.setIcon(pIcon8);
        }
        if(desiredRank == 2) {
            return 5;
        }
        else if(desiredRank == 3) {
            return 10;
        }
        else if(desiredRank == 4) {
            return 15;
        }
        else if(desiredRank == 5) {
            return 20;
        }
        else if(desiredRank == 6) {
            return 25;
        }
        return -1;
    }

    public void actMainRolePass(int roll) {
        JOptionPane.showMessageDialog(null, "YEEHAW PARTNER! You rolled a " + roll +
        ", which is higher than the budget of your scene card.\n" +
        "Now adding 2 credits to your name...",
        "",
        JOptionPane.PLAIN_MESSAGE);
    }

    public void actMainRoleFail(int roll) {
        JOptionPane.showMessageDialog(null, ":( You rolled a " + roll +
        ", which is less than the budget of your scene card.\n" +
        "You'll get 'em next time, partner",
        "",
        JOptionPane.PLAIN_MESSAGE);
    }

    public void actExtraRolePass(int roll) {
        JOptionPane.showMessageDialog(null, "YEEHAW PARTNER! You rolled a " + roll +
        ", which is higher than the budget of your scene card.\n" +
        "Now adding a dollar and a credit to your name...",
        "",
        JOptionPane.PLAIN_MESSAGE);
    }

    public void actExtraRoleFail(int roll) {
        JOptionPane.showMessageDialog(null, ":( You rolled a " + roll +
        ", which is less than the budget of your scene card.\n" +
        "You still get a dollar though!",
        "",
        JOptionPane.PLAIN_MESSAGE);
    }

    public void rehearse() {
        JOptionPane.showMessageDialog(null, "You get a rehearsal token",
        "",
        JOptionPane.PLAIN_MESSAGE);
    }

//finder methods
    private int findRoomByName(String rName, ArrayList<Room> neighbors) {// searches through given arraylist of neighbors and finds index of given room name
        for(int i = 0; i < neighbors.size(); i++) {
            if(rName.equals(neighbors.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    private int findRoleByName(String rName, ArrayList<Role> roles) {//searches through given arraylist of roles and finds index of given role name
        for(int i = 0; i < roles.size(); i++) {
            if(rName.equals(roles.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }
}
