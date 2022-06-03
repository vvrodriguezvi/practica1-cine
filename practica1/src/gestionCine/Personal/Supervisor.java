package gestionCine.Personal;

import java.io.Serializable;
import java.util.*;
import gestionCine.*;


public class Supervisor extends Empleado  {
	public static List<Supervisor> supervisores;
	static {
		supervisores = new ArrayList<Supervisor>();
		
	}
	
	public Supervisor(String nombre, int cedula) {
		super(nombre, cedula);
		supervisores.add(this);
	}
	
	public Supervisor(String nombre, int cedula, List<Servicio> servicios) {
		this(nombre, cedula);
		this.servicios = servicios;
		supervisores.add(this);
	}
	
	private List<Taquilla> verificarProblemas(Servicio servicio){
		ByC producto = servicio.getProducto();
		List<Taquilla> averiados = new ArrayList<Taquilla>();
		for (Taquilla taquilla : producto.getComponentes()) {
			if (taquilla.isAveriado()) {
				averiados.add(taquilla);
			}
		}
		return averiados;
	}
	
	private Taquilla buscarTaquilla(Taquilla taquilla) {
		return Almacen.sacarComponente(taquilla.getNombre());
	}
	public void diagnosticar(Servicio servicio) {
		servicio.setDiagnostico("Se encontraron problemas en los siguientes componentes del producto: "+ verificarProblemas(servicio));
	}
	public void reparar(Servicio servicio) {
		Producto producto = servicio.getProducto();
		List<Taquilla> averiados = verificarProblemas(servicio);
		for (Taquilla taquilla: averiados) {
			Taquilla remplazo = buscarComponente(taquilla);
			if (remplazo != null) {
				Taquilla taquillaAlmacen = Almacen.sacarTaquilla(remplazo);
				producto.quitarTaquilla(taquilla);
				producto.agregarTaquilla(taquillaAlmacen);
				servicio.setCosto(servicio.getCosto()+taquillaAlmacen.getPrecio());
			}
		}
		servicio.setReparado(true);
		quitarServicio(servicio);
	}
	
	public void asignarServicio(Servicio servicio) {
		this.getServicios().add(servicio);
	}
	
	public void quitarServicio(Servicio servicio) {
		this.getServicios().remove(servicio);
	}
	
	public void cobrarSalario(CajaRegistradora caja) {
		double porcentaje = 0.02;
		this.cartera+= caja.descontar(porcentaje);
	}
	
	public String toString() {
		return "Supervisor: " + this.getNombre();
	}
}
