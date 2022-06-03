package gestionCine.Personal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import gestionCine.*;

public abstract class Empleado implements Personal, Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private int cedula;
	protected List<Servicio> servicios;
	protected double cartera;
	private static List<Empleado> empleados;
	static {
		empleados = new ArrayList<Empleado>();
	}
	
	public Empleado (String nombre, int cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
		servicios = new ArrayList<Servicio>();
		Empleado.empleados.add(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public List<Servicio> getServicios(){
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	public double getCartera() {
		return cartera;
	}

	public void setCartera(double cartera) {
		this.cartera = cartera;
	}
	
	public static List<Empleado> getEmpleados() {
		return empleados;
	}

	public static void setEmpleados(List<Empleado> empleados) {
		Empleado.empleados = empleados;
	}

	public abstract String toString();
	
	
	
}
