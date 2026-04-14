import java.util.*;
public class MayorK{
    static int buscar(int[] arr,int k){
        int marca=arr[arr.length/2];

        int[] max=new int[arr.length];
        int[] min=new int[arr.length];
        int contmax=0,contmin=0,conteq=0;

        for(int n:arr){
            if(n>marca){
                max[contmax++]=n;
            } else if(n<marca){
                min[contmin++]=n;
            } else{
                conteq++;
            }
        }
        if(k<=contmax){
            return buscar(Arrays.copyOf(max,contmax),k);
        }else if(k<=contmax+conteq){
            return marca;
        }else{
            return buscar(Arrays.copyOf(min,contmin),k-contmax-conteq);
        }
    }
    public static void main(String[] args){

        int[] a1={4,2,7,10,4,17};
        int[] a2={4,2,7,10,4,1,6};
        int[] a3={4,2,7,1,4,6};
        int[] a4={9,2,7,1,7};
        System.out.println(buscar(a1,3));
        System.out.println(buscar(a2,5));
        System.out.println(buscar(a3,1));
        System.out.println(buscar(a4,4));
    }
}