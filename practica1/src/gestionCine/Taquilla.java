package gestionCine;

import java.util.ArrayList;
import java.util.List;



public class Taquilla {
	private String nombre;
	private int disponible;
	private boolean cine;
	private double precio;
	public static List<Taquilla> taquillas;
	
	static {
		taquillas = new ArrayList<Taquilla>();
	}
	
	public Taquilla(String nombre, double precio, boolean cine) {
		this.nombre = nombre;
		this.precio = precio;
		this.cine =cine;
	}

	public Taquilla(String nombre, int disponible, double precio, boolean cine) {
		this(nombre, precio, cine);
		this.disponible=disponible;
		
		taquillas.add(this);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean getCine() {
		return cine;
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
