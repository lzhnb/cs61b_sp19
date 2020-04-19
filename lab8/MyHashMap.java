import javax.swing.text.html.parser.Entity;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int numEntries = 0;
    private int numBuckets;
    private double maxAvgBucketSize;
    private HashSet<K> keySet;
    private ArrayList<ArrayList<Entry>> buckets;

    private class Entry {
        private K key;
        private V value;
        public Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        numBuckets = initialSize;
        maxAvgBucketSize = loadFactor;
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }

    @Override
    public void clear() {
        numEntries = 0;
        keySet = new HashSet<>();
        buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (!keySet.contains(key)) {
            return null;
        } else {
            int idx = hash(key);
            for (Entry entry : buckets.get(idx)) {
                if (key.equals(entry.key)) {
                    return entry.value;
                }
            }
            return null;
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % numBuckets;
    }

    @Override
    public int size() {
        return numEntries;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (keySet.contains(key)) {
            update(key, value);
        } else {
            if (size() >= numBuckets * maxAvgBucketSize) {
                resize(numBuckets * 2);
            }
            int idx = hash(key);
            buckets.get(idx).add(new Entry(key, value));
            keySet.add(key);
            numEntries += 1;
        }
    }

    public void resize(int capacity) {
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(new ArrayList<>());
        }
        for (K key : keySet) {
            int idx = hash(key);
            newBuckets.get(idx).add(new Entry(key, get(key)));
        }
        numBuckets = capacity;
        buckets = newBuckets;
    }

    private void update(K key, V value) {
        int idx = hash(key);
        for (Entry entry: buckets.get(idx)) {
            if (key.equals(entry.key)) {
                entry.value = value;
            }
        }
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int idx = hash(key);
        for (Entry entry : buckets.get(idx)) {
            if (key.equals(entry.key)) {
                V value = entry.value;
                buckets.get(idx).remove(key);
                numEntries -= 1;
                keySet.remove(key);
                return  value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        int idx = hash(key);
        for (Entry entry : buckets.get(idx)) {
            if (key.equals(entry.key) && value.equals(entry.value)) {
                buckets.get(idx).remove(key);
                numEntries -= 1;
                keySet.remove(key);
                return  value;
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
