package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import proyectofinal.interfaz.Ventana;

public class Ball extends Circle{
    //Esta clase define los circulos, usados para la pelota y obstaculos, que estaran en la simulación.
    public Vector2d velocity;
    private Vector2d accel;
    private Ventana v;
	private Vector2d lastPos;
	public float g;
	int timer;//for tests
	
    /**
     * Método constructor
     * @param pos
     * @param radius
     * @param v 
     */
    public Ball(Vector2d pos,float radius, Ventana v,float restitucion,Color color){
        super(pos, radius,restitucion,color);	
	this.velocity = new Vector2d();
	this.accel = new Vector2d();
        this.v=v;
		this.g = 1;
		this.timer = 0;
    }

    /**
     * Translada el origen del circulo con el vector de translacion "t".
     * @param t 
     */
    public void translate(Vector2d t) {
	this.pos.x += t.x;
	this.pos.y += t.y;
    }
    
	
    /**
     * Actualiza la velocidad y posición del circulo en base a su aceleración y velocidad, respectivamente.
     */
    public void update() {
        this.velocity = Vector2d.sum(velocity, accel);
	translate(velocity);
        restriccion();
		timer++;
//		if (timer >= 30) {
//			
//			timer = 0;
//		}
//		System.out.println("posicion: " + pos.x + "," + pos.y +  " | vel: " + Vector2d.modulo(velocity));
		if(hasStopped()) {
			v.getDp().tiempo.stop();
			v.getDp().comienzo = false;
			v.getDp().velocidadInicial("Relanzar");
		}
    }
	
    /**
     * Recibe un valor con el cual se modifica la aceleración.
     * @param accel 
     */
    public void setAccel(Vector2d accel) {
		this.accel = accel;
    }
	
    /**
     * Recibe un valor con el cual se modifica la velocidad.
     * @param vel 
     */
    public void setVelocity(Vector2d vel) {
	this.velocity = vel;
    }
    
    public void setPos(Vector2d a){
        this.pos=a;
    }
	
	public boolean hasStopped() {
//		if (lastPos.x == pos.x && lastPos.y == pos.y) {
//			return true;
//		} else {
//			lastPos = new Vector2d(pos);
//			return false;
//		}
		if (this.pos.y >= 534 && Vector2d.modulo(velocity) <= 0.47*g + 0.02) return true;
		else return false;
	}
    /**
     * Restringe el rango de movimento del circulo a los confines del area de juego.
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
        g.setColor(color);
        g.fillOval((int)(pos.x-radius), (int)(pos.y-radius), (int)radius*2 + 2, (int)radius*2 + 2);
    }
}
