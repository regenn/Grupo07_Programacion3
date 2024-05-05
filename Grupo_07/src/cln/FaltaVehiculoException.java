package cln;

/**
 * La clase FaltaVehiculoException representa una excepción que se lanza cuando no hay vehículo disponible para crear un viaje.
 * Extiende la clase CreacionViajeException.
 */
public class FaltaVehiculoException extends CreacionViajeException {

    /**
     * Constructor de la clase FaltaVehiculoException.
     * Crea una nueva instancia de la excepción sin proporcionar un mensaje detallado.
     */
	public FaltaVehiculoException() {
		super();
	}
}