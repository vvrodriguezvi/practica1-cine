package gestionCine.Personal;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gestionCine.*;


public class Taquillero extends Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	static List<Taquillero> taquilleros;
	static {
		taquilleros = new ArrayList<Taquillero>();
		
	}

	private CajaRegistradora cajaRegistradora;
	private static final double GANANCIA = 1.5;

	public Taquillero(String nombre, int cedula, CajaRegistradora caja) {
		super(nombre, cedula);
		this.cajaRegistradora = caja;
		taquilleros.add(this);
	}

	public Taquillero(String nombre, int cedula, CajaRegistradora caja, List<Servicio> servicios) {
		this(nombre, cedula, caja);
		this.servicios = servicios;
		taquilleros.add(this);
	}

	
	public void atenderCliente(Cliente cliente, ByC producto) {
		if(cliente.getRecibos().size() == 0) {
			Random rand = new Random();
			Supervisor supervisor = Supervisor.supervisores.get(rand.nextInt(Supervisor.supervisores.size()));
			generarServicio(supervisor, producto, cliente);
		}
	}

	
	public void registrarPago(Servicio servicio) {
		cajaRegistradora.registrarVenta(servicio.getCosto() * GANANCIA, servicio);
		quitarServicio(servicio);
	}

	
	public void quitarServicio(Servicio servicio) {
		this.getServicios().remove(servicio);
	}

	
	public void asignarServicio(Servicio servicio) {
		this.getServicios().add(servicio);
	}

	
	
	public void generarServicio(Supervisor supervisor, ByC byc, Cliente cliente) {
		Servicio servicio = new Servicio(supervisor, byc, cliente, this);
		supervisor.asignarServicio(servicio);
		asignarServicio(servicio);
	}

	
	public void finalizarServicio(Servicio servicio) {
		notificarCliente(servicio);
		entregarByC(servicio);
	}

	
	private void notificarCliente(Servicio servicio) {
		Cliente cliente = servicio.getCliente();
		String recibo = "Factura #" + servicio.getIdentificador() + 
				"\n" + "Cliente: " + cliente.getNombre()  + " con cedula " + cliente.getCedula()
				+ "\nCosto total: " + servicio.getCosto() * GANANCIA
				+ "\n" + "Recibir el producto: " + servicio.getProducto().toString();
		cliente.recibirRecibo(recibo);
	}

	
	private void entregarByC(Servicio servicio) {
		servicio.getCliente().recibirProducto(servicio.getProducto());
	}
	
	
	public void cobrarServicio(Servicio servicio) {
		double cobro = servicio.getCosto() * GANANCIA;
		servicio.getCliente().pagarServicio(servicio, cobro);
		if (!servicio.isPagado()) {
			this.cajaRegistradora.registrarVenta(cobro, servicio);
			servicio.setPagado(true);
		}
	}
	public void cobrarSalario(CajaRegistradora caja) {
		double porcentaje = 0.01;
		this.cartera += caja.descontar(porcentaje);
	}

	public CajaRegistradora getCajaRegistradora() {
		return cajaRegistradora;
	}

	public void setCajaRegistradora(CajaRegistradora cajaRegistradora) {
		this.cajaRegistradora = cajaRegistradora;
	}

	public String toString() {
		return "Taquillero: " + this.getNombre();
	}

	public static List<Taquillero> getTaquillero() {
		return taquilleros;
	}

	public static void setTaquillero(List<Dependiente> taquilleros) {
		Taquillero.taquilleros = taquilleros;
	}

	public static double getMargenGanancia() {
		return GANANCIA;
	}
	
	
	public List<String> liquidar() {
		
		CajaRegistradora caja = this.cajaRegistradora;
		
		List<String> liquidaciones = new ArrayList<String>();
		
		double contador = 0;
		//Se mira la lista de empleados, donde esta el dependiente y los tecnicos.
		for (Empleado empleado : Empleado.getEmpleados()) {
			double carteraInicial = empleado.getCartera();
			
			//Utiliza la ligadura dinamica para utilizar el metodo ya sea de dependiente o de tecnico.
			empleado.cobrarSalario(caja);

			double carteraAhora = empleado.getCartera();
			double liquidado = carteraAhora - carteraInicial;
			//Se va contando cuanto cobra cada empleado para poder descontarlo de la caja.
			contador += liquidado;
			liquidaciones.add("El " + empleado.toString() + " ha recibido " + Math.round(liquidado) + " por su trabajo.");
		}
		
		//Se descuenta de la caja lo cobrado por los empleados.
		caja.setTotalIngresos(caja.getTotalIngresos() - contador);
		return liquidaciones;
	}
}