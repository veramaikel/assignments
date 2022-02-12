package project0.dixcover.structures;

import project0.dixcover.Position;

public class DixcoverStack <T extends Position> {

    private int last; //the index of the last element
    private T[] array;
    private int defaultStep;

    public DixcoverStack(){
        this(10);
    }

    public DixcoverStack(int size){
        if(size<1)  this.defaultStep = 10;
        else this.defaultStep = size;

        this.array = (T[]) new Object[this.defaultStep];
        for(int i =0; i < array.length; i++){
            this.array[i] = null;
        }
        this.last = -1;
    }

    public T peek(){
        if(last>-1) return array[last];

        return null;
    }

    public T pop(){
        if(last>-1){
            T t = array[last];
            last--;
            return t;
        }

        return null;
    }

    public int search(T t){
        for(int i = 0; i <= last; i++){
            if(array[i].equals(t)) return i;
        }
        return -1;
    }

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

        StringBuffer r = new StringBuffer("DixcoverStack{");
        if(last>-1) r.append(array[0].getClass()+", ");
        r.append("size=" + (last+1))
                .append(", length=" + array.length)
                .append(", defaultStep=" + defaultStep)
                .append(", elements=[");
        for (int i = last; i <= 0; i--) {
            if(i<last) r.append(", ");
            r.append(array[i].toString());
        }
        return r.append("] }").toString();
    }
}
