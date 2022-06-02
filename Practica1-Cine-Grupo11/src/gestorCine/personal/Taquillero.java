package gestorCine.personal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import baseDatos.Deserializador;
import gestorCine.tienda.*;


public class Taquillero extends Empleado implements Serializable {

	private static final long serialVersionUID = 1L;

	static List<Dependiente> dependientes;
	static {
		dependientes = new ArrayList<Dependiente>();
		
	}

	private CajaRegistradora cajaRegistradora;
	private static final double MARGEN_GANANCIA = 1.5;

	public Taquillero(String nombre, int cedula, CajaRegistradora caja) {
		super(nombre, cedula);
		this.cajaRegistradora = caja;
		dependientes.add(this);
	}

	public Taquillero(String nombre, int cedula, CajaRegistradora caja, List<Servicio> servicios) {
		this(nombre, cedula, caja);
		this.servicios = servicios;
		dependientes.add(this);
	}

	/**
	 * 
	 * @param cliente
	 * @param producto
	 * @summary Este metodo elige a alguno de los tecnicos disponibles para
	 *          asignarle un nuevo servicio con el producto entregado por el
	 *          cliente.
	 *          
	 */
	public void atenderCliente(Cliente cliente, Producto producto) {
		if(cliente.getRecibos().size() == 0) {
			Random rand = new Random();
			Tecnico tecnico = Tecnico.tecnicos.get(rand.nextInt(Tecnico.tecnicos.size()));
			generarServicio(tecnico, producto, cliente);
		}
	}

	/**
	 * 
	 * @param servicio
	 * @summary Registra el pago en la caja registradora con el costodel servicio
	 *          que decidio el tecnico y luego quita el servicio.
	 *          
	 */
	public void registrarPago(Servicio servicio) {
		cajaRegistradora.registrarVenta(servicio.getCosto() * MARGEN_GANANCIA, servicio);
		quitarServicio(servicio);
	}

	/**
	 * 
	 * @param servicio
	 * @summary El metodo quitarServicio recibe como parametro un servicio y lo
	 *          remueve de la lista de servicios del tecnico en cuestion.
	 * 
	 */
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
	public void generarServicio(Tecnico tecnico, Producto producto, Cliente cliente) {
		Servicio servicio = new Servicio(tecnico, producto, cliente, this);
		tecnico.asignarServicio(servicio);
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
		notificarCliente(servicio);
		entregarProducto(servicio);
	}

	/**
	 * 
	 * @param servicio
	 * @summary metodo que entrega una factura del servicio al cliente, la factura contiene el identificador del servicio,
	 * el nombre y cedula del cliente, y el producto que fue reparado.
	 * 
	 */
	private void notificarCliente(Servicio servicio) {
		Cliente cliente = servicio.getCliente();
		String recibo = "Factura #" + servicio.getIdentificador() + 
				"\n" + "Cliente: " + cliente.getNombre()  + " con cedula " + cliente.getCedula()
				+ "\nCosto total: " + servicio.getCosto() * MARGEN_GANANCIA
				+ "\n" + "Recibir el producto: " + servicio.getProducto().toString();
		cliente.recibirRecibo(recibo);
	}

	/**
	 * 
	 * @param servicio
	 * @summary metodo de entrega del producto al cliente dueno.
	 * 
	 */
	private void entregarProducto(Servicio servicio) {
		servicio.getCliente().recibirProducto(servicio.getProducto());
	}
	
	/**
	 * 
	 * @param servicio
	 * @summary calcula el precio del servicio que el cliente pagar�, utilizando costo asignado por el tecnico.
	 * 
	 */
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

	public static List<Dependiente> getDependientes() {
		return dependientes;
	}

	public static void setDependientes(List<Dependiente> dependientes) {
		Dependiente.dependientes = dependientes;
	}

	public static double getMargenGanancia() {
		return MARGEN_GANANCIA;
	}
	
	/**
	 * 
	 * @return lista de strings
	 * @summary Una de las 5 funcionalidades primarias, mira la lista de empleados, y ya sea un dependiente
	 * o un tecnico, cobra su respectivo salario, lo cual le resta un porcentaje a la caja de la tienda y le suma a la
	 * cartera de cada empleado su respectivo salario.
	 * 
	 */
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
