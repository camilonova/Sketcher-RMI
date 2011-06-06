package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Esta clase representa el elemento rectangulo en la aplicacion,
 * hereda de la clase abstracta Element y utiliza una instancia
 * de <i>RectangleSerializable</i> para representar el rectangulo.
 * 
 * @author Camilo Nova.
 */
public class Rectangle extends Element {
	
	/**
	 * Representa el Rectangulo.
	 */
	private RectangleSerializable rectangle;				

	/**
	 * Constructor de la clase. Genera el rectangulo.
	 * @param start			Punto inicial.
	 * @param end			Punto final.
	 * @param color			Color de la linea.
	 */
	public Rectangle(Point start, Point end, Color color) {
  		super(color);
  		position = new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
  		rectangle = new RectangleSerializable(origin.x, origin.y, Math.abs(start.x - end.x), Math.abs(start.y - end.y));
	}

	/**
	 * Metodo que retorna el area comprendida por la figura.
	 */
	public java.awt.Rectangle getBounds() { 
  		return getBounds(rectangle.getBounds());  
	}

	/**
	 * Metodo que modifica el rectangulo.
	 */
	public void modify(Point start, Point last) {
  		position.x = Math.min(start.x, last.x);
  		position.y = Math.min(start.y, last.y);
  		rectangle.width = Math.abs(start.x - last.x);
  		rectangle.height = Math.abs(start.y - last.y);
	}

	/**
	 * Metodo que dibuja el rectangulo en pantalla.
	 */
	public void draw(Graphics2D g2D)  {
  		draw(g2D, rectangle);                              // Llama al metodo de la superclase   
	}
}