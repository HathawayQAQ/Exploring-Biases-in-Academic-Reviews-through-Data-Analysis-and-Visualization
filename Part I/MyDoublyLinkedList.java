import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> { //it extends the MyLinkedList<E>

	//private fields
	private DNode head;
	private DNode tail;
	
	//My code starts:

	//private class called DNode
	public class DNode{
		//private fields
		private E element;
        private DNode next;
        private DNode prev;

		public DNode(E element, DNode next, DNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
	}

	@Override
	//Implete the public method add, remove and clear
	public void add(E element) {
        DNode newNode = new DNode(element, null, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } 
		else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

	@Override 
	public E remove(){
		//when the list is empty throw error
		if (tail == null) {
            throw new NoSuchElementException();
        }
		//implete the removedElement as the tail.element
        E removedElement = tail.element;

        if (tail.prev != null) {
            tail.prev.next = null;
        } 
		else {
			//if there's no prev, then the head is null itself
            head = null;
        }

        tail = tail.prev;
        size--;
        return removedElement;
	}

	@Override
	public void clear(){
		head = null;
		tail = null;
		size = 0;
	}

	//Add the public class addFirst
	public void addFirst(E element){
		DNode newNode = new DNode(element, null, head); //implement the new node

		//if have sth as the head, then add the new node before the current head
		if(head != null){
			head.prev = newNode;
		}
		else{
			tail = newNode;
		}
		size++;
		head = newNode;
	}

	//Add the public class addLast
	public void addLast(E element){
		DNode newNode = new DNode(element, tail, null);

		if(tail != null){
			tail.next = newNode;
			newNode.prev = tail;
		}
		else{
			head = newNode;
		}
		size++;
		tail = newNode;
	}

	//Add the public class removeFirst
	public E removeFirst(){

		//raise error when the list is empty
		if(isEmpty()){
			throw new NoSuchElementException("The list is empty.");
		}

		E element = head.element;
		//there is only one element
		if (head == tail){
			head = null;
			tail = null;
		}
		else{
			head = head.next;
			head.prev = null;
		}
		size--;
		return element; //the head element
	}

	//Add the public class removeLast
	public E removeLast(){

		//raise error when the list is empty
		if(isEmpty()){
			throw new NoSuchElementException("The list is empty.");
		}

		E element = tail.element;
		//there is only one element
		if (head == tail){
			head = null;
			tail = null;
		}
		else{
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return element;
	}

	//Add the public class peekFirst return the first element without removing it
	public E peekFirst(){
		if (head == null){
			throw new NoSuchElementException("The list is empty.");
		}
		return head.element;
	}

	//Add the public class peekLast return the last element without removing it
	public E peekLast(){
		if (tail == null){
			throw new NoSuchElementException("The list is empty.");
		}
		return tail.element;
	}

	//Add the equals method and return true if all the elements are equal
	public boolean equals(Object object){
		//judge if they are the same class
		if (!(object instanceof MyLinkedList)){
			return false;
		}

		MyDoublyLinkedList<E> otherDoublyLinkedList = (MyDoublyLinkedList<E>) object;

		//first of all compare their size
		if(otherDoublyLinkedList.size != this.size){
			return false;
		}

		//implete 2 new nodes
		DNode newNode = head;
		DNode otherNode = otherDoublyLinkedList.head;

		//if the current node is not null
		if(newNode != null){
			//if they are not equal
			if (!(newNode.element.equals(otherNode.element))) {
                return false;
            } 
			newNode = newNode.next;
            otherNode = otherNode.next;
        }
        return true;
    }

	//My code ends
	

	
	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	// private class DNode {
	// 	private E element;
	// 	private DNode next;
	// 	private DNode prev;
	// }

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
