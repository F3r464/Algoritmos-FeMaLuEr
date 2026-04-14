import java.util.*;
public class MergeSort{
    static void dividir(int[] arr){
        if(arr.length<=1){
            return;
        }
        int mid=arr.length/2;
        int[] izq=Arrays.copyOfRange(arr,0,mid);
        int[] der=Arrays.copyOfRange(arr,mid,arr.length);
        dividir(izq);
        dividir(der);
        juntar(arr,izq,der);
    }
    static void juntar(int[] arr,int[] izq,int[] der){
        int i=0,j=0,k=0;

        while(i<izq.length&&j<der.length){
            if(izq[i]<der[j]){
                arr[k++]=izq[i++];
            } else{
                arr[k++]=der[j++];
            }
        }
        while(i<izq.length){
            arr[k++]=izq[i++];
        }
        while(j<der.length){
            arr[k++]=der[j++];
        }
    }
    public static void main(String[] args){
        int[] a1={5,2,8,1,3};
        int[] a2={9,4,6,2,7,1,8,3};
        int[] a3={10,5,2,8,6,1,9,3,7,4};

        System.out.println("Antes:");
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
        System.out.println(Arrays.toString(a3));

        dividir(a1);
        dividir(a2);
        dividir(a3);

        System.out.println("Despues:");
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));
        System.out.println(Arrays.toString(a3));
    }
}