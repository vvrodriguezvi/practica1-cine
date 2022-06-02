package baseDatos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import gestorCine.personal.Taquillero;
import gestorCine.personal.Empleado;
import gestorCine.personal.Supervisor;
import gestorCine.tienda.Almacen;
import gestorCine.tienda.CajaRegistradora;
import gestorCine.tienda.Cliente;
import gestorCine.tienda.Taquilla;
import gestorCine.tienda.ByC;
import gestorCine.tienda.Servicio;


public class Deserializador {

	public static <E> void deserializador(List<E> list, String className) {
		FileInputStream fileIn;
		try {
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + className + ".txt";
			System.out.println(path);
			File archivo = new File(path);
			archivo.createNewFile(); 
			fileIn = new FileInputStream(path);

			ObjectInputStream in = new ObjectInputStream(fileIn);

			ArrayList<E> listado = (ArrayList<E>) in.readObject();

		
			for (E el : listado) {
				list.add(el);
			}

			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Esta vacio");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// deserializador
	
	public static void deserializarTodo() {
		Deserializador.deserializador(Taquillero.getTaquilleros(), "Taquilleros");
		Deserializador.deserializador(Supervisor.supervisores, "Tecnicos");
		Deserializador.deserializador(CajaRegistradora.cajasRegistradoras, "CajasRegistradoras");
		Deserializador.deserializador(Cliente.getClientes(), "Clientes");
		Deserializador.deserializador(Taquilla.taquillas, "Taquilla");
		Deserializador.deserializador(ByC.carritoCompras, "Productos_del_cinema");
		Deserializador.deserializador(Servicio.getServicios(), "Servicios");
		Deserializador.deserializador(Almacen.getTaquillas(), "Almacen");
		Deserializador.deserializador(Empleado.getEmpleados(), "Empleados");
	}
}
