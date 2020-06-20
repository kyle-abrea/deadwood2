/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/


import javax.swing.*;
import java.util.*;
import org.w3c.dom.Document;


public class Deadwood2 {
    public static void main(String[] args){
        DWView view = new DWView(); //make the view

        view.dropNumP(); //drop down menu for geting player numbers
        view.setUpGUI(); //makes GUI with player pieces visible
        DWController controller = new DWController(view); //starts controller to set up the game
        // Test controller = new Test(view); //starts controller to set up the game
        controller.initGame(); //initialize and play the game
    }

}
