package proyectofinal.interfaz;

public class AlmacenForma {
    private int f;
	
	/**
	 * Método constructor, inicializa la forma en 0 (circulo), por defecto.
	 */
    public AlmacenForma(){
        f=0;
    }
    public void setForma(int forma){
        f=forma;
    }
    public int getForma(){
        return f;
    }
}
