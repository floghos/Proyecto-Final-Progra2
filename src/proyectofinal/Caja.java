package proyectofinal;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Clase encargada de dibujar los bordes del espacio de juego.
 */
public class Caja{
    //Esta clase dibuja los bordes del espacio de juego.
    private int alto;
    private int ancho;
    private Color color;
    /**
     * Método constructor.
     * @param ancho
     * @param alto 
     * @param color
     */
    public Caja(int ancho, int alto, Color color){
        this.ancho=ancho;
        this.alto=alto;
        this.color=color;
    }
    
    /**
     * Actualiza el tamaño de la caja cuando se modifican las dimensiones de ventana.
     * @param alto
     * @param ancho 
     */
    public void updateSize(int alto, int ancho) {
	this.alto = alto;
	this.ancho = ancho;
    }
    
    /**
     * Dibuja los bordes que definen el espacio de juego.
     * @param g 
     */
    public void paint (Graphics g){
        g.setColor(color);        
        g.fillRect(0,0,ancho-215,20); //borde superior.
        g.fillRect(0,alto-45,ancho-215,20);// borde inferior.
        g.fillRect(0,0,20,alto-25); //borde izquierdo.
        g.fillRect(ancho-235,0,20,alto-25); //borde derecho. 
    }
}