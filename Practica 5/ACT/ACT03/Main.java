
public class Main{
    public static void main(String[] args){
        ListLinked<Integer> lista = new ListLinked<>();
        lista.insertLast(1);
        lista.insertLast(2);
        lista.insertLast(3);
        //lista.print();   // 1  2  3
        lista.reverse();
        lista.print();   // 3  2  1
    }
}