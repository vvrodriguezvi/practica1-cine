package gestorCine.tienda;


import java.io.Serializable;
import java.util.*;




public class ByC implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private List<Taquilla> taquillas;
	
	public static List<ByC> carritoCompras;
	
	static {
		carritoCompras = new ArrayList<ByC>();
	}
	
	//constructor
	
	public ByC(String nombre, List<Taquilla> taquillas) {
		super();
		this.nombre = nombre;
		this.taquillas = taquillas;
		carritoCompras.add(this);
	}
	
	
	
	//getter y setter
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public List<Taquilla> getTaquillas() {
		return taquillas;
	}



	public void setTaquillas(List<Taquilla> taquillas) {
		this.taquillas = taquillas;
	}



	public static List<ByC> getCarritoCompras() {
		return carritoCompras;
	}



	public static void setCarritoCompras(List<ByC> carritoCompras) {
		ByC.carritoCompras = carritoCompras;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//metodos
	
	
	public void agregarProducto(Taquilla taquilla) {
		taquillas.add(taquilla);
	}
	

	public void quitarProducto(Taquilla taquilla) {
		taquillas.remove(taquilla);
	}

	public String toString() {
		return this.nombre;
	}
	

}
