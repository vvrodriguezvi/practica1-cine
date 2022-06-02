package gestorCine.tienda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Taquilla implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private boolean dis; //disponibilidad del cine
	private double precio;
	
	public static List<Taquilla> taquillas;
	
	static {
		taquillas = new ArrayList<Taquilla>();
	}
	
	//constructores
	
	public Taquilla(String nombre, boolean dis) {
		this.nombre = nombre;
		this.dis = dis;
	}

	public Taquilla(String nombre, boolean dis, double precio) {
		this(nombre, dis);
		this.precio = precio;
		taquillas.add(this);
	}

	//Getters y setters
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	public boolean isDis() {
		return dis;
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
