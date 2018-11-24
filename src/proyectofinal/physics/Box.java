package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase que define rectangulos.
 */
public class Box {
    //Esta clase define los rectangulos que seran usados como obstaculos, en caso de implementarlos.
    private Vector2d pos;
	private int width;
	private int height;
    
    /**
     * Metodo constructor
     * @param pos vector posicion del rectángulo, apuntando a la esquina superior izquierda.
     * @param width ancho del rectángulo
     * @param height alto del rectángulo
     */
    public Box (Vector2d pos, int width, int height) {
	this.pos = new Vector2d(pos);
		this.width = width;
		this.height = height;
    }
	
    /**
     * Dibuja el rectangulo desde su esquina superior izquierda.
     * @param g 
     */
    public void paint(Graphics g) {	
        g.setColor(Color.red);
		g.fillRect((int)pos.x, (int)pos.y, width, height);
    }
}