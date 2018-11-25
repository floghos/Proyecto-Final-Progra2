package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import proyectofinal.interfaz.Ventana;

/**
 * Esta clase define una pelota con la capacidad de moverse linealmente o de forma acelerada.
 */
public class Ball extends Circle{
    //Esta clase define una pelota con la capacidad de moverse linealmente o de forma acelerada.
    public Vector2d velocity;
    private Vector2d accel;
    private Ventana v;
	public float g;
	
    /**
	 * Método constructor
	 * @param pos Posición del centro de la pelota
	 * @param radius
	 * @param v Referecia a la ventana que contiene la aplicación.
	 * @param restitucion Coeficiente de restitución
	 * @param color 
	 */
    public Ball(Vector2d pos,float radius, Ventana v,float restitucion,Color color){
        super(pos, radius,restitucion,color);	
		this.velocity = new Vector2d();
		this.accel = new Vector2d();
        this.v=v;
		this.g = 1;
    }

    /**
     * Translada el origen de la pelota con el vector de translacion "t".
     * @param t 
     */
    public void translate(Vector2d t) {
	this.pos.x += t.x;
	this.pos.y += t.y;
    }
    
	
    /**
     * Actualiza la velocidad y posición de la pelota en base a su aceleración y velocidad, respectivamente.
     */
    public void update() {
        this.velocity = Vector2d.sum(velocity, accel);
	translate(velocity);
        restriccion();
		if(hasStopped()) {
			v.getDp().tiempo.stop();
			v.getDp().comienzo = false;
			v.getDp().velocidadInicial("Relanzar");
		}
    }
	
    /**
     * Mutator para accel.
     * @param accel 
     */
    public void setAccel(Vector2d accel) {
		this.accel = accel;
    }
	
    /**
     * Mutator para velocity.
     * @param vel 
     */
    public void setVelocity(Vector2d vel) {
		this.velocity = vel;
    }
    
	/**
	 * Determina si la pelota ha llegado a un estado de reposo en el suelo de la caja.
	 * @return 
	 */
	public boolean hasStopped() {
		if (this.pos.y >= v.alto-45-radius && Vector2d.modulo(velocity) <= 0.47*g + 0.02) return true;
		else return false;
	}
	
    /**
     * Restringe el rango de movimento de la pelota a los confines del área de juego.
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
		if (pos.x < 20 + radius) {
			pos.x = 20 + radius;
			velocity.x *= -restitucion;               
		} else if (pos.x > v.ancho-235-radius) {
			pos.x =  v.ancho-235-radius;
			velocity.x *= -restitucion;
		}
    }
	public void paint (Graphics g){
        g.setColor(color);
        g.fillOval(Math.round(pos.x-radius), Math.round(pos.y-radius), (int)radius*2 + 2, (int)radius*2 + 2);
    }
}
