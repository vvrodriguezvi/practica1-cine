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
	
	// constructores
	
	public Supervisor(String nombre, int cedula) {
		super(nombre, cedula);
		supervisores.add(this);
	}
	
	public Supervisor(String nombre, int cedula, List<Servicio> servicios) {
		this(nombre, cedula);
		this.servicios = servicios;
		supervisores.add(this);
	}
	
	//metodos
	
	public void programarPelicula(Servicio servicio) {
		servicio.setDiagnostico("Se encontraron problemas en los siguientes componentes del producto: "+ verificarSala(servicio));
	}
	
	
	private List<Componente> verificarSala(Servicio servicio){
		Producto producto = servicio.getProducto();
		List<Componente> averiados = new ArrayList<Componente>();
		for (Componente componente : producto.getComponentes()) {
			if (componente.isAveriado()) {
				averiados.add(componente);
			}
		}
		return averiados;
	}
	

	private Componente buscarComponente(Componente componente) {
		return Bodega.sacarComponente(componente.getNombre());
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
		return "Tecnico: " + this.getNombre();
	}
}
