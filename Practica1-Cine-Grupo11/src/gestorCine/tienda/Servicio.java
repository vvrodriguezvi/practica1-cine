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
	private String consulta;
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
		this.pagado = false;
		
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public ByC getByc() {
		return byc;
	}

	public void setByc(ByC byc) {
		this.byc = byc;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Taquillero getTaquillero() {
		return taquillero;
	}

	public void setTaquillero(Taquillero taquillero) {
		this.taquillero = taquillero;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	
	
}
