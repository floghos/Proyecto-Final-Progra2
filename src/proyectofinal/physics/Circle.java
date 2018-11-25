package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
/**
 * Clase que Define un círculo, con posición, radio y coeficiente de restitución, para interactuar en la simulación.
 */
public class Circle {
    //Esta clase define los circulos, usados para la pelota y obstaculos, que estaran en la simulación.
    public Vector2d pos;
	protected float radius;
    protected float restitucion;
    protected Color color;
    
    /**
     * Método constructor
     * @param pos Posición del centro del circulo.
     * @param radius
     * @param restitucion
	 * @param color
     */
    public Circle(Vector2d pos, float radius, float restitucion, Color color){
        this.pos = new Vector2d(pos);		
        this.radius = radius;
        this.restitucion = restitucion;
        this.color = color;

    }
    
	/**
	 * Mutator para color.
	 * @param c 
	 */
    public void setColor(Color c){
        this.color = c;
    }
	
    /**
	 * Mutator para pos.
	 * @param a 
	 */
    public void setPos(Vector2d a){
        this.pos = a;
    }
    
	/**
	 * Accessor para radius.
	 * @return 
	 */
    public float getRadius(){
        return radius;
    }
	
	/**
	 * Accessor para restitucion.
	 * @return 
	 */
    public float getRestitucion() {
		return restitucion;
    }
	
    /**
     * Dibuja el circulo desde su centro.
     * @param g 
     */
    public void paint (Graphics g){
        g.setColor(color);
        g.fillOval(Math.round(pos.x-radius), Math.round(pos.y-radius), (int)radius*2, (int)radius*2);
    }
}