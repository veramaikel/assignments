package com.maximize.util;

public abstract class MaximizeDeque<T> {

    private int last; //the index of the last element in array
    private T[] array;
    private int defaultStep;

    public MaximizeDeque(){
        this(10);
    }

    public MaximizeDeque(int step){
        if(step<1)  this.defaultStep = 10;
        else this.defaultStep = step;

        this.array = (T[]) new Object[this.defaultStep];
        for(int i =0; i < array.length; i++){
            this.array[i] = null;
        }
        this.last = -1;
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
                .append(", defaultStep=").append(defaultStep);
        return r.append("] }").toString();
    }
}
