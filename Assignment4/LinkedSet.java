import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Courtney Stewart (cds0081@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {

        //checks to see if set is empty
        if (isEmpty()) {
            Node n = new Node(element);
            front = n;
            rear = n;
            size++;
            return true;
        }

        //finds element
        Node n = front;
        while (n.element.compareTo(element) < 0 && n.next != null) {
            n = n.next;
        }

        //checks if duplicate
        if (n.element.compareTo(element) == 0){
            return false;
        }

        //if m goes after n in ascending order
        else if (n.element.compareTo(element) < 0) {
            Node m = new Node(element);

            //checks if at end and puts m at end
            if (n.next == null) {
                rear = m;
                m.prev = n;
                n.next = m;
            }
            else {
                m.next = n.next;
                n.next.prev = m;
                m.prev = n;
                n.next = m;
            }
            size++;
            return true;
        }

        //if m goes before n in ascending order
        else {
            Node m = new Node(element);
            if (n == front){
                front = m;
                m.next = n;
                n.prev = m;
            }
            else {
                m.prev = n.prev;
                n.prev.next = m;
                m.next = n;
                n.prev = m;
            }
            size++;
            return true;
        }
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {

        //checks if empty
        if (isEmpty()) {
            return false;
        }

        //checks if element is in collection
        if(!contains(element)){
            return false;
        }

        //finds element
        Node n = front;
        while (n.element.compareTo(element) != 0 && n.next != null){
            n = n.next;
        }

        //if only one element
        if (size == 1){
            front = null;
            rear = null;
            size = 0;
            return true;
        }

        //special case of n being front
        if (n == front){
            front = front.next;
            front.prev = null;
        }
        //special case of n being rear
        else if (n == rear){
            rear = n.prev;
            n.prev.next = null;
        }
        else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        size--;
        return true;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        if (isEmpty()){
            return false;
        }

        //finds element
        Node n = front;
        while (n.element.compareTo(element) != 0 && n.next != null){
            n = n.next;
        }

        //if found, return true
        if (n.element.compareTo(element) == 0) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        //checks to see if the same size
        if (this.size() != s.size()){
            return false;
        }

        //iterates through each element and checks with set
        for (T e: s){
            if (!this.contains(e)){
                return false;
            }
        }

        return true;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if (s != null && this.size() == s.size()) {

            Node n = this.front;
            Node m = s.front;

            //iterates through and compares n and m in each set
            while (n != null && m != null) {
                if (n.element.compareTo(m.element) != 0) return false;
                    n = n.next;
                    m = m.next;
            }
            return true; //You return true no matter what
        }
            return false;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        Set<T> newSet = new LinkedSet<T>();

        //check special cases
        if (this.equals(s)){
            return this;
        }

        if (this.isEmpty()){
            return s;
        }

        if (s.isEmpty()){
            return this;
        }

        //iterate through each set and add to new set
        Iterator<T> scanThis = this.iterator();
        Iterator<T> scanS = s.iterator();

        while (scanThis.hasNext()){
            T thisElement = scanThis.next();
            newSet.add(thisElement);
        }

        while (scanS.hasNext()){
            T sElement = scanS.next();
            newSet.add(sElement);
        }

        return newSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        if (this.equals(s)){
            return s;
        }

        //checks special case of both being empty
        if (this.isEmpty() && s.isEmpty()){
            return new LinkedSet<T>();
        }

        //checks if one is empty and returns non empty one
        else if (this.isEmpty()){
            return s;
        }
        else if (s.isEmpty()) {
            return this;
        }

        LinkedSet<T> newSet = new LinkedSet<>();
        Node n = this.front;
        Node m = s.front;

        if (this.size() >= s.size()){
            while (n != null || m != null){
                if (m == null) {
                    newSet.addRear(n.element);
                    n = n.next;
                }
                else if (n == null && m != null){
                    newSet.addRear(m.element);
                    m = m.next;
                }
                else if (n.element.compareTo(m.element) < 0){
                    newSet.addRear(n.element);
                    n = n.next;
                }
                else if (n.element.compareTo(m.element) > 0){
                    newSet.addRear(m.element);
                    m = m.next;
                }
                else {
                    newSet.addRear(n.element);
                    m = m.next;
                    n = n.next;
                }
            }
        }

        else {
            while (m != null || n != null){
                if (n == null) {
                    newSet.addRear(m.element);
                    m = m.next;
                }
                else if (n != null && m == null){
                    newSet.addRear(m.element);
                    m = m.next;
                }
                else if(n.element.compareTo(m.element) < 0){
                    newSet.addRear(n.element);
                    n = n.next;
                }
                else if (n.element.compareTo(m.element) > 0) {
                    newSet.addRear(m.element);
                    m = m.next;
                }
                else {
                    newSet.addRear(n.element);
                    m = m.next;
                    n = n.next;
                }

            }

        }

/*        //if they both are the same, adds one and increments
        if (n.element.compareTo(m.element) == 0){
            newSet.front = new Node(n.element);
            n = n.next;
            m = m.next;
        }

        //if m comes first, makes front
        else if (n.element.compareTo(m.element) > 0){
            newSet.front = new Node(m.element);
            m = m.next;
        }

        //if n comes first, makes front
        else{
            newSet.front = new Node(n.element);
            n = n.next;
        }
        newSet.size++;

        //adds in ascending order
        Node w = newSet.front;
        while(n != null && m != null){
            if (n.element.compareTo(m.element) == 0){
                Node x = new Node(n.element);
                w.prev = x;
                x.prev = w;

                w = w.next;
                n = n.next;
                m = m.next;
            }

            else if(n.element.compareTo(m.element) < 0){
                Node x = new Node (n.element);
                w.next = x;
                x.prev = w;

                w = w.next;
                m = m.next;
            }

            else{
                Node y = new Node(m.element);
                w.next = y;

                w = w.next;
                m = m.next;
            }
            newSet.size++;
        }

        if (n!= null) {
            while (n != null) {
                Node x = new Node(n.element);
                w.next = x;
                x.prev = w;

                w = w.next;
                n = n.next;
                newSet.size++;
            }
        }

        else if(m != null){
            while (m != null) {
                Node y = new Node(m.element);
                w.next = y;
                y.prev = w;

                w = w.next;
                m = m.next;
                newSet.size++;
            }
        }

        newSet.rear = w;*/

        return newSet;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        //checks special case of both being empty
        if (this.isEmpty() || s.isEmpty()) {
            return new LinkedSet<>();
        }

        //special case of both having same elements
        if (this.equals(s)){
            return this;
        }

        LinkedSet<T> newSet = new LinkedSet<>();
        Iterator<T> scanThis = this.iterator();

        while (scanThis.hasNext()) {
            T elementThis = scanThis.next();
            Iterator<T> scanS = s.iterator();
            while(scanS.hasNext()){
                T elementS = scanS.next();
                if (elementThis.equals(elementS)){
                    newSet.add(elementThis);
                    break;
                }
            }
        }
        return newSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        //checks special case of both being empty
        if (this.isEmpty() || s.isEmpty()) {
            return new LinkedSet<>();
        }

        //special case of both having same elements
        if (this.equals(s)){
            return this;
        }

        LinkedSet<T> newSet = new LinkedSet<>();
        Node n = this.front;
        Node m = s.front;

        //compares and adds to set in ascending order
        while (n != null && m != null){
            if (n.element.compareTo(m.element) < 0){
                n = n.next;
            }
            else if (n.element.compareTo(m.element) == 0){
                newSet.addRear(n.element);
                n = n.next;
                m = m.next;
            }

            else {
                m = m.next;
            }
        }

        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {

        //check special case of other set being empty
        if (this.isEmpty()) {
            return new LinkedSet<>();
        }

        //check special case of s being empty
        if (s.isEmpty()) {
            return this;
        }

        //iterates through first set to see if s has it
        LinkedSet<T> newSet = new LinkedSet<>();
        for (T e: this){
            if (!s.contains(e)){
                newSet.add(e);
            }
        }
        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        LinkedSet<T> newSet = new LinkedSet<>();

        //check special case of this and s being same
        if (this.equals(s)){
            return newSet;
        }

        //check special case of other set being empty
        if (this.isEmpty()) {
            return newSet;
        }

        //check special case of s being empty
        if (s.isEmpty()) {
            return this;
        }


        Set<T> intersect = this.intersection(s);
        LinkedSet<T> is = new LinkedSet<T>();
        for (T e : intersect){
            is.add(e);
        }

        Node n = this.front;
        Node m = is.front;

        if (is.size() == 0) {
            return this;
        }

        //compares and adds to set in ascending order
        while (n != null){

            if (m == null) {
                newSet.addRear(n.element);
                n = n.next;
            }

            else if(n.element.compareTo(m.element) == 0){
                m = m.next;
                n = n.next;
            }

            else if (n.element.compareTo(m.element) > 0){
                m = m.next;
            }

            else if (n.element.compareTo(m.element) < 0){
                newSet.addRear(n.element);
                n = n.next;
            }

            else if (n.element.compareTo(m.element) != 0){
                newSet.addRear(n.element);
                m = m.next;
                n = n.next;
            }
        }

        return newSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new LinkedSetIterator<T>(this);
    }

    class LinkedSetIterator<W> implements Iterator<T>{
        Node n;
        public LinkedSetIterator(LinkedSet<T> s) {
            n = s.front;
        }

        @Override
        public boolean hasNext() {
            return (n!= null);
        }

        @Override
        public T next() {
            T newSet = (T) n.element;
            n = n.next;
            return newSet;
        }

    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new LinkedSetDescIterator(this);
    }

    class LinkedSetDescIterator implements Iterator<T>{
        Node n;
        public LinkedSetDescIterator(LinkedSet<T> s){
            n = s.rear;
        }

        @Override
        public boolean hasNext(){
            return (n != null);
        }

        @Override
        public T next() {
            T newSet = (T) n.element;
            n = n.prev;
            return newSet;
        }
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        return new LinkedSetPowerSetIterator(this);
    }

    class LinkedSetPowerSetIterator implements Iterator<Set<T>>{
        // cardinality of set
        int N;

        // cardinality of power set
        int M;

        // current element of power set
        int current;

        Node front;
        Node n;

        public LinkedSetPowerSetIterator(LinkedSet<T> s) {
            current = 0;
            M = (int) Math.pow(2, s.size());
            front = s.front;
        }

        @Override
        public boolean hasNext() {
            return (current < M);
        }

        @Override
        public Set<T> next() {
            LinkedSet<T> newSet = new LinkedSet<T>();
            n = front;
            char[] bitstring = Integer.toBinaryString(current).toCharArray();

            for (int i = bitstring.length - 1; i >= 0; i--){
                if (bitstring[i] == '1'){
                    newSet.add(n.element);
                }

                if (n != null) {
                    n = n.next;
                }
            }

            current++;
            return newSet;
        }


    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.

    private boolean addRear(T element){
        Node n = new Node(element);

        //checks if valid node
        if (element == null){
            return false;
        }

        //if only node in set, makes front and rear
        else if (isEmpty()) {
            front = n;
            rear = n;
            size++;
            return true;
        }

        n.prev = this.rear.prev;
        n.prev = rear;
        n.prev.next = n;
        rear = n;
        size++;
        return true;
    }

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
