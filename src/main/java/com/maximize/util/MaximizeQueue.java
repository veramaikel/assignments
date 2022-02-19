package com.maximize.util;

import java.util.Iterator;

public class MaximizeQueue<T> extends MaximizeDeque {

    private int last; //the index of the last element in array
    private T[] array;
    private int defaultStep;

    public MaximizeQueue(){
        this(10);
    }

    public MaximizeQueue(int size){
        if(size<1)  this.defaultStep = 10;
        else this.defaultStep = size;

        this.array = (T[]) new Object[this.defaultStep];
        for(int i =0; i < array.length; i++){
            this.array[i] = null;
        }
        this.last = -1;
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

    @Override
    public String toString() {

        StringBuffer r = new StringBuffer("MaximizeQueue{");
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

    @Override
    public Iterator iterator() {
        return null;
    }
}
