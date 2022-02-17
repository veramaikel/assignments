package com.maximize.util;

public class MaximizeStack<T> extends MaximizeDeque {

    private int last; //the index of the last element in array
    private T[] array;
    private int defaultStep;

    public MaximizeStack(){
        this(10);
    }

    public MaximizeStack(int size){
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

    @Override
    public String toString() {

        StringBuffer r = new StringBuffer("MaximizeStack{");
        r.append("size=").append(last + 1)
                .append(", length=").append(array.length)
                .append(", defaultStep=").append(defaultStep)
                .append(", elements=[");
        for (int i = last; i >= 0; i--) {
            if(i<last) r.append(", ");
            r.append(array[i]);
        }
        return r.append("] }").toString();
    }
}
