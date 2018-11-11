package proyectofinal.interfaz;
/**
 * Clase que almacena número de la forma seleccionada. 
 */
public class AlmacenForma {
    //Esta clase solo se utilizara en caso de agregar rectangulos como obstaculos.
    private int f;
    /**
     * Método constructor.
     */
    public AlmacenForma(){
        f=0;
    }
    
    /**
     * Recibe y almacena el número entregado por el botón.
     * @param forma 
     */
    public void setForma(int forma){
        f=forma;
    }
    
    /**
     * Entrega el numero de forma guardado.
     * @return 
     */
    public int getForma(){
        return f;
    }
}
