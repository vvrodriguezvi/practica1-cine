package gestionCine;

import java.util.ArrayList;
import java.util.List;



public class Taquilla {
	private String nombre;
	private boolean disponible;
	private double precio;
	public static List<Taquilla> taquillas;
	
	static {
		taquillas = new ArrayList<Taquilla>();
	}
	
	public Taquilla(String nombre, boolean disponible) {
		this.nombre = nombre;
		this.disponible = disponible;
	}

	public Taquilla(String nombre, boolean disponible, double precio) {
		this(nombre, disponible);
		this.precio = precio;
		taquillas.add(this);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean isAveriado() {
		return disponible;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String toString() {
		return nombre;
	}
	
	

}
