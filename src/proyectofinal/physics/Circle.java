package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import proyectofinal.Ventana;

public class Circle {
    float radius;
    Vector2d pos;
    Vector2d velocity;
    Vector2d accel;
    Ventana v;
    final float restitucion = 0.8f;
	
    public Circle(Vector2d pos,float radius, Ventana v){
    	this.pos = new Vector2d(pos);		
	this.velocity = new Vector2d();
	this.accel = new Vector2d();
        this.radius=radius;
        this.v=v;
    }
        
    public void update(){
        this.velocity = Vector2d.sum(velocity, accel);
	this.pos = Vector2d.sum(pos, velocity);
        restriccion();
    }
	
    public void setAccel(Vector2d accel) {
	this.accel = accel;
    }
	
    public void setVelocity(Vector2d vel) {
	this.velocity = vel;
    }
	
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
	if (pos.x < 20-radius) {
            pos.x = 20-radius;
            velocity.x *= -restitucion;
            v.dp.startStop("Stop");
            v.dp.repaint();
                       
	} else if (pos.x > v.ancho-235-radius*3) {
            pos.x =  v.ancho-235-radius*3;
            velocity.x *= -restitucion;
            v.dp.startStop("Stop");
	}
    }
        
    public void paint (Graphics g){
        g.setColor(Color.red);
        g.fillOval((int)(pos.x+radius), (int)(pos.y-radius), (int)radius*2, (int)radius*2);
    }
}