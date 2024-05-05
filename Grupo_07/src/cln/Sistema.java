package cln;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
/**
 * La clase Sistema es el sistema de gestión de una empresa de transporte.
 * Contiene las listas de choferes, vehículos, pedidos, viajes y clientes, así como métodos para realizar diversas operaciones
 * relacionadas con la gestión de estos elementos.
 */
public class Sistema {
	private ArrayList <Chofer> Choferes;
	private ArrayList <Vehiculo> Vehiculos;
	private ArrayList <Pedido> Pedidos;
	private ArrayList <Viaje> Viajes;
	private ArrayList <Cliente> Clientes;


	public ArrayList<Chofer> getChoferes() {
		return Choferes;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return Vehiculos;
	}

	public ArrayList<Pedido> getPedidos() {
		return Pedidos;
	}

	public ArrayList<Viaje> getViajes() {
		return Viajes;
	}

	public ArrayList<Cliente> getClientes() {
		return Clientes;
	}

	private static Sistema uniqueInstance = null;

	private Sistema() {
		Choferes = new ArrayList<Chofer>();
		Vehiculos = new ArrayList<Vehiculo>();
		Pedidos = new ArrayList<Pedido>();
		Viajes = new ArrayList<Viaje>();
		Clientes = new ArrayList<Cliente>();
	}

	 /**
     * Implementra el Patron Singleton: Obtiene la instancia única del sistema
     * @return La instancia única del sistema.
     */
	public static Sistema getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Sistema();
		}
		return uniqueInstance;
	}

	 /**
     * Crea un nuevo cliente y lo agrega a la lista de clientes
     * @param nombre nombre del cliente
     * @return cliente
     */
	public Cliente agregarCliente(String nombre){
		Cliente cliente = new Cliente(nombre);
		Clientes.add(cliente);
		return cliente;
	}

	 /**
     * Crea un nuevo vehiculo, y lo agrega a la lista de vhiculos
     * @param tipo tipo de vehiculo
     * @return vehiculo
     */
	public Vehiculo agregarVehiculo(String tipo) {
		VehiculoFactory factoryvehiculo = new VehiculoFactory();
		Vehiculo vehiculo = factoryvehiculo.getVehiculo(tipo);
		Vehiculos.add(vehiculo);
		return vehiculo;
	}
	
	/**
     * Crea un nuevo chofer temporario, y lo agrega a la lista de choferes
     * @param dni     DNI
     * @param nombre  nombre
     * @param aportes aportes
     * @return chofer temporario
     */
	public ChoferTemporario agregarChoferTemporario(String dni, String nombre,double aportes){
		ChoferTemporario chofer = new ChoferTemporario(dni,nombre, aportes);
		Choferes.add(chofer);
		return chofer;
	}

	/**
     * Crea un nuevo chofer temporario, y lo agrega a la lista de choferes
     * @param dni    DNI
     * @param nombre nombre
     * @return chofer contratado
     */
	public ChoferContratado agregarChoferContratado(String dni, String nombre){
		ChoferContratado chofer = new ChoferContratado(dni,nombre);
		Choferes.add(chofer);
		return chofer;
	}

	/**
     * Crea un nuevo chofer permanente, y lo agrega a la lista de choferes
    * @param dni     DNI
     * @param nombre  nombre
     * @param aportes aportes
     * @param anio    año de ingreso (se usa para calcular la fecha de ingreso)
     * @param mes     mes de ingreso (se usa para calcular la fecha de ingreso)
     * @param dia     día de ingreso (se usa para calcular la fecha de ingreso)
     * @return El chofer permanente agregado.
     */
	public ChoferPermanente agregarChoferPermanente(String dni, String nombre,double aportes,int anio,int mes,int dia){
		ChoferPermanente chofer = new ChoferPermanente(dni,nombre,aportes,anio,mes,dia);
		Choferes.add(chofer);
		return chofer;
	}

	 /**
     * Crea un nuevo pedido, y lo agrega a la lista de pedidos
     * @param anio          año
     * @param mes           mes
     * @param dia           día
     * @param zona          zona
     * @param mascota       indica si se lleva mascota
     * @param usaBaul       indica si se utiliza el baúl
     * @param cantPasajeros cantidad de pasajeros
     * @param cliente       cliente
     * @return pedido 
     * @throws SolicitudIncoherenteException Si el pedido no es coherente según las reglas de la compania de transporte
     */
	public Pedido agregarPedido (int anio, int mes, int dia, String zona, boolean mascota, boolean usaBaul, int cantPasajeros, Cliente cliente) throws SolicitudIncoherenteException {
		assert cantPasajeros > 0;
		Pedido pedido = new Pedido(anio, mes, dia, zona, mascota, usaBaul, cantPasajeros, cliente);
		if (pedido.esValido())	{
			Pedidos.add(pedido);
			return pedido;
		}
		else
			throw new SolicitudIncoherenteException();
	}

	/**
     * Busca un chofer disponible para un pedido específico
     * @return chofer disponible.
     * @throws FaltaChoferException si no hay choferes disponibles.
     */
	public Chofer buscaChofer() throws FaltaChoferException{
		Chofer chofer;
		if (this.Choferes == null || this.Choferes.size()==0 )
			throw new FaltaChoferException();
		else{
			chofer = this.Choferes.get(0);
			this.Choferes.remove(0);
			this.Choferes.add(chofer);
		}
		return chofer;
	}

	 /**
     * Busca un vehículo disponible para un pedido específico
	 * <b>Pre:</b> Pedido valido
     * @param pedido El pedido para el que se busca un vehículo (para poder saber si hay algun vehiculo apropiado)
     * @return El vehículo disponible
     * @throws FaltaVehiculoException si no hay vehículos disponibles para el pedido
     */
	public Vehiculo buscaVehiculo(Pedido pedido) throws FaltaVehiculoException{
		Vehiculo vehiculo = null;
		Integer max=0, aux=0;
		int i, imax = 0;
		for (i=0; i<this.Vehiculos.size(); i++){
			aux = this.Vehiculos.get(i).getPrioridad(pedido);
			if (aux!=null && aux.compareTo(max)>0){
				max = aux;
				vehiculo = this.Vehiculos.get(i);
				imax = i;
			}
		}
		if (vehiculo == null)
			throw new FaltaVehiculoException();
		else {
			this.Vehiculos.remove(imax);
			this.Vehiculos.add(vehiculo);
			return vehiculo;
		}
	}

	/**
     * Crea un nuevo viaje con un pedido específico y una distancia dada
     * @param pedido    El pedido para el viaje.
	 * <b>Pre:</b> Pedido valido
     * @param distancia La distancia del viaje.
     * @return El viaje creado.
     * @throws CreacionViajeException Si no se puede crear el viaje porque la solicitud es incoherente, porque no existen vehiculos disponibles o porque no se puede asignar un chofer
     */
	public Viaje crearViaje(Pedido pedido, double distancia) throws CreacionViajeException{
		Viaje viaje=null;
		try{
			Chofer chofer = buscaChofer();
			Vehiculo vehiculo = buscaVehiculo(pedido);
			ViajeFactory factoryviaje = new ViajeFactory();
			viaje = factoryviaje.getViaje(pedido, chofer, vehiculo, distancia,pedido.getZona());
			this.Viajes.add(viaje);
			chofer.addViaje();
			if(chofer.getTipo() == "CONTRATADO"){
				ChoferContratado contratado=(ChoferContratado)chofer;
				contratado.addGanancia(viaje.calculaCosto());
			}
		}
		catch(FaltaChoferException e){
			throw e;
		}
		catch (FaltaVehiculoException e){
			throw e;
		}
		return viaje;
	}

	 /**
     * Calcula el puntaje mensual para cada chofer en función de los viajes realizados en un mes específico.
     * @param mes El mes para el cual se calcula el puntaje.
     */
	public void PuntajeMensualChoferes(int mes){
		Viaje viajeAct;
		Chofer maxChofer=null, choferAct;
		double maxDist=0,auxDist;
		for(int i=0;i<Choferes.size();i++){
			choferAct=Choferes.get(i);
			auxDist=0;
			for(int j=0;j<Viajes.size();j++){
				viajeAct=Viajes.get(j);
				if((viajeAct.getChofer()==choferAct)&&(viajeAct.getFecha().get(GregorianCalendar.MONTH)==mes)){
					//el mes y el chofer coinciden
					choferAct.setPuntaje(choferAct.getPuntaje()+5);
					auxDist+=viajeAct.distancia;
				}
			}
			if (auxDist>maxDist){
				maxDist=auxDist;
				maxChofer=choferAct;
			}
		}
		maxChofer.setPuntaje(maxChofer.getPuntaje()+15);
	}

	public ArrayList<Cliente> listadoClientes(){
		return this.Clientes;
	}

	public ArrayList<Chofer> listadoChoferes() {
		return this.Choferes;
	}

	public ArrayList<Vehiculo> listadoVehiculos() {
		return this.Vehiculos;
	}

	public ArrayList<Viaje> listadoViajes() throws CloneNotSupportedException {
		try{
		return (ArrayList<Viaje>)clonaListaViajes(Viajes);
		}
		catch(CloneNotSupportedException ex){
			throw ex;
		}
	}


	/**
 * Clona una lista de viajes y luego la ordena
 * 
 * @param viajes La lista de viajes a clonar.
 * @return Una nueva lista de viajes clonada y ordenada
 */
	public Object clonaListaViajes(ArrayList <Viaje> viajes) throws CloneNotSupportedException {
        ArrayList <Viaje> clonada=(ArrayList<Viaje>)viajes.clone();
		clonada.clear();
        for (int i=0;i<viajes.size();i++){
			try{
			clonada.add((Viaje)viajes.get(i).clone());
			}
			catch (CloneNotSupportedException ex){
				throw ex;
			}
		}
		Collections.sort(clonada);
        return clonada;
	}
	 /**
     * Genera un reporte de cada cliente
     * @return La lista de clientes
     */
	public ArrayList <Cliente> reporteClientes(){
		return Clientes;
	}

	/**
     * Genera un reporte de los salarios pagados por la empresa a cada chofer.
     * @return Un mapa que relaciona el nombre de cada chofer con su salario.
     */
	public Map<String, Double> reporteSalarios(){
		Map<String, Double> reporte= new HashMap<>();
		for (Chofer chofer: Choferes)
            reporte.put(chofer.getNombre(), chofer.getSueldo());
		return reporte;
	}

	/**
     * Genera un reporte detallado de los viajes realizados por un chofer en particular en un período de días.
     * @param chofer El chofer para el cual se genera el reporte.
     * @return La lista de viajes realizados por el chofer.
     */
	public ArrayList<Viaje> reporteViajesChofer(Chofer chofer, int anio0, int mes0, int dia0, int anio1, int mes1, int dia1){
		ArrayList<Viaje> viajesChofer = new ArrayList<Viaje>();
		GregorianCalendar fecha0 = new GregorianCalendar(anio0, mes0-1, dia0);
		GregorianCalendar fecha1 = new GregorianCalendar(anio1, mes1-1, dia1);
		for (Viaje viaje : Viajes){
			if (viaje.getChofer() == chofer){
				if ((viaje.pedido.getFecha().compareTo(fecha0)>0) && (viaje.pedido.getFecha().compareTo(fecha1)<0))
					viajesChofer.add(viaje);
			}
		}
		return viajesChofer;
	} 

	/**
     * Genera un reporte de los viajes realizados por un cliente en particular en un período de días.
     * @param cliente El cliente para el cual se genera el reporte.
     * @return La lista de viajes realizados por el cliente.
     */
	public ArrayList<Viaje> reporteViajesCliente(Cliente cliente, int anio0, int mes0, int dia0, int anio1, int mes1, int dia1){
		ArrayList<Viaje> viajesCliente = new ArrayList<Viaje>();
		GregorianCalendar fecha0 = new GregorianCalendar(anio0, mes0-1, dia0);
		GregorianCalendar fecha1 = new GregorianCalendar(anio1, mes1-1, dia1);
		for (Viaje viaje : Viajes){
			if (viaje.getPedido().getCliente() == cliente){
				if ((viaje.pedido.getFecha().compareTo(fecha0)>=0) && (viaje.pedido.getFecha().compareTo(fecha1)<=0))
					viajesCliente.add(viaje);
			}
		}
		return viajesCliente;
	}
}




/*
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⢹⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⢻⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠃⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠘⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⡟⠁⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠀⣸⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⡏⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⠀⠀⢀⡏⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⠏⡇⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⢸⠁⢹⣿⣿⣿⣧⠀⠀⠀⠀⣠⣾⣿⣿⠛⢸⡇⠀⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀⠀⣿⣾⣿⣿⣿⣿⣿⣦⣤⣤⣴⣿⣿⣿⣷⣇⢸⡇⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢏⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⣰⣿⡟⠋⠂⣬⡙⣿⣿⣿⣿⣿⣿⣿⠿⣏⡩⠙⠻⣿⣷⣄⠀⠀⢸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⡏⠀⠀⢸⣿⡇⢸⣿⣿⣿⣿⣿⢃⢸⣿⡇⠀⠀⢹⣿⣿⣷⣀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⡇⠀⠀⠀⠉⠁⣼⣿⣿⣿⣿⣿⠰⠸⠿⠁⠀⠀⢸⣿⣿⣿⣏⠀⠀⢀⡀⠤⠀⠀⠀⠀⣠⣴⣾⣿⣿⣿⣿⣿⣷⣆⠀⠀
⠀⠀⠀⠀⠀⠀⠐⠲⠤⢀⡀⠀⠀⣿⣿⣿⣿⣮⣤⣠⣴⣾⣿⣿⣿⣿⣿⣿⣧⣳⠤⢀⣀⣴⣿⣿⣿⣿⣿⠔⠊⠁⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠒⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣹⣿⣿⣿⣿⣿⡵⢼⢋⡙⠿⠿⠛⠿⢛⣿⠶⠟⢸⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠤⢤⣾⣿⣿⣿⣿⣿⡿⠋⠁⠀⢹⣿⣿⣿⣿⣇
⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⢀⣻⣿⣿⣿⣿⣾⣧⡄⢀⣀⣲⣒⣒⣲⡯⠿⢿⣾⣿⣿⣿⣿⣿⡿⣁⣀⡀⠀⢠⣿⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠸⣿⣿⣿⣿⠟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠴⠊⠁⠀⠈⠻⣿⣿⣿⣿⣿⣟⡤⠡⣤⣤⣀⣤⣵⣿⣿⣿⣿⣿⠿⠋⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠉⠛⠛⠃⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⡉⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⣼⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⢸⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⣀⠤⠄⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠤⣀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠲⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠈⠉⠉⠁⠒⠒⠢⠤⢄⣀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⢀⡠⠴⣒⣠⠤⠔⠒⠂⠉⠉⠉⠁⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠒⠤⣀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡪⠕⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠓⠒⠛⠛⠿⣿⣿⣿⠟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡈⠷⠤⠽⠿⠿⠿⢿⣿⡟⠀⠛⠛⠛⡩⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠑⠦⠐⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*/