package structures;

public class Driver {
    public static void main(String[] args){
        GenericArrayList<String> list = new GenericArrayList<String>(3);
        //System.out.println(list);
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        System.out.println(list);
        GenericArrayList<String> list0 = new GenericArrayList<String>(4);

        list0.add("one");
        list0.add("two");
        list0.add("three");
        list0.add("four");
        System.out.println(list0);
        System.out.println("are equals ? "+ list.equals(list0));
        list0.set(3,"five");
        System.out.println(list);
        System.out.println(list0);
        System.out.println("are equals ? "+ list.equals(list0));
        list.setDefaultStep(2);
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("eight");
        list.add("nine");
        System.out.println(list);
        System.out.println("contains 'ten'? "+ list.contains("ten"));
        System.out.println("contains 'four'? "+ list.contains("four"));
        list.set(3, "ten");
        System.out.println(list);
        System.out.println("contains 'ten'? "+ list.contains("ten"));
        System.out.println("contains 'four'? "+ list.contains("four"));


        /*GenericArrayList<Integer> list2 = new GenericArrayList<Integer>(4);
        System.out.println(list2);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        System.out.println(list2);
        list2.setDefaultStep(2);
        list2.add(6);
        list2.add(7);
        list2.add(8);
        list2.add(9);
        list2.add(10);
        System.out.println(list2);
        System.out.println("contains '11'? "+ list2.contains(11));
        System.out.println("contains '6'? "+ list2.contains(6));
        list2.set(5, 11);
        System.out.println(list2);
        System.out.println("contains '11'? "+ list2.contains(11));
        System.out.println("contains '6'? "+ list2.contains(6));
         */
    }
}
