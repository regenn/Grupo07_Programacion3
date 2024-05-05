package cln;
/**
 * La clase Cliente representa a un cliente que interactua con el sistema. 
 * Como unico atributo tiene un nombre asociado.
 */
public class Cliente {
    private String nombre;

    /** 
     * Constructor de la clase Cliente.
     * @param nombre El nombre del cliente.
     */
    public Cliente(String nombre){
        this.nombre = nombre;
    }
    
     /**
     * Obtiene el nombre del cliente.
     * @return El nombre del cliente.
     */
    public String getNombre(){
        return nombre;
    }
}
