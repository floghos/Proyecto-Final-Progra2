package proyectofinal;

import proyectofinal.interfaz.Ventana;
/**
 * Clase principal de la Aplicación, crea ventana.
 */
public class ProyectoFinal {
	static Ventana v;
    /**
     * Metodo principal, ejecutado al comenzar la aplicación. 
     * Crea la ventana principal.
     * @param args 
     */	
    public static void main(String[] args) {
	//Crea la ventana principal.
        v = new Ventana();
    }
    
}