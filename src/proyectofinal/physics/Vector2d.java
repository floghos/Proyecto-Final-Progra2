package proyectofinal.physics;

public class Vector2d {
    public float x;
    public float y;
	
	/**
	 * Método constructor
	 */
    public Vector2d() {
        this(0, 0);
    }
	/**
	 * Método constructor
	 * @param v 
	 */
    public Vector2d(Vector2d v) {
		this(v.x, v.y);
    }
	/**
	 * Método constructor
	 * @param x
	 * @param y 
	 */
    public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
    }
	
	/**
	 * Rota un vector "v" en un angulo "angle", en sentido horario.
	 * @param v
	 * @param angle
	 * @return vector resultante
	 */
    public static Vector2d rotateVector(Vector2d v, double angle) {
		Vector2d newVector = new Vector2d();
		newVector.x = (float)(v.x * Math.cos(angle) - v.y * Math.sin(angle));
		newVector.y = (float)(v.x * Math.sin(angle) + v.y * Math.cos(angle));
		return newVector;
    }
	
	/**
	 * Suma dos vectores
	 * @param a
	 * @param b
	 * @return vector resultante
	 */
    public static Vector2d sum(Vector2d a, Vector2d b) {
		Vector2d temp = new Vector2d(a.x + b.x, a.y + b.y);
		return temp;
    }
	
	/**
	 * Resta 2 vectores (a - b)
	 * @param a
	 * @param b
	 * @return vector resultante
	 */
    public static Vector2d resta(Vector2d a, Vector2d b){
        Vector2d rest = new Vector2d(b.x - a.x, b.y - a.y);
		return rest;
    }
	
	/**
	 * Calcula el producto escalar (producto punto) entre 2 vectores
	 * @param a
	 * @param b
	 * @return 
	 */
    public static float productoPunto(Vector2d a,Vector2d b){
        float pPunto;
		pPunto=a.x*b.x+a.y*b.y;
        return pPunto;
    }
    
	/**
	 * Calcula el modulo de un vector
	 * @param a
	 * @return 
	 */
    public static float modulo(Vector2d a){
        return (float)Math.sqrt(a.x*a.x + a.y*a.y);
    }
	
     /**
	  * Retorna el angulo entre 2 vectores
	  * @param a
	  * @param b
	  * @return 
	  */   
    public static float angulo(Vector2d a,Vector2d b){
        return (float)Math.acos((productoPunto(a,b))/(modulo(a)*modulo(b)));
    }
	
    /**
	 * Retorna el modulo de la proyeccion de u sobre v (Falta generalizar el uso de esta funcion!!!)
	 * @param a
	 * @param b
	 * @return 
	 */
    public static float escalarProyeccion(Vector2d u,Vector2d v){
//        Vector2d vPosicion = resta(a.pos,b.pos);
//        return (productoPunto(vPosicion,a.velocity)/productoPunto(vPosicion,vPosicion));
		return (productoPunto(u, v)/productoPunto(v, v));
    }
	
	/**
	 * Multiplica un vector "v" por un escalar "e"
	 * @param v
	 * @param e
	 * @return 
	 */
	public static Vector2d vecPorEscalar(Vector2d v, float e) {
		return new Vector2d(v.x * e, v.y * e);
	}
	
	/**
	 * Retorna un vector unitario con la direccion del vector original
	 * @param vec
	 * @return 
	 */
	public static Vector2d normalize(Vector2d vec) {
		float n = modulo(vec);
		return vecPorEscalar(vec, 1/n);
	}
}
