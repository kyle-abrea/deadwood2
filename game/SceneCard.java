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

public class SceneCard {
    protected String name;
    protected int budget;
    protected int sceneNum;
    protected String title;
    protected ArrayList<Role> mainRoles = new ArrayList<Role>();

    SceneCard(String name, int budget, int sceneNum, String title){
        this.name = name;
        this.budget = budget;
        this.sceneNum = sceneNum;
        this.title = title;
    }

    public ArrayList<Role> getMainRoles() {
        return this.mainRoles;
    }

    public void setName(String name) {
        this.name  = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setSceneNum(int sceneNum) {
        this.sceneNum = sceneNum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setmainRoles(ArrayList<Role> mainRoles) {
        this.mainRoles = mainRoles;
    }

    public int getBudget() {
        return this.budget;
    }

    public String getTitle() {
        return this.title;
    }


    public void displayRoles() {
        System.out.println(this.name);
        System.out.println("Scene " + this.sceneNum + ": " + title);
        System.out.println("Main Roles: ");
        int rSize = this.mainRoles.size();
        // String abc = new String("abcd");
        for(int i = 0; i < rSize; i++) {
            System.out.println("    " + (i+1) + ".) " + this.mainRoles.get(i).getName() + " - Rank " +this.mainRoles.get(i).getRoleRank());
        }
    }

    public int rolesLength() {
        return this.mainRoles.size();
    }

    private boolean checkRank(){
        return false;
    }

    private boolean checkRole(){
        return false;
    }
    //
}
