import java.util.NoSuchElementException;

public class MyStack<E> {

    //private fields store the elements of the stack
    private MyDoublyLinkedList<E> stack;

    //Constructor takes no input and creates the empty stack
    public MyStack(){
        stack = new MyDoublyLinkedList<>();
    }

    //push method takes type E input and add the input into the stack top
    public void push(E element){
        stack.addLast(element);
    }

    //pop method removes the top and return it
    public E pop(){
        if (isEmpty()){
            throw new NoSuchElementException("The stack is empty.");
        }
        return stack.removeLast();
    }

    //peek method return the top
    public E peek(){
        if (isEmpty()){
            throw new NoSuchElementException("The stack is empty.");
        }
        return stack.peekLast();
    }

    //isEmpty method reflects the stack is empty or not
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    //clear method clears the stack
    public void clear(){
        stack.clear();
    }

    //getSize method returns the num of elements
    public int getSize(){
        return stack.getSize();
    }
}