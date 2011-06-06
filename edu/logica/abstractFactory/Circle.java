package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Esta clase representa el elemento circulo en la aplicacion,
 * hereda de la clase abstracta Element y utiliza una instancia
 * de <i>CircleSerializable</i> para representar el circulo.
 * 
 * @author Camilo Nova.
 */
public class Circle extends Element {

	/**
	 * Representa el circulo.
	 */
	private CircleSerializable circle;				

	/**
	 * Constructor de la clase. Genera el circulo.
	 * @param center			Centro del circulo.
	 * @param circum			Circunferencia del circulo.
	 * @param color				Color del circulo.
	 */
	public Circle(Point center, Point circum, Color color) {
  		super(color);
	  
  		/*
  		 * Calculamos el radio del circulo
  		 */
  		double radius = center.distance(circum);
  		position = new Point(center.x - (int)radius, center.y - (int)radius);
  
  		circle = new CircleSerializable(origin.x, origin.y, 2.*radius, 2.*radius );
	}

	/**
	 * Metodo que retorna el are comprendida por la figura.
	 */
	public java.awt.Rectangle getBounds() { 
  		return getBounds(circle.getBounds());  
	}

	/**
	 * Metodo que modifica el tamano o la ubicacion del circulo.
	 */
	public void modify(Point center, Point circum) {
  		double radius = center.distance(circum);
  		position.x = center.x - (int)radius;
  		position.y = center.y - (int)radius;
  		circle.width = circle.height = 2*radius;
	}

	/**
	 * Metodo que dibuja el circulo en pantalla.
	 */
	public void draw(Graphics2D g2D)  {
  		draw(g2D, circle);                              // Llamada al metodo principal de la clase.
	}
}