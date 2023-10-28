public class Region {

    //private fields
    private int min_of_x;
    private int min_of_y;
    private int max_of_x;
    private int max_of_y;

    //constructor of Region public class
    public Region(int min_of_x, int min_of_y, int max_of_x, int max_of_y){
        this.min_of_x = min_of_x;
        this.min_of_y = min_of_y;
        this.max_of_x = max_of_x;
        this.max_of_y = max_of_y;
    }

    //contains method returns true if the position is within the range
    public boolean contains(Position position){
        int x = position.getX();
        int y = position.getY();

        if(x <= max_of_x && x >= min_of_x && y <= max_of_y && y >= min_of_y){
            return true;
        }
        return false;
    }

}
