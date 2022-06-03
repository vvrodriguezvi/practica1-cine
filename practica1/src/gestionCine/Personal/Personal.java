package gestionCine.Personal;

import gestionCine.CajaRegistradora;
import gestionCine.Servicio;

public interface Personal {
	public abstract void quitarServicio(Servicio servicio);
	public abstract void asignarServicio(Servicio servicio);
	public abstract void cobrarSalario(CajaRegistradora caja);
}
