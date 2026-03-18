import java.io.*;
import java.util.*;
class Zona{
    String mineral;
    double cantidad;
    double pureza;
    public Zona(String mineral,double cantidad,double pureza){
        this.mineral=mineral;
        this.cantidad=cantidad;
        this.pureza=pureza;
    }
    public double valor(){
        return cantidad*pureza;
    }
    public String toString(){
        return "["+mineral+", cantidad: "+cantidad+", pureza: "+pureza+"]";
    }
}
public class JavPooEjer2{
    static Zona[][] leerArchivo(String nombre)throws Exception{
        Scanner sc=new Scanner(new File(nombre));
        int f=sc.nextInt();
        int c=sc.nextInt();
        Zona[][] matriz=new Zona[f][c];
        for(int i=0;i<f;i++){
            for(int j=0;j<c;j++){
                String mineral=sc.next();
                double cant=sc.nextDouble();
                double pure=sc.nextDouble();
                matriz[i][j]=new Zona(mineral,cant,pure);
            }
        }
        return matriz;
    }
    static void analizar(Zona[][] m,int k){
        int f=m.length;
        int c=m[0].length;
        double max=0;
        int posF=0,posC=0;
        for(int i=0;i<=f-k;i++){
            for(int j=0;j<=c-k;j++){
                double suma=0;
                for(int x=i;x<i+k;x++){
                    for(int y=j;y<j+k;y++){
                        suma+=m[x][y].valor();
                    }
                }
                if(suma>max){
                    max=suma;
                    posF=i;
                    posC=j;
                }
            }
        }
        System.out.println("region mas valiosa encontrada");
        System.out.println("posicion inicial: ("+posF+","+posC+")");
        System.out.println("Tamaño de la region "+k+" x "+k);
        System.out.println("\nZonas analizadas:");
        HashMap<String,Integer> conteo=new HashMap<>();
        for(int i=posF;i<posF+k;i++){
            for(int j=posC;j<posC+k;j++){
                System.out.println(m[i][j]);

                String min=m[i][j].mineral.toLowerCase();
                conteo.put(min,conteo.getOrDefault(min,0)+1);
            }
        }
        System.out.println("\nValor total estimado: "+max);
        String dominante="";
        int mayor=0;
        for(String key:conteo.keySet()){
            if(conteo.get(key)>mayor){
                mayor=conteo.get(key);
                dominante=key;
            }
        }

        System.out.println("mineral predominante en la region: "+dominante);
    }
    public static void main(String[] args)throws Exception{

        Zona[][] matriz=leerArchivo("datos.txt");
        int k=2;
        analizar(matriz,k);
    }
}