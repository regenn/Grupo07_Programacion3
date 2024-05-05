package cln;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * La clase Pedido representa una solicitud de servicio de transporte.
 * Contiene información sobre la fecha del pedido, la zona, si se lleva mascota, si se utiliza el baúl, la cantidad de pasajeros y los detalles del cliente.
 */
public class Pedido implements Cloneable {
	GregorianCalendar fecha;
	protected String zona;
	protected boolean mascota;
	protected boolean usaBaul;
	protected int cantPasajeros;
	protected GregorianCalendar fechaViaje;
	protected Cliente cliente;
	
   /**
     * Constructor de la clase Pedido.
     * @param anio          anio (para calcular fecha)
     * @param mes           mes (para calcular fecha)
     * @param dia           día (para calcular fecha)
     * @param zona          zona (peligrosa, estandar, calle sin asfaltar)
     * @param mascota       indica si se lleva mascota
     * @param usaBaul       indica si se utiliza el baul
     * @param cantPasajeros cantidad de pasajeros
     * @param cliente       cliente que realiza el pedido
     */
	public Pedido(int anio, int mes, int dia, String zona, boolean mascota, boolean usaBaul, int cantPasajeros, Cliente cliente) {
		this.fecha = new GregorianCalendar(anio, mes-1, dia);
		this.zona = zona;
		this.mascota = mascota;
		this.usaBaul = usaBaul;
		this.cantPasajeros = cantPasajeros;
		this.cliente = cliente;
	}
	
	public GregorianCalendar getFecha(){
		return fecha;
	}
	public String getZona() {
		return zona;
	}

	public String getFechaFormato(){
		Date date= this.fecha.getTime();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		return "Fecha: "+ sdf.format(date);
	}
	
	public boolean isMascota() {
		return mascota;
	}

	public boolean isUsaBaul() {
		return usaBaul;
	}

	public int getCantPasajeros() {
		return cantPasajeros;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
	/**
	 * Metodo para la clonacion de un pedido, realiza una clonacion superficial.
	 * @return un clon de clase Object
	 */
	public Object clone() throws CloneNotSupportedException{
		Pedido clon=null;
		try{
			clon=(Pedido)super.clone();
		}
		catch (CloneNotSupportedException ex){
			throw ex;
		}
		return clon;
	}

	/**
     * Verifica si el pedido es válido.
     * @return true si el pedido es válido, false en caso contrario.
     */
	public boolean esValido() {
		boolean esValido = true;
		if (((this.cantPasajeros < 1) || (this.cantPasajeros > 10) || ((this.cantPasajeros > 4 && this.mascota == true)))&&!(zona.equalsIgnoreCase("ZonaPeligrosa")||(zona.equalsIgnoreCase("ZonaEstandar"))||(zona.equalsIgnoreCase("ZonaSinAsfaltar"))))
			esValido = false;
		return esValido;
	}

	/**
     * Devuelve una descripcion del pedido
     * @return un string que representa el pedido.
     */
	public String toString(){
		String aux=this.getZona();
		if(this.isMascota()){
			aux+=", Tiene mascota";
		}
		if (this.isUsaBaul()){
			aux+=", Usa baul";
		}
		return aux;
	}
}
