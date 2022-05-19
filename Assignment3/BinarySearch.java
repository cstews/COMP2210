import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 *
 * @author Courtney Stewart
 * @version 2/24/2022
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //exception
        if (a == null || key == null) {
            throw new NullPointerException();
        }

        //set variables
        int index = -1;
        int beginning = 0;
        int end = a.length - 1;

        //algorithm
        while (beginning <= end) {
            int middle = (beginning + end) / 2;
            Key current = a[middle];

            int result = comparator.compare(key, current);
            if (result == 0) {
                index = middle;
            }
            if (result <= 0) {
                end = middle - 1;
            } else {
                beginning = middle + 1;
            }

        }
        return index;
    }


    /**
     * Returns the index of the last key in a[] that equals the search key,
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //exception
        if (a == null || key == null) {
            throw new NullPointerException();
        }

        //set variables
        int index = -1;
        int beginning = 0;
        int end = a.length - 1;

        //algorithm
        while (beginning <= end) {
            int middle = (beginning + end) / 2;
            Key current = a[middle];

            int result = comparator.compare(key, current);
            if (result == 0) {
                index = middle;
            }
            if (result < 0) {
                end = middle - 1;
            } else {
                beginning = middle + 1;
            }

        }
        return index;
    }

}


