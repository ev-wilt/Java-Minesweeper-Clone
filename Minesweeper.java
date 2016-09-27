// Minesweeper.java
// A clone of Minesweeper built in a Java GUI
// By: Evan Wilt
// Created: 4-4-16
// Last Revised: 4-25-16

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;


public class Minesweeper extends JFrame implements ActionListener {

    // Instantiating variables

    private boolean currentlyFlagging = false;
    private boolean gameOver = false;
    private boolean keepGoing = true;
    private int turnCounter = 0;
    private int pressedButtonCounter = 0;
    private int bombNumber = 15;
    private int xSize = 10;
    private int ySize = 10;
    private Button currentButton = new Button();
    private Button randButton = new Button();
    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Bomb> bombs = new ArrayList<>();
    Random random = new Random();
    Font font = new Font("Verdana", Font.BOLD, 60);
    JTextField turnField = new JTextField("0");
    JButton flagButton = new JButton("Click to flag");
    JPanel menu = new JPanel();
    JPanel playField = new JPanel();


    public Minesweeper() { // Constructor for the minesweeper class

        this.setupGUI(); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(120*this.xSize, 70*ySize);

    } // end constructor


    public void setupGUI() { // Adding all of the GUI components and also placing bombs

        Container mainPanel = this.getContentPane();
        mainPanel.setLayout(new GridLayout(1,1));
        menu.setLayout(new GridLayout(5,5));
        playField.setLayout(new GridLayout(this.xSize+2,this.ySize+2));
        turnField.setFont(font);
        turnField.setEditable(false);
        mainPanel.add(menu);
        mainPanel.add(playField);
        menu.add(turnField);
        menu.add(flagButton); 
        for (int x = 0; x < this.xSize+1; x++) {
            for (int y = 0; y < this.ySize+1; y++) {
                buttons.add(new Button(x,y));
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).getXCoordinate() == x) {
                        if (buttons.get(i).getYCoordinate() == y) {
                            playField.add(buttons.get(i));
                        } // end y if
                    } // end x if
                } // end for
            } // end y for
        } // end x for

        this.hideEdges();
        this.addBombs();
        this.countButtons();

        // Action listeners

        flagButton.addActionListener(this);
        for (int j = 0; j < buttons.size(); j++) {
            buttons.get(j).addActionListener(this);
        } // end for

    } // end setupGUI


    public void actionPerformed(ActionEvent e){
        if (e.getSource() == flagButton) {

            if (this.gameOver == false) {
                if (currentlyFlagging == false) {
                    currentlyFlagging = true;
                    flagButton.setText("Flagging");
                } // end if

                else {
                    currentlyFlagging = false;
                    flagButton.setText("Click to flag");
                } // end else
            } // end if

        } // end if

        for (int i = 0; i < buttons.size(); i++) {
            if (e.getSource() == buttons.get(i)) {
                this.buttonClicked(buttons.get(i));
            } // end if

        } // end for
        
    } // end action listener


    public void callTurnCounter() { // Counter shows how many turns the player has made

        if (gameOver == false) {
            turnCounter++;
	    if (turnCounter == 1) {
                turnField.setText(Integer.toString(turnCounter) + " Turn");
            } // end if

            else {
                turnField.setText(Integer.toString(turnCounter) + " Turns");
            } // end else
  
      } // end if

    } // end callTimer


    public void buttonClicked(Button currentButton) { // Recursive algorithm to check the nearby bombs for buttons

        if (currentlyFlagging == false) {                   // If the player isn't flagging,
            if (currentButton.getIsHidden() == false) {    // the button isn't hidden,
                if (currentButton.getFlagged() == false) {  // and the button isn't flagged...

                    if (currentButton.getBombsChecked() == false) { // Call the turn counter if the button hasn't been checked
                        this.callTurnCounter();
                    }

                    if (currentButton.getHasBomb() == true) { // Checking for a loss
                        gameOver = true;
                        flagButton.setText("You lost");
                        this.showAllBombs();
                    } // end if

                    if (pressedButtonCounter == 0) { // Checking for a win
                        gameOver = true;
                        flagButton.setText("You won!");
                        this.showAllBombs();
                    } // end if

                    if (gameOver == false) { // Check nearby spaces for bombs
                        for (int currentX = currentButton.getXCoordinate() - 1; currentX < currentButton.getXCoordinate() + 2; currentX++) {
                            for (int currentY = currentButton.getYCoordinate() - 1; currentY < currentButton.getYCoordinate() + 2; currentY++) {
                                for (int currentBomb = 0; currentBomb < bombs.size(); currentBomb++) {
                                    if (currentX == bombs.get(currentBomb).getXCoordinate()) {
                                        if (currentY == bombs.get(currentBomb).getYCoordinate()) {
                                            currentButton.setNearbyBombs(currentButton.getNearbyBombs()+1);
                                        } // end Y if
                                    } // end X if
                                } // end bomb for
                            } // end Y for
                        } // end X for

                        if (currentButton.getNearbyBombs() == 0) { // If no bombs are nearby, call the method for every nearby button
                            for (int i = 0; i < buttons.size(); i++) {
                                for (int currentX = currentButton.getXCoordinate() - 1; currentX < currentButton.getXCoordinate() + 2; currentX++) {
                                    for (int currentY = currentButton.getYCoordinate() - 1; currentY < currentButton.getYCoordinate() + 2; currentY++) {
                                        if (currentX == buttons.get(i).getXCoordinate()) {
                                            if (currentY == buttons.get(i).getYCoordinate()) {
                                                if (buttons.get(i).getBombsChecked() == false) {
                                                    buttons.get(i).setBombsChecked(true);
                                                    currentButton.setText(Integer.toString(currentButton.getNearbyBombs()));
                                                    this.buttonClicked(buttons.get(i));
                                                } // end bombsChecked if
                                            } // end Y if
                                        } // end X if
                                    } // end Y for
                                } // end X for
                            } // end for
                        } // end if

                        else if (currentButton.getNearbyBombs() > 0) { // If there's bombs near show the number of them nearby 
                            currentButton.setBombsChecked(true);
                            if (currentButton.getText() == "") {
                                this.pressedButtonCounter--;
                                currentButton.setText(Integer.toString(currentButton.getNearbyBombs()));
                            } // end if

                        } // end else
                    } // end gameOver if     
                }  // end if
            } // end if
        }  // end if

        else if (currentlyFlagging == true) {
            if (currentButton.getFlagged() == false) {
                if (currentButton.getBombsChecked() == false) {
                    currentButton.setFlagged(true);
                    currentButton.setText("?");
                } // end if
            } // end if

            else if (currentButton.getFlagged() == true) {
                if (currentButton.getBombsChecked() == false) {
                    currentButton.setFlagged(false);
                    currentButton.setText("");
                } // end if
            } // end else

        } // end else

    } // end buttonClicked


    public void hideEdges() { // Hides edge cases on the playfield

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getXCoordinate() == 0) {
                buttons.get(i).setVisible(false);
                buttons.get(i).setIsHidden(true);
            } // end if

            else if (buttons.get(i).getXCoordinate() == this.xSize+1) {
                buttons.get(i).setVisible(false);
                buttons.get(i).setIsHidden(true);
            } // end else

            else if (buttons.get(i).getYCoordinate() == 0) {
                buttons.get(i).setVisible(false);
                buttons.get(i).setIsHidden(true);
            } // end else

            else if (buttons.get(i).getYCoordinate() == this.ySize+1) {
                buttons.get(i).setVisible(false);
                buttons.get(i).setIsHidden(true);
            } // end else

        } // end for

    } // end hideEdges


    public void countButtons() { // Counter for buttons without a bomb to check when all of the buttons have been pressed

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getHasBomb() == false) {
                if (buttons.get(i).getIsHidden() == false) {
                    this.pressedButtonCounter++;
                } // end if
            } // end if
        } // end for

    } // end countButtons


    public void addBombs() { // Adds bombs to the playfield in a random area

        while (keepGoing == true) {
            if (bombs.size() <= this.bombNumber-1) {
                this.randButton = buttons.get(random.nextInt(buttons.size()) + 0);
                if (randButton.getIsHidden() == false) {
                    if (randButton.getHasBomb() == false) {
                        randButton.setHasBomb(random.nextBoolean());
                        if (randButton.getHasBomb() == true) {
                            bombs.add(new Bomb(randButton.getXCoordinate(), randButton.getYCoordinate()));
                        } // end if
                    } // end if
                }// end if
            } // end if

            if (bombs.size() > this.bombNumber-1) {
                keepGoing = false;
            } // end if

        } // end while

    } // end addBombs


    public void showAllBombs() { // Shows all bombs on the playfield

        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getHasBomb() == true) {
                buttons.get(i).setText("*");
            } // end if
        } // end for

    } // end showAllBombs


    public static void main(String[] args){ // Main method
        new Minesweeper();
    } // end main


} // end Minesweeper class
