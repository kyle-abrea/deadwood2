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

public class Role {
    private String name;
    private String line;
    private int roleRank;
    private boolean filled;
    private int roleType;
    private int x;
    private int y;
    private int h;
    private int w;
        //role types:
        //0: no role
        //1: main role
        //2: extrarole

    Role(String name, String line, int roleRank, boolean filled, int roleType, int x, int y, int h, int w) {
        this.name = name;
        this.line = line;
        this.roleRank = roleRank;
        this.filled = filled;
        this.roleType = roleType;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public String getName() {
        return this.name;
    }

    public String getLine() {
        return this.line;
    }

    public int getRoleRank() {
        return this.roleRank;
    }

    public boolean getFilled() {
        return this.filled;
    }

    public int getRoleType() {
        return this.roleType;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean checkRank(int rank) {
        if(rank >= this.roleRank) {
            return true;
        }
        return false;
    }

}
