import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class that implements WordSearchGame interface.
 * @author Courtney Stewart cds0081@auburn.edu
 */

public class WordySearchy implements WordSearchGame {
    //fields

    //list of words
    private TreeSet<String> lexicon;
    private String[][] board;
    private static final int MAX_NEIGHBORS = 8;
    private int width;
    private int height;
    private boolean[][] visited;
    private ArrayList<Integer> path;
    private String wordsPartial;
    private SortedSet<String> allWords;
    private ArrayList<Position> path2;

    public WordySearchy() {
        lexicon = null;

        //Makes default board from instructions
        board = new String[4][4];
        board[0][0] = "E";
        board[0][1] = "E";
        board[0][2] = "C";
        board[0][3] = "A";
        board[1][0] = "A";
        board[1][1] = "L";
        board[1][2] = "E";
        board[1][3] = "P";
        board[2][0] = "H";
        board[2][1] = "N";
        board[2][2] = "B";
        board[2][3] = "O";
        board[3][0] = "Q";
        board[3][1] = "T";
        board[3][2] = "T";
        board[3][3] = "Y";

        width = board.length;
        height = board[0].length;
    }

    /**
     * Loads the lexicon into a data structure for later use.
     *
     * @param fileName A string containing the name of the file to be opened.
     * @throws IllegalArgumentException if fileName is null
     * @throws IllegalArgumentException if fileName cannot be opened.
     */
    public void loadLexicon(String fileName) {
        lexicon = new TreeSet<String>();

        //checks if name is null
        if (fileName == null) {
            throw new IllegalArgumentException();
        }

        //reads in file if it exists
        try {
            Scanner scan = new Scanner(new FileReader(new File(fileName)));
            while (scan.hasNext()) {
                String word = scan.next();
                word = word.toUpperCase();
                lexicon.add(word);
                scan.nextLine();
            }
        }

        //if file can't be found, throws exception
        catch (java.io.FileNotFoundException e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Stores the incoming array of Strings in a data structure that will make
     * it convenient to find words.
     *
     * @param letterArray This array of length N^2 stores the contents of the
     *     game board in row-major order. Thus, index 0 stores the contents of board
     *     position (0,0) and index length-1 stores the contents of board position
     *     (N-1,N-1). Note that the board must be square and that the strings inside
     *     may be longer than one character.
     * @throws IllegalArgumentException if letterArray is null, or is  not
     *     square.
     */
    public void setBoard(String[] letterArray) {
        //checks if letterArray is null
        if (letterArray == null){
            throw new IllegalArgumentException();
        }

        //checking to see if board is a square
        int n = (int)Math.sqrt(letterArray.length);
        if ((n * n) != letterArray.length) {
            throw new IllegalArgumentException();
        }

        board = new String[n][n];
        width = n;
        height = n;
        int index = 0;

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                board[i][j] = letterArray[index];
                index++;
            }
        }

    }

    /**
     * Creates a String representation of the board, suitable for printing to
     *   standard out. Note that this method can always be called since
     *   implementing classes should have a default board.
     */
    public String getBoard() {

        String boardString = "";

        for (int i = 0; i < height; i++){
            boardString += "\n";

            for (int j = 0; j < width; j++){
                boardString += board[i][j] + " ";
            }
        }

        return boardString;

    }

    /**
     * Retrieves all scorable words on the game board, according to the stated game
     * rules.
     *
     * @param minimumWordLength The minimum allowed length (i.e., number of
     *     characters) for any word found on the board.
     * @return java.util.SortedSet which contains all the words of minimum length
     *     found on the game board and in the lexicon.
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        //checks for word length validity
        if (minimumWordLength < 1){
            throw new IllegalArgumentException();
        }

        //checks if lexicon has been called
        if (lexicon == null){
            throw new IllegalArgumentException();
        }

        path2 = new ArrayList<Position>();
        allWords = new TreeSet<String>();
        wordsPartial = "";

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                wordsPartial = board[i][j];

                if (isValidWord(wordsPartial) && wordsPartial.length() >= minimumWordLength) {
                    allWords.add(wordsPartial);
                }

                if (isValidPrefix(wordsPartial)) {
                    Position temp = new Position(i, j);
                    path2.add(temp);
                    dfs2(i, j, minimumWordLength);
                    path2.remove(temp);
                }
            }
        }

        return allWords;
    }

    /**
     * Computes the cummulative score for the scorable words in the given set.
     * To be scorable, a word must (1) have at least the minimum number of characters,
     * (2) be in the lexicon, and (3) be on the board. Each scorable word is
     * awarded one point for the minimum number of characters, and one point for
     * each character beyond the minimum number.
     *
     * @param words The set of words that are to be scored.
     * @param minimumWordLength The minimum number of characters required per word
     * @return the cummulative score of all scorable words in the set
     * @throws IllegalArgumentException if minimumWordLength < 1
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        //exceptions check
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }

        if (lexicon == null) {
            throw new IllegalArgumentException();
        }

        int score = 0;

        Iterator<String> itr = words.iterator();

        while (itr.hasNext()){
            String word = itr.next();

            if (word.length() >= minimumWordLength && isValidWord(word) && !isOnBoard(word).isEmpty()){
                score += (word.length() - minimumWordLength) + 1;
            }
        }
        return score;
    }

    /**
     * Determines if the given word is in the lexicon.
     *
     * @param wordToCheck The word to validate
     * @return true if wordToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidWord(String wordToCheck) {
        //exceptions check

        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (lexicon == null){
            throw new IllegalStateException();
        }

        wordToCheck = wordToCheck.toUpperCase();
        return lexicon.contains(wordToCheck);
    }

    /**
     * Determines if there is at least one word in the lexicon with the
     * given prefix.
     *
     * @param prefixToCheck The prefix to validate
     * @return true if prefixToCheck appears in lexicon, false otherwise.
     * @throws IllegalArgumentException if prefixToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public boolean isValidPrefix(String prefixToCheck) {
        if (lexicon == null) {
            throw new IllegalStateException();
        }

        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }

        prefixToCheck = prefixToCheck.toUpperCase();
        String word = lexicon.ceiling(prefixToCheck);

        if (word != null){
            return word.startsWith(prefixToCheck);
        }

        return false;
    }

    /**
     * Determines if the given word is in on the game board. If so, it returns
     * the path that makes up the word.
     * @param wordToCheck The word to validate
     * @return java.util.List containing java.lang.Integer objects with  the path
     *     that makes up the word on the game board. If word is not on the game
     *     board, return an empty list. Positions on the board are numbered from zero
     *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
     *     board, the upper left position is numbered 0 and the lower right position
     *     is numbered N^2 - 1.
     * @throws IllegalArgumentException if wordToCheck is null.
     * @throws IllegalStateException if loadLexicon has not been called.
     */
    public List<Integer> isOnBoard(String wordToCheck) {
        //exceptions check
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }

        if (lexicon == null) {
            throw new IllegalStateException();
        }

        path2 = new ArrayList<Position>();
        wordToCheck = wordToCheck.toUpperCase();
        wordsPartial = "";
        path = new ArrayList<Integer>();

        for (int i = 0; i < height; i++){

            for (int j = 0; j < width; j++) {

                if (wordToCheck.equals(board[i][j])){
                    path.add(i * width + j);
                    return path;
                }

                if (wordToCheck.startsWith(board[i][j])){
                    Position p = new Position(i, j);
                    path2.add(p);
                    wordsPartial = board[i][j];
                    dfs(i, j, wordToCheck);

                    if (!wordToCheck.equals(wordsPartial)) {
                        path2.remove(p);
                    }
                    else {
                        for (Position pos : path2) {
                            path.add((pos.x * width) + pos.y);
                        }

                        return path;

                    }
                }
            }
        }
        return path;
    }

    /**
     * Depth-First search.
     * @param x is x value.
     * @param y is y value.
     * @param wordToCheck is word.
     */
    private void dfs(int x, int y, String wordToCheck) {

        Position start = new Position(x, y);
        allUnvisited();
        pathVisited();

        for (Position p : start.neighbors()) {
            if (!isVisited(p)) {
                    visit(p);

                if (wordToCheck.startsWith(wordsPartial + board[p.x][p.y])) {
                    wordsPartial += board[p.x][p.y];
                    path2.add(p);
                    dfs(p.x, p.y, wordToCheck);

                    if (wordToCheck.equals(wordsPartial)){
                        return;
                    }

                    else {
                        path2.remove(p);

                        int end = wordsPartial.length() - board[p.x][p.y].length();
                        wordsPartial = wordsPartial.substring(0, end);
                    }
                }
            }
        }
        allUnvisited();
        pathVisited();

    }

    /**
     * Depth first search but with min in parameter instead of word.
     */
    private void dfs2(int x, int y, int min) {
        Position start = new Position(x, y);
        allUnvisited();
        pathVisited();
        for (Position p : start.neighbors()){

            if (!isVisited(p)){
                visit(p);

                if (isValidPrefix(wordsPartial + board[p.x][p.y])) {
                    wordsPartial += board[p.x][p.y];
                    path2.add(p);

                    if (isValidWord(wordsPartial) && wordsPartial.length() >= min){
                        allWords.add(wordsPartial);
                    }

                    dfs2(p.x, p.y, min);
                    path2.remove(p);
                    int end = wordsPartial.length() - board[p.x][p.y].length();
                    wordsPartial = wordsPartial.substring(0, end);
                }
            }
        }

        allUnvisited();
        pathVisited();

    }

    /**
     * Marks unvisited positions.
     */
    private void allUnvisited() {
        visited = new boolean[width][height];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }
    }

    /**
     * Creates a visited path.
     */
    private void pathVisited() {
        for (int i = 0; i < path2.size(); i++) {
            visit(path2.get(i));
        }
    }



    //Postion class and associated methods.

    /**
     * Creates an (x, y) position on the graph.
     */
    private class Position {
        int x;
        int y;

        /**
         * Constructor for position.
         */
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns string representation.
         */
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        /**
         * Returns all neighbors of the Position.
         */
        public Position[] neighbors() {
            Position[] neigh = new Position[MAX_NEIGHBORS];
            int count = 0;
            Position p;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++){
                    if (!(i == 0) && (j == 0)) {
                        p = new Position(x + i, y + j);
                        if (isValid(p)) {
                            neigh[count++] = p;
                        }
                    }
                }
            }

            return Arrays.copyOf(neigh, count);

        }
    }

    /**
     * Checks for a valid position.
     * @param p is position.
     */
    private boolean isValid(Position p) {
        return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < height);
    }


    /**
     * Checks if position has been visited.
     * @param p is position.
     */
    private boolean isVisited(Position p) {
        return visited[p.x][p.y];
    }

    /**
     * Marks position as "visited".
     */
    private void visit(Position p) {
        visited[p.x][p.y] = true;
    }

}


