package Practica10.EJER.EJER02;

public class AdjListCiudad {

    private NodoCiudad ciudad;
    private ListLinked<AristaCiudad> aristas;

    public AdjListCiudad(NodoCiudad ciudad) {
        this.ciudad = ciudad;
        this.aristas = new ListLinked<>();
    }

    public NodoCiudad getCiudad() {
        return ciudad;
    }

    public ListLinked<AristaCiudad> getAristas() {
        return aristas;
    }
}