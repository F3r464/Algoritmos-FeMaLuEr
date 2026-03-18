import java.io.File;
import java.util.*;
public class Terreno{
    Zona[][] matriz;
    public Terreno(String archivo)throws Exception{
        cargar(arc);
    }
    private void cargar(String archivo)throws Exception{
        Scanner sc=new Scanner(new File(arc));
        int f=sc.nextInt();
        int c=sc.nextInt();
        matriz=new Zona[f][c];

        for(int i=0;i<f;i++){
            for(int j=0;j<c;j++){
                matriz[i][j]=new Zona(sc.next(),sc.nextDouble(),sc.nextDouble());
            }
        }
    }
    public void mejorRegion(int k){

        int f=matriz.length;
        int c=matriz[0].length;
        double max=0;
        int fi=0;
        int ci=0;
        for(int i=0;i<=f-k;i++){
            for(int j=0;j<=c-k;j++){

                double suma=0;

                for(int x=i;x<i+k;x++)
                    for(int y=j;y<j+k;y++)
                        suma+=matriz[x][y].valor();

                if(suma>max){
                    max=suma;
                    fi=i;
                    ci=j;
                }
            }
        }
        System.out.println("region mas valiosa encontrada");
        System.out.println("posicion inicial: ("+fi+","+ci+")");
        System.out.println("Tamaño de la region "+k+" x "+k);
        System.out.println("\nZonas:");
        HashMap<String,Integer> cont=new HashMap<>();
        for(int i=fi;i<fi+k;i++){
            for(int j=ci;j<ci+k;j++){
                System.out.println(matriz[i][j]);
                String m=matriz[i][j].mineral;
                cont.put(m,cont.getOrDefault(m,0)+1);
            }
        }
        System.out.println("\nValor total: "+max);
        String dom="";
        int mayor=0;
        for(String key:cont.keySet()){
            if(cont.get(key)>mayor){
                mayor=cont.get(key);
                dom=key;
            }
        }
        System.out.println("Mineral predominante: "+dom);
    }
}