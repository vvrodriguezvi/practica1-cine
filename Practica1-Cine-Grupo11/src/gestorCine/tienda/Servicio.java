package gestorCine.tienda;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import gestorCine.personal.*;


public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private static List<Servicio> servicios;
	private Supervisor supervisor;
	private boolean pagado;
	private ByC byc;
	private Date fecha;
	private Cliente cliente;
	private Taquillero taquillero;
	private double costo;
	
	static {
		servicios = new ArrayList<Servicio>();
	}
		

	public Servicio(Supervisor supervisor, ByC byc, Cliente cliente,
			Taquillero taquillero) {
		this.supervisor = supervisor;
		this.byc = byc;
		this.setFecha(new Date());
		this.cliente = cliente;
		this.taquillero = taquillero;
		
		if (servicios.isEmpty())
			id = 0;
		else 
			id = servicios.size();
		
		servicios.add(this);
	}

	//Metodos
	
	public void anadirCosto(double precio) {
		this.costo+=precio;
	}

	public String toString() {
		return "\nIdentificador del servicio: " + this.id
				+ "\nCliente: " + this.cliente
				+ "\nProducto asociado: " + this.byc
				+ "\nPagado: " + this.pagado + "\n";
	}
	
	//Getter y setter
	
	public double getCosto() {
		return costo;
	}
	
	
	public boolean isPagado() {
		return pagado;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getIdentificador() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static List<Servicio> getServicios() {
		return servicios;
	}

	public static void setServicios(List<Servicio> servicios) {
		Servicio.servicios = servicios;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setPagado(boolean pagado){
		this.pagado = pagado;
	}
	
	
	public ByC getByC() {
		return byc;
	}


	public Taquillero getTaquillero() {
		return taquillero;
	}
	
}
