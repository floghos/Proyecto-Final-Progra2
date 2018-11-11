package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase que define rectangulos.
 */
public class Box extends Rectangle{
    //Esta clase define los rectangulos que seran usados como obstaculos, en caso de implementarlos.
    private Vector2d pos;
    private final float restitucion = 0.8f;
    
    /**
     * Metodo constructor
     * @param pos vector posicion del rectángulo, apuntando a la esquina superior izquierda.
     * @param width ancho del rectángulo
     * @param height alto del rectángulo
     */
    public Box (Vector2d pos, int width, int height) {
	super((int)pos.x, (int)pos.y, width, height);
	this.pos = new Vector2d(pos);		
    }
	
    /**
     * Dibuja el rectangulo desde su esquina superior izquierda.
     * @param g 
     */
    public void paint(Graphics g) {	
        g.setColor(Color.red);
	g.fillRect((int)pos.x, (int)pos.y, 30, 30); //para tests, se supone que debe ser un rectangulo!!
    }
}