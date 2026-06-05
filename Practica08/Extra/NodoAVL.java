package Extra;

public class NodoAVL {

    public int codigo;
    public String nombre;

    public int bf;

    public NodoAVL izquierda;
    public NodoAVL derecha;

    public NodoAVL(int codigo,
                   String nombre) {

        this.codigo = codigo;
        this.nombre = nombre;

        bf = 0;
    }

    @Override
    public String toString() {

        return codigo
                + " - "
                + nombre
                + " (BF="
                + bf
                + ")";
    }
}