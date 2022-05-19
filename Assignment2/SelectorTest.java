import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

class SelectorTest {

    /**
     * Defines a total order on integers as ascending natural order.
     */
    static Comparator<Integer> ascendingInteger =
            new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    return i1.compareTo(i2);
                }
            };

// Min tests
@Test public <Int> void minTest1() {
    Integer[] a = {2, 8, 7, 3, 4};
    Collection<Integer> coll = Arrays.asList(a);
    Integer expected = 2;
    Integer actual = Selector.<Integer>min(coll, ascendingInteger);
    assertEquals(expected, actual);
}

    @Test public <Int> void minTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 1;
        Integer actual = Selector.<Integer>min(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void minTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>min(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void minTest4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 2;
        Integer actual = Selector.<Integer>min(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

// Max tests
    @Test public <Int> void maxTest1() {
        Integer[] a = {2, 8, 7, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>max(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void maxTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 9;
        Integer actual = Selector.<Integer>max(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void maxTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>max(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void maxTest4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>max(coll, ascendingInteger);
        assertEquals(expected, actual);
    }

//kmin tests
@Test public <Int> void kminTest1() {
    Integer[] a = {2, 8, 7, 3, 4};
    Collection<Integer> coll = Arrays.asList(a);
    Integer expected = 2;
    Integer actual = Selector.<Integer>kmin(coll, 1, ascendingInteger);
    assertEquals(expected, actual);
}

    @Test public <Int> void kminTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 5;
        Integer actual = Selector.<Integer>kmin(coll, 3, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void kminTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>kmin(coll, 5, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void kminTest4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>kmin(coll, 3, ascendingInteger);
        assertEquals(expected, actual);
    }

    //kmax tests
    @Test public <Int> void kmaxTest1() {
        Integer[] a = {2, 8, 7, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>kmax(coll, 1, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void kmaxTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 5;
        Integer actual = Selector.<Integer>kmax(coll, 3, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void kmaxTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>kmax(coll, 5, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void kmaxTest4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>kmax(coll, 3, ascendingInteger);
        assertEquals(expected, actual);
    }

//range tests
//@Test public <Int> void rangeTest1() {
  //  Integer[] a = {2, 8, 7, 3, 4};
    //Collection<Integer> coll = Arrays.asList(a);
    //int[] expected = new int[]{2, 3, 4};
    //Collection<Integer> actual = Selector.range(coll, 1, 5, ascendingInteger);
    //assertEquals(expected, actual);
//}

//  ceiling test
@Test public <Int> void ceilingTest1() {
    Integer[] a = {2, 8, 7, 3, 4};
    Collection<Integer> coll = Arrays.asList(a);
    Integer expected = 2;
    Integer actual = Selector.<Integer>ceiling(coll, 1, ascendingInteger);
    assertEquals(expected, actual);
}

    @Test public <Int> void ceilingTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 7;
        Integer actual = Selector.<Integer>ceiling(coll, 7, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void ceilingTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>ceiling(coll, 0, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void ceiling4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 7;
        Integer actual = Selector.<Integer>ceiling(coll, 5, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void ceiling5() {
        Integer[] a = {null};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 0;
        Integer actual = Selector.<Integer>ceiling(coll, 1, ascendingInteger);
        assertEquals(expected, actual);

    }


    //floor tests
    @Test public <Int> void floorTest1() {
        Integer[] a = {2, 8, 7, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>floor(coll, 6, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void floorTest2() {
        Integer[] a = {5, 9, 1, 7, 3};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 1;
        Integer actual = Selector.<Integer>floor(coll, 1, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void floorTest3() {
        Integer[] a = {8, 7, 6, 5, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 8;
        Integer actual = Selector.<Integer>floor(coll, 9, ascendingInteger);
        assertEquals(expected, actual);
    }

    @Test public <Int> void floorTest4() {
        Integer[] a = {8, 2, 8, 7, 3, 3, 4};
        Collection<Integer> coll = Arrays.asList(a);
        Integer expected = 4;
        Integer actual = Selector.<Integer>floor(coll, 5, ascendingInteger);
        assertEquals(expected, actual);
    }


}