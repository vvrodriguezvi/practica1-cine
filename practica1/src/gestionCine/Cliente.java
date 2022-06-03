package gestionCine;


import java.util.ArrayList;
import java.util.List;
import gestionCine.*;
import gestionCine.Personal.*;
import gestorAplicacion.personal.Dependiente;
import gestorAplicacion.tienda.Producto;


public class Cliente {
	private String nombre;
	private int cedula;
	private String direccion;
	private Double saldo;
	private List<ByC> carritoCompras;
	static List<Cliente> clientes;
	private Taquillero taquillero;
	static {
		clientes = new ArrayList<Cliente>();
		
	}
	
	
	public Cliente(String nombre, int cedula,String direccion, Double saldo, List<ByC> carritoCompras, Taquillero taquillero) {
		this(nombre, cedula, direccion, saldo, carritoCompras );	
		this.taquillero = taquillero;
	}
	
	public Cliente(String nombre, int cedula, String direccion, Double saldo, List<ByC> carritoCompras) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.saldo = saldo;
		this.direccion = direccion;
		this.carritoCompras = carritoCompras;
		clientes.add(this);
	}

	public void solicitarVenta(ByC producto) {
		this.Taquillero.atenderCliente(this, producto);
		carritoCompras.add(producto);
	}

	
	public void pagarServicio(Servicio servicio, double cobro) {
		if (!servicio.isPagado() && cartera >= cobro) {
			cartera -= cobro;
		}
	}

	public void recibirProducto(ByC producto) {
		this.carritoCompras.add(producto);
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
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public static Cliente eliminarCliente(Cliente cliente) {
		int index = clientes.indexOf(cliente);
		if (index >= 0) {
			return clientes.remove(index);
		}
		return null;
	}

	public List<ByC> getProductos() {
		return carritoCompras;
	}

	public void setProductos(List<ByC> productos) {
		this.carritoCompras = productos;
	}

	public Taquillero getDependiente() {
		return taquillero;
	}

	public void setDependiente(Taquillero taquillero) {
		this.dependiente = dependiente;
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

	public String toString() {
		return " Nombre: " + nombre + " CC: " + cedula + " Saldo: " +saldo + " Direccion: " + direccion + " Taquillero: "+taquillero+" Compras:"+carritoCompras;
	}
}