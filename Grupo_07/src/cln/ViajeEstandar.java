package cln;
/**
 * La clase ViajeEstandar refiere a aquellos viajes cuya zona corresponde a zona estandar.
 */
public class ViajeEstandar extends Viaje{
/**
 * Es el constructor de la clase ViajeEstandar
 * @param pedido Pedido correspondiente al viaje dado
 * @param chofer Chofer asignado a este viaje
 * @param vehiculo Vehiculo asignado a este viaje
 * @param distancia Distancia a recorrer durante el viaje
 */
    public ViajeEstandar(Pedido pedido, Chofer chofer, Vehiculo vehiculo, double distancia){
        super(pedido,chofer,vehiculo,distancia);
    }
    /**
     * Calcula el costo del viaje para una distancia y una cantidad de pasajeros dada, segun las caracterisitcas de un viaje en zona estandar.
     * @return El costo total del viaje
     */
    public double calculaCosto(){
        return Viaje.costoBase + 0.1*this.getPedido().getCantPasajeros()*Viaje.costoBase + 0.1*this.getDistancia()*Viaje.costoBase;
    }
  
	public String getNombreZona() {
		return "Zona Estandar";
	}


}
