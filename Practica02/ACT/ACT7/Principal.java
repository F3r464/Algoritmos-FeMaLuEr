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
        //Golosinas
        Bolsa<Golosinas> bolsaGolo=new Bolsa<>(3);
        Golosina g1=new Golosina("paleta", 0.8);
        Golosina g2=new Golosina("Gomitas", 0.5);
        Golosina g3=new Golosina("Chicle", 0.2);
        bolsaCho.add(g1);
        bolsaCho.add(g2);
        bolsaCho.add(g3);
        for(Golosina golosi: bolsaGolo){
            System.out.println(golosi);
        }
    }
}