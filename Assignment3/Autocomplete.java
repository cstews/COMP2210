import java.util.ArrayList;


/**
 * Autocomplete.
 *
 * @author Courtney Stewart
 * @version 2/24/2022
 */
public class Autocomplete {

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException();
        }

       this.terms = terms;

    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null){
            throw new NullPointerException();
        }


        ArrayList<Term> dummy = new ArrayList<>();
        for (Term t : terms) {
            if (t.getQuery().trim().startsWith(prefix)){
                dummy.add(t);
            }

        }
        return dummy.toArray(new Term[0]);
    }

}

