//represented as MyDoublyLinkedList<Position>
public class Caterpillar extends MyDoublyLinkedList<Position> {

    //constructor takes no input
    public Caterpillar(){
        super();
        //initial size is 1
        //initial position is (7,7)
        Position head = new Position(7, 7);
        addFirst(head);
    }

    //getHead method return the position of the head
    public Position getHead(){
        return peekFirst();
    }

    //eat method adds the input to the front of the list
    public void eat(Position position){
        int distanceX = Math.abs(position.getX() - getHead().getX());
        int distanceY = Math.abs(position.getY() - getHead().getY());

        if(!(distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)){
            throw new IllegalArgumentException("The input position is not adjacent to the current head position.");
        }

        addFirst(position);
    }

    //move method adds the input to the front and remove the last one
    public void move(Position position){
        int distanceX = Math.abs(position.getX() - getHead().getX());
        int distanceY = Math.abs(position.getY() - getHead().getY());

        //if it is orthogonally adjacent
        if(!(distanceX == 1 && distanceY == 0) || (distanceX == 0 && distanceY == 1)){
            throw new IllegalArgumentException("The input position is not adjacent to the current head position.");
        }

        addFirst(position); //adds the input to the front
        removeLast(); //remove the last
    }

    //selfCollision method reflects if the input overlaps one of the body parts
    public boolean selfCollision(Position newPosition) {
        for (Position p : this) {
            if (newPosition.equals(p)) {
                return true;
            }
        }
        return false;
    }

}