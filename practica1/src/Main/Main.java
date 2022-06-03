package Main;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

import gestionCine.*;
import gestionCine.Personal.*;
public class Main {
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		lofi();
		
		String option;
		while(true) {
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Buenas, bienvenido al Cine ");
            System.out.println("Escoja una opcion:");
            System.out.println("1. Surtir Almacen");
            System.out.println("2. Gestion Cuentas");
            System.out.println("3. Gestion Compras");
            System.out.println("0. Salir");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                surtirAlmacen();
            } else if (option.equals("2")) {
               Cuenta();
            } else if(option.equals("3")){
                Compras();
            }else if(option.equals("0")){
                break;
            }
        }
		Cuenta();
		Compras();								
	}
	
	public static void surtirAlmacen() {
		System.out.println("Ingrese el tipo de opcion que va a realizar");
		System.out.println("1. Agregar Pelicula");
		System.out.println("2. Agregar Confiteria");
		System.out.println("3. Inicio");
		int lol= input.nextInt();
		if (lol == 1) {
			System.out.println("Ingresar nombre de la Pelicula:");
			String nombre = input.next();
			System.out.println("Ingresar cantidad de asientos:");
			int cantidad = input.nextInt();
			Taquilla taqui = new Taquilla(nombre,cantidad,PrecioCine.BOLETA.getPrecio(),true);
			Almacen.agregarComponente(taqui);
			main(null);
		}else if(lol == 2) {
			System.out.println("Producto que desea agregar:");
			System.out.println("1. Palomitas");
			System.out.println("2. Perro");
			System.out.println("3. Gaseosa");
			System.out.println("4. Confiteria");
			int confi = input.nextInt();
			if(confi == 1) {
				Taquilla taqui2 = new Taquilla("Palomitas",PrecioCine.PALOMITAS.getPrecio(),false);
				Almacen.agregarComponente(taqui2);
			}else if(confi==2) {
				Taquilla taqui2 = new Taquilla("Perro",PrecioCine.PERRO.getPrecio(),false);
				Almacen.agregarComponente(taqui2);
			}else if(confi==3) {
				Taquilla taqui2 = new Taquilla("Gaseosa",PrecioCine.GASEOSA.getPrecio(),false);
				Almacen.agregarComponente(taqui2);
			}else if(confi==4) {
				Taquilla taqui2 = new Taquilla("Confiteria",PrecioCine.CONFITERIA.getPrecio(),false);
				Almacen.agregarComponente(taqui2);
			}else {
				System.out.println("!!Opcion incorrecta!!");
				System.out.println("Ingrese opcion valida:");
				surtirAlmacen();
			}
			main(null);
		}else if(lol==3) {
			main(null);
		}else {
			System.out.println("!!Opcion incorrecta!!");
			System.out.println("Ingrese opcion valida:");
			surtirAlmacen();
		}
				
	}
	public static void Cuenta() {
		System.out.println("1. Crear una cuenta nueva");
		System.out.println("2. Recargar Saldo de cuenta");
		System.out.println("3. Borrar Cuenta");
		System.out.println("4. Lista Cuentas");
		System.out.println("5. Inicio");
		int cue = input.nextInt();
		if(cue==1) {
			System.out.println("Ingrese numero de cedula");
			int cedula = input.nextInt();
			System.out.println("Ingrese nombre");
			String nombre= input.next();
			System.out.println("Ingrese direccion");
			String direccion= input.next();
			System.out.println("Ingresar saldo de la cuenta");
			Double saldo = input.nextDouble();
			List<ByC> productos = new ArrayList<ByC>();
			Cliente cliente = new Cliente(nombre,cedula,direccion,saldo,productos);
			System.out.println("Cuenta Creada");
			Cuenta();
			
			
		}else if(cue==2) {
			System.out.println("Ingrese numero de cedula");
			int cedula=input.nextInt();
			System.out.println("Ingrese valor que desea recargar");
			Double valor=input.nextDouble();
			int i = 0;
			for (Cliente.getClientes().get(i).getCedula();i<Cliente.getClientes().size();i++) {
				if (Cliente.getClientes().get(i).getCedula()==cedula) {
					Double v1=Cliente.getClientes().get(i).getSaldo();
					Double v2=v1+valor;
					Cliente.getClientes().get(i).setSaldo(v2);
					System.out.println("Recarga realizada exitosamente");
					System.out.println("Su saldo es de:"+Cliente.getClientes().get(i).getSaldo());
				}
			}
			Cuenta();
		}else if(cue==3) {
			System.out.println("Ingrese numero de cedula");
			int cedula=input.nextInt();
			int i=0;
			for (Cliente.getClientes().get(i).getCedula();i<Cliente.getClientes().size();i++) {
				if (Cliente.getClientes().get(i).getCedula()==cedula) {
					Cliente.eliminarCliente(Cliente.getClientes().get(i));
				}
			}
			System.out.println("Cuenta Eliminada");
			Cuenta();
		}else if(cue==4){
			System.out.println(Cliente.getClientes());
		}else if(cue==5) {
			main(null);
		}
		else {
			System.out.println("!!Opcion incorrecta!!");
			System.out.println("Ingrese opcion valida:");
			Cuenta();
		}
	}
	
	
	public static void Compras() {
		System.out.println("1. Comprar Boleteria");
		System.out.println("2. Comprar Confiteria");
		System.out.println("3. Inicio");
		int com= input.nextInt();
		if(com==1) {
			System.out.println("1. Consultar Peliculas Disponibles");
			System.out.println("2. Comprar Boleta");
			System.out.println("3. Volver al inicio");
			int bol=input.nextInt();
			if(bol==1) {
				System.out.println(Almacen.listaPeli());
				Compras();
			}else if(bol==2) {
				System.out.println("Ingrese numero de cedula");
				int cedula=input.nextInt();
				int i=0;
				for (Cliente.getClientes().get(i).getCedula();i<Cliente.getClientes().size();i++) {
					if (Cliente.getClientes().get(i).getCedula()==cedula) {
						Cliente cliente = Cliente.getClientes().get(i);
						System.out.println(cliente);
						System.out.println("Ingresar nombre de la pelicula");
						String nom = input.next();
						List<Taquilla> productos = new ArrayList<Taquilla>();																					
					}
				}
				Compras();
				
			}else if(bol==3) {
				Compras();
			}
		}else if(com==2) {
			System.out.println("1. Consultar Confiteria");
			System.out.println("2. Comprar Confiteria");
			System.out.println("3. Volver al inicio");
			int bole=input.nextInt();
			if(bole==1) {
				System.out.println(Almacen.listaConfi());
				Compras();
			}else if(bole==2) {
				Compras();
			}else if(bole==2) {
				Compras();
			}
			Compras();
		}else if (com==3) {
			main(null);
		}else {
			System.out.println("!!Opcion incorrecta!!");
			System.out.println("Ingrese opcion valida:");
			Compras();
		}
	main(null);
	}
	public static void lofi() {
		Taquilla b = new Taquilla("boleta",14500,true);
		Taquilla x = new Taquilla("Perro",50000,true);
		ByC a= new ByC("Boleta", Taquilla.taquillas);
		ByC a2=a;
		if (Taquillero.getTaquillero().isEmpty())
			new Taquillero("Andres", 7597465, new CajaRegistradora());

		if (Supervisor.supervisores.isEmpty()) {
			new Supervisor("Felipe", 1035689);
			new Supervisor("Karolina", 9856210);
		}
		Taquillero taquillero = Taquillero.getTaquillero().get(0);
		List<ByC> productos = new ArrayList<ByC>();
		productos.add(a);
		productos.add(a2);
		
		Cliente cliente = new Cliente("camilo", 1214731724, "carrera",15500.0,productos,taquillero);
		Almacen.agregarComponente(b);
		Almacen.agregarComponente(x);
	}
		
	
}


