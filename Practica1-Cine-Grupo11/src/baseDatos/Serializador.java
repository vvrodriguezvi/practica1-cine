package baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import gestorCine.personal.Taquillero;
import gestorCine.personal.Empleado;
import gestorCine.personal.Supervisor;
import gestorCine.tienda.Almacen;
import gestorCine.tienda.CajaRegistradora;
import gestorCine.tienda.Cliente;
import gestorCine.tienda.Taquilla;
import gestorCine.tienda.ByC;
import gestorCine.tienda.Servicio;

public class Serializador {

	public static <E> void serializar(List<E> lista, String className) {
		FileOutputStream fileOut;

		try {
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
			fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(lista);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Serializar clases
	
	public static void serializarTodo() {
		Serializador.serializar(Taquillero.getTaquilleros(), "Taquilleros");
		Serializador.serializar(Supervisor.supervisores, "Supervisores");
		Serializador.serializar(CajaRegistradora.cajasRegistradoras, "CajasRegistradoras");
		Serializador.serializar(Cliente.getClientes(), "Clientes");
		Serializador.serializar(Taquilla.taquillas, "Taquilla");
		Serializador.serializar(ByC.carritoCompras, "Productos_del_cinema");
		Serializador.serializar(Servicio.getServicios(), "Servicios");
		Serializador.serializar(Almacen.getTaquillas(), "Almacen");
		Serializador.serializar(Empleado.getEmpleados(), "Empleados");
	}
}


