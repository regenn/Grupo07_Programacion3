package cln;
/**
 * la clase ViajePeligroso refiere a aquellos viajes cuya zona corresponde a zona peligrosa. 
 */
public class ViajePeligroso extends Viaje {

/**
 * Es el constructor de la clase ViajePeligroso
 * @param pedido El pedido para el cual se crea el viaje
 * @param chofer El chofer asignado a este viaje
 * @param vehiculo El vehiculo asignado a este viaje
 * @param distancia Distancia a recorrer durante el viaje
 */
    public ViajePeligroso(Pedido pedido, Chofer chofer, Vehiculo vehiculo, double distancia){
        super(pedido,chofer,vehiculo,distancia);
    }
	/**Calcula el costo del viaje para una distancia y una cantidad de pasajeros dada, en una zona peligrosa.
     * @return el costo total del viaje.
     */
	public double calculaCosto() {
		return 0.10*this.getPedido().getCantPasajeros()*Viaje.costoBase + 0.20*this.distancia*Viaje.costoBase + Viaje.costoBase;
	}
	
	public String getNombreZona() {
		return "Zona peligrosa";
	}
}
