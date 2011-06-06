package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Point;

/**
 * Esta clase es una fabrica concreta de <b>Rectangle</b>, la cual
 * crea elementos de tipo Rectangulo.
 * 
 * @author Camilo
 */
public class RectangleFactory implements AbstractFactory {

	/**
	 * Representa el rectangulo creado por la fabrica.
	 */
	private Element element;
	
	/**
	 * Constructor de la clase. Crea un elemento de tipo Rectangulo.
	 * @param start				Punto inicial.
	 * @param end				Punto final.
	 * @param color				Color del elemento.
	 */
	public RectangleFactory(Point start, Point end, Color color) {
		element = (Element) new Rectangle(start, end, color);
	}
	
	/**
	 * Retorna el elemento creado por la fabrica.
	 */
	public Element getElement() {
		return element;
	}
}