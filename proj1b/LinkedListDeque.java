public class LinkedListDeque<T> implements Deque<T> {

    /**
     * Define Node class
     */
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Init LinkedListDeque empty
     */
    public LinkedListDeque() {
        Sentinel = new Node(null, null, null);
        size = 0;
    }

    /**
     * Init LinkedListDeque by item
     */
    public LinkedListDeque(T item) {
        Node first = new Node(item, null, null);
        Sentinel = new Node(null, null, first);
        first.prev = first;
        first.next = first;
        size = 1;
    }

    /**
     * Init LinkedListDeque by deepcopy
     */
    public LinkedListDeque(LinkedListDeque other) {
        Sentinel = new Node(null, null, null);
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i)); // (T) is cast, since type of other is unknown
        }
    }

    /**
     * Define member
     */
    private Node Sentinel;
    private int size = 0;

    /**
     * AddFirst define
     * @param item
     */
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            Node first = new Node(item, null, null);
            first.prev = first;
            first.next = first;
            Sentinel.next = first;
        } else {
            Node old_first = Sentinel.next;
            Node last = old_first.prev;
            Node first = new Node(item, last, old_first);
            /**
             * Sentinel -> first
             * last <-> first <-> old_first
             */
            last.next = first;
            old_first.prev = first;
            Sentinel.next = first;
        }
        size += 1;
    }

    /**
     * AddLast define
     * @param item
     */
    @Override
    public void addLast(T item) {
        /* equal to addFirst */
        if (size == 0) {
            addFirst(item);
        } else {
            Node first = Sentinel.next;
            Node old_last = first.prev;
            Node last = new Node(item, old_last, first);
            /**
             * old_last <-> last <-> first
             */
            old_last.next = last;
            first.prev = last;
        }
        size += 1;
    }

    /**
     * Get size by size()
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * RemoveFirst define
     */
    @Override
    public T removeFirst() {
        Node first = Sentinel.next;
        T item = first.item;
        Node last = first.prev;
        Node secend = first.next;
        /**
         * last <-> secend
         */
        Sentinel.next = secend;
        secend.prev = last;
        last.next = secend;
        size -= 1;
        return item;
    }

    /**
     * RemoveLast define
     */
    @Override
    public T removeLast() {
        Node last = Sentinel.next.prev;
        T item = last.item;
        Node s_last = last.prev;
        Node first = last.next;
        /**
         * s_last <-> first
         */
        first.prev = s_last;
        s_last.next = first;
        size -= 1;
        return item;
    }

    /**
     * Get according to index
     * @param index
     * @return
     */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node idx = Sentinel.next;
            for (int i = 0; i < index; i++) {
                idx = idx.next;
            }
            return idx.item;
        }
    }

    @Override
    public void printDeque() {
        Node curr = Sentinel.next;
        while (curr != Sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private T getRecursivehelper(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        } else {
           return getRecursivehelper(index - 1, curr.next);
        }
    }

    public T getRecursive(int index) {
        return getRecursivehelper(index, Sentinel.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<String> list = new LinkedListDeque("3");
        list.addFirst("2");
        list.addFirst("1");
        list.addLast("4");
        LinkedListDeque<String> cp_ls = new LinkedListDeque(list);
        int length = list.size();
        for (int i = 0; i < length; i++) {
            System.out.println(cp_ls.getRecursive(i));
        }
    }
}
