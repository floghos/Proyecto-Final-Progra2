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
	
    public PanelDibujo(AlmacenForma af, AlmacenModo am, Ventana v){
        //al = new ArrayList();
        this.setBackground(Color.cyan);
        obstaculos= new ArrayList();
        this.af = af;
        this.am = am;
        this.v=v;
        this.addMouseListener(this);
	this.caja = new Caja(v.ancho, v.alto);
        this.fps = 30;
        tiempo = new Timer(1000/fps,null);
        tiempo.addActionListener(this);
//      tiempo.start();
	//this.box1 = new Box(new Vector2d(200, 100), 20, 20);//testing
        cir= new Circle(new Vector2d(200, 100),20f,v);
        this.inicio=cir;
//      obs1=new Circle(new Vector2d(210,150),20f,v);
//	obs2=new Circle(new Vector2d(400,400),20f,v);
        for(int i=0; i<12;i++){
            float x= (float)Math.random()*(v.ancho-215);
            float y= (float)Math.random()*(v.alto-25);
            float rad=(float)Math.random()*80+5;
            Circle aux= new Circle(new Vector2d(x,y),rad,v);
            obstaculos.add(aux);
        }
//        obstaculos.add(obs1);
//	obstaculos.add(obs2);
        dir = new Vector2d(1, 0);
	gravedad = new Vector2d(0, 1);
	dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
	float factor = (float)Math.random()*9 + 1;
	dir.x *= factor;
	dir.y *= factor;		
	this.cir.setAccel(gravedad);
	this.cir.setVelocity(dir);
    }
	public void startStop(String accion) {
            float factor;
            switch(accion) {                
		case "Start":
                    tiempo.start();
                    break;
		case "Stop":
                    tiempo.stop();
                    break;
                case "Reset":
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
                    factor = (float)Math.random()*9 + 1;
                    dir.x *= factor;
                    dir.y *= factor;
                    cir.setPos(new Vector2d(200,100));
                    cir.setAccel(gravedad);
                    cir.setVelocity(dir);
                    this.repaint();
                    break;
                case "Restart":
                    tiempo.stop();
                    dir = new Vector2d(1, 0);
                    dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
                    factor = (float)Math.random()*9 + 1;
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
            switch(accion){
                case "<--":
                    
                    
                    break;
                case "-->":
                    
                    
                    break;
            }
        }
        
    public void paint (Graphics g){
        super.paint(g);//uso el paint de la super clase para que pinte
        //g.setColor(Color.red);
        caja.paint(g);
        g.setColor(Color.red);
	cir.paint(g);
//        for(int i = 0; i < al.size(); i++){
//            aux = (Forma)al.get(i);
//            aux.paint(g);
//        }
        g.setColor(Color.red);
//        g.drawRect(20, 20, 10, 10);
//        g.drawRect(v.ancho-215, 20, 10, 10);
//        g.drawRect(20, v.alto-25, 10, 10);
//        g.drawRect(v.ancho-235, v.alto-45, 10, 10);
        g.setColor(Color.black);
        for(int i=0; i<obstaculos.size();++i){
            obstaculos.get(i).paint(g);
        }
    }
    public void mousePressed(MouseEvent e) {//e informa donde ocurre el evento
		
        if(am.getModo()==1){//crear
            System.out.println("crear");
            if(af.getForma()==1){
                Circle aux= new Circle(new Vector2d(e.getX(),e.getY()),20f,v);
                obstaculos.add(aux);
                repaint();//llama metodo paint
            }
//            if(af.getForma()==2){
//                Cuadrado g=new Cuadrado(e.getX(),e.getY(), 50);
//                al.add(g);
//                repaint();//llama metodo paint
//            }
        }
        if(am.getModo()==2){//borrar
            for(int i=obstaculos.size()-1;i>=0;i--){
              //  if(obstaculos.get(i).tipo()==1){//circulo
                    if(((e.getX()-obstaculos.get(i).pos.x)*(e.getX()-obstaculos.get(i).pos.x)+(e.getY()-obstaculos.get(i).pos.y)*(e.getY()-obstaculos.get(i).pos.y))<=(obstaculos.get(i).getRadius()*obstaculos.get(i).getRadius())){
                        obstaculos.remove(i);
                        repaint(); //llama metodo paint
                        break;
                    }
                }
//                if(al.get(i).tipo()==2){//cuadrado
//                    if((e.getX()>=al.get(i).x() && e.getX()<=al.get(i).x()+60) && (e.getY()>=al.get(i).y() && e.getY()<=al.get(i).y()+50)){
//                        al.remove(i);
//                        repaint();//llama metodo paint
//                        break;
//                    }
//                }
            } 
//        }
            
//            if(am.getModo()==3){
//            
//                System.out.println("jajaja");
//                cir.newPos(new Vector2d(200,100));
//                cir.setAccel(gravedad);
//                cir.setVelocity(dir);
//                System.out.println("jaja");
//             
//            }
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
                System.out.println("chocan");
                cir.setVelocity(Vector2d.vecPorEscalar(resColCircle(cir,obstaculos.get(i)), cir.getRestitucion()));
                
            }
        }
    }
}
