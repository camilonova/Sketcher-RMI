package edu.logica.builder;

import java.awt.Color;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * Esta clase representa un objeto ToolBarBuilder el cual es
 * la barra de herramientas de la aplicacion, la cual es construida
 * mediante el llamado al constructor <i>ToolBarBuilder()</i>
 * y de la cual se toma su referencia llamando al metodo <i>getComponente()</i>
 * implementado de la interface <i>AbstractBuilder</i>.<p>
 * 
 * @author Camilo Nova.
 */
public class ToolBarBuilder extends JToolBar implements AbstractBuilder {

	/**
	 * Representa el menu de la aplicacion.
	 */
	private MenuBarBuilder menuBar;
	
	/**
	 * Constructor de la clase, agrega los botones a la barra
	 * de menu.
	 * @param menuBar		Menu de la aplicacion.
	 */
	public ToolBarBuilder(MenuBarBuilder menuBar) {
    	this.menuBar = menuBar;
		
		setFloatable(false);
    	addSeparator();
    	addToolBarButton(menuBar.newAction);
    	addToolBarButton(menuBar.openAction);
    	addToolBarButton(menuBar.saveAction);
    	addToolBarButton(menuBar.printAction);
    
    	addSeparator();
    	addToolBarButton(menuBar.lineAction);
    	addToolBarButton(menuBar.rectangleAction);
    	addToolBarButton(menuBar.circleAction);
    	addToolBarButton(menuBar.curveAction);
    	addToolBarButton(menuBar.textAction);

    	addSeparator();
    	addToolBarButton(menuBar.redAction);
    	addToolBarButton(menuBar.yellowAction);
    	addToolBarButton(menuBar.greenAction);
    	addToolBarButton(menuBar.blueAction);
    	addSeparator();                            // Space at the end

    	setBorder(BorderFactory.createCompoundBorder(       // Toolbar border
   	              BorderFactory.createLineBorder(Color.darkGray),
                  BorderFactory.createEmptyBorder(2,2,4,2)));   

	}
	
	/**
	 * Retorna el componente de tipo ToolBar creado por
	 * el constructor.
	 * @return Componente para agregar.
	 */
	public AbstractBuilder getComponente() {
		return this;
	}

	/**
  	 * Metodo que agrega un boton a la barra de herramientas
  	 * mediante una accion y retorna el boton construido.
  	 * @param action					Accion a agregar al boton.
  	 * @return	Boton agregado a la barra.
  	 */
  	private JButton addToolBarButton(Action action) {
	    JButton button = add(action);
    	button.setBorder(BorderFactory.createRaisedBevelBorder());
    	return button;
  	}
}