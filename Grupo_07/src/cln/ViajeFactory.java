package cln;
/**
 * la clase ViajeFactory corresponde a una aplicacion del patron Factory, es una clase que se encarga de instanciar viajes segun la zona dada.
 * Esta clase tambien va a aplicar el patron Decorator, ya que el viaje creado debe ser decorado.
 */
public class ViajeFactory {
    Viaje viaje = null;
    /**
     * Metodo encargado de instanciar un viaje segun el parametro zona recibido.
     * Solo se instancia si la zona recibida es: "ZonaPeligrosa","ZonaEstandar" o "ZonaCalleSinAsfaltar". Este dato es verificado al aceptar el pedido
     * @param pedido Pedido para el cual se crea un viaje.
     * @param chofer Chofer asignado para este viaje.
     * @param vehiculo Vehiculo asignado para este viaje.
     * @param distancia Distancia a recorrer en este viaje.
     * @param zona Zona para la cual sera creado el viaje.
     * @return un objeto viaje instanciado y decorado
     */
    public Viaje getViaje(Pedido pedido, Chofer chofer, Vehiculo vehiculo, double distancia, String zona){
        if (zona.equalsIgnoreCase("ZonaPeligrosa"))
            viaje = new ViajePeligroso(pedido, chofer, vehiculo, distancia);
        else if (zona.equalsIgnoreCase("ZonaEstandar"))
           viaje = new ViajeEstandar(pedido, chofer, vehiculo, distancia);
        else if (zona.equalsIgnoreCase("ZonaCalleSinAsfaltar"))
               viaje = new ViajeSinAsfaltar(pedido, chofer, vehiculo, distancia);
        if (pedido.isMascota()){
            viaje = new TieneMascota(viaje);
        }
        if (pedido.isUsaBaul()){
            viaje = new TieneBaul(viaje);
        }
        return viaje;
        }
    }

