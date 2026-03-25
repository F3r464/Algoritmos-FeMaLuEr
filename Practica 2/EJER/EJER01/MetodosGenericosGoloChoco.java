package EJER01;
public class MetodosGenericosGoloChoco {

    //Método exist()
    public static <T> boolean exist(T[] arreglo, T elemento) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        //Chocolatina
        Chocolatina choco[] = {
            new Chocolatina("milka"),
            new Chocolatina("ferrero")
        };

        System.out.println("¿Existe milka?: " + exist(choco, new Chocolatina("milka")));
        System.out.println("¿Existe snickers?: " + exist(choco, new Chocolatina("snickers")));
        //Golosina
        Golosina golos[] = {
            new Golosina("gomita", 10.5),
            new Golosina("caramelo", 5.0)
        };

        System.out.println("¿Existe gomita?: " + exist(golos, new Golosina("gomita", 10.5)));
        System.out.println("¿Existe chocolate?: " + exist(golos, new Golosina("chocolate", 20.0)));
    }
}   
