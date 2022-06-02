package gestorCine.personal;

import gestorCine.tienda.CajaRegistradora;
import gestorCine.tienda.Servicio;

public interface Personal {
	public abstract void eliminarServicio(Servicio servicio);
	public abstract void asignarServicio(Servicio servicio);
	public abstract void cobrarSueldo(CajaRegistradora caja);
}
