/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/

import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class Board {
    private DWView view;
    private int numPlayers;
    private int numScenes;
    protected int numDays;
    protected ArrayList<SceneCard> sceneCardList = new ArrayList<SceneCard>();
    protected ArrayList<OffCard> offCardList = new ArrayList<OffCard>();

    //initialize neighbors here
    public ArrayList<Room> nCasting = new ArrayList<Room>();
    public ArrayList<Room> nTrailer = new ArrayList<Room>();
    public ArrayList<Room> nMain = new ArrayList<Room>();
    public ArrayList<Room> nSaloon = new ArrayList<Room>();
    public ArrayList<Room> nBank = new ArrayList<Room>();
    public ArrayList<Room> nHotel = new ArrayList<Room>();
    public ArrayList<Room> nChurch = new ArrayList<Room>();
    public ArrayList<Room> nHideout = new ArrayList<Room>();
    public ArrayList<Room> nRanch = new ArrayList<Room>();
    public ArrayList<Room> nTStation = new ArrayList<Room>();
    public ArrayList<Room> nJail = new ArrayList<Room>();
    public ArrayList<Room> nStore = new ArrayList<Room>();

    //initial neighbor arrayList for each room
    public ArrayList<Room> temp;
    public int[] tempShotX;
    public int[] tempShotY;
    //non-set rooms: these rooms won't have scenes nor shot counters in them
    public Room casting;
    public Room trailer;

    //set rooms: these rooms do have scenes and shot counters in them.
    public Room mainSt;
    public Room saloon;
    public Room bank;
    public Room hotel;
    public Room church;
    public Room hideout;
    public Room ranch;
    public Room trainStation;
    public Room jail;
    public Room store;

    //board constructor
    Board(int numPlayers, int numScenes, int numDays, DWView view) {
        this.numPlayers = numPlayers;
        this.numScenes = numScenes;
        this.numDays = numDays;
        this.view = view;

        //non-set rooms: these rooms won't have scenes nor shot counters in them
        this.casting = new Room("Casting", 0, temp, 10, 500, 0, 0, tempShotX, tempShotY);
        this.trailer = new Room("Trailer", 0, temp, 991, 248, 0, 0, tempShotX, tempShotY);

        //set rooms: these rooms do have scenes and shot counters in them.
        this.mainSt = new Room("Main Street", 3, temp, 980, 160, 969, 28, tempShotX, tempShotY);
        this.saloon = new Room("Saloon", 2, temp, 620, 400, 632, 280, tempShotX, tempShotY);
        this.bank = new Room("Bank", 1, temp, 620, 590, 623, 475, tempShotX, tempShotY);
        this.hotel = new Room("Hotel", 3, temp, 980, 850, 969, 740, tempShotX, tempShotY);
        this.church = new Room("Church", 2, temp, 620, 850,623, 734, tempShotX, tempShotY);
        this.hideout = new Room("Secret Hideout", 3, temp, 250, 840, 27, 732, tempShotX, tempShotY);
        this.ranch = new Room("Ranch", 2, temp, 250, 620, 205, 115, tempShotX, tempShotY);
        this.trainStation = new Room("Train Station", 3, temp, 30, 180, 21, 69, tempShotX, tempShotY);
        this.jail = new Room("Jail", 1, temp, 280, 160, 281, 27, tempShotX, tempShotY);
        this.store = new Room("General Store", 1, temp, 350, 400, 370, 282, tempShotX, tempShotY);

        //make neighbor arraylists
        List<Room> nCastingL = Arrays.asList(hideout, ranch, trainStation);
        List<Room> nTrailerL = Arrays.asList(mainSt, saloon, hotel);
        List<Room> nMainL = Arrays.asList(trailer, saloon, jail);
        List<Room> nSaloonL = Arrays.asList(bank, store, mainSt, trailer);
        List<Room> nBankL = Arrays.asList(church, ranch, saloon, hotel);
        List<Room> nHotelL = Arrays.asList(trailer, bank, church);
        List<Room> nChurchL = Arrays.asList(hotel, bank, hideout);
        List<Room> nHideoutL = Arrays.asList(church, casting, ranch);
        List<Room> nRanchL = Arrays.asList(hideout, casting, store, bank);
        List<Room> nTStationL = Arrays.asList(casting, store, jail);
        List<Room> nJailL = Arrays.asList(trainStation, store, mainSt);
        List<Room> nStoreL = Arrays.asList(ranch, trainStation, jail, saloon);
        this.nCasting.addAll(nCastingL);
        this.nTrailer.addAll(nTrailerL);
        this.nMain.addAll(nMainL);
        this.nSaloon.addAll(nSaloonL);
        this.nBank.addAll(nBankL);
        this.nHotel.addAll(nHotelL);
        this.nChurch.addAll(nChurchL);
        this.nHideout.addAll(nHideoutL);
        this.nRanch.addAll(nRanchL);
        this.nTStation.addAll(nTStationL);
        this.nJail.addAll(nJailL);
        this.nStore.addAll(nStoreL);

        //replace temp with actual neighbors
        this.casting.updateNeighbors(nCasting);
        this.trailer.updateNeighbors(nTrailer);
        this.mainSt.updateNeighbors(nMain);
        this.saloon.updateNeighbors(nSaloon);
        this.bank.updateNeighbors(nBank);
        this.hotel.updateNeighbors(nHotel);
        this.church.updateNeighbors(nChurch);
        this.hideout.updateNeighbors(nHideout);
        this.ranch.updateNeighbors(nRanch);
        this.trainStation.updateNeighbors(nTStation);
        this.jail.updateNeighbors(nJail);
        this.store.updateNeighbors(nStore);
    }

    public void shuffleScenes() { //shuffles scenes
        Random rn = new Random();
        int[] numbers = new int[10];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = rn.nextInt(39);
        }
        this.mainSt.assignSceneCard(sceneCardList.get(numbers[0]));
        this.saloon.assignSceneCard(sceneCardList.get(numbers[1]));
        this.bank.assignSceneCard(sceneCardList.get(numbers[2]));
        this.hotel.assignSceneCard(sceneCardList.get(numbers[3]));
        this.church.assignSceneCard(sceneCardList.get(numbers[4]));
        this.hideout.assignSceneCard(sceneCardList.get(numbers[5]));
        this.ranch.assignSceneCard(sceneCardList.get(numbers[6]));
        this.trainStation.assignSceneCard(sceneCardList.get(numbers[7]));
        this.jail.assignSceneCard(sceneCardList.get(numbers[8]));
        this.store.assignSceneCard(sceneCardList.get(numbers[9]));

        this.view.addSceneCards(numbers[0], numbers[1], numbers[2], numbers[3],
        numbers[4], numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
    }


    public void initExtras() {
        this.trainStation.assignOffCards(this.offCardList.get(0));
        this.hideout.assignOffCards(this.offCardList.get(1));
        this.church.assignOffCards(this.offCardList.get(2));
        this.hotel.assignOffCards(this.offCardList.get(3));
        this.mainSt.assignOffCards(this.offCardList.get(4));
        this.jail.assignOffCards(this.offCardList.get(5));
        this.store.assignOffCards(this.offCardList.get(6));
        this.ranch.assignOffCards(this.offCardList.get(7));
        this.bank.assignOffCards(this.offCardList.get(8));
        this.saloon.assignOffCards(this.offCardList.get(9));
    }

    //reads the xml file
    public Document getDocFromFile(String filename)
        throws ParserConfigurationException{
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = null;

            try {
                doc = db.parse(filename);
            }
            catch (Exception ex){
                System.out.println("XML parse failure");
                ex.printStackTrace();
            }
            return doc;
        } // exception handling
    }

    //gets scene cards and roles
    public void makeCardPile(Document d) {
        Element root = d.getDocumentElement();
        NodeList cardList = root.getElementsByTagName("card");

        for (int i=0; i<cardList.getLength();i++) {
            SceneCard sCard = new SceneCard("temp", 1, 1, "temp");
            Node card = cardList.item(i);
            String cardName = card.getAttributes().getNamedItem("name").getNodeValue();
            sCard.setName(cardName);
            String budget = card.getAttributes().getNamedItem("budget").getNodeValue();
            int budgetNum = Integer.parseInt(budget);
            sCard.setBudget(budgetNum);

            NodeList cardChildren = card.getChildNodes();
            for (int j=0; j< cardChildren.getLength(); j++){

              Node tagName = cardChildren.item(j);

              if("scene".equals(tagName.getNodeName())){
                 String sceneNumber = tagName.getAttributes().getNamedItem("number").getNodeValue();
                 int sceneNo = Integer.parseInt(sceneNumber);
                 sCard.setSceneNum(sceneNo);
                 String title = tagName.getTextContent();
                 sCard.setTitle(title);

              }
              else if("part".equals(tagName.getNodeName())){
                 String partName = tagName.getAttributes().getNamedItem("name").getNodeValue();
                 String partLevel = tagName.getAttributes().getNamedItem("level").getNodeValue();
                 int roleRank = Integer.parseInt(partLevel);
                 NodeList lineList = tagName.getChildNodes();
                //  int x = 1;
                //  int y = 1;
                //  int h = 1;
                //  int w = 1;
                 Node area = lineList.item(1);
                 String xVal = area.getAttributes().getNamedItem("x").getNodeValue();
                 int x = Integer.parseInt(xVal);
                 String yVal = area.getAttributes().getNamedItem("y").getNodeValue();
                 int y = Integer.parseInt(yVal);
                 String hVal = area.getAttributes().getNamedItem("h").getNodeValue();
                 int h = Integer.parseInt(hVal);
                 String wVal = area.getAttributes().getNamedItem("w").getNodeValue();
                 int w = Integer.parseInt(wVal);

                 Node line = lineList.item(3);
                 String sLine = line.getTextContent();
                 Role role = new Role(partName, sLine, roleRank, false, 1, x, y, h, w);
                 sCard.mainRoles.add(role);
              }
            }
            this.sceneCardList.add(sCard);
        }
    }

    //gets offcards and roles
    public void getExtras(Document d) {
        Element root = d.getDocumentElement();
        NodeList sets = root.getElementsByTagName("set");

        for (int i=0; i < sets.getLength(); i++) { //for each set
            OffCard extras = new OffCard();

            //reads data from the nodes
            Node set = sets.item(i);
            String setName = set.getAttributes().getNamedItem("name").getNodeValue();
            extras.name = setName;

            NodeList setChildNodes = set.getChildNodes();

            for (int j=1; j< setChildNodes.getLength(); j+=2) {
                Node parts = setChildNodes.item(j);
                NodeList partList = parts.getChildNodes();


                if("parts".equals(parts.getNodeName())) {
                    for(int k=1; k<partList.getLength(); k+=2) {
                        Node part = partList.item(k);
                        String name = part.getAttributes().getNamedItem("name").getNodeValue();
                        int level = Integer.parseInt(part.getAttributes().getNamedItem("level").getNodeValue());

                        NodeList lineList = part.getChildNodes();

                        Node area = lineList.item(1);
                        String xVal = area.getAttributes().getNamedItem("x").getNodeValue();
                        int x = Integer.parseInt(xVal);

                        String yVal = area.getAttributes().getNamedItem("y").getNodeValue();
                        int y = Integer.parseInt(yVal);

                        String hVal = area.getAttributes().getNamedItem("h").getNodeValue();
                        int h = Integer.parseInt(hVal);

                        String wVal = area.getAttributes().getNamedItem("w").getNodeValue();
                        int w = Integer.parseInt(wVal);

                        Node line = lineList.item(3);
                        String sLine = line.getTextContent();

                        Role eRole = new Role(name, sLine, level, false, 2, x, y, h ,w);
                        extras.extraRoles.add(eRole);
                    }
                }

            }
            this.offCardList.add(extras);
        }
    }


    public ArrayList<OffCard> getOffCardList() {
        return this.offCardList;
    }

    public ArrayList<SceneCard> getSceneCardList() {
        return this.sceneCardList;
    }

    private void resetBoard() {
        this.mainSt.resetCard(3);
        this.saloon.resetCard(2);
        this.bank.resetCard(1);
        this.hotel.resetCard(3);
        this.church.resetCard(2);
        this.hideout.resetCard(3);
        this.ranch.resetCard(2);
        this.trainStation.resetCard(3);
        this.jail.resetCard(1);
        this.store.resetCard(1);

        this.mainSt.getOffCard().resetExtras();
        this.saloon.getOffCard().resetExtras();
        this.bank.getOffCard().resetExtras();
        this.hotel.getOffCard().resetExtras();
        this.church.getOffCard().resetExtras();
        this.hideout.getOffCard().resetExtras();
        this.ranch.getOffCard().resetExtras();
        this.trainStation.getOffCard().resetExtras();
        this.jail.getOffCard().resetExtras();
        this.store.getOffCard().resetExtras();

        shuffleScenes();
    }

    private void goBackHome(Player[] pList) {
        for(int i = 0; i < pList.length; i++) {
            pList[i].setCurrentLoc(trailer);
        }
    }

    private int[] calcScores(Player[] pList) {
        int[] scoreboard = new int[pList.length];

        for(int i=0; i<pList.length;i++) {
            scoreboard[i] = pList[i].calcScore();
            System.out.println(pList[i].getName() + "'s score: " + scoreboard[i]);
        }
        System.out.println("\n");
        return scoreboard;
    }

    private void declareWinner(int[] scoreboard, Player[] pList) {
        int max = 0;
        int playerIndex = 0;
        for(int i = 0; i < scoreboard.length; i++) {
            if(max < scoreboard[i]) {
                max = scoreboard[i];
                playerIndex = i;
            }
        }

        System.out.println("Congratulations to " + pList[playerIndex].getName() + ".");
        System.out.println("You won with a total score of " + max);
    }

    //insertion sort: https://www.geeksforgeeks.org/insertion-sort/
    private int[] reverseSort(int[] array) {
        for(int i = 1; i < array.length; ++i) {
            int key = array[i];
            int j = i-1;
            while (j >= 0 && array[j] < key) {
                array[j+1] = array[j];
                j = j-1;
            }
            array[j+1] = key;
        }
        return array;
    }

    private int roll() {
        Random rand = new Random();
        int randomNum = rand.nextInt((6) + 1);
        return randomNum;
    }

    private int[] bonusPayout(int budget, int numOfRoles) {
        int[] rolls = new int[budget];
        for(int i = 0; i < rolls.length; i++) {
            rolls[i] = roll();
        }
        int[] newRolls = reverseSort(rolls);

        int[] distribution = new int[numOfRoles];
        int d = numOfRoles - 1;
        for(int i=0; i < newRolls.length; i++){
            distribution[d] += newRolls[i];
            d--;
            if(d < 0){
                d = numOfRoles -1;
            }
        }
        System.out.println("Now computing bonus payout...");
        System.out.println("Ending scene...");
        System.out.println("Payouts from the highest role to lowest role: ");
        System.out.print("||");
        for(int i=numOfRoles-1; i >= 0; i--){
          System.out.print(distribution[i] + " || ");
        }
        System.out.println();
        return distribution;
    }


    private int[] getRoleRankList(Room currentLoc) {
        int[] roleRankList = new int[currentLoc.getSceneCard().getMainRoles().size()];
        for(int i = 0; i < roleRankList.length; i++) {
            roleRankList[i] = currentLoc.getSceneCard().getMainRoles().get(i).getRoleRank();
        }
        return roleRankList;
    }


    public void playGame(Player[] pList) {
        int[] scoreboard = new int[pList.length];

        while(this.numDays > 0) { //while the game is still going
            for(int i = 0; i < pList.length; i++) { //taking turns
                pList[i].takeTurn2(this.view);

                if(pList[i].getCurrentLoc().getName() != "Trailer" && pList[i].getCurrentLoc().getName() != "Casting") {
                  if(pList[i].getCurrentLoc().getShotCounters() == 0 && pList[i].getCurrentLoc().getSceneCard().getBudget() != 0) {
                      view.wrapUp(pList[i]);
                      int[] distributions = bonusPayout(pList[i].getPlayerBudget(), pList[i].getNumRolesInCurrentRoom());
                      int[] roleRankList = getRoleRankList(pList[i].getCurrentLoc());
                      for(int j = 0; j < pList.length; j ++) { //go through pList again
                          if(pList[j].getRankOfRole() != 0) { //check if currentplayer has a role
                              if(pList[j].getCurrentRole().getRoleType() == 1) { //checks if on the card
                                  if(pList[j].getCurrentLoc() == pList[i].getCurrentLoc()) { //checks if in same room as the one whose scene is wrapping up
                                      for(int k = 0; k < roleRankList.length; k++) { //assign bonus payouts based on relative positions in distributions and roleRankList
                                          if(pList[j].getCurrentRole().getRoleRank() == roleRankList[k]) {
                                            pList[j].addDollars(distributions[k]);
                                          }
                                      }
                                    }
                                  }
                                  else if(pList[j].getCurrentRole().getRoleType() == 2) { //checks if off the cardv
                                    if(pList[j].getCurrentLoc() == pList[i].getCurrentLoc()) { // assigns bonus payout for extra roles
                                    pList[j].addDollars(pList[j].getRankOfRole());
                                    }
                                  }
                          }
                        }


                    pList[i].getCurrentLoc().removeCard();
                    numScenes--;
                    pList[i].setRToken(0);
                    pList[i].resetRole();
                    //reset rehearsal tokens
                }
              }
            }
            if(numScenes == 1) { //if there is only one scene left, reset everything
                this.numDays--;
                resetBoard();
                goBackHome(pList);
            }
        }
        //the game has ended
        scoreboard = calcScores(pList);
        declareWinner(scoreboard, pList);
    }

}
