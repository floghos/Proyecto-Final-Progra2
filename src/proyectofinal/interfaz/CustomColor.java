package proyectofinal.interfaz;

import java.awt.Color;

public class CustomColor {
    //Esta clase nos permite conseguir un gradiente de colores entre verde y azul, aún no sabemos si será utilizada.
    private int g;
    private int b;
    private Color color;
    
    /**
     * Metodo constructor, inicializa el color en RGB(0,50,50).
     */
    public CustomColor() {
        this.g = 50;
	this.b = 50;
	this.color = new Color(0, g, b);
    }
    
    /**
     * Entrega el color almacenado despues de modificarlo en update.
     * @return 
     */
    public Color getColor() {
    	update();
	return color;
    }
    /**
     * Modifica el color en el espectro de verdes y azules.
     */
    public void update() {
	if (g < 250 && b == 50) {
            g += 25;
	} else if (g == 250 && b < 250) {
            b += 25;
	} else if (g > 50 && b == 250) {
            g -= 25;
	} else if (g == 50 && b > 50) {
            b -= 25;
	}
	this.color = new Color(0, g, b);
    }
}