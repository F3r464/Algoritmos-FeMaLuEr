public class Main{
    public static void main(String [] args){
        List<String> lista=new ArrayList();
        lista.add("hola");
        lista.add("adios");
        String palabra = lista.get(1);
        System.out.println(palabra);
        
        List lista2 = new ArrayList();
        lista2.add("hoa");
        lista2.add(3);
        lista2.add(24,56);
        int palabra2 = (int)lista2.get(1);
        palabra2 = (int)lista2.get(0);
    }
}     
