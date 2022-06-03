package gestionCine;

public enum PrecioCine {
	BOLETA(14500),  
	PALOMITAS(19500), 
	PERRO(10900), 
	GASEOSA(8700), 
	CONFITERIA(23000);
	
	double precio;
	private PrecioCine(double p) {
		precio = p;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
