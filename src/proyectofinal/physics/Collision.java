package proyectofinal.physics;

import static java.lang.Math.sqrt;

public class Collision {
    public float distance(Vector2d a, Vector2d b) {
		return (float)sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}
	
//	public static boolean boxVbox(Box a, Box b) {
//		if (a.min.x < b.max.x || a.max.x > b.min.x) return false;
//		if (a.max.y > b.min.y || a.min.y < b.max.y) return false;
//		
//		return true;
//	}
        
        // dududu

	public static boolean circleVcircle(Circle c, Circle b) {
		float r = a.radius + b.radius;
		r *= r;
		//more meaningless changes
		return r < (Math.pow((a.pos.x - b.pos.x), 2) + Math.pow((a.pos.y - b.pos.y), 2));
	}
	//making changes... changes, changes...
}
