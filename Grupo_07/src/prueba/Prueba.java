package prueba;

import cln.VehiculoFactory;
import cln.ChoferTemporario;
import cln.ChoferPermanente;
import cln.ChoferContratado;
import cln.Chofer;
import cln.Pedido;
import cln.CreacionViajeException;
import cln.FaltaChoferException;
import cln.FaltaVehiculoException;
import cln.SolicitudIncoherenteException;
import cln.Sistema;
import cln.Vehiculo;
import cln.ViajeFactory;
import cln.Cliente;
import cln.Viaje;
import java.util.ArrayList;
import java.util.Map;

public class Prueba {
	public static void main (String[] args) {

		// Singleton para sistema
		Sistema sistema = Sistema.getInstance();
		
		// Factories
		VehiculoFactory factoryVehiculos = new VehiculoFactory();
		ViajeFactory factoryViajes = new ViajeFactory();

   		// Clientes
		Cliente cliente1, cliente2, cliente3;
		Vehiculo vehiculo1, vehiculo2, vehiculo3;
		Pedido pedido1, pedido2, pedido3, pedido4, pedido5;
		Viaje viaje1, viaje2, viaje3, viaje4;
		ChoferTemporario choferTemporario;
		ChoferPermanente choferPermanente;
		ChoferContratado choferContratado;
		
		// prueba 1: chofer no encontrado
		cliente1 = sistema.agregarCliente("Cliente");
		vehiculo1 = sistema.agregarVehiculo("Moto");

		try{
			pedido1 = sistema.agregarPedido(2024, 7, 7, "ZonaPeligrosa", false, false, 1, cliente1);
			viaje1 = sistema.crearViaje(pedido1, 100);
		}
		catch (FaltaChoferException e) {
			System.out.println("No hay ningun chofer disponible para tomar el pedido");
		}
		catch(CreacionViajeException e) {
			System.out.println("Hubo un error en la creacion del viaje");
		}
		catch (SolicitudIncoherenteException e) {
			System.out.println("La solicitud de pedido es incoherente");
		}

		// prueba 2: vehiculo no encontrado
		choferTemporario = sistema.agregarChoferTemporario("43797177","Chofer Temporario",0.2);
		try{
			pedido2 = sistema.agregarPedido(2024, 4, 5, "ZonaEstandar", true, true, 4, cliente1);
			viaje2 = sistema.crearViaje(pedido2, 300);
		} 
		catch (FaltaVehiculoException e){
			System.out.println("Ningun vehiculo disponible para tomar este pedido");
		}
		catch (CreacionViajeException e) {
			System.out.println("Hubo un error en la creacion del viaje");
		}
		catch (SolicitudIncoherenteException e) {
			System.out.println("La solicitud de pedido es incoherente");
		}

		// prueba 3: solicitud incoherente
		try{
			pedido3 = sistema.agregarPedido(2024, 4, 5, "ZonaEstandar", true, true, 17, cliente1);
		} catch (SolicitudIncoherenteException e){
			System.out.println("La solicitud es incoherente");
		}	

		// prueba 4: casos validos
		choferPermanente = sistema.agregarChoferPermanente("XXXXXXXX", "Chofer Permanente", 0.3, 2018, 6, 5);
		choferContratado = sistema.agregarChoferContratado("XXXXXXXX", "Chofer Contratado");
		vehiculo2 = sistema.agregarVehiculo("Automovil");
		try{
			pedido3 = sistema.agregarPedido(2024, 5, 4, "ZonaPeligrosa", true, true, 4, cliente1);	
			viaje2 = sistema.crearViaje(pedido3, 100.3);
			pedido4 = sistema.agregarPedido(2024, 6, 7, "ZonaEstandar", true, false, 4, cliente1);
			viaje3 = sistema.crearViaje(pedido4, 200.0);
			pedido5 = sistema.agregarPedido(2024,5,4, "ZonaEstandar", false, false, 1, cliente1);
			viaje4 = sistema.crearViaje(pedido5, 200.0);
		} catch (Exception e){
			System.out.println("Hubo un problema en la creacion del viaje");
		}

		cliente3 = sistema.agregarCliente("Cliente 2");
		vehiculo3 = sistema.agregarVehiculo("Automovil");
		try{
			pedido1 = sistema.agregarPedido(2024, 5, 4, "ZonaPeligrosa", true, true, 3, cliente1);
			viaje1 = sistema.crearViaje(pedido1, 100);
		} catch (Exception e){
			System.out.println(e);
		}
		System.out.println();

		System.out.println("=->LISTADOS<-=");
		System.out.println();

		System.out.println("Listado de Clientes:");
		ArrayList<Cliente> clientes = sistema.listadoClientes();
		for (Cliente clientesaux: clientes)
			System.out.println(clientesaux.getNombre());
		System.out.println();
	
		System.out.println("Listado de Choferes:");
		ArrayList<Chofer> choferes = sistema.listadoChoferes();
		for (Chofer choferaux: choferes)
			System.out.println(choferaux.getNombre());	
		System.out.println();
		
		ArrayList<Vehiculo> vehiculos = sistema.listadoVehiculos();
		System.out.println("Listado de Vehiculos:");
		for (Vehiculo vehiculoaux: vehiculos)
			System.out.println(vehiculoaux.getTipo());
		System.out.println();

		System.out.println("Listado de viajes:");
		try{
		ArrayList<Viaje> viajes = sistema.listadoViajes();
		for (Viaje viajeaux: viajes)
			System.out.println(viajeaux.getChofer().getNombre() +" "+ viajeaux.getVehiculo().getTipo() +" "+ viajeaux.getDistancia()+"km"+" $"+viajeaux.calculaCosto());
		System.out.println();
		}
		catch(CloneNotSupportedException ex){
			System.out.println("El objeto no es clonable");
		}
	
		System.out.println("=->REPORTES<-=");
		System.out.println();

		System.out.println("Reporte 1: detalles de cada cliente");
		ArrayList<Cliente> clientes1= sistema.reporteClientes();
		for (Cliente clienteaux: clientes1)
		    System.out.println(clienteaux.getNombre());
		System.out.println();

		System.out.println("Reporte 2: salarios de cada chofer");
		Map<String, Double> salarios = sistema.reporteSalarios();
		for (String nombre : salarios.keySet()) {
			Double sueldo = salarios.get(nombre);
			System.out.println("Nombre: " + nombre + ", Sueldo: " + sueldo);
		}
		System.out.println();

		// reporte 3: detalle de los viajes realizados por un chofer en particular en un periodo de dias
		System.out.println("Reporte 3: detalles de los viajes realizados por un chofer en un periodo de dias");
		ArrayList<Viaje> viajesChofer = sistema.reporteViajesChofer(choferTemporario,  2024, 5, 3, 2024, 6, 8);
		System.out.println("Reporte de los viajes realizados por el chofer "+ choferTemporario.getNombre());
		for (Viaje viajeAct:viajesChofer){
			System.out.println(viajeAct.getPedido().getFechaFormato()+", Vehiculo: "+viajeAct.getVehiculo().getTipo()+", Detalles del pedido: "+viajeAct.getPedido().toString()+".");
		}
		System.out.println();
		// reporte 4:  detalle de los viajes realizados por un cliente en particular en un periodo de dias
		System.out.println("Reporte 4: detalles de los viajes realizados por un cliente en un periodo de dias");
		ArrayList<Viaje> viajesCliente = sistema.reporteViajesCliente(cliente1, 2024, 5, 3, 2024, 6, 8);
		System.out.println("Reporte de los viajes realizados por el cliente "+ cliente1.getNombre());
		for (Viaje viajeAct:viajesCliente){
			System.out.println(viajeAct.getPedido().getFechaFormato()+", Vehiculo: "+viajeAct.getVehiculo().getTipo()+", Detalles del pedido: "+viajeAct.getPedido().toString()+".");
		}
	}
}