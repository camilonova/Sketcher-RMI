package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * Esta clase representa el elemento texto en la aplicacion,
 * hereda de la clase abstracta Element y utiliza una instancia
 * de <i>TextSerializable</i> para representar el texto.
 * 
 * @author Camilo Nova.
 */
public class Text extends Element {

	/**
	 * Representa la fuente del texto.
	 */
	private Font font;                
    
	/**
	 * Rrepresenta el texto a mostrar.
	 */
	private String text;          
	
	/**
	 * Representa el area del texto.
	 */
	private java.awt.Rectangle bounds;

		/**
		 * Constructor de la clase. Genera el texto.
		 * @param font					Fuente del texto.
		 * @param text					Texto.
		 * @param position				Posicion.
		 * @param color					Color.
		 * @param bounds				Area.
		 */
	public Text(Font font, String text, Point position, Color color, java.awt.Rectangle bounds) {
		super(color);
  		this.font = font;
  		this.position = position;
  		this.position.y -= (int)bounds.getHeight();
  		this.text = text;
  		this.bounds = new java.awt.Rectangle(origin.x, origin.y, bounds.width, bounds.height);
	}

	/**
	 * Metodo que retorna el area del texto
	 */
	public java.awt.Rectangle getBounds() {
      	return getBounds(bounds);
	}

	/**
	 * Metodo que dibuja el texto.
	 */
	public void draw(Graphics2D g2D)  {
      	g2D.setPaint(highlighted ? Color.magenta : color);      // Determina el color
	  	Font oldFont = g2D.getFont();                			// Almacena la fuente
  		g2D.setFont(font);                          			// Determina la nueva fuente

  		AffineTransform old = g2D.getTransform();      			// Almacena la transformacion
  		g2D.translate(position.x, position.y);         			// Determina la posicion
  		g2D.rotate(angle);                              		// Rota el angulo determinado
  		g2D.drawString(text, origin.x, origin.y+(int)bounds.getHeight());
  		g2D.setTransform(old);                         			// Recupera la transformacion

  		g2D.setFont(oldFont);                        			// Recupera la fuente
	}

	/**
	 * Metodo que modifica el texto, no requiere codigo pero sin embargo hay
	 * que implementarlo debido a la superclase.
	 */
	public void modify(Point start, Point last) {
    }
}