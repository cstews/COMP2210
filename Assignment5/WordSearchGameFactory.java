
/**
 * Provides a factory method for creating word search games.
 * @author Courtney Stewart cds0081@auburn.edu
 */
public class WordSearchGameFactory {

    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame() {
        return new WordySearchy();
    }

}
