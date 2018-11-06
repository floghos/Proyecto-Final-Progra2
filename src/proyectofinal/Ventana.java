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
        controles.add(ja);
		
        //direccion
        JLabel direccion= new JLabel("Direccion");        
        JPanel dir=new JPanel();
        dir.add(direccion);
        dir.add(new BotonDireccion("<--"));
        dir.add(new BotonDireccion("-->"));
        controles.add(dir);
        
        //nada
        JPanel jb=new JPanel(); jb.setBackground(Color.LIGHT_GRAY); 
        controles.add(jb);
        
        //jpd contiene agregar y quitar obstaculos
        //jpd contiene jp2 al norte y jp1 al centro
        JPanel jpd=new JPanel(); jpd.setBackground(Color.LIGHT_GRAY);
        jpd.setLayout(new BorderLayout());   
        JPanel jp2=new JPanel(); jp2.setBackground(Color.LIGHT_GRAY);                
        BotonModo m1=new BotonModo("Añadir",1);        
        jp2.add(m1);
        BotonModo m2=new BotonModo("Quitar",2);        
        jp2.add(m2);                
        jpd.add(jp2,BorderLayout.NORTH);        
        ButtonGroup bm=new ButtonGroup();
        bm.add(m1);bm.add(m2);        
        JPanel jp1=new JPanel(); jp1.setBackground(Color.LIGHT_GRAY);      
        BotonFigura x=new BotonFigura("Circulo", 1);
        jp1.add(x);
        jpd.add(jp1,BorderLayout.CENTER);     
        controles.add(jpd);
        
        //reset y restart
        JPanel jc=new JPanel(); jc.setBackground(Color.LIGHT_GRAY);
        jc.add(new ActionButton("Restart"));
        jc.add(new ActionButton("Reset"));        
        controles.add(jc);
		
        // start stop
        JPanel jd=new JPanel(); jd.setBackground(Color.LIGHT_GRAY);
	jd.add(new ActionButton("Start"));
	jd.add(new ActionButton("Stop"));
        controles.add(jd);
        
        this.add(controles,BorderLayout.WEST);     
        this.setSize(ancho,alto);
        this.setVisible(true);
    }
        
    private class BotonDireccion extends JButton implements ActionListener{
        public String direccion;
        public BotonDireccion(String direccion){
            super(direccion);
            this.direccion=direccion;
            this.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            dp.direccionInicial(direccion);
        }        
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
            dp.accion(action);
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
