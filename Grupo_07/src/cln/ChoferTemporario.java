package cln;

/**
 * La clase ChoferTemporario representa a un tipo esppecifico de la clase empleado. Ademas contiene el atributo plusPorCantidadViajes.
 * Implementa métodos específicos para calcular el sueldo y obtener el tipo de empleado.
 */
public class ChoferTemporario extends Empleado {
	
	protected final double plusPorCantidadViajes = 0.02;
	
    /**
     * Constructor de la clase ChoferTemporario.
     *
     * @param dni DNI del empleado.
     * @param nombre Nombre del empleado.
     * @param aportes Aportes del empleado.
     */
	public ChoferTemporario(String dni, String nombre,double aportes) {
		super(dni, nombre, aportes, "TEMPORARIO");
	}

    /**
     * Método para calcular el sueldo del empleado agregando un plus por cantidad de viajes y restando los aportes
     *
     * @return El sueldo calculado del empleado.
     */
	public double getSueldo() {
		return sueldo_basico + sueldo_basico*this.plusPorCantidadViajes - this.aportes;
	}
	public String getTipo(){
		return "TEMPORARIO";
	}
}