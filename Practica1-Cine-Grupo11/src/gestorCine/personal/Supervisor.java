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
	
	
	private List<Taquilla> verificarProblemas(Servicio servicio){
		ByC byc = servicio.getByC();
		List<Taquilla> noDis = new ArrayList<Taquilla>();
		for (Taquilla taquilla : byc.getTaquillas()) {
			if (taquilla.isDis()) {
				noDis.add(taquilla);
			}
		}
		return noDis;
	}
	
	private Taquilla buscarProducto(Taquilla taquillas) {
		return Bodega.sacarComponente(componente.getNombre());
	}
	
	public void diagnosticar(Servicio servicio) {
		servicio.setDiagnostico("Se encontraron problemas en los siguientes componentes del producto: "+ verificarProblemas(servicio));
	}
	
	public void reparar(Servicio servicio) {
		Producto producto = servicio.getProducto();
		List<Componente> averiados = verificarProblemas(servicio);
		for (Componente componente: averiados) {
			Componente remplazo = buscarComponente(componente);
			if (remplazo != null) {
				Componente componenteBodega = Bodega.sacarComponente(remplazo);
				producto.quitarComponente(componente);
				producto.agregarComponente(componenteBodega);
				servicio.setCosto(servicio.getCosto()+componenteBodega.getPrecio());
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
