package edu.logica.builder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Esta clase representa un objeto StatusBar el cual es la
 * barra de estado de la aplicacion, la cual es construida
 * mediante el llamado al constructor <i>StatusBarBuilder()</i>
 * y de la cual se toma su referencia llamando al metodo <i>getComponente()</i>
 * implementado de la interface <i>AbstractBuilder</i>.<p>
 * @author Camilo
 */
public class StatusBarBuilder extends JPanel implements AbstractBuilder {

	/**
	 * Representa el area donde se muestra el color.
	 */
	private StatusPane colorPane = new StatusPane("BLUE");
	
	/**
	 * Representa el area donde se muestra el tipo.
	 */
	private StatusPane typePane = new StatusPane("LINE");

	/**
	 * Constructor de la clase, define los parametros de la
	 * barra de estatus.
	 */
	public StatusBarBuilder() {
	    setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));
    	setBackground(Color.LIGHT_GRAY);
    	setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    	setColorPane(DEFAULT_ELEMENT_COLOR);
    	setTypePane(DEFAULT_ELEMENT_TYPE);
    	add(colorPane);                      // Add color pane to status bar
    	add(typePane);                       // Add type pane to status bar
  	}

	/**
	 * Retorna el componente de tipo StatusBar creado por
	 * el constructor.
	 * @return Componente para agregar.
	 */
	public AbstractBuilder getComponente() {	
		return this;
	}

	/**
  	 * Metodo que define los colores de la barra de estatus.
  	 * @param color				Color de en la barra.
  	 */
  	public void setColorPane(Color color) {
	    String text = null;                              // Text for the color pane
    	if(color.equals(Color.RED)) 
      		text = "RED";
    	else if(color.equals(Color.YELLOW))
      		text = "YELLOW";
    	else if(color.equals(Color.GREEN))
      		text = "GREEN";
    	else if(color.equals(Color.BLUE))
      		text = "BLUE";
    	else
      		text = "CUSTOM COLOR";
    	colorPane.setForeground(color);
    	colorPane.setText(text);                         // Set the pane text
  	}

  	/**
  	 * Metodo que muestra el tipo de elemento pasado por parametro.
  	 * @param elementType		Tipo de elemento a mostrar.
  	 */
  	public void setTypePane (int elementType) { 
	    String text = null;                           // Text for the type pane
    	switch(elementType) {
      		case LINE:
        		text = "LINE";
        		break;
      		case RECTANGLE:
        		text = "RECTANGLE";
        		break;
      		case CIRCLE:
        		text = "CIRCLE";
        		break;
      		case CURVE:
        		text = "CURVE";
        		break;
      		case TEXT:
        		text = "TEXT";
        		break;
      		default:
		
    	}
    	typePane.setText(text);                       // Set the pane text
  	}

  	/**
  	 * Inner Class que crea las etiquetas a mostrar en la
  	 * barra de estatus, usando la misma clase para las de
  	 * color y las de tipo.
  	 * 
  	 * @author Camilo Nova.
  	 */
  	class StatusPane extends JLabel {
		// Font for pane text
		private Font paneFont = new Font("Serif", Font.PLAIN, 10);
 
 	    /**
 	     * Contructor de la clase, crea la etiqueta con el parametro.
 	     * @param text		Texto a mostrar en la barra.
 	     */
 	    public StatusPane(String text) {
      		setBackground(Color.LIGHT_GRAY);      // Set background color
      		setForeground(Color.BLACK);
      		setFont(paneFont);                   // Set the fixed font
      		setHorizontalAlignment(CENTER);      // Center the pane text 
      		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
      		setPreferredSize(new Dimension(100,20));
      		setText(text);                       // Set the text in the pane
    	}
 	}
}