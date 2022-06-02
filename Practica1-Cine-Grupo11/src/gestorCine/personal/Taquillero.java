package gestorCine.personal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import baseDatos.Deserializador;
import gestorCine.tienda.*;

public class Taquillero extends Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	static List<Taquillero> taquilleros;
	static {
		taquilleros = new ArrayList<Taquillero>();	
	}

	private CajaRegistradora cajaRegistradora;
	private static final double MARGEN_GANANCIA = 1.5; //revisar por que ni idea de eso :O

	// constructores
	
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

	//metodos
	
	public void atenderCliente(Cliente cliente, ByC byc) {
		if(cliente.getRecibos().size() == 0) {
			Random rand = new Random();
			Supervisor supervisor = Supervisor.supervisores.get(rand.nextInt(Supervisor.supervisores.size()));
			generarServicio(supervisor, byc, cliente);
		}
	}

	public void registrarPago(Servicio servicio) {
		cajaRegistradora.registrarVenta(servicio.getCosto() * 
				MARGEN_GANANCIA, servicio);
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
		informarCliente(servicio);
		entregarProducto(servicio);
	}
	
	//factura
	
	private void informarCliente(Servicio servicio) {
		Cliente cliente = servicio.getCliente();
		String recibo = "Factura #" + servicio.getId() + 
				"\n" + "Cliente: " + cliente.getNombre()  + " con cedula " + cliente.getCedula()
				+ "\nCosto total: " + servicio.getCosto() * MARGEN_GANANCIA
				+ "\n" + "Recibir el producto: " + servicio.getByc().toString();
		cliente.recibirRecibo(recibo);
	}


	private void entregarProducto(Servicio servicio) {
		servicio.getCliente().recibirProducto(servicio.getByc());
	}
	
	public void cobrarServicio(Servicio servicio) {
		double cobro = servicio.getCosto() * MARGEN_GANANCIA;
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

	public static List<Taquillero> getTaquilleros() {
		return taquilleros;
	}

	public static void setTaquilleros(List<Taquillero> taquilleros) {
		Taquillero.taquilleros = taquilleros;
	}

	public static double getMargenGanancia() {
		return MARGEN_GANANCIA;
	}
	
	public List<String> liquidar() {
		
		CajaRegistradora caja = this.cajaRegistradora;
		
		List<String> liquidaciones = new ArrayList<String>();
		
		double contador = 0;
		
		for (Empleado empleado : Empleado.getEmpleados()) {
			double carteraInicial = empleado.getCartera();
			
			empleado.cobrarSalario(caja);

			double carteraAhora = empleado.getCartera();
			double liquidado = carteraAhora - carteraInicial;
			contador += liquidado;
			liquidaciones.add("El " + empleado.toString() + " ha recibido " + Math.round(liquidado) + " por su trabajo.");
		}
		
		caja.setTotalIngresos(caja.getTotalIngresos() - contador);
		return liquidaciones;
	}
}