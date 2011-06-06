package edu.logica.builder;

import java.awt.Color;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import edu.presentacion.FontDialog;

/**
 * Esta clase representa un objeto MenuBarBuilder el cual es
 * la barra de menu de la aplicacion, la cual es construida
 * mediante el llamado al constructor <i>MenuBarBuilder()</i>
 * y de la cual se toma su referencia llamando al metodo <i>getComponente()</i>
 * implementado de la interface <i>AbstractBuilder</i>.
 * 
 * @author Camilo Nova.
 */
public class MenuBarBuilder extends JMenuBar implements AbstractBuilder, ActionListener {

	/**
	 * Representa el Menu File.
	 */
	private JMenu fileMenu = new JMenu("File");
	
	/**
	 * Representa el Menu Elements.
	 */
	private JMenu elementMenu = new JMenu("Elements");
	
	/**
	 * Representa el Menu Options.
	 */
	private JMenu optionsMenu = new JMenu("Options");
	
	/**
	 * Representa el Menu Help.
	 */
	private JMenu helpMenu = new JMenu("Help");

	/**
	 * Representa el Menu de Click derecho.
	 */
	public JPopupMenu popup = new JPopupMenu("Menu Auxiliar");    

	/**
	 * Representa las acciones para el menu File.
	 */
	public FileAction newAction, openAction, closeAction, saveAction, saveAsAction, printAction;

	/**
	 * Representa las acciones para los elementos.
	 */
	public TypeAction lineAction, rectangleAction, circleAction, curveAction, textAction;

	/**
	 * Representa los colores para los elementos.
	 */
	public ColorAction redAction, yellowAction, greenAction, blueAction;

	/**
	 * Representa los items del menu principal
	 */
	private JMenuItem aboutItem, fontItem, customColorItem;
	
	/**
	 * Representa el tipo actual del elemento.
	 */
	public int elementType = DEFAULT_ELEMENT_TYPE;       

	/**
	 * Representa el color actual del elemento.
	 */
	public Color elementColor = DEFAULT_ELEMENT_COLOR;   

	/**
	 * Representa una referencia de la barra de estatus.
	 */
	private StatusBarBuilder statusBar;
	
	/**
	 * Representa una referencia del dialogo de seleccion de Fuente.
	 */
	private FontDialog fontDlg;

	/**
	 * Constructor de la clase, crea el menu principal de la aplicacion
	 * y les da eventos a cada elemento del menu, utilizando las
	 * Inner Class <i>FileAction</i>, <i>TypeAction</i> y <i>ColorAction</i>.
	 * 
	 * @param statusBar			La barra de estatus (Referencia).
	 * @param fontDlg			El dialogo de seleccion de fuente (Referencia).
	 */
	public MenuBarBuilder(StatusBarBuilder statusBar, FontDialog fontDlg) {
		this.statusBar = statusBar;
		this.fontDlg = fontDlg;
		
    	fileMenu.setMnemonic(KeyEvent.VK_F);
    	elementMenu.setMnemonic(KeyEvent.VK_E);
    	optionsMenu.setMnemonic(KeyEvent.VK_O);
    	helpMenu.setMnemonic(KeyEvent.VK_H); 
		
    	newAction = new FileAction("New", KeyStroke.getKeyStroke('N',Event.CTRL_MASK ), "Create new sketch");
    	openAction = new FileAction("Open", KeyStroke.getKeyStroke('O',Event.CTRL_MASK), "Open existing sketch");
    	closeAction = new FileAction("Close", "Close sketch");
    	saveAction = new FileAction("Save", KeyStroke.getKeyStroke('S',Event.CTRL_MASK), "Save sketch");
    	saveAsAction = new FileAction("Save As...", "Save as new file");
    	printAction = new FileAction("Print", KeyStroke.getKeyStroke('P',Event.CTRL_MASK), "Print sketch");

    	addMenuItem(fileMenu, newAction);
    	addMenuItem(fileMenu, openAction);
    	addMenuItem(fileMenu, closeAction);
    	fileMenu.addSeparator();
    	addMenuItem(fileMenu, saveAction);
    	addMenuItem(fileMenu, saveAsAction);
    	fileMenu.addSeparator();
    	addMenuItem(fileMenu, printAction);
    	
	    addMenuItem(elementMenu, lineAction = new TypeAction("Line", LINE, "Draw lines")); 
    	addMenuItem(elementMenu, rectangleAction = new TypeAction("Rectangle",RECTANGLE, "Draw rectangles"));
    	addMenuItem(elementMenu, circleAction = new TypeAction("Circle", CIRCLE, "Draw circles"));
    	addMenuItem(elementMenu, curveAction = new TypeAction("Curve", CURVE, "Draw curves"));
    	addMenuItem(elementMenu, textAction = new TypeAction("Text", TEXT, "Draw text"));

	    elementMenu.addSeparator();
		
    	JMenu colorMenu = new JMenu("Color");
    	elementMenu.add(colorMenu);
		
	    addMenuItem(colorMenu, redAction = new ColorAction("Red", Color.RED, "Draw in red"));
    	addMenuItem(colorMenu, yellowAction = new ColorAction("Yellow", Color.YELLOW, "Draw in yellow"));
	    addMenuItem(colorMenu, greenAction = new ColorAction("Green", Color.GREEN, "Draw in green"));
	    addMenuItem(colorMenu, blueAction = new ColorAction("Blue", Color.BLUE, "Draw in blue"));
	    
	    add(fileMenu);
    	add(elementMenu); 
    	add(optionsMenu);

    	saveAction.setEnabled(false);
    	closeAction.setEnabled(false);
    	printAction.setEnabled(false);

    	// Add the About item to the Help menu
    	aboutItem = new JMenuItem("About");           // Create the item
    	aboutItem.addActionListener(this);            // Listener is the frame
    	helpMenu.add(aboutItem);                      // Add item to menu
    	add(helpMenu);                        // Add the Help menu

    	// Add the font choice item to the options menu
    	fontItem = new JMenuItem("Choose font...");
    	fontItem.addActionListener(this);
    	optionsMenu.add(fontItem);

    	// Create pop-up menu
    	popup.add(lineAction);
    	popup.add(rectangleAction);
    	popup.add(circleAction);
    	popup.add(curveAction);
    	popup.add(textAction);

    	popup.addSeparator();
    	popup.add(redAction);
    	popup.add(yellowAction);
    	popup.add(greenAction);
    	popup.add(blueAction);

    	customColorItem = popup.add("Custom Color...");    // Add the item
    	customColorItem.addActionListener(this);           // and add its listener

	}
	
	/**
	 * Retorna el componente de tipo JMenuBar creado por
	 * el constructor.
	 * @return Componente para agregar.
	 */
	public AbstractBuilder getComponente() {
		return this;
	}

	/**
	 * Metodo que agrega un item al menu pasado por parametro.
	 * @param menu						Menu al cual agregar la accion.
	 * @param action					Accion a agregar.
	 * @return JMenuItem construido.
	 */
	private JMenuItem addMenuItem(JMenu menu, Action action) {
		JMenuItem item = menu.add(action);
		KeyStroke keystroke = (KeyStroke)action.getValue(Action.ACCELERATOR_KEY);
		if(keystroke != null)
			item.setAccelerator(keystroke);
      
		return item;
	}

	/**
	 * Metodo que se encarga de manejar los eventos del menu about.
	 */
	public void actionPerformed(ActionEvent e)  {
		if(e.getSource() == aboutItem) {
			// Create about dialog with the menu item as parent
			JOptionPane.showMessageDialog(null,                          // Parent
							"Sketcher Copyright Ivor Horton 2001 \n Powered by Camilo H. Nova 2004", // Message
							"About Sketcher",                      // Title
							JOptionPane.INFORMATION_MESSAGE);      // Message type

		} else if(e.getSource() == fontItem) {      // Set the dialog window position
			Rectangle bounds = getBounds();
			fontDlg.setLocation(bounds.x + bounds.width/3, bounds.y + bounds.height/3);
			fontDlg.setVisible(true);            // Show the dialog

		} else if(e.getSource() == customColorItem) {
			Color color = JColorChooser.showDialog(this, "Select Custom Color", elementColor);
			if(color != null) {
				elementColor = color;
				statusBar.setColorPane(color);
			}
		}
	}

  	/**
  	 * Inner Class que genera las acciones para el menu file. Hereda de AbstractAction.
  	 * @author Camilo
  	 */
  	class FileAction extends AbstractAction { 
    	
    	/**
    	 * Constructor de la clase.
    	 * @param name					Nombre de la accion.
    	 */
    	FileAction(String name) {
      		super(name);
      		JApplet hi = new JApplet();
	      	String iconFileName = "edu/repositorio/Images/" + name + ".gif";  	
      		if(new File(iconFileName).exists())
        		putValue(SMALL_ICON, new ImageIcon(iconFileName));
    		}

   		/**
   		 * Constructor de la clase.
   		 * @param name					Nombre de la accion.
   		 * @param keystroke				KeyStroke de la accion.
   		 */
   		FileAction(String name, KeyStroke keystroke) {
      		this(name);
      		if(keystroke != null)
        		putValue(ACCELERATOR_KEY, keystroke);
    	}

    	/**
    	 * Constructor de la clase.
    	 * @param name					Nombre de la accion.
    	 * @param tooltip				Tooltip de la accion.
    	 */
    	FileAction(String name, String tooltip) {
      		this(name);                                // Call the other constructor
      		if(tooltip != null)                        // If there is tooltip text
        		putValue(SHORT_DESCRIPTION, tooltip);    // ...squirrel it away
    	}

    	/**
    	 * Constructor de la clase.
    	 * @param name					Nombre de la accion.
    	 * @param keystroke				KeyStroke de la accion.
    	 * @param tooltip				Tooltip de la accion.
    	 */
    	FileAction(String name, KeyStroke keystroke, String tooltip) {
      		this(name, keystroke);                         // Call the other constructor
      		if(tooltip != null)                             // If there is tooltip text
        		putValue(SHORT_DESCRIPTION, tooltip);         // ...squirrel it away
    	}
    
    	/**
    	 * Metodo que se encarga de manejar los eventos.
    	 * <b>Sin funcionalidad por el momento</b>.
    	 */
    	public void actionPerformed(ActionEvent e) {
    	}
  	}

  	/**
  	 * Inner Class que genera los eventos para las acciones de la aplicacion.
  	 * @author Camilo
  	 * 
  	 */
  	class TypeAction extends AbstractAction {  
  		
		private int typeID;
    	
    	/**
    	 * Constructor de la clase.
    	 * @param name						Nombre de la accion.
    	 * @param typeID					typeID de la accion.
    	 */
    	TypeAction(String name, int typeID) {
      		super(name);
      		this.typeID = typeID;
      		String iconFileName = "edu/repositorio/Images/" + name + ".gif";
      		if(new File(iconFileName).exists())
        		putValue(SMALL_ICON, new ImageIcon(iconFileName));
    	}
    
    	/**
    	 * Constructor de la clase.
    	 * @param name						Nombre de la accion.
    	 * @param typeID					typeID de la accion.
    	 * @param tooltip					Tooltip de la accion.
    	 */
    	TypeAction(String name, int typeID, String tooltip) {
      		this(name, typeID);
      		if(tooltip != null)                               	// If there is a tooltip
        		putValue(SHORT_DESCRIPTION, tooltip);           // ...squirrel it away
    	}

    	/**
    	 * Metodo que se encarga de los eventos de la clase.
    	 */
    	public void actionPerformed(ActionEvent e) {
      		elementType = typeID;  
     		statusBar.setTypePane(typeID);
    	}
  	}

  	/**
  	 * Inner Class que genera los eventos para el menu color de la aplicacion.
  	 * Hereda de AbstractAction.
   	 * @author Camilo
 	 */
  	class ColorAction extends AbstractAction {

		private Color color;
  		
  		/**
  		 * Constructor de la clase.
  		 * @param name					Nombre de la accion.
  		 * @param color					Color de la accion.
  		 */
    	public ColorAction(String name, Color color) {
      		super(name);
      		this.color = color;
      		String iconFileName = "edu/repositorio/Images/" + name + ".gif";
      		if(new File(iconFileName).exists())
        		putValue(SMALL_ICON, new ImageIcon(iconFileName));
    	}
    
    	/**
    	 * Constructor de la clase.
    	 * @param name					Nombre de la accion.
    	 * @param color					Color de la accion.
    	 * @param tooltip				Tooltip de la accion.
    	 */
    	public ColorAction(String name, Color color, String tooltip) {
      		this(name, color);
       		if(tooltip != null)                               // If there is a tooltip
         		putValue(SHORT_DESCRIPTION, tooltip);           // ...squirrel it away
    	}
    
    	/**
    	 * Metodo que se encarga de los eventos de la clase.
    	 */
    	public void actionPerformed(ActionEvent e) {
      		elementColor = color;
   			statusBar.setColorPane(color);
    	}
  	}
}