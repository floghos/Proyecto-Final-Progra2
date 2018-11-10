package proyectofinal.interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import proyectofinal.Caja;
import proyectofinal.physics.*;
import static proyectofinal.physics.Collision.circleVcircle;
import static proyectofinal.physics.Collision.resColCircle;
import static proyectofinal.physics.Vector2d.*;

public class PanelDibujo extends JPanel implements MouseListener,ActionListener {
    AlmacenForma af;
    AlmacenModo am;
    Caja caja;
    Timer tiempo;
    int fps;//cuadros por segundo
    Ventana ventana;
    Circle pelota;
    ArrayList<Circle> obstaculos;
    Vector2d gravedad;
    Vector2d dir;
    boolean comenzo;
    Vector2d respaldoVel;
	
	/**
	 * Método constructor 
	 * @param af 
	 * @param am
	 * @param ventana
	 */
    public PanelDibujo(AlmacenForma af, AlmacenModo am, Ventana ventana){
        comenzo=false;
        this.setBackground(Color.darkGray);
        obstaculos= new ArrayList();
        this.af = af;
        this.am = am;
        this.ventana=ventana;
        this.addMouseListener(this);
	this.caja = new Caja(ventana.ancho, ventana.alto);
        this.fps = 30;
        tiempo = new Timer(1000/fps,null);
        tiempo.addActionListener(this);

        pelota= new Circle(new Vector2d(200, 100),20f,ventana);
        for(int i=0; i<12;i++){
            float x= (float)Math.random()*(ventana.ancho-215);
            float y= (float)Math.random()*(ventana.alto-25);
            float rad=(float)Math.random()*80+5;
            Circle aux= new Circle(new Vector2d(x,y),rad,ventana);
            obstaculos.add(aux);
        }

        dir = new Vector2d(1, 0);
		gravedad = new Vector2d(0, 1);
		dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
		float factor = (float)Math.random()*20 + 5;
		dir.x *= factor;
		dir.y *= factor;		
		this.pelota.setAccel(gravedad);
		this.pelota.setVelocity(dir);
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
                comenzo=true;
                break;
			case "Stop":
				tiempo.stop();
                break;
			case "Reset":
				comenzo=false;                    
				tiempo.stop();
                obstaculos.removeAll(obstaculos);    
                for(int i=0; i<12;i++){
					float x= (float)Math.random()*(ventana.ancho-215);
                    float y= (float)Math.random()*(ventana.alto-25);
                    float rad=(float)Math.random()*80+5;
                    Circle aux= new Circle(new Vector2d(x,y),rad,ventana);
                    obstaculos.add(aux);
                }
                dir = new Vector2d(1, 0);
                dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
                factor = (float)Math.random()*20 + 5;
                dir.x *= factor;
                dir.y *= factor;
                pelota.setPos(new Vector2d(200,100));
                pelota.setAccel(gravedad);
                pelota.setVelocity(dir);
                this.repaint();
                break;
            case "Restart":
		comenzo=false;
                tiempo.stop();
                dir = new Vector2d(1, 0);
                dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
                factor = (float)Math.random()*20 + 5;
                dir.x *= factor;
                dir.y *= factor;
                pelota.setPos(new Vector2d(200,100));
                pelota.setAccel(gravedad);
                pelota.setVelocity(dir);
                this.repaint();
                break;
        }
	}
	/**
	 * Modifica la dirección inicial en la cual saldrá la pelota.
	 * @param accion "<--" gira la la dirección en sentido anti-horario, "-->" gira la dirección en sentido horario
	 */
    public void velocidadInicial(String accion){            
        if(!comenzo){
            switch(accion){
            case "↺":
                dir=rotateVector(dir,-Math.PI/36);                           
                break;                    
            case "↻":                    
                dir=rotateVector(dir,Math.PI/36);                                
                break;
            case "+":
                if(modulo(dir)==0) dir=respaldoVel;
                dir=sum(dir,normalize(dir));
                break;
            case "-":      
                if(modulo(dir)!=0) respaldoVel=dir;
                if(modulo(dir)<1){
                    dir=new Vector2d();
                }else{                    
                    dir=resta(normalize(dir),dir);
                }
                break;
            }            
            pelota.setVelocity(dir);
            repaint();
		}
    }
        
    public void paint (Graphics g){
        super.paint(g);
        caja.paint(g);
        g.setColor(Color.red);
	pelota.paint(g);
        g.setColor(Color.red);
        g.setColor(Color.black);
        for(int i=0; i<obstaculos.size();++i){
            obstaculos.get(i).paint(g);
        }
        g.setColor(Color.white);
        if(!comenzo)g.drawLine((int)pelota.pos.x, (int)pelota.pos.y, (int)(pelota.pos.x+dir.x*3), (int)(pelota.pos.y+dir.y*3));       
    }
	
    public void mousePressed(MouseEvent e) {	
        if(am.getModo()==1){//crear obstaculo
            //if(af.getForma()==1){ //circulo
                Circle aux= new Circle(new Vector2d(e.getX(),e.getY()),20f,ventana);
                obstaculos.add(aux);
                repaint();
            //}
        }
        if(am.getModo()==2){//borrar obstaculo
            for(int i=obstaculos.size()-1;i>=0;i--){
                if(((e.getX()-obstaculos.get(i).pos.x)*(e.getX()-obstaculos.get(i).pos.x)+(e.getY()-obstaculos.get(i).pos.y)*(e.getY()-obstaculos.get(i).pos.y))<=(obstaculos.get(i).getRadius()*obstaculos.get(i).getRadius())){
                    obstaculos.remove(i);
                    repaint();
                    break;
                }
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
	
    @Override
    public void actionPerformed(ActionEvent e) {
        this.repaint();
		pelota.update();
        for(int i=0; i<obstaculos.size();i++){
            Vector2d vPosicion = resta(pelota.pos,obstaculos.get(i).pos);
            if(circleVcircle(pelota,obstaculos.get(i)) && escalarProyeccion(pelota.velocity, vPosicion)>0){
		pelota.translate(Collision.pushOut(pelota, obstaculos.get(i)));
                pelota.setVelocity(Vector2d.vecPorEscalar(resColCircle(pelota,obstaculos.get(i)), pelota.getRestitucion()));     

            }
        }
    }
}
