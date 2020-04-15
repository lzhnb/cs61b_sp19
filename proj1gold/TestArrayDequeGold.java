import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testTask1() {
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();

        // addFirst
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            solution.addFirst(random);
            student.addFirst(random);
        }
        for (int i=0; i<10; i++) {
            int actual = solution.get(i);
            int expected = student.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // addLast
        for (int i=0; i<10; i++) {
            int random = StdRandom.uniform(100);
            solution.addLast(random);
            student.addLast(random);
        }
        for (int i=0; i<10; i++) {
            int actual = solution.get(i);
            int expected = student.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            actualList.add(solution.removeFirst());
            expectedList.add(student.removeFirst());
        }
        for (int i=0; i<10; i++) {
            int actual = solution.get(i);
            int expected = student.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i=0; i<10; i++) {
            actualList.add(solution.removeLast());
            expectedList.add(student.removeLast());
        }
        int actual = solution.size();
        int expected = student.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   actual size " + actual
                        + " not equal to " + expected + "!",
                expected, actual);
        for (int i=0; i<10; i++) {
            assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
                            + " not equal to " +  expectedList.get(i) + "!",
                    expectedList.get(i), actualList.get(i));
        }
    }

    @Test
    public void testTask2(){
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        int random = StdRandom.uniform(100);
        solution.addFirst(random);
        student.addFirst(random);
        assertEquals("addFirst("+random+")", solution.get(0), student.get(0));
        System.out.println("addFirst("+random+")");

        random = StdRandom.uniform(100);
        solution.addLast(random);
        student.addLast(random);
        assertEquals("addLast("+random+")", solution.get(1), student.get(1));
        System.out.println("addLast("+random+")");

        int actual = solution.removeFirst();
        int expected = solution.removeFirst();
        assertEquals("removeFirst()", actual, expected);
        System.out.println("removeFirst()");

        actual = solution.removeLast();
        expected = student.removeLast();
        assertEquals("removeLast()", actual, expected);
        System.out.println("removeLast()");
    }
}
