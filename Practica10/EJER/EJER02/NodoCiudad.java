package Practica10.EJER.EJER02;

public class NodoCiudad{
        private String nombre;

    public NodoCiudad(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
