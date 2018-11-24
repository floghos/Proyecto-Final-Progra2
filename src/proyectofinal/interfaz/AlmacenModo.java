package proyectofinal.interfaz;

/**
 * Clase que almacena número del modo seleccionado: añadir obstaculos(1) - quitar obstaculos(2).
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
    * Mutator para m.
    * @param modo 
    */
    public void setModo(int modo){
        m=modo;
    }
    
    /**
     * Accessor para m.
     * @return 
     */
    public int getModo(){
        return m;
    }
}

