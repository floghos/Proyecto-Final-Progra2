package proyectofinal.physics;

/**
 * Esta clase contiene metodos para lidiar con colisiones
 * 
 */
public class Collision {
	
	
//	public static boolean boxVbox(Box a, Box b) {
//		if (a.min.x < b.max.x || a.max.x > b.min.x) return false;
//		if (a.max.y > b.min.y || a.min.y < b.max.y) return false;
//		
//		return true;
//	}
	
	/**
	 * Determina si 2 circulos, a y b, estan colisionando
	 * @param a
	 * @param b
	 * @return
	 */
    public static boolean circleVcircle(Circle a, Circle b) {
        float r = a.radius + b.radius;
		r *= r;
		return r > (Math.pow((a.pos.x - b.pos.x), 2) + Math.pow((a.pos.y - b.pos.y), 2));
    }
	
	/**
	 * Resuelve una colision entre 2 circulos, devolviendo el vector velocidad con el que debe rebotar la pelota.
	 * @param a
	 * @param b
	 * @return 
	 */
	public static Vector2d resColCircle(Circle a, Circle b) {
		float m = (b.pos.y - a.pos.y)/(b.pos.x - a.pos.x);
		m = -(1/m);
		Vector2d velA = new Vector2d(a.velocity);
		Vector2d newVector = new Vector2d();
		newVector.x = ((1 - m*m) * velA.x + 2 * m * velA.y) / (m*m + 1);
		newVector.y = ((m*m - 1) * velA.y + 2 * m * velA.x) / (m*m + 1);
		
		return newVector;
	}
	
	/**
	 * En caso de que la pelota (player) esté sobreponiedose a un obstaculo, el metodo devuelve el vector que indica la distancia sobrepuesta
	 * @param player 
	 * @param obstacle
	 * @return 
	 */
	public static Vector2d pushOut(Circle player, Circle obstacle) {
		Vector2d vecDir = Vector2d.resta(player.pos, obstacle.pos);
		float sumRadios = player.radius + obstacle.radius;
		float penetracion = sumRadios - Vector2d.modulo(vecDir);
		vecDir = Vector2d.normalize(vecDir);
		
		return Vector2d.vecPorEscalar(vecDir, -penetracion);
	}
	
}
