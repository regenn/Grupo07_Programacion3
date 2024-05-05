package cln;

/**
 * La clase TieneMascota es un decorador para un viaje que indica que se permite la presencia de mascotas.
 * Extiende la clase DecoratorViaje.
 */
public class TieneMascota extends DecoratorViaje{
	Viaje viaje;
	
    /**
     * Constructor de la clase TieneMascota.
     *
     * @param viaje El viaje al que se le añade la característica de permitir mascotas.
     */
	public TieneMascota(Viaje viaje){
		this.viaje = viaje;
        this.pedido = viaje.pedido;
        this.chofer=viaje.chofer;
        this.vehiculo=viaje.vehiculo;
        this.fecha=viaje.fecha;
		this.distancia = viaje.distancia;
		this.pedido.cantPasajeros = viaje.pedido.cantPasajeros;
	}

    /**
     * Método para calcular el costo del viaje con el decorador de mascotas.
     *
     * @return El costo calculado del viaje con la característica de permitir mascotas.
     */
	public double calculaCosto() {
		return 0.10*viaje.pedido.cantPasajeros*Viaje.costoBase + 0.20*viaje.distancia*Viaje.costoBase + viaje.calculaCosto();
	}
}