package Ejercicio09;

public class Smartphone implements Cargable {
	private String modelo;
	private double consumoVatios;
	private int nivelBateria;
	
	public Smartphone(String modelo, double consumoVatios) {
		this.modelo= modelo;
		this.consumoVatios= consumoVatios;
		this.nivelBateria = 0;
	}
	@Override
	public double getConsumoVatios() {
		return consumoVatios;
	}
	@Override
	public int getNivelBateria() {
		return nivelBateria;
	}
	@Override
	public void cargar(int cantidad) {
		this.nivelBateria += cantidad;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) { //si el objeto es nulo o esta vacio O al revelar el modelo (si es un string o cualquier tipo de dato) utilizando el metodo getClass()
			return false;
		}
		Smartphone otro = (Smartphone) obj; //aqui se usa el casteo ya que necesitamos decirle a java que el objeto es de tipo smartphone para que despues se pueda usar
		return this.modelo.equals(otro.modelo); //aca es donde retorna si es true o false con el equals
	}
}
	
