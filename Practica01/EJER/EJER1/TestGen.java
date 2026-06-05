public class TestGen{
    public static void main(String[]args){
        String[] v={"Kana", "Paredes", "Alarcon", "Alvarez"};
        Integer[] w={12,34,56};
        System.out.println(DemoMetodosGenericos.exist(v, "Alvarez"));
        System.out.println(DemoMetodosGenericos.exist(w, "34"));
        System.out.println(DemoMetodosGenericos.exist(v, "12"));
   }
}