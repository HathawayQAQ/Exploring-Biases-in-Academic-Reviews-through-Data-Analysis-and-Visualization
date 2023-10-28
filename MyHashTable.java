import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
//import java.util.NoSuchElementException;

public class MyHashTable<K, V> implements Iterable<MyPair<K, V>> {

    // num of entries to the table
    private int size;
    // num of buckets
    private int capacity = 16;
    // load factor needed to check for rehashing
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<MyPair<K, V>>> buckets;

    // constructors
    public MyHashTable() {
        // ADD YOUR CODE BELOW THIS
        this.size = 0;
        this.buckets = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            this.buckets.add(new LinkedList<>());
        }
        // ADD YOUR CODE ABOVE THIS
    }

    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.size = 0;
        this.capacity = initialCapacity;
        this.buckets = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            this.buckets.add(new LinkedList<>());
        }
        // ADD YOUR CODE ABOVE THIS
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int numBuckets() {
        return this.capacity;
    }

    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList<MyPair<K, V>>> getBuckets() {
        return this.buckets;
    }

    /**
     * Given a key, return the bucket position for the key.
     */
    public int hashFunction(K key) {
        return Math.abs(key.hashCode()) % this.capacity;
    }

    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        // ADD YOUR CODE BELOW HERE
        int hashValue = this.hashFunction(key);
        for (MyPair<K, V> pair : this.buckets.get(hashValue)) {
            if (pair.getKey().equals(key)) {
                V oldValue = pair.getValue();
                pair.setValue(value);
                return oldValue;
            }
        }
        this.buckets.get(hashValue).add(new MyPair<>(key, value));
        this.size++;
        if (this.size > this.capacity * MAX_LOAD_FACTOR) {
            this.rehash();
        }
        return null;
        // ADD YOUR CODE ABOVE HERE
    }


    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    public V get(K key) {
        // ADD YOUR CODE BELOW HERE
        int hashValue = this.hashFunction(key);
        for (MyPair<K, V> pair : this.buckets.get(hashValue)) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
        // ADD YOUR CODE ABOVE HERE
    }

    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1)
     */
    public V remove(K key) {
        // ADD YOUR CODE BELOW HERE
        int hashValue = this.hashFunction(key);
        for (MyPair<K, V> pair : this.buckets.get(hashValue)) {
            if (pair.getKey().equals(key)) {
                V oldValue = pair.getValue();
                this.buckets.get(hashValue).remove(pair);
                this.size--;
                return oldValue;
            }
        }
        return null;
        // ADD YOUR CODE ABOVE HERE
    }

    /**
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
        // ADD YOUR CODE BELOW HERE
        int oldCapacity = this.capacity;
        ArrayList<LinkedList<MyPair<K, V>>> oldBuckets = this.buckets;
        this.capacity *= 2;
        this.buckets = new ArrayList<>(this.capacity);
        for (int i = 0; i < this.capacity; i++) {
            this.buckets.add(new LinkedList<>());
        }
        for (int i = 0; i < oldCapacity; i++) {
            for (MyPair<K, V> pair : oldBuckets.get(i)) {
                int hashValue = this.hashFunction(pair.getKey());
                this.buckets.get(hashValue).add(pair);
            }
        }
        // ADD YOUR CODE ABOVE HERE
    }

    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public ArrayList<K> getKeySet() {
        // ADD YOUR CODE BELOW HERE
        ArrayList<K> keys = new ArrayList<>();
        for (LinkedList<MyPair<K, V>> bucket : this.buckets) {
            for (MyPair<K, V> pair : bucket) {
                keys.add(pair.getKey());
            }
        }
        return keys;
        // ADD YOUR CODE ABOVE HERE
    }

    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> getValueSet() {
        // ADD CODE BELOW HERE
        ArrayList<V> values = new ArrayList<>();
        for (LinkedList<MyPair<K, V>> bucket : this.buckets) {
            for (MyPair<K, V> pair : bucket) {
                if (!values.contains(pair.getValue())) {
                    values.add(pair.getValue());
                }
            }
        }
        return values;
        // ADD CODE ABOVE HERE
    }

    /**
     * Returns an ArrayList of all the key-value pairs present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<MyPair<K, V>> getEntries() {
        // ADD CODE BELOW HERE
        ArrayList<MyPair<K, V>> entries = new ArrayList<>();
        for (LinkedList<MyPair<K, V>> bucket : this.buckets) {
            entries.addAll(bucket);
        }
        return entries;
        // ADD CODE ABOVE HERE
    }

    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }

    private class MyHashIterator implements Iterator<MyPair<K, V>> {

        private int index;
        private Iterator<MyPair<K, V>> bucketIterator;

        private MyHashIterator() {
            // ADD YOUR CODE BELOW HERE
            for (int i = 0; i < MyHashTable.this.capacity; i++) {
                if (MyHashTable.this.buckets.get(i).size() > 0) {
                    this.index = i;
                    this.bucketIterator = MyHashTable.this.buckets.get(i).iterator();
                    return;
                }
            }
            this.index = MyHashTable.this.capacity - 1;
            this.bucketIterator = MyHashTable.this.buckets.get(this.index).iterator();
            // ADD YOUR CODE ABOVE HERE
        }

        @Override
        public boolean hasNext() {
            // ADD YOUR CODE BELOW HERE
            if (this.bucketIterator.hasNext()) {
                return true;
            }
            for (int i = this.index + 1; i < MyHashTable.this.capacity; i++) {
                if (!MyHashTable.this.buckets.get(i).isEmpty()) {
                    return true;
                }
            }
            return false;
            // ADD YOUR CODE ABOVE HERE
        }

        @Override
        public MyPair<K, V> next() {
            // ADD YOUR CODE BELOW HERE
            if (this.bucketIterator.hasNext()) {
                return this.bucketIterator.next();
            }
            for (int i = this.index + 1; i < MyHashTable.this.capacity; i++) {
                if (!MyHashTable.this.buckets.get(i).isEmpty()) {
                    this.index = i;
                    this.bucketIterator = MyHashTable.this.buckets.get(i).iterator();
                    return this.bucketIterator.next();
                }
            }
//            throw new NoSuchElementException();
            throw new RuntimeException("No more elements");
            // ADD YOUR CODE ABOVE HERE
        }

    }

}
