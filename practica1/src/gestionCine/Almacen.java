package gestionCine;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import baseDatos.Deserializador;

public class Almacen {//implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	private static List<Taquilla> taquillas;
	static {
		taquillas = new ArrayList<Taquilla>();
	}
	
	public static void agregarComponente(Taquilla taquilla) {
		taquillas.add(taquilla);	
	}	

	public static Taquilla sacarComponente(String nombreTaquilla) {
		for (Taquilla taquilla: taquillas) {
			if (taquilla.getNombre().equals(nombreTaquilla)) {
				return taquilla;
			}
		}
		return null;
	}
	
	public static Taquilla sacarComponente(Taquilla taquilla) {
		int index = taquillas.indexOf(taquilla);
		if (index >= 0) {
			return taquillas.remove(index);
		}
		return null;
	}
	
	public static List<Taquilla> getTaquillas(){
		return taquillas;
	}

	public static void setComponentes(List<Taquilla> taquillas) {
		Almacen.taquillas = taquillas;
	}
	
}
