package gestorCine.personal;
import java.io.Serializable;
import java.util.*;
import gestorCine.tienda.*;


public class Supervisor extends Empleado implements Serializable {
	private static final long serialVersionUID = 1L;
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
	
	//verificar problema !
	
	private List<Taquilla> verificarProducto(Servicio servicio){
		ByC byc = servicio.getByc();
		List<Taquilla> noDis = new ArrayList<Taquilla>();
		for (Taquilla taquilla : byc.getTaquillas()) {
			if (taquilla.isDis()) {
				noDis.add(taquilla);
			}
		}
		return noDis;
	}
	
	//buscarComponente, revisar este metodo
	
	private Taquilla buscarProducto(Taquilla taquilla) {
		return Almacen.sacarProducto(taquilla.getNombre());
	}
	
	// consultar
	
	public void consultarPeli(Servicio servicio) {
		servicio.setConsulta("Se encontraron disponibles"
				+ " las siguientes películas en el cinema: "+ verificarProducto(servicio));
	}
	
	public void prepararProd(Servicio servicio) {
		ByC byc = servicio.getByc();
		List<Taquilla> preparados = verificarProducto(servicio);
		for (Taquilla taquilla: preparados) {
			Taquilla entregar = buscarProducto(taquilla);
			if (entregar != null) {
				Taquilla productoAlmacen = Almacen.sacarProducto(entregar);
				byc.quitarProducto(taquilla);
				byc.agregarProducto(productoAlmacen);
				servicio.setCosto(servicio.getCosto()+productoAlmacen.getPrecio());
			}
		}
		servicio.setPagado(true);
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
