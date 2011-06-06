package edu.logica.builder;

import java.io.Serializable;

import edu.repositorio.Constants;

/**
 * Interface que define los metodos para la creacion
 * secuencial de la GUI de la aplicacion utilizando el
 * patron <b>Builder</b>.<p>
 * 
 * La construccion de los componentes se debe hacer mediante
 * la interface, luego de contruidos han de ser convertidos
 * a su tipo original para llamar a sus metodos propios de
 * cada clase.
 * 
 *<pre>El cliente de este patron es la clase SketchFrame</pre>
 * 
 * @author Camilo Nova
 */
public interface AbstractBuilder extends Serializable, Constants {
	
	/**
	 * Este metodo es el encargado de retornar el componente
	 * contruido para la clase cliente mediante el constructor.
	 * @return Componente construido.
	 */
	public AbstractBuilder getComponente();

}
