package cln;

import java.util.GregorianCalendar;

/**
 * La clase ChoferPermanente representa a un tipo especifico de empleado. Ademas contiene los atributos (plusPorAntiguedad y plusPorHijos)
 * Contiene un objeto fechaIngreso de tipo GregorianCalendar.
 * Implementa métodos específicos para calcular el sueldo y obtener el tipo de empleado.
 */
public class ChoferPermanente extends Empleado{
	
	private static final double plusPorAntiguedad = 0.2; //asumo que estos son los porcentajes del plus por antiguedad y por hijos.
	private static final double plusPorHijos = 0.2;
	private GregorianCalendar fechaIngreso;
	
    /**
     * Constructor de la clase ChoferPermanente.
     *
     * @param dni DNI del empleado.
     * @param nombre Nombre del empleado.
     * @param aportes Aportes del empleado.
     * @param anio Año de ingreso del empleado.
     * @param mes Mes de ingreso del empleado.
     * @param dia Día de ingreso del empleado.
     */
	public ChoferPermanente(String dni, String nombre, double aportes,int anio, int mes, int dia) {
		super(dni, nombre, aportes, "PERMANENTE");
		this.fechaIngreso=new GregorianCalendar(anio,mes-1,dia);
	}
	
    /**
     * Método para calcular el sueldo del empleado en base a un valor basico y un plus por antiguedad
     * @return El sueldo calculado del empleado.
     */
	public double getSueldo(){
		GregorianCalendar hoy = new GregorianCalendar();
		long x;
		long milisinyear;
		x= (hoy.getTimeInMillis() - this.fechaIngreso.getTimeInMillis());
		milisinyear= 1000l * 3600l * 24l * 365l;
	 	return sueldo_basico+(sueldo_basico*(plusPorAntiguedad*(x/milisinyear)))+(sueldo_basico*plusPorHijos) - sueldo_basico*this.aportes;
	}

	public String getTipo(){
		return "PERMANENTE";
	}
	
}