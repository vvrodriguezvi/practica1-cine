package gestorCine.tienda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CajaRegistradora implements Serializable {
	private static final long serialVersionUID = 1L;
	private double totalIngresos;
	private List<Servicio> servicios;
	
	public static List<CajaRegistradora> cajasRegistradoras;
	
	static {
		cajasRegistradoras = new ArrayList<CajaRegistradora>();
	}
	
	//Constructor

	public CajaRegistradora() {
		servicios = new ArrayList<Servicio>();
		cajasRegistradoras.add(this);
	}
	
	//Metodos
	
	public void registrarVenta(double precio, Servicio servicio) {
		servicios.add(servicio);
		this.totalIngresos += precio;
	}
	
	public double descontar(double porcentaje) {
		double descuento = this.totalIngresos*porcentaje;
		return descuento;
	}
	
	//Getter y setter
	
	public double getTotalIngresos() {
		return this.totalIngresos;
	}

	public void setTotalIngresos(double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	
}
