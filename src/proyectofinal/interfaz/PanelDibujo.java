package proyectofinal.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import proyectofinal.Caja;
import proyectofinal.physics.*;
import static proyectofinal.physics.Collision.circleVcircle;
import static proyectofinal.physics.Collision.pushOut;
import static proyectofinal.physics.Collision.resColCircle;
import static proyectofinal.physics.Vector2d.*;
/**
 * Clase utilizada para llevar a cabo las acciones de botones que se muestran por pantalla. 
 * Dirección: "↺", "↻"
 * Rapidez: "-", "+"
 * Modo: "Añadir", "Quitar"
 * Estado: "Start", "Stop", "Restart", "Reset"
 */
public class PanelDibujo extends JPanel implements MouseListener,ActionListener {
    //Esta clase crea los objetos iniciales (Pelota y obstaculos), 
    private AlmacenForma aForma;
    private AlmacenModo aModo;
    private Caja caja;
    private int fps;//cuadros por segundo.
    private Ventana ventana;
    private Ball pelota;
    private ArrayList<Circle> obstaculos;
    private Vector2d gravedad;
    private Vector2d velPelota;
    private Vector2d respaldoVel;
    private float gravMultiplier;
    private float radObs;
    private Color cPelota;
    private Color cObstaculo;
    private Color cPotenciador;
    private Color cError;
	public Timer tiempo;
    public boolean comienzo;
	
    /**
     * Método constructor. Crea la pelota en su posición inicial y le da una velocidad aleatoria, crea 12 obstaculos de radio y posición al azar en el espacio de juego.
     * @param aForma
     * @param aModo
     * @param ventana
     */
    public PanelDibujo(AlmacenForma aForma, AlmacenModo aModo, Ventana ventana){        
        comienzo=false;
        this.setBackground(new Color(150,150,100));
        obstaculos= new ArrayList();
        this.aForma = aForma;
        this.aModo = aModo;
        this.ventana=ventana;
	this.gravMultiplier = 1;
        this.radObs= 20;
        this.addMouseListener(this);
	this.caja = new Caja(ventana.ancho, ventana.alto);
        this.fps = 30;
        tiempo = new Timer(1000/fps,null);
        tiempo.addActionListener(this);
        cPelota=new Color(150,40,40);
        cObstaculo=new Color(40,40,40);
        cPotenciador=new Color(40,70,120);
        cError=new Color (100,10,10);
        pelota= new Ball(new Vector2d(40,40),20f,ventana,0.9f,cPelota);
        
        for(int i=0; i<10;i++){
            float x= (float)Math.random()*(ventana.ancho-215);
            float y= (float)Math.random()*(ventana.alto-25);
            float rad=(float)Math.random()*75+5;
            Circle aux= new Circle(new Vector2d(x,y),rad,0.9f,cObstaculo);
			if (!circleVcircle(pelota, aux)) {
				obstaculos.add(aux);
			} else {
				i--;
			}
            
        }
        for(int i=0; i<2;i++){
            float x= (float)Math.random()*(ventana.ancho-215);
            float y= (float)Math.random()*(ventana.alto-25);
            float rad=(float)Math.random()*75+5;
            Circle aux= new Circle(new Vector2d(x,y),rad,1.1f,cPotenciador);
			if (!Collision.circleVcircle(pelota, aux)) {
				obstaculos.add(aux);
			} else {
				i--;
			}
            obstaculos.add(aux);
        }

        velPelota = new Vector2d(1, 0);
        gravedad = new Vector2d(0, 1);
        velPelota = Vector2d.rotateVector(velPelota, Math.random()*2*Math.PI);
        float factor = (float)Math.random()*20 + 5;
		velPelota = vecPorEscalar(velPelota, factor);
        this.pelota.setAccel(gravedad);
        this.pelota.setVelocity(velPelota);
    }
	

	public Caja getCaja() {
		return this.caja;
	}
	public float getGravMultiplier() {
		return gravMultiplier;
	}
	
    /**
     * Detiene y reanuda el tiempo, además de reiniciar y reconfigurar la simulacion.
     * @param accion "Start", "Stop", "Reset", "Restart"
     */
    public void accion(String accion) {
        float factor;
        switch(accion) {                
            case "Start":
                tiempo.start();
                comienzo=true;
                break;
            case "Stop":
		tiempo.stop();
                break;
            case "Reset":
		comienzo=false;                    
		tiempo.stop();
		ventana.slider.setValue(10);
                obstaculos.removeAll(obstaculos);    
                for(int i=0; i<10;i++){
                    float x= (float)Math.random()*(ventana.ancho-215);
                    float y= (float)Math.random()*(ventana.alto-25);
                    float rad=(float)Math.random()*80+5;
                    Circle aux= new Circle(new Vector2d(x,y),rad,0.9f,cObstaculo);
					if (!circleVcircle(pelota, aux)) {
						obstaculos.add(aux);
					} else {
						i--;
					}
                }
                for(int i=0; i<2;i++){
                    float x= (float)Math.random()*(ventana.ancho-215);
                    float y= (float)Math.random()*(ventana.alto-25);
                    float rad=(float)Math.random()*80+5;
                    Circle aux= new Circle(new Vector2d(x,y),rad,1.1f,cPotenciador);
					if (!circleVcircle(pelota, aux)) {
						obstaculos.add(aux);
					} else {
						i--;
					}
                }
                velPelota = new Vector2d(1, 0);
                velPelota = Vector2d.rotateVector(velPelota, Math.random()*2*Math.PI);
                factor = (float)Math.random()*20 + 5;
                velPelota.x *= factor;
                velPelota.y *= factor;
                pelota.setPos(new Vector2d(40,40));
                pelota.setAccel(gravedad);
                pelota.setVelocity(velPelota);
                this.repaint();
                break;
            case "Restart":
				comienzo=false;
                tiempo.stop();
                velPelota = new Vector2d(1, 0);
                velPelota = Vector2d.rotateVector(velPelota, Math.random()*2*Math.PI);
                factor = (float)Math.random()*20 + 5;
                velPelota.x *= factor;
                velPelota.y *= factor;
                pelota.setPos(new Vector2d(40,40));
                pelota.setAccel(vecPorEscalar(gravedad, gravMultiplier));
                pelota.setVelocity(velPelota);
                this.repaint();
                break;
        }
    }
    
    /**
     * Modifica la velocidad inicial en la cual saldrá la pelota.
     * @param accion "↺" gira la dirección en sentido anti-horario, "↻" gira la dirección en sentido horario, "+" aumenta la rapidez inicial, "-" dismunuye la rapidez inicial.
     */
    public void velocidadInicial(String accion){            
        if(!comienzo){
            switch(accion){
                case "↺":
                    velPelota=rotateVector(velPelota,-Math.PI/36);                           
                    break;                    
                case "↻":                    
                    velPelota=rotateVector(velPelota,Math.PI/36);                                
                    break;
                case "+":
                    if(modulo(velPelota)==0) velPelota=respaldoVel;
                    velPelota=sum(velPelota,normalize(velPelota));
                    break;
                case "-":      
                    if(modulo(velPelota)!=0) respaldoVel=velPelota;
                    if(modulo(velPelota)<1){
                        velPelota=new Vector2d();
                    }else{                    
                        velPelota=resta(normalize(velPelota),velPelota);
                    }
                    break;
				default:
					break;
            }            
            pelota.setVelocity(velPelota);
            repaint();
        }
    }
	
	public void modGrav (float multiplicador) {
		gravMultiplier = multiplicador;
		pelota.setAccel(vecPorEscalar(gravedad, multiplicador));
		pelota.g = multiplicador;
	}
    
    public void obstacleRadius(float radio){
        radObs = radio;
    }
    
    /**
    * Dibuja todos los elementos de la simulación: Caja, Pelota, Obstaculos y Linea de velocidad inicial.
    * @param g 
    */
    public void paint (Graphics g){
        super.paint(g);        
        pelota.paint(g);
        for(int i=0; i<obstaculos.size();++i){
            obstaculos.get(i).paint(g);
        }
        caja.paint(g);
        g.setColor(Color.white); //color linea de lanzamiento
        if(!comienzo)g.drawLine((int)pelota.pos.x, (int)pelota.pos.y, (int)(pelota.pos.x+velPelota.x*3), (int)(pelota.pos.y+velPelota.y*3));       
    }
    
    /**
    * Realiza la acción seleccionada por BotonModo: "Añadir" y "Quitar".
    * @param e 
    */
    public void mousePressed(MouseEvent e) {	
        if(aModo.getModo()==1){//Añadir obstaculo.
            if(aForma.getForma()==1){//normal
//              float rad=(float)Math.random()*40+5;
                Circle aux= new Circle(new Vector2d(e.getX(),e.getY()),radObs,0.9f,cObstaculo);
                obstaculos.add(aux);
                if (circleVcircle(pelota, aux)) {
                    aux.pos=sum(aux.pos,pushOut(aux,pelota));
		}
                
                
            }else if(aForma.getForma()==2){//potenciador
//                float rad=(float)Math.random()*40+5;
                Circle aux= new Circle(new Vector2d(e.getX(),e.getY()),radObs,1.1f,cPotenciador);
                obstaculos.add(aux);
                if (circleVcircle(pelota, aux)) {
                    aux.pos=sum(aux.pos,pushOut(aux,pelota));
		}
            }
            repaint();
        }
        if(aModo.getModo()==2){//Quitar obstaculo.
            for(int i=obstaculos.size()-1;i>=0;i--){
                Circle aux = obstaculos.get(i);
                if(((e.getX()-aux.pos.x)*(e.getX()-aux.pos.x)+(e.getY()-aux.pos.y)*(e.getY()-aux.pos.y))<=(aux.getRadius()*aux.getRadius())){
                    obstaculos.remove(i);
                    repaint();
                    break;
                }
            }
        } 
    }
    
    @Override
    /**
    * Ejecutado a cada tick de nuestro Timer, redibuja todos los objetos, actualiza la posición de la pelota y ejecuta el motor de colisiones
    */
    public void actionPerformed(ActionEvent e) {
		pelota.update();
        this.repaint();
        motorColisiones();	
    }
	/**
	 * Revisa el ArrayList de circulos a cada actualización del Timer comprobando si hay o no colisión entre la pelota y los obstaculos.
	 */
	private void motorColisiones() {
		 for(int i=0; i<obstaculos.size();i++){
            Circle aux = obstaculos.get(i);
            Vector2d vPosicion = resta(pelota.pos,aux.pos);
            if(circleVcircle(pelota,aux) && escalarProyeccion(pelota.velocity, vPosicion)>0){
                pelota.translate(Collision.pushOut(pelota, aux));
                pelota.setVelocity(Vector2d.vecPorEscalar(resColCircle(pelota,aux), aux.getRestitucion()));     
            }
        }
	}
    
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}    
}