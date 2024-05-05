package cln;

/**
 * La clase ViajeSinAsfaltar representa un tipo especific de viaje.
 * Extiende la clase Viaje.
 */
public class ViajeSinAsfaltar extends Viaje{

    /**
     * Constructor de la clase ViajeSinAsfaltar.
     *
     * @param pedido El pedido asociado al viaje.
     * @param chofer El chofer asignado al viaje.
     * @param vehiculo El vehículo asignado al viaje.
     * @param distancia La distancia del viaje.
     */
    public ViajeSinAsfaltar(Pedido pedido, Chofer chofer, Vehiculo vehiculo, double distancia){
        super(pedido,chofer,vehiculo,distancia);
    }

    /**
     * Método para calcular el costo del viaje en una zona sin asfaltar.
     *
     * @return El costo calculado del viaje en una zona sin asfaltar.
     */
    public double calculaCosto(){
        return Viaje.costoBase + .20*this.getPedido().getCantPasajeros()*Viaje.costoBase + .15*distancia+Viaje.costoBase;
    } 

	public String getNombreZona() {
		return "Zona sin asfaltar";
	}
}