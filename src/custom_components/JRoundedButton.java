package custom_components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class JRoundedButton extends JButton{

	private int radius;

	public JRoundedButton(String text, int radius) {
		
		super(text);
		this.radius = radius;
		
		setOpaque(false);
		setContentAreaFilled(false);
		setBorder(new RoundedBorder(radius));
		setFocusPainted(false);
		
	}
	
	public JRoundedButton(String text) {
		this(text, 10);
	}
	
	public JRoundedButton(int radius) {
		this("", radius);
	}
	
	public JRoundedButton() {
		this("" , 10);
	}

	
	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g.create();
		
		// Anti-Aliasing per bordi lisci
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Colore
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
		
		graphics.dispose();
		
		// Disegna tutto quello che sta nel bottone
		super.paintComponent(g);
	}
	
}
