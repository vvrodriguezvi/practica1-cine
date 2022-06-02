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

	/**
	 * 
	 * @param servicio
	 * @summary El metodo asignarServicio recibe como parametro un servicio y lo
	 *          agrega a la lista de servicios del tecnico en cuestion.
	 * 
	 */
	public void asignarServicio(Servicio servicio) {
		this.getServicios().add(servicio);
	}

	/**
	 * 
	 * @param tecnico
	 * @param producto
	 * @param cliente
	 * @summary generar servicio crea un servicio para revisar un producto que se le
	 *          asigna a la lista de servicios dependiente que lo creo y al tecnico
	 *          que va a realizarlo.
	 *          
	 */
	public void generarServicio(Supervisor supervisor, ByC byc, Cliente cliente) {
		Servicio servicio = new Servicio(supervisor, byc, cliente, this);
		supervisor.asignarServicio(servicio);
		asignarServicio(servicio);
	}

	/**
	 * 
	 * @param servicio
	 * @summary Se hace entrega del producto al dueno (cliente) para que lo revise y
	 *          recibir luego el pago.
	 *          
	 */
	public void finalizarServicio(Servicio servicio) {
		informarCliente(servicio);
		entregarProducto(servicio);
	}

	/**
	 * 
	 * @param servicio
	 * @summary metodo que entrega una factura del servicio al cliente, la factura contiene el identificador del servicio,
	 * el nombre y cedula del cliente, y el producto que fue reparado.
	 * 
	 */
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
		return "Dependiente: " + this.getNombre();
	}

	public static List<Taquillero> getTaquilleros() {
		return taquilleros;
	}

	public static void setDependientes(List<Taquillero> taquilleros) {
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