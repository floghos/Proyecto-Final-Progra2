package proyectofinal.physics;

import java.awt.Graphics;
import proyectofinal.interfaz.Ventana;

public class Circle {
    float radius;
    public Vector2d pos;
    public Vector2d velocity;
    public Vector2d accel;
    Ventana v;
    private float restitucion;
	
	/**
	 * Método constructor
	 * @param pos
	 * @param radius
	 * @param v 
	 */
    public Circle(Vector2d pos,float radius, Ventana v){
        this.pos = new Vector2d(pos);		
		this.velocity = new Vector2d();
		this.accel = new Vector2d();
        this.radius=radius;
        this.v=v;
		this.restitucion = 0.9f;
    }
    
	
    public float getRadius(){
        return radius;
    }
    public float getRestitucion() {
    	return restitucion;
    }   
    public void setPos(Vector2d a){
        this.pos=a;
    }
	
	/**
	 * Translada el origen del circulo con el vector de translacion "t"
	 * @param t 
	 */
    public void translate(Vector2d t) {
		this.pos.x += t.x;
		this.pos.y += t.y;
    }
    
	/**
	 * Actualiza la velocidad y posición del circulo en base a su aceleración y velocidad, respectivamente
	 */
    public void update() {
        this.velocity = Vector2d.sum(velocity, accel);
		translate(velocity);
        restriccion();
    }
	
    public void setRestitucion(float res) {
		restitucion = res;
    }
	
    public void setAccel(Vector2d accel) {
		this.accel = accel;
    }
	
    public void setVelocity(Vector2d vel) {
		this.velocity = vel;
    }
	
	/**
	 * Restringe el rango de movimento del circulo a los confines del area de juego
	 */
    public void restriccion() {
		if (pos.y > v.alto-45-radius) {
            pos.y = v.alto-45-radius;
            if (Math.abs(this.velocity.x) > 0.001f) {
				this.velocity.x *= 0.95f;
			}
            velocity.y *= -restitucion;
		} else if (pos.y < 20+radius) {
            pos.y = 20+radius;
            velocity.y *= -restitucion;
        }
		if (pos.x < 20+radius) {
            pos.x = 20+radius;
            velocity.x *= -restitucion;               
		} else if (pos.x > v.ancho-235-radius) {
            pos.x =  v.ancho-235-radius;
            velocity.x *= -restitucion;
		}
    }
        
    public void paint (Graphics g){
        g.fillOval((int)(pos.x-radius), (int)(pos.y-radius), (int)radius*2, (int)radius*2);
    }
}