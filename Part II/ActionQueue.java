public class ActionQueue extends MyQueue<Direction> {
    //private fields

    //constructor takes care of initializing
    public ActionQueue(){
        super();
    }

    @Override
    //clear method that removes all items from this queue
    public void clear(){
        super.clear();
    }

    //loadFromEncodedString method convert the input into a queue of Direction
    public void loadFromEncodedString(String encodedMessage) throws IllegalArgumentException {
        // MyQueue<Direction> directions = new MyQueue<>();
        String parsedString = Parse(encodedMessage);
        for (int i = 0; i < parsedString.length(); i++) {
            char c = parsedString.charAt(i);
            if (c == 'N') {
                this.enqueue(Direction.NORTH);
            } else if (c == 'S') {
                this.enqueue(Direction.SOUTH);
            } else if (c == 'E') {
                this.enqueue(Direction.EAST);
            } else if (c == 'W') {
                this.enqueue(Direction.WEST);
            } 
            else {
                // throw new IllegalArgumentException("Invalid character in encoded message: " + c);
                continue;
            }
        }
        //return directions;
    }
    
    public int find(int l,String encodeMessage){
        int stk=0;
        for(int i=l;l<encodeMessage.length();i++){
            if(encodeMessage.charAt(i)=='[') stk++;
            if(encodeMessage.charAt(i)==']')stk--;
            if(stk==0)return i;
        }
        return -1;
    }
    public String Parse(String encodedMessage){
        String ans="";
        if(encodedMessage.length() == 0){
            return ans;
        }
        int multiply=1;
        for(int i=0;i<encodedMessage.length();i++){
           // if(encodedMessage.charAt(i)==']'||encodedMessage.charAt(i)=='[')continue;
            if(Character.isDigit(encodedMessage.charAt(i))){
                multiply=Integer.parseInt(encodedMessage.substring(i, i + 1));
                int l=i+1,r=find(l,encodedMessage);
                if(encodedMessage.charAt(l)!='['||r==-1)throw new IllegalArgumentException("No Symbol After Number");
                if(r-1<l+1)throw new IllegalArgumentException("No item");
                ans+=(encodedMessage.substring(l+1, r)).repeat(multiply);
                multiply=1;
                i=r;
            }else{
                ans+=encodedMessage.charAt(i);
            }
        }
        return ans;
    }
}