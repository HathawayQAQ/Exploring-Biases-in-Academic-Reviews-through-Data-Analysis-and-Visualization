public class TargetQueue extends MyQueue<Position> {
    
    //private fields
    private MyStack<String> stack;

    //the constructor should invoke the constructor of the superclass and initialize its fields
    public TargetQueue() {
        super();
        this.stack = new MyStack<String>();
    }

    //clear method clears this queue and its stack field
    public void clear() {
        super.clear(); //clear MyQueue
        this.stack.clear(); //clear stack field
    }

    //addTargets method takes the String input and parses it into a queue of Positions
    public void addTargets(String theInput){
        if(theInput == null || theInput.isEmpty()) {
            throw new IllegalArgumentException("The syntax of the input is emptyß.");
        }
        String num = ""; //local String variable to store the numbers contained in the input string

        // If so, push "(" onto the stack
        for (int i = 0; i < theInput.length(); i++) {
            char c = theInput.charAt(i);

            //if the character is a left parenthesis, the stack and num should both be empty
            if (c == '(') {
                if (!(this.stack.getSize() == 0)) {
                    throw new IllegalArgumentException("any of the elements are missing 3333333"+this.getSize());
                }
                if (!this.stack.isEmpty() || !num.isEmpty()) {
                    throw new IllegalArgumentException("It's empty.");
                }
                this.stack.push("(");
            } 

            //if the character is a digit, append the digit to num
            else if (Character.isDigit(c)) {
                num += c;
            } 

            //if the character is a comma, check if num is a valid integer
            else if (c == ',') {
                if (!(this.stack.getSize() == 1)) {
                    throw new IllegalArgumentException("any of the elements are missing 474747"+this.getSize());
                }
                else if (!(this.stack.peek().equals("("))) {
                    throw new IllegalArgumentException("it is not correct ");
                }
                {
                try {
                    int number = Integer.parseInt(num);
                } catch (IllegalArgumentException e) {
                    // TODO: handle exception
                    System.out.println("The num is not a valid integer.");
                }
                //if num is empty, we must have read a comma before the x-coordinate
                if (num.isEmpty()) {
                    throw new IllegalArgumentException("The num is empty.");
                }
                
                }

                //push num and "," onto the stack
                this.stack.push(num);
                this.stack.push(",");
                //reset num
                num = "";

            } 
            //if the character is a right parenthesis
            else if (c == ')') { //(1,
                //the order is not correct or any of the elements are missing
                // if (this.stack.getSize() < 3 || !this.stack.peek().equals(",")) {
                //     throw new IllegalArgumentException("the order is not correct or any of the elements are missing");
                // }
                if (!(this.stack.getSize() == 3)) {
                    throw new IllegalArgumentException("any of the elements are missing 646464");
                }
                else if (!(this.stack.peek().equals(","))) {
                    throw new IllegalArgumentException("the order is not correct ");
                }

                //num should be an integer representing the y-coordinate
                String yStr = num;
                //if num is empty
                if (yStr.isEmpty()) {
                    throw new IllegalArgumentException("We must have reached the right parenthesis without finding a y-coordinate.");
                }

                //add the x-y coordinate as a Position object onto the queue and reset num
                String comma = this.stack.pop();
                if (!comma.equals(",")) {
                    throw new IllegalArgumentException("Invalid syntax");
                }
                String xStr = this.stack.pop();
                if (xStr.equals(",")){
                    throw new IllegalArgumentException("haha"+comma+xStr);
                }
                // if (!isInteger(xStr) || !isInteger(yStr)) {
                //     throw new IllegalArgumentException("Invalid syntax");
                // }
                int x = Integer.parseInt(xStr);
                int y = Integer.parseInt(yStr);
                this.enqueue(new Position(x, y));
                num = "";
                this.stack.pop();
                if (!stack.isEmpty()) {
                    throw new IllegalArgumentException("it is not empty.");
                }
                }
                
                //if the character is a period
                else if (c == '.') {
                    //the stack and num should both be empty
                    if (!stack.isEmpty() || !num.isEmpty()) {
                        throw new IllegalArgumentException("Invalid syntax");
                    }
                    
                } else {
                    throw new IllegalArgumentException("Invalid character: " + c);
                }
        }
        //if the stack and num is not empty
        if (!stack.isEmpty() || !num.isEmpty()) {
            throw new IllegalArgumentException("Invalid syntax");
        }
    }
}
