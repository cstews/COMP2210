import java.util.Comparator;

/**
 * Autocomplete term representing a (query, weight) pair.
 *
 * @author Courtney Stewart
 * @version 2/27/2022
 */
public class Term implements Comparable<Term> {

    private static String theQuery;
    private static long theWeight;

    /**
     * Initialize a term with the given query and weight.
     * This method throws a NullPointerException if query is null,
     * and an IllegalArgumentException if weight is negative.
     */
    public Term(String query, long weight) {
        //exceptions
        if (query == null) {
            throw new NullPointerException();
        }
        if (weight < 0){
            throw new IllegalArgumentException();
            }

        //setting to static variables
        theQuery = query;
        theWeight = weight;
        }

        //getter methods
        public String getQuery() {
            return theQuery;
        }
        public long getWeight() {
            return theWeight;
        }

    /**
     * Compares the two terms in descending order of weight.
     */
    public static Comparator<Term> byDescendingWeightOrder() {
        //make new comparator
        return new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                if (t1.getWeight() > t2.getWeight()) {
                    return -1;
                }

                if (t1.getWeight() < t2.getWeight()){
                    return 1;
                }

                else {
                    return 0;
                }
            }
        };
    }

    /**
     * Compares the two terms in ascending lexicographic order of query,
     * but using only the first length characters of query. This method
     * throws an IllegalArgumentException if length is less than or equal
     * to zero.
     */
    public static Comparator<Term> byPrefixOrder(int length) {
        //exception
        if (length <= 0) {
            throw new IllegalArgumentException();
        }
        //make new comparator
        return new Comparator<Term>() {
            public int compare(Term t1, Term t2) {
                //gets length of queries
                int t1Length = t1.getQuery().length();
                int t2Length = t2.getQuery().length();

                //compares the substrings
                int num = Math.min(t1Length, t2Length);
                int result = t1.getQuery().substring(0, num).compareTo(t2.getQuery().substring(0, num));

                if (result == 0) {
                    if (t1Length < t2Length) {
                        return -1;
                    }
                    return 1;
                }
                return result;
            }
        };
    }


    /**
     * Compares this term with the other term in ascending lexicographic order
     * of query.
     */
    @Override
    public int compareTo(Term other) {
        return theQuery.compareTo(other.getQuery());
    }

    /**
     * Returns a string representation of this term in the following format:
     * query followed by a tab followed by weight
     */
    @Override
    public String toString(){
        String output = theQuery + "\t" + theWeight;
        return output;
    }

}

