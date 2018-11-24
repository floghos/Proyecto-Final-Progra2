package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Clase que Define un círculo, con posición, radio y restitucion, para interactuar en la simulación.
 */
public class Circle {
    //Esta clase define los circulos, usados para la pelota y obstaculos, que estaran en la simulación.
    public Vector2d pos;
	protected float radius;
    protected float restitucion;
    protected Color color;
    
    /**
     * Método constructor
     * @param pos
     * @param radius
     * @param restitucion
     */
    public Circle(Vector2d pos,float radius,float restitucion,Color color){
        this.pos = new Vector2d(pos);		
        this.radius=radius;
        this.restitucion=restitucion;
        this.color=color;

    }
    
    public float getRadius(){
        return radius;
    }
    
    public float getRestitucion() {
   	return restitucion;
    }   
	
    /**
     * Dibuja el circulo desde su centro.
     * @param g 
     */
    public void paint (Graphics g){
        g.setColor(color);
        g.fillOval((int)(pos.x-radius), (int)(pos.y-radius), (int)radius*2, (int)radius*2);
    }
}