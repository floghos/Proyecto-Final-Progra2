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

public class PanelDibujo extends JPanel implements MouseListener,ActionListener {
    //ArrayList<Forma> al;
    AlmacenForma af;
    AlmacenModo am;
    Caja caja;
    Timer tiempo;
    Box box1;//testing
    int fps;//cuadros por segundo
    Ventana v;
    Circle cir;
	
    public PanelDibujo(AlmacenForma af, AlmacenModo am, Ventana v){
        //al = new ArrayList();
        this.setBackground(Color.cyan);
        this.af = af;
        this.am = am;
        this.v=v;
        this.addMouseListener(this);
	this.caja = new Caja(v.ancho, v.alto);
        this.fps = 15;
        tiempo = new Timer(1000/fps,null);
        tiempo.addActionListener(this);
//      tiempo.start();
	//this.box1 = new Box(new Vector2d(200, 100), 20, 20);//testing
        cir= new Circle(new Vector2d(200, 100),20f,v);
	
	Vector2d dir = new Vector2d(1, 0);
	Vector2d gravedad = new Vector2d(0, 1);
	dir = Vector2d.rotateVector(dir, Math.random()*2*Math.PI);
	float factor = (float)Math.random()*9 + 1;
	dir.x *= factor;
	dir.y *= factor;		
	this.cir.setAccel(gravedad);
	this.cir.setVelocity(dir);
    }
	public void startStop(String accion) {
            switch(accion) {
		case "Start":
                    tiempo.start();
                    break;
		case "Stop":
                    tiempo.stop();
                    break;
            }
	}
    public void paint (Graphics g){
        super.paint(g);//uso el paint de la super clase para que pinte
        caja.paint(g);
	cir.paint(g);
//        for(int i = 0; i < al.size(); i++){
//            aux = (Forma)al.get(i);
//            aux.paint(g);
//        }
        g.setColor(Color.red);
        g.drawRect(20, 20, 10, 10);
        g.drawRect(v.ancho-235, 20, 10, 10);
        g.drawRect(20, v.alto-45, 10, 10);
        g.drawRect(v.ancho-235, v.alto-45, 10, 10);
    }
    public void mousePressed(MouseEvent e) {//e informa donde ocurre el evento
		
//        if(am.getModo()==1){//crear
//            if(af.getForma()==1){
//                Circulo f=new Circulo(e.getX(),e.getY(), 50);
//                al.add(f);
//                repaint();//llama metodo paint
//            }
//            if(af.getForma()==2){
//                Cuadrado g=new Cuadrado(e.getX(),e.getY(), 50);
//                al.add(g);
//                repaint();//llama metodo paint
//            }
//        }
//        if(am.getModo()==2){//borrar
//            for(int i=al.size()-1;i>=0;i--){
//                if(al.get(i).tipo()==1){//circulo
//                    if(((e.getX()-al.get(i).x()-25)*(e.getX()-al.get(i).x()-25)+(e.getY()-al.get(i).y()-25)*(e.getY()-al.get(i).y()-25))<=(25*25)){
//                        al.remove(i);
//                        repaint(); //llama metodo paint
//                        break;
//                    }
//                }
//                if(al.get(i).tipo()==2){//cuadrado
//                    if((e.getX()>=al.get(i).x() && e.getX()<=al.get(i).x()+60) && (e.getY()>=al.get(i).y() && e.getY()<=al.get(i).y()+50)){
//                        al.remove(i);
//                        repaint();//llama metodo paint
//                        break;
//                    }
//                }
//            } 
//        }
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
    }
}
