class DemoMetodosGenericos{
    public static <T extends Comparable<T>> boolean igualArrays (T[] x, T[]y){
        if(x.length != y.length){
            System.out.println("no son misma cantidad");
            return false;
        }
        for(int i=0; i<x.length;i++){
            if(!x[i].equals(y[i])){
                System.out.println("no son iguales");
                return false;
            }
        }
        return true;
    }

    // Inicio parte Ejer1
    public static <T> boolean exist(T[] x, T y){
        for(T i:x){
            if(i.equals(y)){
                return true;
            }
        }
        return false;
    }
    //Fin Parte Ejer1

    public static void main(String []args){
        Integer num1[]={1,2,2,3};
        Integer num2[]={1,2,2,3};
        Integer num3[]={1,2,3,4};
        Integer num4[]={1,2,2,3,6};
        if(igualArrays(num1,num1)){
            System.out.println("num1 es igual a nums");
        }
        if(igualArrays(num1,num2)){
            System.out.println("num1 es igual a nums2");
        }
        if(igualArrays(num1,num3)){
            System.out.println("num1 es igual a num3");
        }
        if(igualArrays(num1,num4)){
            System.out.println("num1 es igual a num4");
        }
        /*
        Double dvals[]={1.1,2.2,3.3,4.4,5.5};
        if(igualArrays(num1, dvals)){
            System.out.println("num es igual a dvals");
        }
        */
    }
}