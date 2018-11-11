package proyectofinal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import proyectofinal.physics.*;

public class Caja{
    
    int ancho;
    int alto;
    ArrayList<Box> bordes;
	
	/**
	 * Método constructor
	 * @param ancho
	 * @param alto 
	 */
    public Caja(int ancho, int alto){
	this.bordes = new ArrayList();
        bordes.add((new Box(new Vector2d(),ancho-180,20)));
        bordes.add((new Box(new Vector2d(0,alto-45),ancho-180,20)));
        bordes.add((new Box(new Vector2d(),20,alto-25)));
        bordes.add((new Box(new Vector2d(ancho-235,0),20,alto-25)));
        this.ancho=ancho;
        this.alto=alto;
    }
    
	/* //metodo no utilizado
    public boolean colision(Box box) {
        int i;
        for (i = 0; i < bordes.size(); ++i) {
            if(bordes.get(i).intersects(box)) return true;
        }
        return false;
    }*/
    
    /**
     * Dibuja los bordes que definen el espacio de juego
     * @param g 
     */
    public void paint (Graphics g){
        g.setColor(Color.black);        
        g.fillRect(0,0,ancho-180,20); //borde superior
        g.fillRect(0,alto-45,ancho-180,20);// borde inferior
        g.fillRect(0,0,20,alto-25); //borde izquierdo
        g.fillRect(ancho-235,0,20,alto-25); //borde derecho 
    }
}
