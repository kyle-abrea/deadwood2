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

public class OffCard {
    public String name;
    protected int numRoles;
    protected ArrayList<Role> extraRoles = new ArrayList<Role>();

    public ArrayList<Role> getExtraRoles() {
        return this.extraRoles;
    }

    public String getName() {
        return this.name;
    }

    public void resetExtras() {
        for(int i = 0; i < this.extraRoles.size(); i++) {
            this.extraRoles.get(i).setFilled(false);
        }
    }

    public int rolesLength() {
        return this.extraRoles.size();
    }

    public void displayRoles() {
        int rSize = this.extraRoles.size();
        System.out.println("Extra Roles: ");
        // String abc = new String("abcd");
        for(int i = 0; i < rSize; i++) {
            System.out.println("    " + (i+1) + ".) " + this.extraRoles.get(i).getName() + " - Rank " + this.extraRoles.get(i).getRoleRank());
        }
    }



    private boolean checkRank() {
        return false;
    }

    private boolean checkRole() {
        return false;
    }

}
