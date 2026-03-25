package EJER02;
public class Principal{
    public static <T> void mostrarBolsa(Bolsa<T> bolsa){
        for (T elemento : bolsa) {
            System.out.println(elemento);
        }
    }
    public static void main(String[] args){
        //Chocolatina
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(3);
        bolsaCho.add(new Chocolatina("milka"));
        bolsaCho.add(new Chocolatina("ferrero"));
        //Golosina
        Bolsa<Golosina> bolsaGol = new Bolsa<>(3);
        bolsaGol.add(new Golosina("gomita", 10.5));
        bolsaGol.add(new Golosina("caramelo", 5.0));
        //Método genérico
        System.out.println("Chocolatinas:");
        mostrarBolsa(bolsaCho);

        System.out.println("Golosinas:");
        mostrarBolsa(bolsaGol);
    }
}