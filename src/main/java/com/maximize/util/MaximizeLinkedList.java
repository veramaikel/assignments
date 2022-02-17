package com.maximize.util;

public class MaximizeLinkedList<T> extends MaximizeDeque {

    private int last; //the index of the last element in array
    private T[] array;
    private int defaultStep;
    private int head; //the index

    public MaximizeLinkedList(){
        this(10);
    }

    public MaximizeLinkedList(int step){
        super(step);
        if(step<1)  this.defaultStep = 10;
        else this.defaultStep = step;

        this.array = (T[]) new Object[this.defaultStep];
        for(int i =0; i < array.length; i++){
            this.array[i] = null;
        }
        this.last = -1;
        this.head = -1;
    }

    /**
     * This method retrieves but does not remove, the head (first element) of this Deque.
     * Doing the LinkedList functions
     * @return E if List isn't empty, otherwise null
     */
    public T getHead(){
        if(head>-1 && last>-1) return array[head];

        return null;
    }

    /**
     * This method update the element to the head of this List.
     * Doing the LinkedList functions
     */
    public void setHead(T t){
        if(head>-1 && last>-1) array[head] = t;
    }

    /**
     * This method set the pointer to the head of this List.
     * Doing the LinkedList functions
     */
    public void setHeadIndex(int head){
        if(head>-1 && head <= last) this.head = head;
    }

    /**
     * This method move the head at the next element in the List and retrieves but does not remove.
     * Doing the LinkedList functions
     * @return E if List isn't empty, otherwise null
     */
    public T next(){
        if(head>-1 && last>-1){
            head++;
            if(head > last) head = 0;
            return array[head];
        }

        return null;
    }

    /**
     * This method move the head at the prev element in the List and retrieves but does not remove.
     * Doing the LinkedList functions
     * @return E if List isn't empty, otherwise null
     */
    public T prev(){
        if(head>-1 && last>-1){
            head--;
            if(head < 0) head = last;
            return array[head];
        }

        return null;
    }

    /**
     * This method Appends the specified element to the end (tail) of this List.
     * Doing the LinkedList function
     */
    public void add(T t){
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
        if(head==-1) head = 0;
    }

    /**
     * This method retrieves and removes the head of this LinkedList.
     * Doing the LinkedList function
     * @return E if List isn't empty, otherwise null
     */
    public T poll(){
        if(head>-1){
            T t = array[head];
            for (int i = head; i < last; i++) {
                array[i] = array[i+1];
            }
            last--;
            if(head>last) head = last;
            return t;
        }

        return null;
    }

    public void set(int index, T t){
        if(index>-1 && index<=last) array[index] = t;
    }

    public boolean empty(){
        return (last==-1);
    }

    public int size(){
        return (this.last+1);
    }

    @Override
    public String toString() {

        StringBuffer r = new StringBuffer("MaximizeLinkedList{");
        r.append("size=").append(last + 1);
        if(head>-1) r.append(", head=").append(head);
        r.append(", elements=[");
        for (int i = 0; i <= last; i++) {
            if(i>0) r.append(", ");
            r.append(array[i]);
        }
        return r.append("] }").toString();
    }
}
