package gestorCine.tienda;

import java.io.Serializable;
import java.util.*;


//import baseDatos.Deserializador;


public class Almacen implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static List<Taquilla> taquillas;
	static {
		taquillas = new ArrayList<Taquilla>();
	}
	
	public static void agregarProducto(Taquilla taquilla) {
		taquillas.add(taquilla);	
	}
	
	public static Taquilla sacarProducto(String nombreProducto) {
		for (Taquilla taquilla: taquillas) {
			if (taquilla.getNombre().equals(nombreProducto)) {
				return taquilla;
			}
		}
		return null;
	}
	
	public static Taquilla sacarProducto(Taquilla taquilla) {
		int index = taquillas.indexOf(taquilla);
		if (index >= 0) {
			return taquillas.remove(index);
		}
		return null;
	}
	
	//get y set
	
	public static List<Taquilla> getTaquillas(){
		return taquillas;
	}

	public static void setComponentes(List<Taquilla> taquillas) {
		Almacen.taquillas = taquillas;
	}
	
}
