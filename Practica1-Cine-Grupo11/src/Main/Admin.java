package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorCine.personal.*;
import gestorCine.tienda.*;

import java.io.IOException;
import java.lang.Math;

public class Admin {
	static Scanner sc = new Scanner(System.in);
	static Almacen almacen = new Almacen();
	
	//usar scanner entradas
	
	static int readInt() {
		return sc.nextInt();
	}

	static String readString() {
		return sc.nextLine();
	}

	static double readDouble() {
		return sc.nextDouble();
	}

	//MAIN EJECUTA
	
	public static void main(String[] args) {
		
		//Ejecuta deserializador.
		
		cargar();
		inicializar();
		System.out.println("Bienvenido Administrador \n");
		for (Empleado empleado : Empleado.getEmpleados()) {
			System.out.println(empleado.toString() + " cartera: " + empleado.getCartera());
		}
		int opcion;
		Cliente cliente;
		do { 
			//despliega menu de opciones!
			System.out.println("Que desea realizar?");
			System.out.println(" 1. Menu de los producto");
			System.out.println(" 2. Preparar un producto");
			System.out.println(" 3. Finalizar un servicio");
			System.out.println(" 4. Cobrar un servicio");
			System.out.println(" 5. Liquidacion del periodo");
			System.out.println(" Opciones alternativas");
			System.out.println(" 6. Mostrar clientes");
			System.out.println(" 7. Mostrar servicios");
			System.out.println(" 8. Guardar y cerrar");
			System.out.println("Elija una opcion: ");
			opcion = (int) readInt(); //entrada usuario

			switch (opcion) {

			case 1:
				menuDiagnosticar();
				break;
			case 2:
				reparar();
				break;
			case 3:
				finalizarServicio();
				break;
			case 4:
				cobrarServicio();
				break;
			case 5:
				liquidar();
				break;
			case 6:
				for (int i = 0; i < Cliente.getClientes().size(); i++) {
					cliente = Cliente.getClientes().get(i);
					System.out.println("Cliente id: " + i + cliente.toString());
				}
				break;
			case 7:
				System.out.println("Servicio activos:");
				for (int i = 0; i < Servicio.getServicios().size(); i++) {
					Servicio servicio = Servicio.getServicios().get(i);
					System.out.println("Servicio id:" + i + servicio.toString());
				}
				break;
				
			case 8:
				guardar();
				System.out.println("Vuelve pronto");
			}
			if (opcion != 8) {
				System.out.println("\nPresione Enter para continuar");
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} while (opcion != 8);

	}
	
	/**
	 * @summary Diagnostica el servicio seleccionado por el administrador.
	 */
	static void diagnosticar() {
		try {
			System.out.println("Ingrese el id del servicio");
			int idServicio = readInt();
			Servicio servicio = Servicio.getServicios().get(idServicio);
			// Busca los componentes con problemas en el producto asociado al servicio.
			if (!servicio.isReparado()) {
				servicio.getTecnico().diagnosticar(servicio);
				// Devuelve el diagnostico hecho por el tecnico.
				System.out.println(servicio.getDiagnostico());
				System.out.println("Ya puede volver al menu principal para solicitar reparacion\n");
			} else {
				System.out.println("Este producto ya habia sido reparado\n");
			}
		} catch (Exception e) {
			System.out.println("El id del servicio no es correcto\n");
		}
	}
	
	/**
	 * @summary Revisa varias condiciones ante de intentar reparar, adicional, Revisa que el producto 
	 * asociado al servicio haya sido diagnosticado, y lo repara con el metodo reparar de tecnico.
	 * Printea el nombre del cliente, el tecnico que repara, y el costo para la empresa.
	 */
	static void reparar() {
		try {
			System.out.println("Escoja el servicio con su index para reparar el producto asociado: ");

			int index = readInt();

			Servicio servicio = Servicio.getServicios().get(index);

			if (!servicio.isReparado()) {
				if (servicio.getDiagnostico() != null) {
					servicio.getTecnico().reparar(servicio);
					System.out.println("El servicio de " + servicio.getCliente().getNombre() + " fue arreglado por "
								+ servicio.getTecnico() + " y tuvo un costo para la empresa de " + servicio.getCosto());
					
				//Excepciones para revisar que se haya reparado, diagnosticado, y exista el servicio.
				} else
					System.out.println("No se ha diagnosticado el producto del cliente " + servicio.getCliente());

			} else
				System.out.println("Ya se ha reparado el producto!");

		} catch (Exception e) {
			
			System.out.println("El id del servicio no es correcto");
		}
	}
	
	/**
	 * @summary Se le genera un servicio al cliente seleccionado con su producto.
	 * 
	 */
	static void solicitarReparacion() {
		System.out.println("Ingrese el id del cliente para solicitar la reparacion de su producto: ");
		try {
			int index = readInt();
			Cliente cliente = Cliente.getClientes().get(index);
			if (cliente.getRecibos().size() == 0) {
				Producto producto = cliente.getProductos().get(0);
				cliente.solicitarReparacion(producto);

				System.out.println("El cliente fue atendido exitosamente por " + cliente.getDependiente().getNombre()
						+ " y se ha generado el servicio con: " + producto
						+ ".\nYa puede consultar en los servicios para iniciar su diagnostico.");
			} else {
				System.out.println("El cliente " + cliente.getNombre() + " ya habia sido atendido\n");
			}
		} catch (Exception e) {
			System.out.println("El id del cliente no es correcto");
		}
	}
	/**
	 * @summary Genera el recibo para el cliente y le entrega el producto reparado al cliente.
	 * 
	 */
	static void finalizarServicio() {
		try {
			System.out.println("Ingrese el id del servicio a finalizar: ");
			int index = readInt();
			Servicio servicio = Servicio.getServicios().get(index);

			if (servicio.isReparado()) {
				Dependiente dependiente = servicio.getDependiente();
				dependiente.finalizarServicio(servicio);
				System.out.println(servicio.getCliente().getRecibos().get(0));
				System.out.println("El servicio ya esta listo para ser cobrado");
			} else {
				System.out.println("El servicio no ha sido reparado aun y no se puede finalizar");
			}
		} catch (Exception e) {
			System.out.println("El id del servicio es incorrecto");
		}
	}
	
	/**
	 * @summary Se cobra el servicio utilizando el costo calculado por el tecnico, multiplicado por el margen de ganancia,
	 * y una vez el cliente pague, se le agrega el pago a la caja registradora.
	 * 
	 */
	static void cobrarServicio() {
		try {
			System.out.println("Ingrese el id del servicio a cobrar: ");
			int index = readInt();
			Servicio servicio = Servicio.getServicios().get(index);
			Dependiente dependiente = Dependiente.getDependientes().get(0);

			if (!servicio.isPagado()) {

				if (servicio.isReparado()) {
					dependiente.cobrarServicio(servicio);
					System.out.println("Se cobra el servicio por un total de "
							+ servicio.getCosto() * Dependiente.getMargenGanancia());
					System.out.println("En la caja registradora ahora hay "
							+ dependiente.getCajaRegistradora().getTotalIngresos() + " pesos");
				} else
					System.out.println("Aun no se ha reparado el producto, Que esperas?");
			} else {
				System.out.println("Ya se ha cobrado el servicio! Se lamenta la molestia.");

			}
		} catch (Exception e) {
			System.out.println("El id del cliente no es correcto");
		}
	}
	
	/**
	 * @summary Calcula y devuelve lo cobrado por los empleados, y los ingresos de la caja registradora antes y despues de liquidar.
	 */
	static void liquidar() {
		
		Dependiente dependiente = Dependiente.getDependientes().get(0);
		System.out.println("En la caja registradora hay " + Math.round(dependiente.getCajaRegistradora().getTotalIngresos()) + " antes de liquidar.");
		
		for (String liquidacion: dependiente.liquidar()) {
			System.out.println(liquidacion);
		}
		
		System.out.println("En la caja registradora quedan " + Math.round(dependiente.getCajaRegistradora().getTotalIngresos()));
	}
		
	/**
	 * 
	 * @return Cliente
	 * @summary funcion para generar un cliente con los parametros de las listas definidas dentro del metodo, utilizando 
	 * random para escoger aleatoriamente entre ellos.
	 */
	public static Cliente generarCliente() {
		String[] nombres = { "Esteban", "Emilio", "Felipe", "Erik", "Alexander", "Jaime", "Alejandro", "Emiliana",
				"Dua lipa", "Erika", "Michael", "Juliana" };

		
		Componente[] componentes = { new Componente("Memoria 4g Kinsgton", false, PrecioComponente.RAM_4GB.getPrecio()),
				new Componente("Disco duro SSD 256gb", false, PrecioComponente.DISCO_DURO_SSD_256GB.getPrecio()),
				new Componente("Bateria laptop lenovo supercharger", false,
						PrecioComponente.BATERIA_LAPTOP_SUPERCHARGER.getPrecio()),
				new Componente("Procesador AMD", false, PrecioComponente.PROCESADOR_AMD.getPrecio()),
				new Componente("Display 15 pulgadas", false, PrecioComponente.DISPLAY_LAPTOP_15In.getPrecio()),
				new Componente("Memoria 8g Kinsgton", false, PrecioComponente.RAM_8GB.getPrecio()),
				new Componente("Disco duro HDD 512gb", false, PrecioComponente.DISCO_DURO_HDD_512GB.getPrecio()),
				new Componente("Bateria laptop lenovo", false, PrecioComponente.BATERIA_LAPTOP.getPrecio()),
				new Componente("Procesador Intel", false, PrecioComponente.PROCESADOR_INTEL.getPrecio()),
				new Componente("Display 17 pulgadas", false, PrecioComponente.DISPLAY_LAPTOP_17In.getPrecio()), };

		Random rand = new Random();
		Dependiente dependiente = Dependiente.getDependientes().get(0);
		
		Producto producto = generarProductoAleatorio();
		List<Producto> productos = new ArrayList<Producto>();
		productos.add(producto);
		double cartera = Math.round(450000 + 1000000 * Math.random());

		Cliente cliente = new Cliente(nombres[rand.nextInt(nombres.length)], "" + rand.nextInt(9999999), productos,
				dependiente, cartera);
		for (Componente componente : componentes) {
			Bodega.agregarComponente(componente);
		}
		return cliente;
	}

	static void menuDiagnosticar() {
		Cliente cliente;
		int opcion;
		do {
			System.out.println(" 1. Crear cliente manualmente");
			System.out.println(" 2. Generar cliente");
			System.out.println(" 3. Solicitar servicio");
			System.out.println(" 4. Diagnosticar producto");
			System.out.println(" 5. Volver al menu principal");

			opcion = (int) readInt();

			switch (opcion) {
			case 1:
				crearClienteManualmente();
				break;
			case 2:
				cliente = generarCliente();
				System.out
				.println("Se genero el cliente ID:" + (Cliente.getClientes().size() - 1) + cliente.toString());
				break;
			case 3:
				solicitarReparacion();
				break;
			case 4:
				diagnosticar();
				break;
			}
		} while (opcion != 5);
	}

	public static void guardar() {
		Serializador.serializarTodo();
	}

	public static void cargar() {
		Deserializador.deserializarTodo();
	}
	
	public static void crearClienteManualmente() {
		boolean crearCliente = true;
		
		System.out.println("Nuevo cliente\n Ingrese el nombre del cliente: ");
		readString();
		String nombre = ingresarCampo("", crearCliente);
		if (nombre.equals("0")) crearCliente = false;
		System.out.println("Cedula: ");
		String cc = ingresarCampo("", crearCliente);
		if (cc.equals("0")) crearCliente = false;
		System.out.println("Direccion: ");
		String direccion = ingresarCampo("", crearCliente);
		if (direccion.equals("0")) crearCliente = false;
		
		double cartera = 0;
		while( cartera < 400000 && crearCliente) {
			if (cartera > 0) 
				System.out.println("Ingrese un valor mayor a 400000 \n O ingrese 0 para regresar al menu anterior");
			System.out.println("cartera: ");			
			cartera = readDouble();
			if(cartera == 0) {
				crearCliente = false;
				break;
			}
		};
		
		if(crearCliente) {
			Producto producto = generarProductoAleatorio();
			List<Producto> productos = new ArrayList<Producto>();
			productos.add(producto);
			Cliente cliente = new Cliente(nombre, cc, productos, Dependiente.getDependientes().get(0), cartera, direccion);
			System.out
			.println("Se creo el cliente manualmente con ID:" +
			(Cliente.getClientes().size() - 1) + 
			cliente.toString() + "\n");
		}
	}
	
	public static Producto generarProductoAleatorio() {
		Random rand = new Random();
		String[] nombreProductos = { "Laptop Legion 5", "Hp zbook 1", "Hp Omen 15", "Asus TUF Gaming", "HP XPS",
				"Macbook pro", "Lenovo Thinkpad", "Hp pavilion", "Notebook Gigabyte", "MSI Strike" };
		
		//Componentes solo para productos, no para la bodega
		Componente[] componentes = { 
				new Componente("Memoria 4g Kinsgton", true),
				new Componente("Disco duro SSD 256gb", true),
				new Componente("Bateria laptop lenovo supercharger", true),
				new Componente("Procesador AMD", true),
				new Componente("Display 15 pulgadas", true),
				new Componente("Memoria 8g Kinsgton", true),
				new Componente("Disco duro HDD 512gb", true),
				new Componente("Bateria laptop lenovo", true),
				new Componente("Procesador Intel", true),
				new Componente("Display 17 pulgadas", true), 
		};
		
		List<Componente> productoComponentes = new ArrayList<Componente>();
		productoComponentes.add(componentes[rand.nextInt(componentes.length)]);
		productoComponentes.add(componentes[rand.nextInt(componentes.length)]);
		
		return new Producto(nombreProductos[rand.nextInt(nombreProductos.length)], productoComponentes);
	}

	private static String ingresarCampo(String campo, boolean crearCliente) {
		while(campo.equals("") && crearCliente) {
			campo = readString();
			if(campo.equals("")) {
				System.out.println("\nIngrese el campo valido o presione 0 para cancelar.");
			}
			if(campo.equals("0")) {
				break;
			}
		}
		return campo;
	}
	
	//Genera un dependiente y tecnicos si no hay.
	public static void inicializar() {
		if (Dependiente.getDependientes().isEmpty())
			new Dependiente("Camila", 1237465, new CajaRegistradora());

		if (Tecnico.tecnicos.isEmpty()) {
			new Tecnico("Emilio", 987654);
			new Tecnico("Sebastian", 496875);
		}
	}
}

