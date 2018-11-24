package proyectofinal.interfaz;

/**
 * Clase que almacena número del tipo seleccionado: obstaculo normal(1)-obstaculo potenciador(2).
 */
public class AlmacenTipo {
    private int t;
    /**
     * Método constructor.
     */
    public AlmacenTipo(){
        t=0;
    }
    
    /**
     * Mutator para t.
     * @param tipo 
     */
    public void setTipo(int tipo){
        t=tipo;
    }
    
    /**
     * Accessor para t.
     * @return 
     */
    public int getTipo(){
        return t;
    }
}
