package cln;
/**
 * La clase abstracta Chofer representa un conductor genérico que puede realizar viajes.
 * Contiene atributos comunes a los conductores (DNI, nombre, cantidad de viajes tomados, tipo y puntaje)
 */
public abstract class Chofer {
	protected String dni;
	protected String nombre;
	protected int cantViajesTomados;
	protected String tipo;
	protected int puntaje;

	public String getDNI() {
		return dni;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPuntaje(){
		return puntaje;
	}
	public void setPuntaje(int puntaje){
		this.puntaje=puntaje;
	}
	/**
	 * Constructor de la clase Chofer.
     * @param dni    DNI del chofer
     * @param nombre nombre del chofer
     * @param tipo   tipo de chofer (temporario, permanente, contratado)
     */
	public Chofer(String dni, String nombre, String tipo) {
		super();
		this.dni = dni;
		this.cantViajesTomados=0;
		this.nombre = nombre;
		this.tipo=tipo;
	}

	/**
     * Método abstracto para obtener el sueldo del chofer.
     * @return sueldo del chofer.
     */
	public abstract double getSueldo();
	
	/**
     * Método void que aumenta el atributo de cantViajesTomados
     * Cada vez que se toma un viaje, se llama a este metodo para aumentar el atributo CantViajesTomados de cada Chofer
	 */
    public void addViaje(){
		this.cantViajesTomados++;
	}

	protected abstract String getTipo();
}
