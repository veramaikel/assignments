package com.maximize.util;

public class MaximizeDeque<T> {

    private int last; //the index of the last element
    private T[] array;
    private int defaultStep;

    public MaximizeDeque(){
        this(10);
    }

    public MaximizeDeque(int size){
        if(size<1)  this.defaultStep = 10;
        else this.defaultStep = size;

        this.array = (T[]) new Object[this.defaultStep];
        for(int i =0; i < array.length; i++){
            this.array[i] = null;
        }
        this.last = -1;
    }

    /**
     * This method retrieves but does not remove, the head (first element) of this Deque.
     * Doing the Stack function LIFO (Last-In-First-Out)
     * @return E if Deque isn't empty, otherwise null
     */
    public T peek(){
        if(last>-1) return array[last];

        return null;
    }

    /**
     * This method pops an element from the stack represented by this Deque.
     * Doing the Stack function LIFO (Last-In-First-Out)
     *  @return E if Deque isn't empty, otherwise null
     */
    public T pop(){
        if(last>-1){
            T t = array[last];
            last--;
            return t;
        }

        return null;
    }

    /**
     * This method pushes an element (head) onto the stack represented by this Deque.
     * Doing the Stack function LIFO (Last-In-First-Out)
     */
    public void push(T t){
        if(array.length > last+1) {
            array[last+1] = t;
        }
        else{
            //System.out.println("resizing to "+(array.length+defaultStep));
            T[] aux = (T[]) new Object[(array.length+defaultStep)];
            for(int i =0; i < array.length; i++){
                aux[i] = array[i];
            }
            aux[last+1] = t;
            this.array = aux;
        }
        last++;
    }

    /**
     * This method retrieves, but does not remove, the first element (head) of this Deque.
     * Doing the Queue function FIFO (First-In-First-Out)
     * @return E if Deque isn't empty, otherwise null
     */
    public T peekFirst(){
        if(last>-1) return array[0];

        return null;
    }

    /**
     * This method retrieves, but does not remove, the last element (tail) of this Deque.
     * Doing the Queue function FIFO (First-In-First-Out)
     * @return E if Deque isn't empty, otherwise null
     */
    public T peekLast(){
        if(last>-1) return array[last];

        return null;
    }

    /**
     * This method retrieves and removes the head (first element) of this Deque.
     * Doing the Queue function FIFO (First-In-First-Out)
     * @return E if Deque isn't empty, otherwise null
     */
    public T poll(){
        if(last>-1){
            T t = array[0];
            for (int i = 0; i < last; i++) {
                array[i] = array[i+1];
            }
            last--;
            return t;
        }

        return null;
    }

    /**
     * This method Appends the specified element to the end (tail) of this Deque.
     * Doing the Queue function FIFO (First-In-First-Out)
     */
    public void add(T t){
        if(array.length > last+1) {
            for (int i = 0; i <= last; i++) {
                array[i+1] = array[i];
            }
            array[0] = t;
        }
        else{
            //System.out.println("resizing to "+(array.length+defaultStep));
            T[] aux = (T[]) new Object[(array.length+defaultStep)];
            for(int i =0; i < array.length; i++){
                aux[i+1] = array[i];
            }
            aux[0] = t;
            this.array = aux;
        }
        last++;
    }

    public int search(T t){
        for(int i = 0; i <= last; i++){
            if(array[i].equals(t)) return i;
        }
        return -1;
    }

    public int getDefaultStep() {
        return defaultStep;
    }

    public void setDefaultStep(int defaultStep) {
        this.defaultStep = defaultStep;
    }

    public boolean empty(){
        return (last==-1);
    }

    public int size(){
        return (this.last+1);
    }

    @java.lang.Override
    public String toString() {

        StringBuffer r = new StringBuffer("MaximizeDeque{");
        if(last>-1) r.append(array[0].getClass()).append(", ");
        r.append("size=").append(last + 1)
                .append(", length=").append(array.length)
                .append(", defaultStep=").append(defaultStep)
                .append(", elements=[");
        for (int i = last; i <= 0; i--) {
            if(i<last) r.append(", ");
            r.append(array[i]);
        }
        return r.append("] }").toString();
    }
}
