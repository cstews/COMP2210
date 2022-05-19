import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedSetTest {

    //doesn't work
    @Test
    public void complementLinkedTest() {
        LinkedSet<Integer> sln = new LinkedSet<>();
        sln.add(1);

        LinkedSet<Integer> x = new LinkedSet<>();
        x.add(1);
        x.add(2);
        x.add(3);

        LinkedSet<Integer> y = new LinkedSet<>();
        y.add(2);
        y.add(3);
        y.add(4);

        Set<Integer> comp = x.complement(y);

        assertEquals(true, sln.equals(comp));


    }

    //still broken too :/
    @Test
    public void LinkedSetUnionTest() {
        LinkedSet<Integer> sln = new LinkedSet<>();
        sln.add(1);
        sln.add(2);
        sln.add(3);
        sln.add(4);

        LinkedSet<Integer> x = new LinkedSet<>();
        x.add(1);
        x.add(2);
        x.add(3);

        LinkedSet<Integer> y = new LinkedSet<>();
        y.add(3);
        y.add(4);
        y.add(2);

        Set<Integer> union = x.union(y);

        assertEquals(true, sln.equals(union));

    }

    @Test
    public void LinkedSetIntersectionTest() {
        LinkedSet<Integer> sln = new LinkedSet<>();
        sln.add(2);
        sln.add(3);

        LinkedSet<Integer> x = new LinkedSet<>();
        x.add(1);
        x.add(2);
        x.add(3);

        LinkedSet<Integer> y = new LinkedSet<>();
        y.add(2);
        y.add(3);
        y.add(4);

        Set<Integer> intersection = x.intersection(y);

        assertEquals(true, sln.equals(intersection));

    }

@Test
    public void removeTest() {
        LinkedSet<Integer> s = new LinkedSet<Integer>();
        s.add(1);
        assertEquals(true, s.remove(1));

        assertEquals(false, s.remove(2));

}


}