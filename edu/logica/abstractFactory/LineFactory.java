package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Point;

/**
 * Esta clase es una fabrica concreta de <b>Line</b>, la cual
 * crea elementos de tipo linea.
 * 
 * @author Camilo Nova.
 */
public class LineFactory implements AbstractFactory {

	/**
	 * Representa el elemento creado por la fabrica.
	 */
	private Element element;
	
	/**
	 * Constructor de la clase, crea un Elemento de tipo <i>Line</i>
	 * @param start				Punto inicial.
	 * @param end				Punto final.
	 * @param color				Color del elemento.
	 */
	public LineFactory(Point start, Point end, Color color){
		element = (Element) new Line(start, end, color);
	}
	
	/**
	 * Retorna el elemento creado por la fabrica.
	 */
	public Element getElement() {
		return element;
	}
}