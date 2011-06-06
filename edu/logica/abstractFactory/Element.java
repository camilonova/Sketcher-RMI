package edu.logica.abstractFactory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.io.Serializable;

/**
 * Clase abstracta que representa a los elementos de la aplicacion.
 * Implementa la interface <i>Serializable</i>, i provee de metodos
 * genericos para los elementos que hereden de esta clase.
 * 
 * @author Camilo Nova.
 */
public abstract class Element implements Serializable {
  	
  	/**
  	 * Representa el color del elemento.
  	 */
	protected Color color;              
	
	/**
	 * Representa si esta o no seleccionado el elemento.
	 */
	protected boolean highlighted = false;
	
	/**
	 * Representa el punto de origen del elemento.
	 */
	final static Point origin = new Point();      
	
	/**
	 * Representa la posicion del elemento.
	 */
	protected Point position;              
	
	/**
	 * Representa el angulo de rotacion del elemento.
	 */
	protected double angle = 0.0;            		

  	/**
  	 * Constructor de la clase.
  	 * @param color			El color del elemento
  	 */
  	public Element(Color color) {
    	this.color = color;  
  	}
  	
  	/**
  	 * Metodo que retorna el color del elemento
  	 * @return		El color del elemento
  	 */
  	public Color getColor() {
    	return color;  
  	}

  	/**
  	 * Metodo que cambia el estado del elemento a highLighted
  	 * @param highlighted		True si es highLighted, False de lo contrario
  	 */
  	public void setHighlighted(boolean highlighted) {
    	this.highlighted = highlighted;
  	}

  	/**
  	 * Metodo que retorna la posicion del elemento
  	 * @return		Posicion del elemento
  	 */
  	public Point getPosition() {  
    	return position;  
  	}

  	/**
  	 * Metodo que dibuja el elemento en pantalla.
  	 * @param g2D				Tipo de dibujo
  	 * @param element			Elemento a dibujar
  	 */
  	protected void draw(Graphics2D g2D, Shape element) {
    	g2D.setPaint(highlighted ? Color.MAGENTA : color);  // Define el color del elemento
    	AffineTransform old = g2D.getTransform();     		// Almacena la transformacion
    	g2D.translate(position.x, position.y);         		// Mueve a la posicion
    	g2D.rotate(angle);                              	// Rota el elemento
    	g2D.draw(element);                             		// Dibuja el elemento
    	g2D.setTransform(old);                         		// Recupera la transformacion
  	}
	
	/**
	 * Metodo que retorna el area comprendida por los bordes pasados por parametro
	 * @param 	bounds		Bordes del area.
	 * @return	Area conprendida en los bordes.
	 */
  	protected java.awt.Rectangle getBounds(java.awt.Rectangle bounds) { 
	    AffineTransform at = AffineTransform.getTranslateInstance(position.x, position.y);
    	at.rotate(angle);
    	return at.createTransformedShape(bounds).getBounds();
  	}

	/**
	 * Metodo que mueve el elemento a la posicion pasada por parametro
	 * @param deltax		Movimiento en x.
	 * @param deltay		Movimiento en y.
	 */
	public void move(int deltax, int deltay) {
    	position.x += deltax;
    	position.y += deltay;
  	} 

  	/**
  	 * Metodo que rota el elemento un angulo pasado por parametro
  	 * @param angle			Angulo a rotar el elemento.
  	 */
  	public void rotate(double angle) {  
		this.angle += angle;  
	}

  	/**
  	 * Metodo abstracto para obtener el area comprendida por el elemento
  	 * @return		Area comprendida por el elemento
  	 */
  	public abstract java.awt.Rectangle getBounds();
  	
  	/**
  	 * Metodo abstracto que modifica el elemento.
  	 * @param start			Punto de inicio.
  	 * @param last			Punto de meta.
  	 */
  	public abstract void modify(Point start, Point last);
  	
  	/**
  	 * Metodo abstracto que dibuja la figura pasada por parametro
  	 * @param g2D			Figura a dibujar
  	 */
  	public abstract void draw(Graphics2D g2D);
}