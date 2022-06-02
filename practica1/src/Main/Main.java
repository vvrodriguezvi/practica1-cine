package Main;

import gestionCine.PrecioCine;
import gestionCine.Taquilla;
import gestionCine.Almacen;



public class Main {
	public static void main(String[] args) {
		System.out.println(PrecioCine.GASEOSA.getPrecio());
		Taquilla a = new Taquilla("camilo", false, PrecioCine.PALOMITAS.getPrecio());
		Taquilla[] componentes = { new Taquilla("Memoria 4g Kinsgton", false, PrecioCine.PERRO.getPrecio()),
				new Taquilla("camilo", false, PrecioCine.PALOMITAS.getPrecio())};
		System.out.println(Taquilla.taquillas);
		System.out.println(componentes[1].getPrecio());
		System.out.println(Almacen.getTaquillas());
		Almacen.agregarComponente(a);
		System.out.println(Almacen.getTaquillas());
		Almacen.sacarComponente(a);
		System.out.println(Almacen.getTaquillas());
		
	}
}
