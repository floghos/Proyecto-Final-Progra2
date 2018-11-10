package proyectofinal.interfaz;

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
	
	/**
	 * Método constructor
	 */
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
        
        //Panel Gravedad
        JPanel pGravedad=new JPanel(); pGravedad.setBackground(Color.LIGHT_GRAY); 
		GravitySlider slider = new GravitySlider(0, 10, 1);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setToolTipText("Modifica la gravedad");
		pGravedad.add(slider);
        controles.add(pGravedad);
		
        //Panel Dirección
        JLabel direccion= new JLabel("Direccion");        
        JPanel pDireccion=new JPanel();
        pDireccion.add(direccion);
        pDireccion.add(new BotonDireccion("<--"));
        pDireccion.add(new BotonDireccion("-->"));
        controles.add(pDireccion);
        
        //Panel Extra
        JPanel pExtra1=new JPanel(); pExtra1.setBackground(Color.LIGHT_GRAY); 
        controles.add(pExtra1);
        
        //Panel Añadir y Quitar Obstaculos
        //jpd contiene jp2 al norte y jp1 al centro
        JPanel pOrdenar1=new JPanel(); pOrdenar1.setBackground(Color.LIGHT_GRAY);
        pOrdenar1.setLayout(new BorderLayout());   
        JPanel pAñadirQuitar=new JPanel(); pAñadirQuitar.setBackground(Color.LIGHT_GRAY);                
        BotonModo añadir=new BotonModo("Añadir",1);        
        pAñadirQuitar.add(añadir);
        BotonModo m2=new BotonModo("Quitar",2);        
        pAñadirQuitar.add(m2);                
        pOrdenar1.add(pAñadirQuitar,BorderLayout.NORTH);        
        ButtonGroup bm=new ButtonGroup();
        bm.add(añadir);bm.add(m2);        
        JPanel jp1=new JPanel(); jp1.setBackground(Color.LIGHT_GRAY);      
        BotonFigura x=new BotonFigura("Circulo", 1);
        jp1.add(x);
        pOrdenar1.add(jp1,BorderLayout.CENTER);     
        controles.add(pOrdenar1);
        
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
		
		/**
		 * Método constructor
		 * @param nom nombre del botón
		 * @param modo (1 = añadir, 2 = quitar) 
		 */
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
		
		/**
		 * Método constructor
		 * @param nom Nombre del botón
		 * @param forma (1 = circulo, 2 = rectángulo)
		 */
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
		
		/**
		 * Método constructor
		 * @param nomnom Nombre del boton e identificador de la accion (Start, Stop, Reset, Restart)
		 */
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
		/**
		 * Método constructor
		 * @param min valor mínimo
		 * @param max valor máximo
		 * @param ini valor inicial
		 */
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
