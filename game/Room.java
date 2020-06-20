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

public class Room {
    public String roomName;
    private Player[] playerList;
    protected ArrayList<Room> neighbors = new ArrayList<Room>();
    private int shotCounters;
    private int[] shotX;
    private int[] shotY;
    private SceneCard sceneCard;
    private OffCard extraRole;
    private int x;
    private int y;
    private int sceneX;
    private int sceneY;
    // private int h;
    // private int w;

    public Room(String roomName, int shotCounters,
    ArrayList<Room> neighbors, int x, int y, int sceneX, int sceneY, int[] shotX, int[] shotY) {
        this.roomName = roomName;
        this.shotCounters = shotCounters;
        this.neighbors = neighbors;
        this.x = x;
        this.y = y;
        this.sceneX = sceneX;
        this.sceneY = sceneY;
        this.shotX = new int[shotCounters];
        this.shotY = new int[shotCounters];
    }

    public void displayNeighbors() {
        int nSize = this.neighbors.size();
        for(int i = 0; i < nSize; i++) {
            System.out.println("    " + (i+1) + ".) " + this.neighbors.get(i).roomName);
        }
    }


    public void updateNeighbors(ArrayList<Room> neighbors) {
        this.neighbors = neighbors;
    }

    public void assignOffCards(OffCard extras) {
        this.extraRole = extras;
    }

    public void assignSceneCard(SceneCard sceneCard) {
        this.sceneCard = sceneCard;
    }

    public String getName() {
        return this.roomName;
    }

    public OffCard getOffCard() {
        return this.extraRole;
    }

    public SceneCard getSceneCard() {
        return this.sceneCard;
    }




    public void takeShot() {
        this.shotCounters--;
    }


    public OffCard getExtraRole() {
        return this.extraRole;
    }

    public int getShotCounters() {
        return this.shotCounters;
    }

    public ArrayList<Room> getNeighbors() {
        return this.neighbors;
    }

    public int getX() { //returns the x-coordinate for the location where players dice moves in each room
        return this.x;
    }

    public int getY() { //returns the x-coordinate for the location where players dice moves in each room
        return this.y;
    }

    public int getSceneX() { //returns the x-coordinate for scene card
        return this.sceneX;
    }

    public int getSceneY() { //returns the y-coordinate for scene card
        return this.sceneY;
    }

    public int getShotX(int shotNum){ //returns the x-coordinate for given shot counter
        return this.shotX[shotNum];
    }

    public int getShotY(int shotNum){ //returns the y-coordinate for the given shot counter
        return this.shotY[shotNum];
    }

    public int neighborLength() { // returns the number of neighbors
        return this.neighbors.size();
    }


    public void resetCard(int shotCounters) {
        this.shotCounters = shotCounters;
    }

    public void removeCard() {
        this.sceneCard = new SceneCard("null", 0, 0, "null");
    }


}
