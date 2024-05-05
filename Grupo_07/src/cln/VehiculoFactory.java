package cln;

/**
 * La clase VehiculoFactory es una fábrica que produce instancias de vehículos según el tipo especificado.
 */
public class VehiculoFactory {
	
    /**
     * Constructor de la clase VehiculoFactory.
     */
	public VehiculoFactory() {}
	
    /**
     * Método para obtener una instancia de vehículo según el tipo especificado.
     *<b>Pre:</b> Tipo valido
     * @param tipo El tipo de vehículo deseado ("Moto", "Automovil" o "Combi").
     * @return Una instancia de vehículo del tipo especificado, o null si el tipo no es válido.
     */
	public Vehiculo getVehiculo (String tipo) {
		Vehiculo vehiculo = null;
		if (tipo.equalsIgnoreCase("Moto")){
			vehiculo = new Moto();
		} else if (tipo.equalsIgnoreCase("Automovil")) {
			vehiculo = new Automovil();
		} else if (tipo.equalsIgnoreCase("Combi")) {
			vehiculo = new Combi();
		}
		return vehiculo;	
	}
}