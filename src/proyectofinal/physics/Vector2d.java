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
    public static float productoPunto(Vector2d a,Vector2d b){
        float pPunto;
		pPunto=a.x*b.x+a.y*b.y;
        return pPunto;
	}
        
    public static float norma(Vector2d a){
        float norma;
        norma=(float)Math.sqrt(a.x*a.x+a.y*a.y);
        return norma;
    }
        
    public static float angulo(Vector2d a,Vector2d b){
        float angulo;
        angulo=(float)Math.acos((productoPunto(a,b))/(norma(a)*norma(b)));
        return angulo;
    }
	//para reflejar un vector, podemos reflejar el punto al que representa en torno al plano de incidencia
	//ubicado en el origen.
}
