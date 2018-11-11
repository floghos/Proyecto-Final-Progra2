package proyectofinal;

import java.awt.Color;
import java.awt.Graphics;

public class Caja{
	//Esta clase dibuja los bordes del espacio de juego
	
    int ancho;
    int alto;
	
	/**
	 * Método constructor
	 * @param ancho
	 * @param alto 
	 */
    public Caja(int ancho, int alto){
        this.ancho=ancho;
        this.alto=alto;
    }
    
    public void paint (Graphics g){
        g.setColor(Color.black);        
        g.fillRect(0,0,ancho-180,20); //borde superior
        g.fillRect(0,alto-45,ancho-180,20);// borde inferior
        g.fillRect(0,0,20,alto-25); //borde izquierdo
        g.fillRect(ancho-235,0,20,alto-25); //borde derecho 
    }
}
