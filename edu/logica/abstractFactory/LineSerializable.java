package edu.logica.abstractFactory;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * Esta clase es un desarrollo especial de <b>Camilo Nova</b> para implementar
 * en el API de java la serializacion de los objetos ya que esta clase hereda
 * de <i>Line2D.Double</i> e implementa la interface <i>Serializable</i>.
 * 
 * @author Camilo Nova.
 */
public class LineSerializable extends Line2D.Double implements Serializable {
	
	/**
	 * Constructor de la clase, le envia los parametros pasados por
	 * parametro a la super clase para crear el elemento.
	 * @param arg0			Punto de inicio de la linea.
	 * @param arg1			Punto final de la linea.
	 */
	public LineSerializable(Point2D arg0, Point2D arg1) {
		super(arg0, arg1);
	}
}
