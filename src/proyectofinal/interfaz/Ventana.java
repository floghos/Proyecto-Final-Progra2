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
        pDireccion.add(new BotonDireccion("↺"));
        pDireccion.add(new BotonDireccion("↻"));
        controles.add(pDireccion);
        
        //Panel Rapidez
        JLabel rapidez= new JLabel("Rapidez");        
        JPanel pRapidez=new JPanel();
        pRapidez.add(rapidez);
        pRapidez.add(new BotonDireccion("-"));
        pRapidez.add(new BotonDireccion("+"));
        controles.add(pRapidez);
        
        
        //Panel ordenar 1   
        JPanel pOrdenar1=new JPanel(); pOrdenar1.setBackground(Color.LIGHT_GRAY);
        pOrdenar1.setLayout(new BorderLayout());
        pOrdenar1.add(pDireccion,BorderLayout.NORTH);
        pOrdenar1.add(pRapidez,BorderLayout.CENTER);
        controles.add(pOrdenar1);
        
        //Panel Extra
        JPanel pExtra1=new JPanel(); pExtra1.setBackground(Color.LIGHT_GRAY); 
        controles.add(pExtra1);
        
        //Panel Añadir y Quitar Obstaculos
        //jpd contiene jp2 al norte y jp1 al centro          
        JPanel pAñadirQuitar=new JPanel(); pAñadirQuitar.setBackground(Color.LIGHT_GRAY);                
        BotonModo añadir=new BotonModo("Añadir",1);        
        pAñadirQuitar.add(añadir);
        BotonModo quitar=new BotonModo("Quitar",2);        
        pAñadirQuitar.add(quitar);    
        ButtonGroup bGrupo1=new ButtonGroup();
        bGrupo1.add(añadir);bGrupo1.add(quitar);   
        
        //Panel Ordenar 2
        JPanel pOrdenar2=new JPanel(); pOrdenar2.setBackground(Color.LIGHT_GRAY);
        pOrdenar2.setLayout(new BorderLayout()); 
        pOrdenar2.add(pAñadirQuitar,BorderLayout.NORTH);        
        controles.add(pOrdenar2);     
//        JPanel pObstaculo=new JPanel(); pObstaculo.setBackground(Color.LIGHT_GRAY);      
//        BotonFigura circulo=new BotonFigura("Circulo", 1);
//        pObstaculo.add(circulo);
//        pOrdenar2.add(pObstaculo,BorderLayout.CENTER);     
        
        
        //Panel reset-restart
        JPanel pRestartReset=new JPanel(); pRestartReset.setBackground(Color.LIGHT_GRAY);
        pRestartReset.add(new ActionButton("Restart"));
        pRestartReset.add(new ActionButton("Reset"));        
        controles.add(pRestartReset);
		
        //Panel start-stop
        JPanel pStartStop=new JPanel(); pStartStop.setBackground(Color.LIGHT_GRAY);
	pStartStop.add(new ActionButton("Start"));
	pStartStop.add(new ActionButton("Stop"));
        controles.add(pStartStop);
        
        //Panel Extra
        JPanel pExtra2=new JPanel(); pExtra2.setBackground(Color.LIGHT_GRAY); 
        controles.add(pExtra2);
        
        //Panel Ordenar 3
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
