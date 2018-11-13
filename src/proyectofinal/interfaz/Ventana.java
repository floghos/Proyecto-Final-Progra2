package proyectofinal.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Extensión de JFrame, define una ventana para nuestra aplicación, junto con sus respectivos botones, JPanel, etc...
 */
public class Ventana extends JFrame{
    //Define la ventana en la cual trabajara nuestra aplicación.
    private PanelDibujo dp;
    private AlmacenForma af;
    private AlmacenModo am;
	public GravitySlider slider;
    public final int alto=600;
    public final int ancho=940;
	
    /**
     * Método constructor.
     */
    public Ventana(){
	super("Bounce!");
        this.setLayout(new BorderLayout());
        af=new AlmacenForma();
        am=new AlmacenModo();
        dp=new PanelDibujo(af,am, this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(dp,BorderLayout.CENTER);
        JPanel controles=new JPanel();
        controles.setLayout(new GridLayout(6,1));
        
        //Panel Gravedad.
        JPanel pGravedad=new JPanel(); pGravedad.setBackground(Color.LIGHT_GRAY); 

	this.slider = new GravitySlider(0, 50, 10); 
	Hashtable etiquetas = new Hashtable();
	etiquetas.put(new Integer(0), new JLabel("0"));
	etiquetas.put(new Integer(10), new JLabel("1"));
	etiquetas.put(new Integer(20), new JLabel("2"));
	etiquetas.put(new Integer(30), new JLabel("3"));
	etiquetas.put(new Integer(40), new JLabel("4"));
	etiquetas.put(new Integer(50), new JLabel("5"));
	slider.setLabelTable(etiquetas);
        slider.setBackground(Color.LIGHT_GRAY); 
	slider.setMajorTickSpacing(50);
	slider.setMinorTickSpacing(10);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);
	slider.setToolTipText("Modifica la gravedad");
	pGravedad.add(slider);
        controles.add(pGravedad);
		
        //Panel Dirección.
        JLabel direccion= new JLabel("Direccion"); 
        JPanel pDireccion=new JPanel(); pDireccion.setBackground(Color.LIGHT_GRAY); 
        pDireccion.add(direccion);
        pDireccion.add(new BotonDireccion("↺"));
        pDireccion.add(new BotonDireccion("↻"));
        controles.add(pDireccion);
        
        //Panel Rapidez.
        JLabel rapidez= new JLabel("Rapidez");        
        JPanel pRapidez=new JPanel(); pRapidez.setBackground(Color.LIGHT_GRAY); 
        pRapidez.add(rapidez);
        pRapidez.add(new BotonDireccion("-"));
        pRapidez.add(new BotonDireccion("+"));
        controles.add(pRapidez);
        
        //Panel Ordenar 1.   
        JPanel pOrdenar1=new JPanel(); pOrdenar1.setBackground(Color.LIGHT_GRAY);
        pOrdenar1.setLayout(new BorderLayout());
        pOrdenar1.add(pDireccion,BorderLayout.NORTH);
        pOrdenar1.add(pRapidez,BorderLayout.CENTER);
        controles.add(pOrdenar1);
        
        //Panel Extra.
        JPanel pExtra1=new JPanel(); pExtra1.setBackground(Color.LIGHT_GRAY); 
        controles.add(pExtra1);
        
        //Panel Añadir y Quitar Obstaculos.        
        JLabel añadirQuitar= new JLabel(" Añadir/Quitar Obstaculo"); 
        JPanel pAñadirQuitar=new JPanel(); pAñadirQuitar.setBackground(Color.LIGHT_GRAY);                
        BotonModo añadir=new BotonModo("Añadir",1);        
        pAñadirQuitar.add(añadir);
        BotonModo quitar=new BotonModo("Quitar",2);        
        pAñadirQuitar.add(quitar);    
        ButtonGroup bGrupo1=new ButtonGroup();
        bGrupo1.add(añadir);bGrupo1.add(quitar);   
        
        //Panel obstaculo normal-potenciador
        JPanel pNormalPoten=new JPanel(); pNormalPoten.setBackground(Color.LIGHT_GRAY);                
        BotonFigura normal=new BotonFigura("Normal",1);        
        pNormalPoten.add(normal);
        BotonFigura potenciador=new BotonFigura("Potenciador",2);        
        pNormalPoten.add(potenciador);    
        ButtonGroup bGrupo2=new ButtonGroup();
        bGrupo2.add(normal);bGrupo2.add(potenciador);
        
        //Panel Ordenar 2.
        JPanel pOrdenar2=new JPanel(); pOrdenar2.setBackground(Color.LIGHT_GRAY);
        pOrdenar2.setLayout(new BorderLayout()); 
        pOrdenar2.add(añadirQuitar,BorderLayout.NORTH); 
        pOrdenar2.add(pAñadirQuitar,BorderLayout.CENTER);   
        pOrdenar2.add(pNormalPoten,BorderLayout.SOUTH); 
        controles.add(pOrdenar2);        
               
        //Panel Extra.
        JPanel pExtra2=new JPanel(); pExtra2.setBackground(Color.LIGHT_GRAY); 
        controles.add(pExtra2);
		
        //Panel reset-restart.
        JPanel pRestartReset=new JPanel(); pRestartReset.setBackground(Color.LIGHT_GRAY);
        pRestartReset.add(new ActionButton("Restart"));
        pRestartReset.add(new ActionButton("Reset"));        
        controles.add(pRestartReset);
		
        //Panel start-stop.
        JPanel pStartStop=new JPanel(); pStartStop.setBackground(Color.LIGHT_GRAY);
	pStartStop.add(new ActionButton("Start"));
	pStartStop.add(new ActionButton("Stop"));
        controles.add(pStartStop);
		
        //Panel Ordenar 3.
        JPanel pOrdenar3=new JPanel(); pOrdenar3.setBackground(Color.LIGHT_GRAY);
        pOrdenar3.setLayout(new BorderLayout()); 
        pOrdenar3.add(pStartStop,BorderLayout.NORTH); 
        pOrdenar3.add(pRestartReset,BorderLayout.CENTER);
        controles.add(pOrdenar3);
        		
        this.add(controles,BorderLayout.WEST);     
        this.setSize(ancho,alto);
        this.setVisible(true);
    }
        
    private class BotonDireccion extends JButton implements ActionListener{
        public String direccion;
	/**
	 * Método constructor.
	 * @param direccion Nombre del boton e identificador de acción.
	 */
        public BotonDireccion(String direccion){
            super(direccion);
            this.direccion=direccion;
            this.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            dp.velocidadInicial(direccion);
        }        
    }
   
    private class BotonModo extends JRadioButton implements ActionListener{
        private int modo;
	/**
	 * Método constructor.
	 * @param nom nombre del botón.
	 * @param modo (1 = añadir, 2 = quitar). 
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
	//Estos botones se usaran para seleccionar que figura se desea. 
	//agregar como obstaculo (circulo o rectangulo).
        private int forma;
	/**
	 * Método constructor.
	 * @param nom Nombre del botón.
	 * @param forma (1 = circulo, 2 = rectángulo).
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
	//Estos botones permiten dar comienzo, pausar, reanudar, reiniciar y reconfigurar la simulación.
    	private String action;
		
	/**
	 * Método constructor.
	 * @param nomnom Nombre del boton e identificador de la accion (Start, Stop, Reset, Restart).
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
	//Esta clase define un slider que será usado para controlar la gravedad en la simulación.
	/**
	 * Método constructor.
	 * @param min valor mínimo.
	 * @param max valor máximo.
	 * @param ini valor inicial.
	 */
    	public GravitySlider(int min, int max, int ini) {
            super(min, max, ini);
            this.addChangeListener(this);
		}
		
		public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            dp.modGrav(source.getValue()/10f);
		}
    }
}