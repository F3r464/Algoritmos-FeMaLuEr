import java.util.*;
public class Principal{
    public static void main(String [] args){
        Bolsa<Chocolatina> bolsaCho=new Bolsa<>(3);
        Chocolatina c1=new Chocolatina("milky");
        Chocolatina c2=new Chocolatina("Iberica");
        Chocolatina c3=new Chocolatina("Valenzuela");
        bolsaCho.add(c1);
        bolsaCho.add(c2);
        bolsaCho.add(c3);
        for(Chocolatina choco: bolsaCho){
            System.out.println(choco);
        }
    }
}