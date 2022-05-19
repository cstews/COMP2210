import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Courtney Stewart (cds0081@auburn.edu)
 *
 */
public final class Selector {

    /**
     * Can't instantiate this class.
     *
     * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
     *
     */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        //exceptions
        if (coll == null) {
            throw new IllegalArgumentException();
        }

        if (comp == null) {
            throw new IllegalArgumentException();
        }

        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }


        //Make iterator for collections
        Iterator<T> itr = coll.iterator();
        T min = itr.next();

        // Set min to first spot, compare to next one.
        while (itr.hasNext()) {
            T num = itr.next();
            if (comp.compare(num, min)<0) {
                min = num;
            }
        }
        return min;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {
        //exceptions
        if (coll == null) {
            throw new IllegalArgumentException();
        }

        if (comp == null) {
            throw new IllegalArgumentException();
        }

        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }

        //Make iterator for collections
        Iterator<T> itr = coll.iterator();
        T max = itr.next();

        // Set min to first spot, compare to next one.
        while (itr.hasNext()) {
            T num = itr.next();
            if (comp.compare(num, max)>0) {
                max = num;
            }
        }
        return max;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        //exceptions
        if (coll == null) {
            throw new IllegalArgumentException();
        }

        if (comp == null) {
            throw new IllegalArgumentException();
        }

        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }

        if (k < 1) {
            throw new NoSuchElementException();
        }

        if (k > coll.size()) {
            throw new NoSuchElementException();
        }

        // makes collection into an ArrayList
        List<T> aList = new ArrayList<>(coll);
        java.util.Collections.<T>sort(aList, comp);


        // Checks to see if k is larger than collection
        if (aList.size() < k) {
            throw new IllegalArgumentException();
        }

        //Checks if looking for 1st min value
        if (k == 1){
            return aList.get(0);
        }

        //Sorts through copy of collection
        T kmin = aList.get(0);
        for (int i = 0; i < k - 1; i++){
            if (aList.get(i) == aList.get(i + 1)) {
                k++;
                if (k > aList.size()) {
                    throw new IllegalArgumentException();
                }
            }
        }
        kmin = aList.get(k - 1);
        return kmin;
    }


    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
        //exceptions
        if (coll == null) {
            throw new IllegalArgumentException();
        }

        if (comp == null) {
            throw new IllegalArgumentException();
        }

        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }

        if (k < 1) {
            throw new NoSuchElementException();
        }

        if (k > coll.size()) {
            throw new NoSuchElementException();
        }

        // makes collection into an ArrayList
        List<T> aList = new ArrayList<>(coll);
        java.util.Collections.<T>sort(aList, comp);
        java.util.Collections.<T>reverse(aList);

        //Sorts through copy of collection
        T kmax = aList.get(0);
        for (int i = 0; i < k - 1; i++){
            if (aList.get(i) == aList.get(i + 1)) {
                k++;
                if (k > aList.size()) {
                    throw new IllegalArgumentException();
                }
            }
        }
        kmax = aList.get(k - 1);
        return kmax;
    }


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                          Comparator<T> comp) {
        //exceptions
        if (coll == null) {
            throw new IllegalArgumentException();
        }

        if (comp == null) {
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()) {
            throw new NoSuchElementException();
        }

        //Make iterator for collections
        Iterator<T> itr = coll.iterator();
        ArrayList<T> range = new ArrayList<>();

        //Iterates through collection, adds to arraylist
        while (itr.hasNext()) {
            T num = itr.next();
            if (comp.compare(num, high) <= 0 && comp.compare(num, low) >= 0) {
                range.add(num);
            }
        }

        // checks if range is empty
        if (range.isEmpty()) {
            throw new NoSuchElementException();
        }

        return range;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
        // Exceptions
        if (coll == null){
            throw new IllegalArgumentException();
        }
        if (comp == null){
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }

        // Sets ceiling to max value, then works down to key
        boolean notHere = true;
        T ceiling = Selector.max(coll, comp);

        Iterator<T> itr = coll.iterator();
        while (itr.hasNext()) {
            T num = itr.next();
            if (comp.compare(num, key) >= 0 && comp.compare(num, ceiling) <= 0) {
                ceiling = num;
                notHere = false;
            }
        }

            if (notHere == true) {
                throw new NoSuchElementException();
        }

        return ceiling;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
        // Exceptions
        if (coll == null){
            throw new IllegalArgumentException();
        }
        if (comp == null){
            throw new IllegalArgumentException();
        }
        if (coll.isEmpty()){
            throw new NoSuchElementException();
        }

        // Sets floor to min value, then works down to key
        boolean notHere = true;
        T floor = Selector.min(coll, comp);

        Iterator<T> itr = coll.iterator();
        while (itr.hasNext()) {
            T num = itr.next();
            if (comp.compare(num, key) <= 0 && comp.compare(num, floor) >= 0) {
                floor = num;
                notHere = false;
            }
        }

        if (notHere == true) {
            throw new NoSuchElementException();
        }

        return floor;
    }

}
