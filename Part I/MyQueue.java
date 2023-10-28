import java.util.NoSuchElementException;

public class MyQueue<E> {

    //private fields
    private MyDoublyLinkedList<E> queue;

    //constructor that takes no inputs and creates an empty queue
    public MyQueue(){
        queue = new MyDoublyLinkedList<>();
    }

    //enqueue method that takes an input of type E and adds it to the queue back
    public void enqueue(E element){
        queue.addLast(element);
    }

    //dequeue method removes the first element and returns it
    public E dequeue(){
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
        return queue.removeFirst();
    }

    //isEmpty method reflects the queue is empty or not
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    //clear method that clears the queue
    public void clear(){
        queue.clear();
    }

    //equals method that takes as input an Object and return true if inputs are equal
    public boolean equals(Object obj) {
        if (obj instanceof MyQueue) {
            MyQueue<E> other = (MyQueue<E>) obj;
            return queue.equals(other.queue);
        }
        return false;
    }

    //getSize method returns the num of elements
    public int getSize() {
        return queue.getSize();
    }

}
