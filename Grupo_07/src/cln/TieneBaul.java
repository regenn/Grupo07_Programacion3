package cln;

/**
 * La clase TieneBaul es un decorador para un viaje que indica que el vehículo tiene baúl.
 * Extiende la clase DecoratorViaje.
 */
public class TieneBaul extends DecoratorViaje{
	Viaje viaje;

    /**
     * Constructor de la clase TieneBaul.
     *
     * @param viaje El viaje al que se le añade la característica de tener baúl.
     */
	public TieneBaul(Viaje viaje){
		this.viaje = viaje;
        this.pedido = viaje.pedido;
        this.chofer=viaje.chofer;
        this.vehiculo=viaje.vehiculo;
        this.fecha=viaje.fecha;
		this.distancia = viaje.distancia;
		this.pedido.cantPasajeros = viaje.pedido.cantPasajeros;
	}	

    /**
     * Método para calcular el costo del viaje con la característica de tener baúl.
     *
     * @return El costo calculado del viaje con la característica de tener baúl.
     */
	public double calculaCosto() {
		return 0.10*viaje.pedido.cantPasajeros*Viaje.costoBase + 0.05*viaje.distancia*Viaje.costoBase + viaje.calculaCosto();
    }
}