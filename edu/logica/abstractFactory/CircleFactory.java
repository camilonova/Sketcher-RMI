package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Point;

/**
 * Esta clase es una fabrica concreta de <b>Circle</b>, la cual
 * crea elementos de tipo circulo.
 * 
 * @author Camilo Nova.
 */
public class CircleFactory implements AbstractFactory {

	/**
	 * Representa el circulo creado por la fabrica.
	 */
	private Element element;
	
	/**
	 * Constructor de la clase, crea un Elemento de tipo <i>Circle</i>.
	 * @param center			Centro del circulo.
	 * @param circum			Circunferencia del circulo.
	 * @param color				Color del elemento.
	 */
	public CircleFactory(Point center, Point circum, Color color) {
		element = (Element) new Circle(center, circum, color);
	}
	
	/**
	 * Retorna el elemento creado por la fabrica.
	 */
	public Element getElement() {
		return element;
	}
}