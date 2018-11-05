package proyectofinal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ventana extends JFrame{
    public PanelDibujo dp;
    AlmacenForma af;
    AlmacenModo am;
    public final int alto=600;
    public final int ancho=940;

	public Ventana(){
	super("Bounce!");
        this.setLayout(new BorderLayout());
        af=new AlmacenForma();
        am=new AlmacenModo();
        dp=new PanelDibujo(af,am, this);//apunta a panel de dibujo
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar aplicacion
        this.add(dp,BorderLayout.CENTER);//se agrega al centro
        JPanel controles=new JPanel();//panel instanciado directamente
        controles.setLayout(new GridLayout(6,1));//panel con filas y columnas
        
        JPanel ja=new JPanel(); ja.setBackground(Color.LIGHT_GRAY); 
	GravitySlider slider = new GravitySlider(0, 10, 1);
	slider.setMajorTickSpacing(50);
	slider.setMinorTickSpacing(10);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	slider.setToolTipText("Modifica la gravedad");
	ja.add(slider);
		
        JPanel jb=new JPanel(); jb.setBackground(Color.LIGHT_GRAY); 
        controles.add(ja);
        controles.add(jb);
        
        //boton forma
        this.add(controles,BorderLayout.WEST);
        JPanel jp1=new JPanel(); jp1.setBackground(Color.LIGHT_GRAY);      
        BotonFigura x=new BotonFigura("Circulo", 1);
        jp1.add(x);
//        BotonFigura y=new BotonFigura("Cuadrado", 2);
//        jp1.add(y);
        ButtonGroup bf=new ButtonGroup();//si apreta uno se descelecciona otro
        bf.add(x); //bf.add(y);
        controles.add(jp1);
        
     
        //boton modo
        JPanel jp2=new JPanel(); jp2.setBackground(Color.LIGHT_GRAY);     //jp3.add(new JButton("3"));
        BotonModo m1=new BotonModo("Añadir",1);
        jp2.add(m1);
        BotonModo m2=new BotonModo("Quitar",2);
        jp2.add(m2);
//        BotonModo m3=new BotonModo("Reset",3);
//        jp2.add(m3);
        ButtonGroup bm=new ButtonGroup();//si apreta uno se descelecciona otro
        bm.add(m1);bm.add(m2);
        controles.add(jp2);

        JPanel jc=new JPanel(); jc.setBackground(Color.LIGHT_GRAY);
        jc.add(new ActionButton("Reset"));
        jc.add(new ActionButton("Restart"));
        controles.add(jc);
		
        JPanel jd=new JPanel(); jd.setBackground(Color.LIGHT_GRAY);
	jd.add(new ActionButton("Start"));
	jd.add(new ActionButton("Stop"));
//        jc.add(new ActionButton("Reset"));
        controles.add(jd);
                
        this.setSize(ancho,alto);
        this.setVisible(true);
    }
    
    private class BotonModo extends JRadioButton implements ActionListener{
        private int modo;
        BotonModo(String nom, int modo){
            super(nom);
            this.addActionListener(this);
            this.modo=modo;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            am.setModo(modo);
            dp.repaint();
//            System.out.println(modo);
        }
    } 
     
    private class BotonFigura extends JRadioButton implements ActionListener{
        private int forma;
        BotonFigura(String nom, int forma){
            super(nom);
            this.forma=forma;
            this.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            af.setForma(forma);
            dp.repaint();
        }
    } 
	
    private class ActionButton extends JButton implements ActionListener {
    	private String action;
    	public ActionButton (String nomnom) {
            super(nomnom);
            this.action = nomnom;
            this.addActionListener(this);
        }
	
	@Override
	public void actionPerformed(ActionEvent e) {
            dp.startStop(action);
	}
    }
	
    class GravitySlider extends JSlider implements ChangeListener{
    	public GravitySlider(int min, int max, int ini) {
            super(min, max, ini);
            this.addChangeListener(this);
	}
	public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            System.out.println("Falta implementar");
            //dp.changeSpeed(source.getValue()); //falta implementar
	}
    }
}
