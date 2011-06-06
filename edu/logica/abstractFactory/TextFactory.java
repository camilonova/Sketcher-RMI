package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 * Esta clase es una fabrica concreta de <b>Text</b>, la cual
 * crea elementos de tipo texto.
 * 
 * @author Camilo Nova.
 */
public class TextFactory implements AbstractFactory {

	/**
	 * Representa el texto creado por la fabrica.
	 */
	private Element element;
	
	/**
	 * Constructor de la clase, crea un Elemento de tipo <i>Text</i>.
	 * @param font					Fuente del texto.
	 * @param text					Texto a mostrar.
	 * @param position				Posicion del texto.
	 * @param color					Color del texto.
	 * @param bounds				Area comprendida por el texto.
	 */
	public TextFactory(Font font, String text, Point position, Color color, java.awt.Rectangle bounds) {
		element = (Element) new Text(font, text, position, color, bounds);
	}
	
	/**
	 * Retorna el elemento creado por la fabrica.
	 */
	public Element getElement() {
		return element;
	}
}