package gestionCine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import gestionCine.Personal.*;

public class CajaRegistradora implements Serializable {
	private static final long serialVersionUID = 1L;
	private double totalIngresos;
	private List<Servicio> servicios;
	
	public static List<CajaRegistradora> cajasRegistradoras;
	
	static {
		cajasRegistradoras = new ArrayList<CajaRegistradora>();
	}
	
	public CajaRegistradora() {
		servicios = new ArrayList<Servicio>();
		cajasRegistradoras.add(this);
	}
	
	public void registrarVenta(double precio, Servicio servicio) {
		servicios.add(servicio);
		this.totalIngresos += precio;
	}
	
	public double getTotalIngresos() {
		return this.totalIngresos;
	}

	public void setTotalIngresos(double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	
	public double descontar(double porcentaje) {
		double descuento = this.totalIngresos*porcentaje;
		return descuento;
	}
}