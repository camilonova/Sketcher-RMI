package edu.logica.abstractFactory;

/**
 * Esta interface define los metodos para la creacion de elementos
 * en la aplicacion mediante el uso del patron <b>Abstract Factory</b>.<p>
 * 
 * La construccion de los componentes debe hacerse mediante el
 * llamado del contructor de las fabricas concretas que implementan
 * esta interface para luego obtener el producto mediante el
 * llamado al metodo <i>getElement()</i> de la interface.
 * 
 * <pre>El cliente de esta clase es SketchView</pre>
 * @author Camilo
 */
public interface AbstractFactory {
	
	public Element getElement();

}
