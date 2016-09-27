// Bomb.java
// Class representing a bomb on the playing field


public class Bomb {


    private int xCoordinate;
    private int yCoordinate;


    public Bomb() {
        xCoordinate = 0;
        yCoordinate = 0;
    } // end constructor


    public Bomb(int x,int y) {
        xCoordinate = x;
        yCoordinate = y;
    } // end overloaded constructor


    public int getXCoordinate() {
        return xCoordinate;
    } // end x getter


    public int getYCoordinate() {
        return yCoordinate;
    } // end y getter


} // end bomb class
