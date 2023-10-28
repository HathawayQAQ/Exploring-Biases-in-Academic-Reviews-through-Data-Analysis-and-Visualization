import java.util.Iterator;

public interface MyList<E> extends Iterable<E> { //extends the interface
    int getSize();
    boolean isEmpty();
    void add(E element);
    void clear();
    E remove();

    @Override
    Iterator<E> iterator();
}
