package proyectofinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import proyectofinal.physics.*;
import static proyectofinal.physics.Collision.circleVcircle;
import static proyectofinal.physics.Collision.resColCircle;
import static proyectofinal.physics.Vector2d.*;

public class PanelDibujo extends JPanel implements MouseListener,ActionListener {
    AlmacenForma af;
    AlmacenModo am;
    Caja caja;
    Timer tiempo;
    Box box1;//testing
    int fps;//cuadros por segundo
    Ventana v;
    Circle cir;
    Circle obs1,obs2;
    ArrayList<Circle> obstaculos;
    Circle inicio;
    Vector2d gravedad;
    Vector2d dir;
    boolean comenzo;
	
    public PanelDibujo(AlmacenForma af, AlmacenModo am, Ventana v){
        comenzo=false;
        this.setBackground(Color.darkGray);
        obstaculos= new ArrayList();
        this.af = af;
        this.am = am;
        this.v=v;
        this.addMouseListener(this);
		this.caja = new Caja(v.ancho, v.alto);
        this.fps = 30;
        tiempo = new Timer(1000/fps,null);
        tiempo.addActionListener(this);

        cir= new Circle(new Vector2d(200, 100),20f,v);
        this.inicio=cir;
        for(int i=0; i<12;i++){
            float x= (float)Math.random()*(v.ancho-215);
            float y= (float)Math.random()*(v.alto-25);
            float rad=(float)Math.random()*80+5;
            Circle aux= new Circle(new Vector2d(x,y),rad,v);
            obstaculos.add(aux);
        }

        dir = new Vector2d(1, 0);
		gravedad = new Vector2d(0, 1);
		dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
		float factor = (float)Math.random()*20 + 5;
		dir.x *= factor;
		dir.y *= factor;		
		this.cir.setAccel(gravedad);
		this.cir.setVelocity(dir);
    }
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
					float x= (float)Math.random()*(v.ancho-215);
                    float y= (float)Math.random()*(v.alto-25);
                    float rad=(float)Math.random()*80+5;
                    Circle aux= new Circle(new Vector2d(x,y),rad,v);
                    obstaculos.add(aux);
                }
                dir = new Vector2d(1, 0);
                dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
                factor = (float)Math.random()*20 + 5;
                dir.x *= factor;
                dir.y *= factor;
                cir.setPos(new Vector2d(200,100));
                cir.setAccel(gravedad);
                cir.setVelocity(dir);
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
                cir.setPos(new Vector2d(200,100));
                cir.setAccel(gravedad);
                cir.setVelocity(dir);
                this.repaint();
                break;
        }
	}
	
    public void direccionInicial(String accion){            
        if(!comenzo){
            switch(accion){
            case "<--":
                dir=rotateVector(dir,-Math.PI/36);                           
                break;                    
            case "-->":                    
                dir=rotateVector(dir,Math.PI/36);                                
                break;
            }
            cir.setVelocity(dir);
            repaint();
		}
    }
        
    public void paint (Graphics g){
        super.paint(g);
        caja.paint(g);
        g.setColor(Color.red);
		cir.paint(g);
        g.setColor(Color.red);
        g.setColor(Color.black);
        for(int i=0; i<obstaculos.size();++i){
            obstaculos.get(i).paint(g);
        }
        g.setColor(Color.white);
        if(!comenzo)g.drawLine((int)cir.pos.x, (int)cir.pos.y, (int)(cir.pos.x+dir.x*3), (int)(cir.pos.y+dir.y*3));       
    }
	
    public void mousePressed(MouseEvent e) {	
        if(am.getModo()==1){//crear obstaculo
            if(af.getForma()==1){ //circulo
                Circle aux= new Circle(new Vector2d(e.getX(),e.getY()),20f,v);
                obstaculos.add(aux);
                repaint();
            }
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
		cir.update();
        for(int i=0; i<obstaculos.size();i++){
            if(circleVcircle(cir,obstaculos.get(i))&& escalarProyeccion(cir,obstaculos.get(i))>0){
				cir.translate(Collision.pushOut(cir, obstaculos.get(i)));
                cir.setVelocity(Vector2d.vecPorEscalar(resColCircle(cir,obstaculos.get(i)), cir.getRestitucion()));     
            }
        }
    }
}
