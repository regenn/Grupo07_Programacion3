package cln;

/**
 * La clase Empleado es una clase abstracta que representa a un empleado en general.
 * Extiende la clase Chofer, contiene los atributos sueldo_basico y aportes, y define métodos abstractos para obtener el sueldo.
 */
public abstract class Empleado extends Chofer{
	protected static double sueldo_basico=10000;
	protected double aportes;
	
    /**
     * Constructor de la clase Empleado.
     *
     * @param dni DNI del empleado.
     * @param nombre Nombre del empleado.
     * @param aportes Aportes del empleado.
     * @param tipo Tipo de empleado.
     */
	public Empleado(String dni, String nombre, double aportes, String tipo) {
		super(dni, nombre, tipo);
		this.aportes = aportes;
	}

    /**
     * Método abstracto para obtener el sueldo del empleado.
     *
     * @return El sueldo calculado del empleado.
     */
	public abstract double getSueldo();
}