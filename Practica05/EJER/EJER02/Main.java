public class Main{
   public static void main(String[] args){
      ListLinked<Integer> lista1 = new ListLinked<>();
      ListLinked<Integer> lista2 = new ListLinked<>();
      lista1.insertLast(10);
      lista1.insertLast(20);
      lista1.insertLast(30);
      lista1.insertLast(40);
      lista1.insertLast(50);
      lista1.insertLast(60);
      lista1.insertLast(70);
      lista1.insertLast(80);
      lista1.insertLast(90);
      lista2=NuevaListaInvertida.invertirLista(lista1);
      lista2.print();
    }
}