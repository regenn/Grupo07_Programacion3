package cln;

/**
 * La clase FaltaChoferException representa una excepción que se lanza
 * cuando no se puede crear un viaje debido a la falta de un chofer disponible.
 * Esta excepción hereda de la clase CreacionViajeException.
 */
public class FaltaChoferException extends CreacionViajeException{
	
	 /**
     * Constructor vacío de la excepción FaltaChoferException.
     * Se utiliza para crear una nueva instancia de FaltaChoferException.
     */
	public FaltaChoferException() {
		super();
	}
}