package structures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenericArrayListTest {
    /*
    I would like you all to write some automated testing methods
    in junit
    using:
    @Test
    some form of assertion
    (assertTrue, assertEquals)

    As well as continuing to work on your Arraylist implementations
    (make them generic if you like, it might help you
    on your projects)
     */
    GenericArrayList<String> lists;
    GenericArrayList<Integer> listi;

    @BeforeEach
    void before(){
        lists = new GenericArrayList<>(5);
        lists.add("One");
        lists.add("Two");
        lists.add("Three");
        listi = new GenericArrayList<>(3);
        listi.add(1);
        listi.add(2);
        listi.add(3);
    }

    @Test
    void get() {
        assertEquals("Three", lists.get(2));

        assertEquals(1, listi.get(0));
    }

    @Test
    void findIndex() {
        assertEquals(1, lists.findIndex("Two"));

        assertEquals(-1, listi.findIndex(5));
    }

    @Test
    void add() {
        lists.add("Four");
        lists.add("Five");
        assertEquals("Five", lists.get(4));
        assertEquals("Four", lists.get(3));

        listi.add(new Integer[]{4, 5, 6, 7});
        System.out.println(listi.toString());
        assertEquals(7, listi.get(6));
        assertEquals(4, listi.findIndex(5));
    }

    @Test
    void contains() {
        lists.add("Ten");
        assertTrue(lists.contains("Ten"));
        assertFalse(lists.contains("Nine"));
        assertTrue(listi.contains(3));
        assertFalse(listi.contains(0));
    }

    @Test
    void testEquals() {
        GenericArrayList<String> lists2 = new GenericArrayList<>();
        lists2.add(new String[]{"One","Two","Three"});
        assertTrue(lists.equals(lists2));
        lists2.add("Four");
        assertFalse(lists.equals(lists2));
    }

    @Test
    void set() {
        assertEquals("Three", lists.get(2));
        lists.set(2,"Four");
        assertEquals("Four", lists.get(2));
        lists.set(4,"Five");
        assertNull(lists.get(4));
    }

    @Test
    void size() {
        assertEquals(3, lists.size());
        lists.add(new String[]{"Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"});
        assertEquals(10, lists.size());
    }
}