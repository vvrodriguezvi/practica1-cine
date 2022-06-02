package gestorCine.tienda;

public enum PrecioCine {
	BOLETA(14500),  
	COMBO1(15500), //PALOMITAS PEQUEÑA+GASEOSA
	COMBO2(20000), //PALOMITAS MEDIANAS + 2 GASEOSAS
	COMBO3(25500), //PALOMITAS GRANDES + 2 GASEOSAS
	PERRO(8900), 
	GAS_PEQUENA(1200),
	GAS_MEDIANA(1500), 
	GAS_GRANDE(2500), 
	PAL_PEQUENA(8700),
	PAL_MEDIANA(15400), 
	PAL_GRANDE(19200),
	PAQUETES_PAPAS(6000),
	CHOCOLATINA(3800),
	CONFITE(1600);
	
	
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
