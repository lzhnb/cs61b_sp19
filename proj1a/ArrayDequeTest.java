import static org.junit.Assert.*;
import org.junit.Test;

public class ArrayDequeTest {

    /** Empty unit test. */
    @Test
    public void addRemoveTest() {
        ArrayDeque<String> test = new ArrayDeque<>();
        assertEquals(true, test.isEmpty());

        test.addFirst("front");
        assertEquals(false, test.isEmpty());

        test.removeFirst();
        assertEquals(true, test.isEmpty());
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("front");
        assertEquals(1, test.size());

        test.addLast("middle");
        assertEquals(2, test.size());

        test.addLast("back");
        assertEquals(3, test.size());
    }

    /** Adds ten thousand items, removes ten thousand, ensures that it is empty */
    @Test
    public void addAlotTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();

        assertEquals(true, test.isEmpty());

        for (int i = 0; i < 100000; i++) {
            test.addFirst(1);
        }
        assertEquals(false, test.isEmpty());

        for (int i = 0; i < 100000; i++) {
            test.removeFirst();
        }
        assertEquals(true, test.isEmpty());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(5);
        test.addLast(10);
        test.addLast(20);
        test.addLast(40);
        test.addFirst(1);
        int get1 = test.get(0);
        int get2 = test.get(1);
        int get3 = test.get(2);
        assertEquals(1, get1);
        assertEquals(5, get2);
        assertEquals(10, get3);
        assertEquals(null, test.get(6));
    }
}
