package cln;
import java.util.GregorianCalendar;

/**
 * La clase Viaje es una clase abstracta que implementa la interfaz Cloneable y la interfaz Comparable.
 */
public abstract class Viaje implements Cloneable, Comparable<Viaje>{
	protected static final double costoBase = 1000;
	
	protected GregorianCalendar fecha;
	protected Pedido pedido;
	protected Chofer chofer;
	protected Vehiculo vehiculo;
	protected double distancia;

    /**
     * Constructor de la clase Viaje.
     *
     * @param pedido El pedido asociado al viaje.
     * @param chofer El chofer asignado al viaje.
     * @param vehiculo El vehículo asignado al viaje.
     * @param distancia La distancia del viaje.
     */
	public Viaje(Pedido pedido, Chofer chofer, Vehiculo vehiculo, double distancia) {
		super();
		this.pedido = pedido;
		this.chofer = chofer;
		this.vehiculo = vehiculo;
		this.distancia = distancia;
	}

    /**
     * Constructor por defecto de la clase Viaje.
     */
	public Viaje(){}

   /**
     * Método para clonar un objeto de tipo Viaje.
	 * Realiza una clonacion profunda para el atributo pedido.
     * @return Una copia del objeto Viaje.
     */
	public Object clone() throws CloneNotSupportedException{ 
		Viaje clon=null;
		try{
			clon=(Viaje)super.clone();
			clon.pedido=(Pedido)this.pedido.clone();
		}
		catch (CloneNotSupportedException ex){
			throw ex;
		}
		return clon;
	}

    /**
     * Método para comparar dos objetos de tipo Viaje por su costo.
     *
     * @param otroViaje El otro Viaje con el que se va a comparar.
	 * <b>Pre:</b> Viaje no null
     * @return El resultado de la comparación por el costo del viaje.
     */ 

	public int compareTo(Viaje otroViaje){
		return Double.compare(this.calculaCosto(),otroViaje.calculaCosto());
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Chofer getChofer(){
		return chofer;
	}
	
	public Vehiculo getVehiculo(){
		return vehiculo;
	}

	public GregorianCalendar getFecha(){
		return fecha;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public double getDistancia() {
		return distancia;
	}
	
    /**
     * Método abstracto para calcular el costo del viaje.
     *
     * @return El costo calculado del viaje.
     */

	public abstract double calculaCosto();
}