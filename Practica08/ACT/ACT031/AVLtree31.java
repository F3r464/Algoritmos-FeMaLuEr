package ACT.ACT031;

public class AVLtree31<E extends Comparable<E>>{
//Inicio Act 3.1
    public class AVLnodo{
        public E dato;
        public int bf;
        public AVLnodo left;
        public AVLnodo right;

        public AVLnodo(E dato){
            this.dato=dato;
            this.bf=0;
            left=null;
            right=null;
        }
        public String toString(){
            return dato+"(bf:"+bf+")";
        }
    }
    protected AVLnodo root;
    protected boolean altura;

    public AVLtree31(){
        root=null;
    }
    public boolean isEmpty(){
        return root==null;
    }
//Fin Act 3.1

}