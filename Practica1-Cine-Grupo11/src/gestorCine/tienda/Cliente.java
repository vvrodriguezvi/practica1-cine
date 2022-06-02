package gestorCine.tienda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import baseDatos.Deserializador;
import gestorCine.personal.Taquillero;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String cedula;
	private List<ByC> productos;
	private List<String> recibos;
	private Taquillero taquillero;
	private double cartera;
	private String direccion;
	static List<Cliente> clientes;
	static {
		clientes = new ArrayList<Cliente>();
	}
	
	// constructores
	
	public Cliente(String nombre, String cedula, List<ByC> productos, Taquillero taquillero, double cartera, String direccion) {
		this(nombre, cedula, productos, taquillero, cartera);	
		this.direccion = direccion;
	}
	
	public Cliente(String nombre, String cedula, List<ByC> productos, Taquillero taquillero, double cartera) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.productos = productos;
		this.taquillero = taquillero;
		this.cartera = cartera;
		this.recibos = new ArrayList<String>();
		clientes.add(this);
	}

	//metodos
	
	public void solicitarCompra(ByC byc) {
		this.taquillero.atenderCliente(this, byc);
		productos.remove(byc);
	}

	public void pagarServicio(Servicio servicio, double cobro) {
		if (!servicio.isPagado() && cartera >= cobro) {
			cartera -= cobro;
		}
	}

	public void recibirProducto(ByC byc) {
		this.productos.add(byc);
	}

	public String toString() {
		return " Nombre: " + nombre + " CC: " + cedula + " Cartera: " + cartera + " Direccion: " + direccion;
	}

	// get y setts
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public List<ByC> getProductos() {
		return productos;
	}

	public void setProductos(List<ByC> productos) {
		this.productos = productos;
	}

	public Taquillero getTaquillero() {
		return taquillero;
	}

	public void setDependiente(Taquillero taquillero) {
		this.taquillero = taquillero;
	}

	public void recibirRecibo(String recibo) {
		this.recibos.add(recibo);
	}

	public List<String> getRecibos() {
		return this.recibos;
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(List<Cliente> clientes) {
		Cliente.clientes = clientes;
	}

	public double getCartera() {
		return cartera;
	}	
}
