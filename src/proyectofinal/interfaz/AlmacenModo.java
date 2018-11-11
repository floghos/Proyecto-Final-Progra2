package proyectofinal.interfaz;
/**
 * Clase que almacena número de la accion a realizar: añadir obstaculos(1) - quitar obstaculos(2).
 */
public class AlmacenModo {
    private int m;	
    /**
     * Método constructor.
     */
    public AlmacenModo(){
        m=0;
    }
    /**
    * Recibe y almacena el número entregado por el botón.
    * @param modo 
    */
    public void setModo(int modo){
        m=modo;
    }
    /**
     * Entrega el numero del tipo de acción guardado.
     * @return 
     */
    public int getModo(){
        return m;
    }
}

