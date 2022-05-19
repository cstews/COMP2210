import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SelectorTest {
    @Test public void minTest1() {
        int[] a = new int[]{2, 8, 7, 3, 3};
        int expected = 2;
        int actual = Selector.min(a);
        assertEquals(expected, actual);
    }

    @Test public void minTest2() {
        int[] a = new int[]{5, 9, 1, 7, 3};
        int expected = 1;
        int actual = Selector.min(a);
        assertEquals(expected, actual);
    }

    @Test public void maxTest1() {
        int[] a = new int[]{5, 9, 1, 7, 3};
        int expected = 9;
        int actual = Selector.max(a);
        assertEquals(expected, actual);
    }

    @Test public void kMinTest1() {
        int[] a = new int[]{2, 8, 7, 3, 4};
        int expected = 2;
        int actual = Selector.kmin(a, 1);
        assertEquals(expected, actual);
    }

    @Test public void kMinTest2() {
        int[] a = new int[]{5, 9, 1, 7, 3};
        int expected = 5;
        int actual = Selector.kmin(a, 3);
        assertEquals(expected, actual);
    }

    @Test public void kMinTest3() {
        int[] a = new int[]{8, 7, 6, 5, 4};
        int expected = 8;
        int actual = Selector.kmin(a, 5);
        assertEquals(expected, actual);
    }

    @Test public void kMinTest4() {
        int[] a = new int[]{8, 2, 8, 7, 3, 3, 4};
        int expected = 4;
        int actual = Selector.kmin(a, 3);
        assertEquals(expected, actual);
    }

    @Test public void kMaxTest1() {
        int[] a = new int[]{2, 8, 7, 3, 4};
        int expected = 8;
        int actual = Selector.kmax(a, 1);
        assertEquals(expected, actual);
    }

    @Test public void kMaxTest2() {
        int[] a = new int[]{5, 9, 1, 7, 3};
        int expected = 5;
        int actual = Selector.kmax(a, 3);
        assertEquals(expected, actual);
    }

    @Test public void kMaxTest3() {
        int[] a = new int[]{8, 7, 6, 5, 4};
        int expected = 4;
        int actual = Selector.kmax(a, 5);
        assertEquals(expected, actual);
    }

    @Test public void kMaxTest4() {
        int[] a = new int[]{8, 2, 8, 7, 3, 3, 4};
        int expected = 4;
        int actual = Selector.kmax(a, 3);
        assertEquals(expected, actual);
    }

    @Test public void kMaxTest5() {
        int[] a = new int[]{-3,
                -7, -3, -3, -1, -9, -1, -1, -1, -5};
        int expected = -9;
        int actual = Selector.kmax(a, 5);
        assertEquals(expected, actual);
    }

    @Test public void rangeTest1() {
        int a[] = new int[]{2, 8, 7, 3, 4};
        int[] expected = new int[]{2, 3, 4};
        int[] actual = Selector.range(a, 1, 5);
        assertArrayEquals(expected, actual);
    }

    @Test public void rangeTest2() {
        int a[] = new int[]{8, 7, 6, 5, 4};
        int[] expected = new int[]{8, 7, 6, 5, 4};
        int[] actual = Selector.range(a, 4, 8);
        assertArrayEquals(expected, actual);
    }

    @Test public void floorTest1() {
        int a[] = new int[]{2, 8, 7, 3, 4};
        int expected = 4;
        int actual = Selector.floor(a, 6);
        assertEquals(expected, actual);
    }

    @Test public void ceilingTest1() {
        int a[] = new int[]{2, 8, 7, 3, 4};
        int expected = 2;
        int actual = Selector.ceiling(a, 1);
        assertEquals(expected, actual);
    }

}