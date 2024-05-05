package cln;

/**
 * La clase Moto representa un tipo de vehículo que extiende la clase Vehiculo 
 * e implementa métodos específicos para definir sus características y calcular puntos.
 */
public class Moto extends Vehiculo{
	
    /**
     * Constructor de la clase Moto.
     * Establece las características específicas de una moto.
     */
	public Moto(){
        this.tipo="Moto";
		this.cantPasajeros = 1;
		this.petFriendly = false;
		this.tieneBaul = false;
	}	
	
    /**
     * Método para obtener los puntos asociados a una entrega con una moto.
     *
     * @param pedido El pedido asociado a la entrega.
	 * <b>Pre:</b> Pedido valido
     * @return Los puntos asignados para la entrega con una moto.
     */
	public Integer getPuntos(Pedido pedido) {
		return 1000;
	}
    public String getTipo(){
        return this.tipo;
    }
}