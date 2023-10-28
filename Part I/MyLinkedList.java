public abstract class MyLinkedList<E> implements MyList<E> {

    //A int indicating the size of the list.
    protected int size;

    //implement the public methods getSize() and isEmpty()
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
