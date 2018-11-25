package proyectofinal.interfaz;

import java.awt.*;
import static java.awt.Font.*;
import java.awt.event.*;
import java.util.Hashtable;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import proyectofinal.physics.Ball;

/**
 * Extensión de JFrame, define una ventana para la aplicación, junto con sus respectivos botones, JPanel, etc...
 */
public class Ventana extends JFrame implements ComponentListener{
    //Define la ventana en la cual trabajara nuestra aplicación.
    public radiusSlider radSlider;
    public GravitySlider slider;
    public int alto;
    public int ancho;
    private JLabel gravLabel;
    private JLabel radLabel;
    private AlmacenTipo at;
    private PanelDibujo dp;
    private AlmacenModo am;
    public Color fondo0;
    public Color fondo1;
    public Color fondo2;
    public Color fondo3;
    public Color fondo4;
    private Color lTit;
    private Color lGrav;
    private Color lVel;
    private Color lAñQui;
    private Color lStaStop;
    private Font letraChica;
    private Font letraMediana;
    private Font letraGrande;
    
    /**
     * Método constructor.
     */
    public Ventana(){
        super("Bounce!");
	this.alto = 600;
	this.ancho = 940;
	fondo0= new Color(0 ,0 ,0);
	fondo1= new Color(0, 11, 41);
	fondo2= new Color(215, 0, 38);
	fondo3= new Color(248, 245, 242);
	fondo4= new Color(237, 184, 61);
	lTit = new Color(248, 245, 242);
	lGrav = new Color(248, 245, 242);
	lVel = Color.WHITE;
	lAñQui = Color.BLACK;
	lStaStop = Color.BLACK;
	letraChica = new Font("Times new roman", PLAIN, 13);
	letraMediana = new Font("Times new roman", PLAIN, 16);
	letraGrande = new Font("Georgia", BOLD, 22);

	this.setLayout(new BorderLayout());
	at=new AlmacenTipo();
	am=new AlmacenModo();
	dp=new PanelDibujo(at,am, this);
	this.addComponentListener(this);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(dp,BorderLayout.CENTER);
        JPanel controles=new JPanel();
        controles.setLayout(new GridLayout(6,1));
        controles.setBackground(new Color(20,20,20));
        
        //Panel Titulo.
        JLabel titulo1=new JLabel("SIMULADOR"); titulo1.setForeground(lTit); titulo1.setFont(letraGrande);
        JLabel titulo2=new JLabel("DE"); titulo2.setForeground(lTit); titulo2.setFont(letraGrande);
        JLabel titulo3=new JLabel("COLISIONES"); titulo3.setForeground(lTit); titulo3.setFont(letraGrande);
        JPanel pTitulo=new JPanel();
        pTitulo.setLayout(new GridLayout(3,1));
        JPanel t1 = new JPanel(); t1.setBackground(fondo0); 
        JPanel t2 = new JPanel(); t2.setBackground(fondo0); 
        JPanel t3 = new JPanel(); t3.setBackground(fondo0); 
        t1.add(titulo1);t2.add(titulo2);t3.add(titulo3);
        pTitulo.add(t1);pTitulo.add(t2);pTitulo.add(t3);
        controles.add(pTitulo);
        
        //Panel Gravedad.
        JPanel pGravedad=new JPanel(); pGravedad.setBackground(fondo1); 
	this.slider = new GravitySlider(0, 50, 10); slider.setBackground(fondo1); slider.setForeground(lGrav);
	Hashtable etiquetas = new Hashtable();
        JLabel LabelG0=new JLabel("0"),LabelG1 =new JLabel("1g"), LabelG2=new JLabel("2g");
        JLabel LabelG3=new JLabel("3g"),LabelG4 =new JLabel("4g"),LabelG5 =new JLabel("5g");
	etiquetas.put(0, LabelG0); LabelG0.setForeground(lGrav); LabelG0.setFont(letraChica);
	etiquetas.put(10, LabelG1); LabelG1.setForeground(lGrav); LabelG1.setFont(letraChica);
	etiquetas.put(20, LabelG2); LabelG2.setForeground(lGrav); LabelG2.setFont(letraChica);
	etiquetas.put(30, LabelG3); LabelG3.setForeground(lGrav); LabelG3.setFont(letraChica);
	etiquetas.put(40, LabelG4); LabelG4.setForeground(lGrav); LabelG4.setFont(letraChica);
	etiquetas.put(50, LabelG5); LabelG5.setForeground(lGrav); LabelG5.setFont(letraChica);
	slider.setLabelTable(etiquetas);
	slider.setMajorTickSpacing(10);
	slider.setMinorTickSpacing(1);
	slider.setPaintTicks(true);
	slider.setPaintLabels(true);        
	slider.setToolTipText("Modifica la gravedad");
	pGravedad.add(slider);

        //Panel Nombre Slider.
        gravLabel=new JLabel("  Gravedad: " + 1f); gravLabel.setForeground(lGrav); gravLabel.setFont(letraChica);
        JPanel pOrdenar=new JPanel(); pOrdenar.setBackground(fondo1);
        pOrdenar.setLayout(new BorderLayout());
        pOrdenar.add(gravLabel,BorderLayout.NORTH);
        pOrdenar.add(pGravedad,BorderLayout.CENTER);
        controles.add(pOrdenar);
        
        //Panel Dirección.
        JLabel direccion= new JLabel("Direccion"); direccion.setForeground(lVel);direccion.setFont(letraChica);
        JPanel pDireccion=new JPanel(); pDireccion.setBackground(fondo2); 
        pDireccion.add(direccion);
        BotonDireccion d1=new BotonDireccion("↺"); d1.setBackground(fondo2); d1.setForeground(lVel);
	d1.setToolTipText("Gira en sentido anti-horario");
        BotonDireccion d2=new BotonDireccion("↻"); d2.setBackground(fondo2); d2.setForeground(lVel);
	d2.setToolTipText("Gira en sentido horario");
        pDireccion.add(d1);
        pDireccion.add(d2);
        controles.add(pDireccion);
        
        //Panel Rapidez.
        JLabel rapidez= new JLabel("Rapidez"); rapidez.setForeground(lVel); rapidez.setFont(letraChica);
        JPanel pRapidez=new JPanel(); pRapidez.setBackground(fondo2);
        pRapidez.add(rapidez);
        BotonDireccion b1=new BotonDireccion("-"); b1.setBackground(fondo2); b1.setForeground(lVel);
        BotonDireccion b2=new BotonDireccion("+"); b2.setBackground(fondo2); b2.setForeground(lVel);        
        pRapidez.add(b1);
        pRapidez.add(b2);
        controles.add(pRapidez);
        
        //Panel Ordenar 1.   
        JLabel velLabel=new JLabel("  Velocidad"); velLabel.setForeground(lVel); velLabel.setFont(letraMediana);
        JPanel pOrdenar1=new JPanel(); pOrdenar1.setBackground(fondo2);
        pOrdenar1.setLayout(new BorderLayout());
        pOrdenar1.add(velLabel,BorderLayout.NORTH);
        pOrdenar1.add(pDireccion,BorderLayout.CENTER);
        pOrdenar1.add(pRapidez,BorderLayout.SOUTH);
        controles.add(pOrdenar1);
        
        //Panel Añadir y Quitar Obstaculos.        
        JLabel añadirQuitar= new JLabel(" Añadir/Quitar Obstaculo"); añadirQuitar.setForeground(lAñQui); añadirQuitar.setFont(letraMediana);
        JPanel pAñadirQuitar=new JPanel(); pAñadirQuitar.setBackground(fondo3);                
        BotonModo añadir=new BotonModo("Añadir",1); añadir.setBackground(fondo3); añadir.setForeground(lAñQui); añadir.setFont(letraChica); 
        BotonModo quitar=new BotonModo("Quitar",2); quitar.setBackground(fondo3); quitar.setForeground(lAñQui); quitar.setFont(letraChica);
        pAñadirQuitar.add(añadir);
        pAñadirQuitar.add(quitar);    
        ButtonGroup bGrupo1=new ButtonGroup();
        bGrupo1.add(añadir);bGrupo1.add(quitar);
        
        //Panel Obstaculo Normal-Potenciador
        JPanel pNormalPoten=new JPanel(); pNormalPoten.setBackground(fondo3);                
        BotonTipo normal=new BotonTipo("Normal",1); normal.setBackground(fondo3); normal.setForeground(lAñQui); normal.setFont(letraChica); 
	normal.setToolTipText("Agrega un obstaculo que disminuye la velocidad con cada colisión");
        BotonTipo potenciador=new BotonTipo("Potenciador",2); potenciador.setBackground(fondo3); potenciador.setForeground(lAñQui); potenciador.setFont(letraChica); 
	potenciador.setToolTipText("Agrega un obstaculo que aumenta la velocidad con cada colisión");
        pNormalPoten.add(normal);
        pNormalPoten.add(potenciador);    
        ButtonGroup bGrupo2=new ButtonGroup();
        bGrupo2.add(normal);bGrupo2.add(potenciador);
        
        //Panel Ordenar 2.
        JPanel pOrdenar2=new JPanel(); pOrdenar2.setBackground(fondo3);
        pOrdenar2.setLayout(new BorderLayout()); 
        pOrdenar2.add(añadirQuitar,BorderLayout.NORTH); 
        pOrdenar2.add(pAñadirQuitar,BorderLayout.CENTER);   
        pOrdenar2.add(pNormalPoten,BorderLayout.SOUTH); 
        controles.add(pOrdenar2);        
    
        //Panel Radio Obstaculos.
        JPanel pRadObs=new JPanel(); pRadObs.setBackground(fondo3);
	this.radSlider = new radiusSlider(50, 800, 200); radSlider.setBackground(fondo3); radSlider.setForeground(lAñQui);
	Hashtable etiquetasRadio = new Hashtable();
        JLabel LabelR0=new JLabel(""), LabelR1=new JLabel("1"), LabelR2=new JLabel("2");
        JLabel LabelR3=new JLabel("3"), LabelR4=new JLabel("4"), LabelR5=new JLabel("5");
        JLabel LabelR6=new JLabel("6"), LabelR7=new JLabel("7"), LabelR8=new JLabel("8");
	etiquetasRadio.put(50, LabelR0); LabelR0.setForeground(lAñQui); LabelR0.setFont(letraChica);
	etiquetasRadio.put(100, LabelR1); LabelR1.setForeground(lAñQui); LabelR1.setFont(letraChica);
	etiquetasRadio.put(200, LabelR2); LabelR2.setForeground(lAñQui); LabelR2.setFont(letraChica);
	etiquetasRadio.put(300, LabelR3); LabelR3.setForeground(lAñQui); LabelR3.setFont(letraChica);
	etiquetasRadio.put(400, LabelR4); LabelR4.setForeground(lAñQui); LabelR4.setFont(letraChica);
	etiquetasRadio.put(500, LabelR5); LabelR5.setForeground(lAñQui); LabelR5.setFont(letraChica);
        etiquetasRadio.put(600, LabelR6); LabelR6.setForeground(lAñQui); LabelR6.setFont(letraChica);
        etiquetasRadio.put(700, LabelR7); LabelR7.setForeground(lAñQui); LabelR7.setFont(letraChica);
        etiquetasRadio.put(800, LabelR8); LabelR8.setForeground(lAñQui); LabelR8.setFont(letraChica);
	radSlider.setLabelTable(etiquetasRadio);         
	radSlider.setMajorTickSpacing(50);
	radSlider.setMinorTickSpacing(10);
	radSlider.setPaintTicks(true);
	radSlider.setPaintLabels(true);
	radSlider.setToolTipText("Modifica radio del obstaculo");
	pRadObs.add(radSlider);
        		
        //Panel Nombre Modificador Radio.
        radLabel=new JLabel("  Radio: " + 20f); radLabel.setForeground(lAñQui); radLabel.setFont(letraChica);
        JPanel pOrdenarRad=new JPanel(); pOrdenarRad.setBackground(fondo3);
        pOrdenarRad.setLayout(new BorderLayout());
        pOrdenarRad.add(radLabel,BorderLayout.CENTER);
        pOrdenarRad.add(pRadObs,BorderLayout.SOUTH);
        controles.add(pOrdenarRad); 
        
        //Panel Restart-Reset.
        JPanel pRestartReset=new JPanel(); pRestartReset.setBackground(fondo4);
        ActionButton r1=new ActionButton("Restart"); r1.setBackground(fondo4); r1.setForeground(lStaStop); r1.setFont(letraChica);
        ActionButton r2=new ActionButton("Reset"); r2.setBackground(fondo4); r2.setForeground(lStaStop); r2.setFont(letraChica);
        r1.setToolTipText("Reiniciar en mismo mapa");
        r2.setToolTipText("Reiniciar en mapa aleatorio");
        pRestartReset.add(r1);
        pRestartReset.add(r2);        
        controles.add(pRestartReset);
		
        //Panel Start-Stop.
        JPanel pStartStop=new JPanel(); pStartStop.setBackground(fondo4);
        ActionButton s1=new ActionButton("Start"); s1.setBackground(fondo4); s1.setForeground(lStaStop); s1.setFont(letraChica);
        ActionButton s2=new ActionButton("Stop"); s2.setBackground(fondo4); s2.setForeground(lStaStop); s2.setFont(letraChica);
	pStartStop.add(s1);
	pStartStop.add(s2);
        controles.add(pStartStop);
		
        //Panel Ordenar 3.
        JPanel pOrdenar3=new JPanel(); pOrdenar3.setBackground(fondo4);
        pOrdenar3.setLayout(new BorderLayout()); 
        pOrdenar3.add(pStartStop,BorderLayout.NORTH); 
        pOrdenar3.add(pRestartReset,BorderLayout.CENTER);
        controles.add(pOrdenar3);
        
        this.add(controles,BorderLayout.WEST);     
        this.setSize(ancho,alto);
        this.setVisible(true);
    }
    
    /**
     * Accessor para dp.
     * @return 
     */
    public PanelDibujo getDp() {
    	return this.dp;
    }

    @Override
    public void componentResized(ComponentEvent ce) {
	Dimension d = new Dimension();
	d = this.getSize();
	this.alto = d.height;
	this.ancho = d.width;
	dp.getCaja().updateSize(this.alto, this.ancho);
	Ball pelota = dp.getPelota();
	float r = pelota.getRadius();
	if (!dp.comienzo) {
            if (pelota.pos.y != 40 || pelota.pos.x != 40) {
                if (pelota.pos.y >= this.alto - 46 - r) {
                    pelota.pos.y = this.alto - 46 - r;
                }
                if (pelota.pos.x > this.ancho - 235 - r) {
                    pelota.pos.x = this.ancho - 235 - r;
                }
                if (pelota.pos.x < 20 + r) {
                    pelota.pos.x = 20 + r;
                }
                if (pelota.pos.y < 20 + r) {
                    pelota.pos.y = 20 + r;
                }
                dp.repaint();
            }
	}
    }
	
    public void componentMoved(ComponentEvent ce) {;}
    public void componentShown(ComponentEvent ce) {;}
    public void componentHidden(ComponentEvent ce) {;}
    
    /**
     * Clase utilizada para realizar las siguientes acciones:
     * Dirección: "↺", "↻"
     * Rapidez: "-", "+"
     */
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
   
    /**
     * Clase utilizada para realizar las siguientes acciones:
     * Modo: "Añadir", "Quitar"
     */
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
    
    /**
     * Clase utitizada para realizar las siguienres acciones:
     * Tipo: "Normal", "Potenciador"
     */
    private class BotonTipo extends JRadioButton implements ActionListener{
	//Estos botones se usaran para seleccionar que figura se desea. 
	//agregar como obstaculo (normal o potenciador).
        private int tipo;
	/**
	 * Método constructor.
	 * @param nom Nombre del botón.
	 * @param tipo (1 = normal, 2 = potenciador).
	 */
        BotonTipo(String nom, int tipo){
            super(nom);
            this.tipo=tipo;
            this.addActionListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            at.setTipo(tipo);
            dp.repaint();
        }
    } 
	
    /**
     * Clase utilizada para realizar las siguientes acciones:
     * Estado: "Start", "Stop", "Restart", "Reset"
     */
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

    /**
     * Clase que define JSlider para modificar la gravedad.
     */
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
            gravLabel.setText("  Gravedad: " + (source.getValue()/10f));
	}
    }
    
    /**
     * Clase que define JSlider para modificar radio de el obstaculo a añadir.
     */
    class radiusSlider extends JSlider implements ChangeListener{
	//Esta clase define un slider que será usado para controlar el radio de los obstaculos.
	/**
	 * Método constructor.
	 * @param min valor mínimo.
	 * @param max valor máximo.
	 * @param ini valor inicial.
	 */
    	public radiusSlider(int min, int max, int ini) {
            super(min, max, ini);
            this.addChangeListener(this);
	}
		
	public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();
            dp.obstacleRadius(source.getValue()/10f);
            radLabel.setText("  Radio: " + (source.getValue()/10f));        
	}
    }
}