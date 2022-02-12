package structures;

/**
 * Generic version of the ArrayList class.
 * @param <T> the type of the value being boxed
 */
public class GenericArrayList<T> {
//    pseudocode

//    variables needed:
//    Array of something;
//    The current amount of somethings;

//    Constructor that creates an initial array of some size;

//    Get method that returns the something of a given index;

//    Add method
//    First, check that the arraylist will not break if we try to add at the next index:
//    IE, check if the current amount of somethings+1 is not greater that the array size
//    if it is greater:
//          Copy the elements of the current array to a new array.
//          set the pointer of the arrayList's internal array to the new array.
//    that adds things at an index that is currently unused
//    (this will be the current size)




//    a method that can return a boolean determining if your array list contains a certain item
//    (if you had an arraylist of objects, as opposed to primitives, you may need to implement and use .equals)
//    a method that can determine if your arraylist is identical to another one
//    (again, .equals)
//    a method that sets a given index to a particular value


    private int last; //the index of the last element
    private T[] array;
    private int defaultStep;

    public GenericArrayList(){
        this(10);
    }

    public GenericArrayList(int size){
        if(size<1)  this.defaultStep = 10;
        else this.defaultStep = size;

        this.array = (T[]) new Object[this.defaultStep];
        this.last = -1;
    }

    public T get(int index){
        if(index>-1 && index <= last) return array[index];
        else{
            System.out.println("ArrayIndexOutOfBounds");
            return null;
        }
    }

    public int findIndex(T t){
        for(int i = 0; i <= last; i++){
            if(array[i].equals(t)) return i;
        }
        return -1;
    }

    public void add(T t){
        if(array.length > last+1) {
            array[last+1] = t;
        }
        else{
            System.out.println("resizing to "+(array.length+defaultStep));
            T[] aux = (T[]) new Object[(array.length+defaultStep)];
            for(int i =0; i < array.length; i++){
                aux[i] = array[i];
            }
            aux[last+1] = t;
            this.array = aux;
        }
        last++;
    }

    public void add(T[] arr){
        if(array.length > last+arr.length) {
            for (int i = 0; i < arr.length; i++) {
                array[last+1+i] = arr[i];
            }
        }
        else{
            System.out.println("resizing to "+(array.length+arr.length));
            T[] aux = (T[]) new Object[(array.length+arr.length)];
            for(int i =0; i <= last; i++){
                aux[i] = array[i];
            }
            for (int i = 0; i < arr.length; i++) {
                aux[last+1+ i] = arr[i];
            }
            this.array = aux;
        }
        last = last+arr.length;
    }

    public boolean contains(T t) {
        for(int i = 0; i <= last; i++){
            if(array[i].equals(t)) return true;
        }
        return false;
    }

    /*
    Determine if your arraylist is identical to another one
     */
    public boolean equals(GenericArrayList<T> arr){
        if(this.last == arr.last){
            for(int i = 0; i <= last; i++){
                if(!this.array[i].equals(arr.get(i))) return false;
            }
        }
        else return false;

        return true;
    }

    public void set(int index, T t){
        if(index>=0 && index <= last) {
            array[index] = t;
        }
        else{
            System.out.println("ArrayIndexOutOfBounds");
        }
    }

    public int getDefaultStep() {
        return defaultStep;
    }

    public void setDefaultStep(int defaultStep) {
        this.defaultStep = defaultStep;
    }

    public int size(){
        return (this.last+1);
    }

    @java.lang.Override
    public String toString() {

        String r = "GenericArrayList{";
        if(last>-1) r+= array[0].getClass()+", ";
        r   +=  "size=" + (last+1) +
                ", length=" + array.length +
                ", defaultStep=" + defaultStep +
                ", elements=[";
        for (int i = 0; i < last+1; i++) {
            if(i>0) r += ", ";
            r += array[i].toString();
        }
        return r + "] }";
    }
}
