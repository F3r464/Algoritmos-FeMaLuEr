package Practica11.Interfaz;

public class Step {

    private String mensaje;
    private int posicion;
    private Register register;

    public Step(String mensaje, int posicion, Register register) {
        this.mensaje = mensaje;
        this.posicion = posicion;
        this.register = register;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getPosicion() {
        return posicion;
    }

    public Register getRegister() {
        return register;
    }
}