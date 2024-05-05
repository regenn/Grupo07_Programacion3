package cln;

/**
 * La clase ChoferContratado es un tipo específico de chofer, ademas contiene los atributos (ganacia_viaje y acumGanancias)
 * Hereda de la clase Chofer
 */
public class ChoferContratado extends Chofer {
	private static final double ganancia_viaje= 0.75;
	private double acumGanancias;


	public void setAcumGanancias(double acumGanancias) {
		this.acumGanancias = acumGanancias;
	}
	 /**
     * Añade la ganancia de cada viaje al acumulador de ganancia de un Chofer Contratado.
     * @param ganancia La ganancia a añadir.
     */
	public void addGanancia(double ganancia){
		this.acumGanancias+=ganancia;
	}

	 /**
     * Constructor de la clase ChoferContratado.
     * @param dni    DNI
     * @param nombre nombre
     */

	public ChoferContratado(String dni, String nombre) {
		super(dni, nombre, "CONTRATADO");
	}
	
	/**
     * Calcula el sueldo del chofer contratado.
     * El sueldo de un Chofer Contratado se calcula multiplicando la ganancia por viaje por la acumulación de ganancias del chofer.
     * @return El sueldo del chofer contratado.
     */
	public double getSueldo() {
		return ganancia_viaje * this.acumGanancias;
	}

	public String getTipo(){
		return "CONTRATADO";
	}
}