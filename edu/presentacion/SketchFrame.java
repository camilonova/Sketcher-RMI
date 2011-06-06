package edu.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import edu.logica.builder.AbstractBuilder;
import edu.logica.builder.MenuBarBuilder;
import edu.logica.builder.StatusBarBuilder;
import edu.logica.builder.ToolBarBuilder;
import edu.repositorio.Constants;

/**
 * Ventana principal de la aplicacion, esta clase es la la mas importante de
 * la aplicacion ya que es la base de las otras funciones de la aplicacion misma.
 * Esta clase hereda de JFrame e implementa Constants y ActionListener.
 * 
 * @author Camilo Nova.
 */
public class SketchFrame extends JFrame implements Constants, Serializable {

	/**
	 * Representa la barra de Menu.
	 */
	private MenuBarBuilder menuBar;

	/**
	 * Representa la barra de herramientas.
	 */
	private ToolBarBuilder toolBar; 
	
	/**
	 * Representa la ventana principal.
	 */
	private Sketcher theApp;
	
	/**
	 * Representa el titulo de la ventana.
	 */
	private String title;

	/**
	 * Representa la barra de estatus de la aplicacion.
	 */
	private StatusBarBuilder statusBar;

	/**
	 * Representa la fuente actual.
	 */
	private Font font = DEFAULT_FONT;

	/**
	 * Representa el cuadro de seleccion de fuente.
	 */
	private FontDialog fontDlg = new FontDialog(this);
	
	/**
	 * Representa la interface de construccion de componentes.
	 */
	private AbstractBuilder componente;
  		
	/**
	 * Constructor de la clase, se encarga de construir la ventana principal de la
	 * aplicacion agregando la barra de menu, la barra de herramientas, la barra
	 * de estatus y el lienzo donde se agregan los elementos secuancialmente usando
	 * para ello el patron <i>Builder</i> el cual usa construccion de objetos secuencialmente.
	 * @param title						El titulo de la ventana.
	 * @param theApp					El app de la aplicacion.
	 */
	public SketchFrame(String title, Sketcher theApp) {
		this.title = title;
		this.theApp = theApp;

		setTitle(title);                             	// Set the window title
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);   	// Default is exit the application
		
		// Construimos los elementos de la aplicacion.
		componente = new StatusBarBuilder();
		statusBar = (StatusBarBuilder) componente.getComponente();
    	getContentPane().add(statusBar, BorderLayout.SOUTH);
    	
    	componente = new MenuBarBuilder(statusBar, fontDlg);
    	menuBar = (MenuBarBuilder) componente.getComponente();
		setJMenuBar(menuBar);
 
		componente = new ToolBarBuilder(menuBar);
		toolBar = (ToolBarBuilder) componente.getComponente();
		getContentPane().add(toolBar, BorderLayout.NORTH);

  	}

  	/**
  	 * Metodo que retorna el PopUp Menu de la aplicacion.
  	 * @return	Popup Menu de la aplicacion.
  	 */
  	public JPopupMenu getPopup() {  
    	return menuBar.popup;  
  	}

	/**
	 * Metodo que retorna el color actual.
	 * @return	Color actual de la aplicacion.
	 */
	public Color getElementColor() { 
		return menuBar.elementColor; 
	}

	/**
	 * Metodo que retorna el tipo de elemento actual.
	 * @return	Entero que representa el tipo de elemento. 
	 */
	public int getElementType() { 
		return menuBar.elementType; 
	}

	/**
	 * Metodo que retorna la fuente actual.
	 * @return	Fuente actual de la aplicacion.
	 */
	public Font getCurrentFont() {  
		return font;  
	}

	/**
	 * Metodo que determina la fuente de la aplicacion.
	 * @param font				Nueva fuente a cambiar.
	 */
	public void setCurrentFont(Font font) {  
		this.font = font;  
	}
	
	/**
	 * Inner Class que que genera los eventos para el menu about de la aplicacion.
	 * Hereda de JDialog e implementa ActionListener
	 * 
	 * @author Camilo Nova.
	 */
  	class AboutDialog extends JDialog implements ActionListener   {

  		/**
  		 * Constructor de la aplicacion.
  		 * @param parent					Ventana principal de la aplicacion.
  		 * @param title						Titulo de la ventana.
  		 * @param message					Mensaje a mostrar.
  		 */
    	public AboutDialog(Frame parent, String title, String message) {
      		super(parent, title, true);
      		// If there was a parent, set dialog position inside
      		if(parent != null) {
        		Dimension parentSize = parent.getSize();     // Parent size
        		Point p = parent.getLocation();              // Parent position
        		setLocation(p.x+parentSize.width/4,p.y+parentSize.height/4); 
      		}

      		// Create the message pane
      		JPanel messagePane = new JPanel();
      		messagePane.add(new JLabel(message));        
      		getContentPane().add(messagePane);
	
    		// Create the button pane
      		JPanel buttonPane = new JPanel();
      		JButton button = new JButton("OK");        // Create OK button
      		buttonPane.add(button);                    // add to content pane
      		button.addActionListener(this);
      		getContentPane().add(buttonPane, BorderLayout.SOUTH);
      		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      		pack();                                    // Size window for components
      		setVisible(true);
    	}

    	/**
    	 * Metodo que genera los eventos para el boton OK.
    	 */
    	public void actionPerformed(ActionEvent e)  {
      		setVisible(false);                         // Set dialog invisible
      		dispose();                                 // Release the dialog resources
    	}
  	}
}