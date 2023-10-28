public class World {
    //private fields
    private Caterpillar caterpillar;
    private Position foodPosition;
    private Region mapRegion;
    private ActionQueue actionQueue;
    private TargetQueue targetQueue;
    private GameState gameState;

    //constructor takes two arguments
    public World(TargetQueue targetQueue, ActionQueue actionQueue){ 
        this.targetQueue = targetQueue;
        this.actionQueue = actionQueue;

        //The Region of the game is set as x ∈ [0,15],y ∈ [0,15]
        this.mapRegion = new Region(0, 0, 15, 15);
        //initial state (7,7)
        this.caterpillar = new Caterpillar();
        this.caterpillar.add(new Position(7, 7));

        if (targetQueue.isEmpty()) {
            throw new IllegalArgumentException("targetQueue must have at least one food position");
        }
        this.foodPosition = targetQueue.dequeue();;

        // Set the game state to MOVE
        this.gameState = GameState.MOVE; 
    }

    public void step() {
        //If the ActionQueue is empty, set the GameState to NO MORE ACTION
        if (actionQueue.isEmpty()) {
            this.gameState = GameState.NO_MORE_ACTION;
        }
        else{
        Direction direction = this.actionQueue.dequeue();
        
        //if the GameState is not MOVE or EAT, return
        if ((this.gameState != GameState.MOVE) && (this.gameState != GameState.EAT)) {
            return;
        }

        //get the current head and next action
        Position currentHead = this.caterpillar.getHead();

        switch(direction){
            case NORTH:
                currentHead.moveNorth();
                break;
            case SOUTH:
                currentHead.moveSouth();
                break;
            case WEST:
                currentHead.moveWest();
                break;
            case EAST:
                currentHead.moveEast();
                break;
        }

        //if the next position will result in the caterpillar moving out of the map
        if (!(this.mapRegion.contains(currentHead))) {
            this.gameState = GameState.WALL_COLLISION;
            return;
        }

        //if the next position will result in a self-collision of the caterpillar
        if (this.caterpillar.selfCollision(currentHead)) {
            this.gameState = GameState.SELF_COLLISION;
            return;
        }

        //if the next one is food
        if (currentHead.equals(this.foodPosition)) {
            //then the caterpillar can “eat” it
            this.caterpillar.eat(foodPosition);

            //here is not more food on the TargetQueue
            if (this.targetQueue.isEmpty()) {
                //set the GameState to DONE
                this.gameState = GameState.DONE;
            } 
            else {
                this.foodPosition = targetQueue.dequeue();
                this.gameState = GameState.EAT;
            }
        } 
        else {
            this.caterpillar.move(currentHead);
            this.gameState = GameState.MOVE;
        }
        }

    }

    //getState method that returns the current state of the game
    public GameState getState(){
        return this.gameState;
    }
    
    //getCaterpillar method that returns the caterpillar
    public Caterpillar getCaterpillar() {
        return this.caterpillar;
    }
    
    //getFood method that returns the food position
    public Position getFood() {
        return this.foodPosition;
    }
    
    //isRunning method that returns true if the game is still running
    public boolean isRunning() {
        return this.gameState == GameState.MOVE || this.gameState == GameState.EAT;
    }
}
