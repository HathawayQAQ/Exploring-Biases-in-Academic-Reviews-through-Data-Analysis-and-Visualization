public class Position {
    
    //private fields
    private int x; //the x coordinate
    private int y; //the y coordinate

    //constructor takes two int as input
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //copy constructors
    public Position(Position ideal){
        this.x = ideal.x;
        this.y = ideal.y;
    }

    //reset method that takes two int as inputs
    public void reset(int x, int y){
        this.x = x;
        this.y = y;
    }

    //reset method that takes Position as input and change the corresponding values
    public void reset(Position ideal) {
        this.x = ideal.x;
        this.y = ideal.y;
    }

    //static method called getDistance returns the absolute distance
    public static int getDistance(Position firstPlace, Position secondPlace){
        return Math.abs(firstPlace.x - secondPlace.x) + Math.abs(firstPlace.y - secondPlace.y);
    }

    //getX and getY return the x and y coordinate respectively
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    //moveWest method that moves the position one step to the west
    public void moveWest() {
        this.x--;
    }
    //moveEast that moves the position one step to the east
    public void moveEast() {
        this.x++;
    }
    //moveNorth that moves the position one step to the north
    public void moveNorth() {
        this.y--;
    }
    //moveSouth that moves the position one step to the south
    public void moveSouth() {
        this.y++;
    }

    //equals method reflects if the two positions are equal
    public boolean equals(Object object) {
        if (object instanceof Position) {
            Position other = (Position) object;
            if (this.x == other.x && this.y == other.y){
                return true;
            }  
        }
        return false;
    }

}