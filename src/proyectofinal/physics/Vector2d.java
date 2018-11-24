package proyectofinal.physics;

import static java.lang.Math.sqrt;

/**
 * Clase que contiene metodos para trabajar con vectores.
 */
public class Vector2d {
    public float x;
    public float y;
	
    /**
     * Método constructor.
     */
    public Vector2d() {
        this(0, 0);
    }
    /**
     * Método constructor.
     * @param v 
     */
    public Vector2d(Vector2d v) {
	this(v.x, v.y);
    }
    /**
     * Método constructor.
     * @param x
     * @param y 
     */
    public Vector2d(float x, float y) {
	this.x = x;
	this.y = y;
    }
	
    /**
     * Determina la distancia entre 2 puntos.
     * @param a
     * @param b
     * @return 
     */
    public static float distance(Vector2d a, Vector2d b) {
	return (float)sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
	
    /**
     * Rota un vector "v" en un angulo "angle", en sentido horario.
     * @param v
     * @param angle
     * @return Vector2d resultante.
     */
    public static Vector2d rotateVector(Vector2d v, double angle) {
    	Vector2d newVector = new Vector2d();
    	newVector.x = (float)(v.x * Math.cos(angle) - v.y * Math.sin(angle));
	newVector.y = (float)(v.x * Math.sin(angle) + v.y * Math.cos(angle));
	return newVector;
    }
	
    /**
     * Suma dos vectores.
     * @param a
     * @param b
     * @return Vector2d resultante.
     */
    public static Vector2d sum(Vector2d a, Vector2d b) {
    	Vector2d temp = new Vector2d(a.x + b.x, a.y + b.y);
    	return temp;
    }
	
    /**
     * Resta 2 vectores (a - b).
     * @param a
     * @param b
     * @return Vector2d resultante.
     */
    public static Vector2d resta(Vector2d a, Vector2d b){
        Vector2d rest = new Vector2d(b.x - a.x, b.y - a.y);
	return rest;
    }
	
    /**
     * Calcula el producto escalar (producto punto) entre 2 vectores.
     * @param a
     * @param b
     * @return Escalar (float) resultante.
     */
    public static float productoPunto(Vector2d a,Vector2d b){
        float pPunto;
	pPunto=a.x*b.x+a.y*b.y;
        return pPunto;
    }
    
    /**
     * Calcula el modulo de un vector.
     * @param a
     * @return Escalar (float) resultante.
     */
    public static float modulo(Vector2d a){
        return (float)Math.sqrt(a.x*a.x + a.y*a.y);
    }
	
     /**
      * Retorna el angulo entre 2 vectores.
      * @param a
      * @param b
      * @return Angulo resultante en radianes (float).
      */   
    public static float angulo(Vector2d a,Vector2d b){
        return (float)Math.acos((productoPunto(a,b))/(modulo(a)*modulo(b)));
    }
	
    /**
     * Retorna el factor escalar de la proyeccion de u sobre v (será negativo si la proyección de u sobre v es en el sentido opuesto).
     * @param u vector proyectado.
     * @param v vector sobre el cual se proyecta u.
     * @return retorna el factor escalar de la proyeccion.
     */
    public static float escalarProyeccion(Vector2d u,Vector2d v){
	return (productoPunto(u, v)/productoPunto(v, v));
    }
	
    /**
     * Multiplica un vector "v" por un escalar "e".
     * @param v
     * @param e
     * @return Vector2d resultante
     */
    public static Vector2d vecPorEscalar(Vector2d v, float e) {
    	return new Vector2d(v.x * e, v.y * e);
    }
	
    /**
     * Retorna un vector unitario con la dirección del vector original.
     * @param v
     * @return Vector2d resultante.
     */
    public static Vector2d normalize(Vector2d v) {
    	float n = modulo(v);
	return vecPorEscalar(v, 1/n);
    }
}