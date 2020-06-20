/*
Kyle Iser Abrea and Kelly Lam
CSCI345
*/


import java.util.*;
import java.util.ArrayList;

public class Player {
    private int rank;
    protected Room currentLoc;
    protected Role currentRole;
    protected int rankOfRole;
    private int credits;
    private int dollars;
    private int rTokens;
    public  String name;
    private int state;

    public Player(String name, int rank, Room currentLoc, int rankOfRole,
    int credits, int dollars, int rTokens, int state) {
        this.name = name;
        this.rank = rank;
        this.currentLoc = currentLoc;
        this.rankOfRole = rankOfRole;
        this.credits = credits;
        this.dollars = dollars;
        this.rTokens = rTokens;
        this.state = state;

    }


    private int rollDice() {
        Random rand = new Random();
        int randomNum = rand.nextInt(5) + 1;
        return randomNum;
    }

    //getters
    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }

    public Room getCurrentLoc() {
        return this.currentLoc;
    }

    public Role getCurrentRole() {
        return this.currentRole;
    }

    public int getRankOfRole() {
        return this.rankOfRole;
    }

    public int getDollars() {
        return this.dollars;
    }

    public int getCredits() {
        return this.credits;
    }

    public int getRTokens() {
        return this.rTokens;
    }

    public Role getRole() {
        return this.currentRole;
    }

    public int getState() {
        return this.state;
    }



    //setters
    public void setRToken(int rToken){
      this.rTokens = rToken;
    }

    public void setRole(Role role) {
        this.currentRole = role;
    }

    public void setCurrentLoc(Room currentLoc) {
        this.currentLoc = currentLoc;
    }

    //other things
    public void takeRToken() {
        this.rTokens++;
    }

    public void updateRank(int rank) {
        this.rank = rank;
    }

    public void setState(int state) {
        this.state = state;
    }

    protected String displayStats() {
        String stats = new String();
        stats = "Current Player: " + this.name + "\n";
        stats += "Current Location: " + this.currentLoc.roomName + "\n";
        stats += "Rank: " + this.rank + "\n";
        if(this.rankOfRole == 0) {
            stats += "Rank of Role: N/A (" + this.name + " does not currently have\n";
            stats += "a role)"  + "\n";
        }
        else {
            stats += "Rank of Role: " + this.rankOfRole  + "\n";
        }
        stats += "Dollars: " + this.dollars + "\n";
        stats += "Credits: " + this.credits + "\n";
        stats += "Rehearsal Tokens: " + this.rTokens + "\n";
        stats += "Shot counters in current room: " + this.getCurrentLoc().getShotCounters();
        return stats;
    }



    public int calcScore() {
        return this.dollars + this.credits + (this.rank * 5);
    }

    private boolean hasRole() {
        if(this.rankOfRole != 0) {
            return true;
        }
        return false;
    }

    public void resetRole() {
        this.currentRole = null;
        this.rankOfRole = 0;
    }

    public void takeTurn2(DWView view) { //taketurn for the GUI implementation
        int choice = view.primaryChoices(this);
        if(this.getState() == 1) { //state 1: you're in trailer
            if(choice == 1) { //move
                move2(view);
            }
        }
        else if(this.getState() == 2) { //state 2: you're in casting
            if(choice == 1) { //move
                move2(view);
            }
            else if(choice == 2) {
                int newRank = view.chooseUpgrade(this);
                if(newRank == 9999) {
                    ;
                }
                else {
                    int dOrC = view.dollarsOrCredits(this, newRank);
                    if(dOrC == 9999) {
                        ;
                    }
                    else if(dOrC == 1) {
                        view.updateWithCash(this, newRank);
                        this.rank = newRank;
                    }
                    else {
                        view.updateWithCredits(this, newRank);
                        this.rank = newRank;
                    }
                }

            }
        }
        else if(this.getState() == 3) { //you're in an unwrapped scene
            if(choice == 1) {
                move2(view);
            }
            else if(choice == 2) {
                takeRole(view);
            }
        }
        else if(this.getState() == 4) { //you're in a wrapped scene
            if(choice == 1) {
                move2(view);
            }
        }
        else if(this.getState() == 5) { //you're acting a main role
            if(choice == 1) {
                int roll = rollDice() + this.rTokens;
                if(roll >= this.getCurrentLoc().getSceneCard().getBudget()) {
                    view.actMainRolePass(roll);
                    this.credits += 2;
                    this.getCurrentLoc().takeShot();
                }
                else {
                    view.actMainRoleFail(roll);
                }
            }
            else if(choice == 2) {
                this.rTokens++;
            }
        }
        else if(this.getState() == 6) { //you're acting in an extra role
            if(choice == 1) {
                int roll = rollDice() + this.rTokens;
                if(roll >= this.getCurrentLoc().getSceneCard().getBudget()) {
                    view.actExtraRolePass(roll);
                    this.credits += 1;
                    this.dollars += 1;
                    this.getCurrentLoc().takeShot();
                }
                else {
                    view.actExtraRoleFail(roll);
                    this.dollars += 1;
                }
            }
            else if(choice == 2) {
                this.rTokens++;
            }
        }
    }

    public void takeTurn() { //take turn for the text based implementation
        boolean goodNum = false;
        if(!this.hasRole() || (/*this.hasRole() && */this.getPlayerBudget() == 0)) {
            System.out.println("1.) Move into an neighboring room and take a role if you wish.");
            this.getCurrentLoc().displayNeighbors();
            if(this.currentLoc.getName() == "Casting") {
                System.out.println("2: Upgrade your rank");
            }
            else if(this.currentLoc.getName() == "Trailer") {
                System.out.println("2.) End your turn.");
            }
            else if(this.currentLoc.getSceneCard().getBudget() == 0) {
                System.out.println("2.) End your turn.");
            }
            else if(this.currentLoc.getName() != "Trailer" && this.currentLoc.getName() != "Casting") {
                System.out.println("2.) Take a role in your current room.");
            }
            else if(getPlayerBudget() == 0) {

            }

            System.out.print("What would you like to do, partner?? (Type the number that corresponds with the above choice(s)) ");

            Scanner reader = new Scanner(System.in);
            //trap the player in a loop until he enter's good input
            int choiceNum = 0;
            while(!goodNum) {
                if(!reader.hasNextInt()) {
                    System.out.print("That ain't no number partner! Type a number from 1-2: ");
                    reader.nextLine();
                }
                else {
                    choiceNum = reader.nextInt();
                    if(choiceNum < 1 || choiceNum > 2) {
                        System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above choice(s)): ");
                    }
                    else {
                        goodNum = true;
                    }
                }
            }

            //logic for moving
            if(choiceNum == 1) {
                System.out.println("Where to??");
                this.getCurrentLoc().displayNeighbors();
                move();
                if(this.currentLoc.getName() == "Casting") {
                    displayCastingOptions();
                    System.out.print("Would you like to upgrade your rank?? Type 1 for yes or 2 for no: ");
                    int yesOrNo = onOrOff();
                    if(yesOrNo == 1) {
                        this.upgradeRank();
                    }
                    else {
                        System.out.println("You chose not to upgrade your rank.");
                    }

                }
                else if(this.currentLoc.getName() == "Trailer") {
                    System.out.println("You moved back to Trailer. Ending turn...");
                }
                else if(getPlayerBudget() == 0) {
                    System.out.println("Move to a wrapped scene. Ending turn...");
                }
                else {
                    this.currentLoc.getSceneCard().displayRoles();
                    this.currentLoc.getOffCard().displayRoles();
                    System.out.print("Would you like to take a role in this room? (Pick 1 for yes or 2 for no) ");
                    reader.nextLine();
                    goodNum = false;
                    while(!goodNum) {
                        if(!reader.hasNextInt()) {
                            System.out.print("That ain't no number partner! Type 1 for yes or 2 for no: ");
                            reader.nextLine();
                        }
                        else {
                            choiceNum = reader.nextInt();
                            if(choiceNum < 1 || choiceNum > 2) {
                                System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                            }
                            else {
                                goodNum = true;
                            }
                        }
                    }
                    if(choiceNum == 1) { //choose between scene card or off card

                        System.out.println("Act on a card or off the card? (Type 1 for on the card or 2 for off the card) ");
                        int onOrOff = onOrOff();
                        if(onOrOff == 1) {
                            this.currentLoc.getSceneCard().displayRoles();
                            takeMainRole();
                        }

                        else {
                            this.currentLoc.getOffCard().displayRoles();
                            takeExtraRole();
                        }
                    }
                    else {
                        System.out.println("You ended your turn.");
                    }
                }
            }

            //for upgrading rank
            else if(choiceNum == 2 && this.currentLoc.getName() == "Casting") {
                displayCastingOptions();
                System.out.print("Would you like to upgrade your rank?? Type 1 for yes or 2 for no: ");
                int yesOrNo = onOrOff();
                if(yesOrNo == 1) {
                    this.upgradeRank();
                }
                else {
                    System.out.println("You chose not to upgrade your rank.");
                }

            }

            //for if in trailer
            else if(choiceNum == 2 && this.currentLoc.getName() == "Trailer") {
                System.out.println("You chose to end your turn.");
            }

            //if scene ended in this room
            else if(choiceNum == 2 && this.currentLoc.getSceneCard().getBudget() == 0) {
                System.out.println("You chose to end your turn.");
            }

            //ask if they want to act
            else if(choiceNum == 2 && (this.currentLoc.getName() != "Casting" && this.currentLoc.getName() != "Trailer")) {
                //choose between scene card or off card
                System.out.println("Act on a card or off the card? (Type 1 for on the card or 2 for off the card) ");
                int onOrOff = onOrOff();
                if(onOrOff == 1) {
                    this.currentLoc.getSceneCard().displayRoles();
                    takeMainRole();
                }

                else {
                    this.currentLoc.getOffCard().displayRoles();
                    takeExtraRole();
                }
            }
        }

        //if player has role...
        else {
            if(this.currentLoc.getShotCounters() != 0) {

                System.out.println("1.) Act on your current role");
                System.out.println("2.) Rehearse");

                System.out.print("What would you like to do, partner (Type 1 or 2)?? ");
                int actOrRehearse = onOrOff();
                if(actOrRehearse == 1) {
                    if(this.currentRole.getRoleType() == 1) {
                        actMainRole();
                    }
                    else if(this.currentRole.getRoleType() == 2) {
                        actExtraRole();
                    }
                }
                else {
                    if(this.getRTokens() <= (getPlayerBudget() - 1)) {
                        System.out.println("Taking rehearsal token...");
                        this.rTokens++;
                        System.out.println("You now have " + this.rTokens + "rehearsal tokens.");
                    }
                    else {
                        System.out.println("You reached the max amount of rehearsal tokens. Ending turn..");
                    }
                }
            }
            else {
                System.out.println("All shot counters for the current player's room have been taken.");
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    }

    private void move2(DWView view) {
        Room newRoom = view.move(this);
        this.setCurrentLoc(newRoom);
        if(!this.getCurrentLoc().getName().equals("Trailer") ||
        !this.getCurrentLoc().getName().equals("Casting") ||
        this.getCurrentLoc().getSceneCard().getBudget() == 0) {
            view.flip(this);
        }

        if(this.getCurrentLoc().getName().equals("Trailer")) {
            this.setState(1);
        }
        else if(this.getCurrentLoc().getName().equals("Casting")) {
            this.setState(2);
            // view.chooseUpgrade(this);
        }
        else if(this.getCurrentLoc().getSceneCard().getBudget() == 0) {
            this.setState(4);
        }

        else { //moving into unwrapped scene
            this.setState(3);
            takeRole(view);
        }
    }

    private void takeRole(DWView view) {
        int choice2 = view.askRoleType(this);
        if(choice2 == 9999) {
            ;
        }
        else if(choice2 == 1) { //main role
            takeMain(view);
        }
        else {
            takeExtra(view);
        }
    }

    private void takeMain(DWView view) {
        int roleIndex = view.selectMainRole(this);
        if(roleIndex == 9999) {
            ;
        }
        else {
            this.currentRole = this.getCurrentLoc().getSceneCard().getMainRoles().get(roleIndex);
            this.rankOfRole = this.currentRole.getRoleRank();
            this.setState(5);
        }
    }

    private void takeExtra(DWView view) {
        int roleIndex = view.selectExtraRole(this);
        if(roleIndex == 9999) {
            ;
        }
        else {
            this.currentRole = this.getCurrentLoc().getOffCard().getExtraRoles().get(roleIndex);
            this.rankOfRole = this.currentRole.getRoleRank();
            this.setState(6);
        }
    }



    private void move() {
        boolean goodNum = false;
        int choiceNum = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Choose number: ");
        while(!goodNum) {
            if(!reader.hasNextInt()) {
                System.out.print("That ain't no number partner!");
                reader.nextLine();
            }

            else {
                choiceNum = reader.nextInt();
                if(choiceNum < 1 || choiceNum > this.currentLoc.neighborLength()) {
                    System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        this.currentLoc = this.currentLoc.getNeighbors().get(choiceNum-1);
        System.out.println("You are in: " + this.currentLoc.getName() + ".");
    }



    private void takeMainRole() {
        boolean goodNum = false;
        int choiceNum = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Type a number that corresponds with your desired role (Type 0 to cancel choosing): ");
        while(!goodNum) {
            if(!reader.hasNextInt()) {
                System.out.print("That ain't no number partner!");
                reader.nextLine();
            }

            else {
                choiceNum = reader.nextInt();
                if(choiceNum == 0) {
                    System.out.println("Cancelling...");
                    goodNum = true;
                }
                else if(choiceNum < 1 || choiceNum > this.currentLoc.getSceneCard().rolesLength()){
                    System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                }
                else if(this.currentLoc.getSceneCard().getMainRoles().get(choiceNum-1).getFilled()) {
                    System.out.print("This role is already filled. Choose another role: ");
                }

                else if(this.rank < this.currentLoc.getSceneCard().getMainRoles().get(choiceNum-1).getRoleRank()) {
                    System.out.print("Get your rank up, partner. Type a different one: ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        if(choiceNum == 0) {
            System.out.println();
        }
        else {
            this.currentLoc.getSceneCard().getMainRoles().get(choiceNum-1).setFilled(true);
            this.currentRole = this.currentLoc.getSceneCard().getMainRoles().get(choiceNum-1);
            this.rankOfRole = this.currentLoc.getSceneCard().getMainRoles().get(choiceNum-1).getRoleRank();
        }
    }

    private void takeExtraRole() {
        boolean goodNum = false;
        int choiceNum = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Type a number that corresponds with your desired role (Type 0 to cancel choosing): ");
        while(!goodNum) {
            if(!reader.hasNextInt()) {
                System.out.print("That ain't no number partner!");
                reader.nextLine();
            }
            else {
                choiceNum = reader.nextInt();
                if(choiceNum == 0) {
                    System.out.println("Cancelling...");
                    goodNum = true;
                }
                else if(choiceNum < 1 || choiceNum > this.currentLoc.getOffCard().rolesLength()){
                    System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                }
                else if(this.currentLoc.getOffCard().getExtraRoles().get(choiceNum-1).getFilled()) {
                    System.out.println("This role is already filled. Choose another role.");
                }


                else if(this.rank < this.currentLoc.getExtraRole().getExtraRoles().get(choiceNum-1).getRoleRank()) {
                    System.out.print("Get your rank up, partner. Type a different one: ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        if(choiceNum == 0) {
            System.out.println();
        }
        else {
            this.currentLoc.getOffCard().getExtraRoles().get(choiceNum-1).setFilled(true);
            this.currentRole = this.currentLoc.getOffCard().getExtraRoles().get(choiceNum-1);
            this.rankOfRole = this.currentLoc.getOffCard().getExtraRoles().get(choiceNum-1).getRoleRank();
        }

    }

    //method to choose between two choices
    private int onOrOff() {
        boolean goodNum = false;
        int choiceNum = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Choose number (1 or 2): ");
        while(!goodNum) {
            if(!reader.hasNextInt()) {
                System.out.print("That ain't no number partner!");
                reader.nextLine();
            }
            else {
                choiceNum = reader.nextInt();
                if(choiceNum < 1 || choiceNum > 2){
                    System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        return choiceNum;
    }

    public int getPlayerBudget() {
        return this.currentLoc.getSceneCard().getBudget();
    }

    public int getNumRolesInCurrentRoom() {
        return this.currentLoc.getSceneCard().getMainRoles().size();
    }

    private void displayCastingOptions() {
        System.out.println("UPGRADE YOUR RANK:");
        System.out.println("Rank 2: 4 Dollars or 5 Credits");
        System.out.println("Rank 3: 10 Dollars or 10 Credits");
        System.out.println("Rank 4: 18 Dollars or 15 Credits");
        System.out.println("Rank 5: 28 Dollars or 20 Credits");
        System.out.println("Rank 6: 40 Dollars or 25 Credits");
    }


    private void upgradeRank() {
        boolean goodNum = false;
        int choiceNum = 0;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type a number that corresponds with the rank choices above. Type 0 to cancel choosing if you wish.");
        System.out.print("Type number: ");
        while(!goodNum) {
            if(!reader.hasNextInt()) { //they typed a non int value
                System.out.print("That ain't no number partner!");
                reader.nextLine();
            }
            else {
                choiceNum = reader.nextInt();
                if(choiceNum == 0) {
                    goodNum = true;
                }
                else if(choiceNum < 2 || choiceNum > 6){ //they chose a number outside the range
                    System.out.print("You chose the wrong number, buckaroo. (Type the number that corresponds with the above room choice(s)): ");
                }
                else if(this.rank == 6){
                  System.out.println("You are already at the maximum rank! Type 0 to cancel: ");
                }
                else if(choiceNum < this.rank) { //the rank they want is lower than their rank
                    System.out.println("You can't downgrade your rank. Choose another rank: ");
                }
                else if(choiceNum == this.rank){
                  System.out.println("You are already this rank. Choose a higher rank: ");
                }
                else if(!this.suffFunds(choiceNum) && choiceNum != 0) { //they don't have the funds for this rank
                    System.out.print("You ain't got the funds for this, partner. Choose another rank: ");
                }
                else {
                    goodNum = true;
                }
            }
        }
        if(choiceNum == 0) { //turn ends if player can't upgrade
            System.out.println("Cancelling...");

        }
        else {
            System.out.println("You chose a rank of: " + choiceNum);
            System.out.print("Would you like to pay in cash or credit? (Type 1 for cash or 2 for credit)");
            int cashOrCredit = onOrOff();
            if(cashOrCredit == 1) {
                if(affordWithDollars(choiceNum)) {
                    payDollars(choiceNum);
                    this.rank = choiceNum;
                }
                else {
                    System.out.println("You don't have enough cash, so you spend credits instead");
                    payCredits(choiceNum);
                    this.rank = choiceNum;
                }
            }
            else {
                if(affordWithCredits(choiceNum)) {
                    payCredits(choiceNum);
                    this.rank = choiceNum;
                }
                else {
                    System.out.println("You don't have enough credits, so you spend cash instead");
                    payDollars(choiceNum);
                    this.rank = choiceNum;
                }
            }
        }
    }

    private void payDollars(int desiredRank) {
        if(desiredRank == 2) {
            this.dollars -= 4;
        }
        else if(desiredRank == 3) {
            this.dollars -= 10;
        }
        else if(desiredRank == 4) {
            this.dollars -= 18;
        }
        else if(desiredRank == 5) {
            this.dollars -= 28;
        }
        else {
            this.dollars -= 40;
        }

    }

    private boolean affordWithDollars(int desiredRank) {
        if(desiredRank == 2) {
            return this.dollars >= 4;
        }
        else if(desiredRank == 3) {
            return this.dollars >= 10;
        }
        else if(desiredRank == 4) {
            return this.dollars >= 18;
        }
        else if(desiredRank == 5) {
            return this.dollars >= 28;
        }
        else {
            return this.dollars >= 40;
        }
    }

    private void payCredits(int desiredRank) {
        if(desiredRank == 2) {
            this.credits -= 5;
        }
        else if(desiredRank == 3) {
            this.credits -= 10;
        }
        else if(desiredRank == 4) {
            this.credits -= 15;
        }
        else if(desiredRank == 5) {
            this.credits -= 20;
        }
        else {
            this.credits -= 25;
        }
    }

    private boolean affordWithCredits(int desiredRank) {
        if(desiredRank == 2) {
            return this.credits >= 5;
        }
        else if(desiredRank == 3) {
            return this.credits >= 10;
        }
        else if(desiredRank == 4) {
            return this.credits >= 15;
        }
        else if(desiredRank == 5) {
            return this.credits >= 20;
        }
        else {
            return this.credits >= 25;
        }
    }

    public void addDollars(int dollars) {
        this.dollars += dollars;
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    //check if player can buy
    private boolean suffFunds(int desiredRank) {
        if(desiredRank == 2) {
            if(this.dollars >= 4 && this.credits >= 5)
                return true;
            if(this.dollars >= 4)
                return true;
            if(this.credits >= 5)
                return true;
        }
        if(desiredRank == 3) {
            if(this.dollars >= 10 && this.credits >= 10)
                return true;
            if(this.dollars >= 10)
                return true;
            if(this.credits >= 10)
                return true;
        }
        if(desiredRank == 4) {
            if(this.dollars >= 18 && this.credits >= 15)
                return true;
            if(this.dollars >= 18)
                return true;
            if(this.credits >= 15)
                return true;
        }
        if(desiredRank == 5) {
            if(this.dollars >= 28 && this.credits >= 20)
                return true;
            if(this.dollars >= 28)
                return true;
            if(this.credits >= 20)
                return true;
        }
        if(desiredRank == 6) {
            if(this.dollars >= 40 && this.credits >= 25)
                return true;
            if(this.dollars >= 40)
                return true;
            if(this.credits >= 25)
                return true;
        }
        return false;
    }


    private void actMainRole() {
        System.out.println("You have this many rehearsal tokens: " + this.rTokens);
        int diceRoll = rollDice() + this.rTokens;
        System.out.println("You rolled a " + (diceRoll-this.rTokens) + ".");
        System.out.println("Adding your rehearsal tokens gives you a whopping total roll of: " + diceRoll);
        if(diceRoll >= this.currentLoc.getSceneCard().getBudget()) {
            System.out.println("Success!! You rolled higher than the budget of your film. You take 2 dollars");
            this.currentLoc.takeShot();
            this.credits += 2;
        }
        else {
            System.out.println("You failed!! You rolled lower than the budget of the film. Sucks for you!! You don't get anything.");
        }
    }

    private void actExtraRole() {
        System.out.println("You have this many rehearsal tokens: " + this.rTokens);
        int diceRoll = rollDice() + this.rTokens;
        System.out.println("You rolled a " + (diceRoll-this.rTokens) + ".");
        System.out.println("Adding your rehearsal tokens gives you a whopping total roll of: " + diceRoll);
        if(diceRoll >= this.currentLoc.getSceneCard().getBudget()) {
            System.out.println("Success!! You rolled higher than the budget of your film. You take a dollar and a credit");
            this.currentLoc.takeShot();
            this.credits++;
            this.dollars++;
        }
        else {
            System.out.println("You failed!! You rolled lower than the budget of the film. Sucks for you!!");
            System.out.println("You still get one dollar though!");
            this.dollars++;
        }
    }
}
