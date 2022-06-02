package gestionCine;

import java.util.ArrayList;
import java.util.List;


public class ByC {
	private String nombre;
	private List<Taquilla> taquillas;
	
	public static List<ByC> carritoCompras;
	
	static {
		carritoCompras = new ArrayList<ByC>();
	}
	
	public ByC(String nombre, List<Taquilla> taquillas) {
		super();
		this.nombre = nombre;
		this.taquillas = taquillas;
		carritoCompras.add(this);
	}
	
	/**
	 * 
	 * @param componente
	 * @summary El metodo agregarComponente recibe como parametro un componente y lo agrega a la lista de componentes del producto.
	 * 
	 */
	public void agregarComponente(Taquilla taquilla) {
		taquillas.add(taquilla);
	}
	
	/**
	 * 
	 * @param componente
	 * @summary El metodo quitarComponente recibe como parametro un componente y lo quita de la lista de componentes del producto.
	 * 
	 */
	public void quitarComponente(Taquilla taquilla) {
		taquillas.remove(taquilla);
	}

	public List<Taquilla> getComponentes() {
		return taquillas;
	}
	
	public String toString() {
		return this.nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
