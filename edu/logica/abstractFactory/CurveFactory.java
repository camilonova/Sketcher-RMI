package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Point;

/**
 * Esta clase es una fabrica concreta de <b>Curve</b>, la cual
 * crea elementos de tipo curva.
 * 
 * @author Camilo Nova.
 */
public class CurveFactory implements AbstractFactory {

	/**
	 * Representa la curva creada por la fabrica.
	 */
	private Element element;
	
	/**
	 * Constructor de la clase, crea un elemento de tipo <i>Curve</i>.
	 * @param start				Punto inicial.
	 * @param end				Punto final.
	 * @param color				Color del elemento.
	 */
	public CurveFactory(Point start, Point end, Color color) {
		element = (Element) new Curve(start, end, color);
	}
	
	/**
	 * Retorna el elemento creado por la fabrica.
	 */
	public Element getElement() {
		return element;
	}
}