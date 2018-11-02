package proyectofinal.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Box extends Rectangle{
	Vector2d velocity;
	Vector2d accel;
	Vector2d pos;
	final float restitucion = 0.8f;
		
	public Box (Vector2d pos, int width, int height) {
		super((int)pos.x, (int)pos.y, width, height);
		this.pos = new Vector2d(pos);		
		this.velocity = new Vector2d();
		this.accel = new Vector2d();
	}
	
	public void update(){
		this.velocity = Vector2d.sum(velocity, accel);
		this.pos = Vector2d.sum(pos, velocity);
		
		this.x = (int)pos.x; // might not be needed
		this.y = (int)pos.y; //	might not be needed
                restriccion();
	}
	
	public void setAccel(Vector2d accel) {
		this.accel = accel;
	}
	
	public void setVelocity(Vector2d vel) {
		this.velocity = vel;
	}
	
	public void restriccion() {
		if (pos.y > 525) {
			pos.y = 525;
			if (Math.abs(this.velocity.x) > 0.001f) {
				this.velocity.x *= 0.95f;
			}
			velocity.y *= -restitucion;
		} else if (pos.y < 0) {
			pos.y = 0;
			velocity.y *= -restitucion;
		}
		if (pos.x < 20) {
			pos.x = 20;
			velocity.x *= -restitucion;
		} else if (pos.x > 675) {
			pos.x = 675;
			velocity.x *= -restitucion;
		}
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.red);
		g.fillOval((int)pos.x, (int)pos.y, 30, 30); //para tests, se supone que debe ser un rectangulo!!
	}
}
