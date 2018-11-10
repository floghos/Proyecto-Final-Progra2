package proyectofinal.interfaz;

import java.awt.Color;

public class CustomColor {
	private int g;
	private int b;
	private Color color;
	
	public CustomColor() {
		this.g = 50;
		this.b = 50;
		this.color = new Color(0, g, b);
	}
	
	public Color getColor() {
		update();
		return color;
	}
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
