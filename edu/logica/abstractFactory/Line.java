package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Esta clase representa el elemento linea en la aplicacion,
 * hereda de la clase abstracta Element y utiliza una instancia
 * de <i>LineSerializable</i> para representar la linea.
 * 
 * @author Camilo Nova.
 */
public class Line extends Element {

	/**
	 * Representa la Linea.
	 */
	private LineSerializable line;		

	/**
	 * Constructor de la clase, genera la linea.
	 * @param start			Punto inicial.
	 * @param end			Punto final.
	 * @param color			Color de la linea.
	 */
    public Line(Point start, Point end, Color color) {
  		super(color);
  		position = start;
  		line = new LineSerializable(origin, new Point(end.x - position.x, end.y - position.y));
	}

	/**
	 * Metodo que retorna el area comprendida por la figura.
	 */
	public java.awt.Rectangle getBounds() {
      	return getBounds(line.getBounds());    
	}

	/**
	 * Metodo que modifica la linea.
	 */
	public void modify(Point start, Point last) {
      	line.x2 = last.x - position.x;
  		line.y2 = last.y - position.y;
	}

	/**
	 * Metodo que dibuja la linea en pantalla.
	 */
	public void draw(Graphics2D g2D)  {
      	draw(g2D, line);                              	// Llamada al metodo de la superclase
	}
}