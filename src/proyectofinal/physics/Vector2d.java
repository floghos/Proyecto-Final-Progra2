package proyectofinal.physics;

public class Vector2d {
    public float x;
    public float y;
	
    public Vector2d() {
        this(0, 0);
    }
	
    public Vector2d(Vector2d v) {
		this(v.x, v.y);
    }
	
    public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
    }
	
    public static Vector2d rotateVector(Vector2d v, double angle) {
		Vector2d newVector = new Vector2d();
		newVector.x = (float)(v.x * Math.cos(angle) - v.y * Math.sin(angle));
		newVector.y = (float)(v.x * Math.sin(angle) + v.y * Math.cos(angle));
		return newVector;
    }
	
    public static Vector2d sum(Vector2d a, Vector2d b) {
		Vector2d temp = new Vector2d(a.x + b.x, a.y + b.y);
		return temp;
    }
    public static Vector2d resta(Vector2d a, Vector2d b){
        Vector2d rest = new Vector2d(b.x - a.x, b.y - a.y);
		return rest;
    }
	
    public static float productoPunto(Vector2d a,Vector2d b){
        float pPunto;
		pPunto=a.x*b.x+a.y*b.y;
        return pPunto;
    }
        
    public static float norma(Vector2d a){
        return (float)Math.sqrt(a.x*a.x + a.y*a.y);
    }
        
    public static float angulo(Vector2d a,Vector2d b){
        return (float)Math.acos((productoPunto(a,b))/(norma(a)*norma(b)));
    }
    
    public static float escalarProyeccion(Circle a,Circle b){
        Vector2d vPosicion = resta(a.pos,b.pos);
        return (productoPunto(vPosicion,a.velocity)/productoPunto(vPosicion,vPosicion));
    }
	
	public static Vector2d vecPorEscalar(Vector2d v, float e) {
		return new Vector2d(v.x * e, v.y * e);
	}
	
	public static Vector2d normalize(Vector2d vec) {
		float n = norma(vec);
		return vecPorEscalar(vec, 1/n);
	}
	
	public static float module(Vector2d vec) {
		return (float)Math.sqrt((vec.x*vec.x) + (vec.y*vec.y));
	}
}
