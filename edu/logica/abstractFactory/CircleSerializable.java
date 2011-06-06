package edu.logica.abstractFactory;

import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
 * Esta clase es un desarrollo especial de <b>Camilo Nova</b> para implementar
 * en el API de java la serializacion de los objetos ya que esta clase hereda
 * de <i>Ellipse2D.Double</i> e implementa la interface <i>Serializable</i>.
 * 
 * @author Camilo Nova.
 */
public class CircleSerializable extends Ellipse2D.Double implements Serializable {
	
	/**
	 * Constructor de la clase, le envia los parametros recibidos a la
	 * super clase para crear el elemento.
	 * @param arg0				Coordenada en x del elemento.
	 * @param arg1				Coordenada en y del elemento.
	 * @param arg2				La anchura del elemento.
	 * @param arg3				La altura del elemento.
	 */
	public CircleSerializable(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}