package EJER.EJER03;

public class Prueba{
    public static <E extends Comparable<E>> boolean sameArea(LinkedBSTit<E> a1,LinkedBSTit<E> a2){
        return a1.areaBST()==a2.areaBST();
    }
    public static void main(String[] args) throws Exception{
        LinkedBSTit<Integer> a1=new LinkedBSTit<>();
        LinkedBSTit<Integer> a2=new LinkedBSTit<>();
        a1.insert(50);
        a1.insert(30);
        a1.insert(70);
        a1.insert(60);
        a1.insert(80);
        a1.insert(20);
        a2.insert(100);
        a2.insert(10);
        a2.insert(20);
        a2.insert(90);
        a2.insert(80);
        a2.insert(70);
        System.out.println("Area a1: "+a1.areaBST());
        System.out.println("Area a2: "+a2.areaBST());
        System.out.println(sameArea(a1,a2));
        a1.drawBST();
        a2.drawBST();
    }
}