package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Esta clase representa el elemento curva en la aplicacion,
 * hereda de la clase abstracta Element y utiliza una instancia
 * de <i>CurveSerializable</i> para representar la curva.
 * 
 * @author Camilo Nova.
 */
public class Curve extends Element {

	/**
	 * Representa la curva.
	 */
	private CurveSerializable curve;	

	/**
	 * Constructor de la clase. Genera la curva.
	 * @param start				Punto inicial.
	 * @param next				Punto final.
	 * @param color				Color de la curva
	 */
	public Curve(Point start, Point next, Color color) {
		super(color);
  		curve = new CurveSerializable();
  		position = start;
  		curve.moveTo(origin.x, origin.y);
  		curve.lineTo(next.x - position.x, next.y - position.y);
	}

	/**
	 * Metodo que modifica el tamano o la ubicacion de la curva.
	 */
	public void modify(Point start, Point next) {
  		curve.lineTo(next.x - start.x, next.y - start.y);
	}

	/**
	 * Metodo que retorna el area comprendida por la curva.
	 */
	public java.awt.Rectangle getBounds() { 
  		return getBounds(curve.getBounds());  
	}

	/**
	 * Metodo que dibuja la curva en pantalla.
	 */
	public void draw(Graphics2D g2D)  {
  		draw(g2D, curve);                              	// Llamada al metodo de la superclase.
	}
}