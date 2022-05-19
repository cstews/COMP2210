import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AutocompleteTest {

@Test public <Term> void allMatchesTest1() {
    Term[] terms = (Term[]) new Object[]{(Term) "abcde      6", (Term) "abcde        6", (Term) "abcde        6",
            (Term) "abcde        6", (Term) "abcde        6"};
    String prefix = "ab";

    Term[] expected = ["abcde      6", "abcde        6", "abcde        6", "abcde        6", "abcde        6"];


}
}