package Practica11.Interfaz;

public class Register {

    private int id;
    private String nombre;
    private int saldo;

    public Register(int id, String nombre, int saldo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){ 
        return nombre; 
    }

    public int getSaldo(){
        return saldo;
    }

    @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }
}