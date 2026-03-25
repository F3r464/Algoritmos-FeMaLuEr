package Ejercicio09;

public class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;

    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
    }

    @Override
    public double getConsumoVatios() {
        return consumoVatios;
    }

    @Override
    public int getNivelBateria() {
        return 0; 
    }

    @Override
    public void cargar(int cantidad) {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;

        Laptop otra = (Laptop) obj; //aqui se usa el casteo ya que necesitamos decirle a java que el objeto es de tipo laptop para que despues se pueda usar

        return this.marca.equals(otra.marca); //comparamos el contenido real con el equals
    }
}