package Practica10.EJER.EJER02;

public class AristaCiudad{
    private NodoCiudad destino;
    private int peso;
    public AristaCiudad(NodoCiudad destino){
        this(destino, 1);
    }
    public AristaCiudad(NodoCiudad destino, int peso){
        this.destino = destino;
        this.peso = peso;
    }
    public NodoCiudad getDestino(){
        return destino;
    }
    public int getpeso(){
        return peso;
    }
    public void setDestino(NodoCiudad destino){
        this.destino = destino;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    @Override
    public String toString() {
        return destino.toString();
    }
}
