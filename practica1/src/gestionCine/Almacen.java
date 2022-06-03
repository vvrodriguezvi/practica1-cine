package gestionCine;

//import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import baseDatos.Deserializador;

public class Almacen {//implements Serializable{
	
	//private static final long serialVersionUID = 1L;
	private static List<Taquilla> taquillas;
	private static List<Taquilla> taquillas2;
	private static List<Taquilla> taquillas3;
	static {
		taquillas2 = new ArrayList<Taquilla>();
	}
	static {
		taquillas3 = new ArrayList<Taquilla>();
	}
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
	public static List<Taquilla> listaPeli(){
		int i=0;
		for(taquillas.get(i);i<taquillas.size();i++) {
			if(taquillas.get(i).getCine()==true) {
				taquillas2.add(taquillas.get(i));
			}
		}
		return taquillas2;
	}
	public static List<Taquilla> listaConfi(){
		int i=0;
		for(taquillas.get(i);i<taquillas.size();i++) {
			if(taquillas.get(i).getCine()==false) {
				taquillas3.add(taquillas.get(i));
			}
		}
		return taquillas3;
	}
	

	public static void setComponentes(List<Taquilla> taquillas) {
		Almacen.taquillas = taquillas;
	}
	
}
