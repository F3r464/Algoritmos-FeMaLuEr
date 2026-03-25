package EJER01;
public class Chocolatina {
    private String marca;
    //Constructor
    public Chocolatina(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    //Método equals (comparar objetos por su marca)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Chocolatina other = (Chocolatina) obj;
        return marca.equals(other.marca);
    }
}