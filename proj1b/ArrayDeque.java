public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /**
     * Create an empty ArrayDeque.
     */
    public ArrayDeque() {
        // Java does not allow to create new generic array directly. So need cast.
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /**
     * Create an empty ArrayDeque by deepcopy.
     */
    public ArrayDeque(ArrayDeque other) {
        // Java does not allow to create new generic array directly. So need cast.
        items = (T[]) new Object[other.size()];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size();

        System.arraycopy(other.items, 0, items, 0, items.length);
    }

    /**
     * Return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return true if deque is full, false otherwise.
     */
    private boolean isFull() {
        return size == items.length;
    }

    private void resize(int capacity) {
        T[] newDeque = (T[]) new Object[capacity];
        int old_first = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newDeque[i] = items[old_first];
            old_first = plusOne(old_first);
        }
        items = newDeque;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * upsize implementation double call resize()
     */
    private void upsize() {
        resize(size * 2);
    }

    /**
     * downsize implementation double call resize()
     */
    private void downsize() {
        resize(items.length / 2);
    }

    /**
     * Whether to downsize the deque.
     */
    private boolean isSparse() {
        return items.length >= 16 && size < (items.length / 4);
    }

    /**
     * Add one circularly
     * @param index
     */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /**
     * Add one circularly
     * @param index
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /**
     * Implementation of addFirst
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            upsize();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /**
     * Implementation of addLast
     * @param item
     */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            upsize();
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Implementation of removeFirst
     * @return item
     */
    @Override
    public T removeFirst() {
        if (isSparse()) {
            downsize();
        }
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return item;
    }

    /**
     * Implementation of removeLast
     * @return item
     */
    @Override
    public T removeLast() {
        if (isSparse()) {
            downsize();
        }
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return item;
    }

    /**
     * Implementation of get
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);
        T item = items[(start + index) % items.length];
        return item;
    }

    /**
     * Implementation of printDeque
     */
    @Override
    public void printDeque() {
        int counter = 0;
        // To get the first element we must look at nextFirst plusOne
        int curr = plusOne(nextFirst);
        // We only need to look up to the size
        while (counter < size) {
            System.out.print(items[curr] + " ");
            curr = plusOne(curr);
            counter++;
        }
        // Print out a line after
        System.out.println();
    }
}
