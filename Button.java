// Button.java
// Class for the buttons on the playing field


import javax.swing.*;


public class Button extends JButton {


    private int xCoordinate;
    private int yCoordinate;
    private int nearbyBombs;
    private boolean bombsChecked;
    private boolean flagged;
    private boolean isHidden;
    private boolean hasBomb;


    public Button() {

        xCoordinate = 0;
        yCoordinate = 0;
        nearbyBombs = 0;
        bombsChecked = false;
        flagged = false;
        isHidden = false;
        hasBomb = false;

    } // end constructor


    public Button(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    } // end overloaded constructor


    // Getters


    public int getXCoordinate() {
        return xCoordinate;
    } // end x getter


    public int getYCoordinate() {
        return yCoordinate;
    } // end y getter


    public boolean getBombsChecked() {
        return bombsChecked;
    } // end bombChecked getter


    public boolean getFlagged() {
        return flagged;
    } // end flagged getter


    public int getNearbyBombs() {
        return nearbyBombs;
    } // end nearbyBombs getter


    public boolean getIsHidden() {
        return isHidden;
    } // end isHidden getter


    public boolean getHasBomb() {
        return hasBomb;
    } // end hasBomb getter


    // Setters


    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    } // end flagged setter


    public void setBombsChecked(Boolean bombsChecked) {
        this.bombsChecked = bombsChecked;
    } // end bombsChecked setter


    public void setNearbyBombs(int nearbyBombs) {
        this.nearbyBombs = nearbyBombs;
    } // end nearbyBombs setter


    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    } // end isHidden setter


    public void setHasBomb(Boolean hasBomb) {
        this.hasBomb = hasBomb;
    } // end hasBomb setter


} // end Button class
