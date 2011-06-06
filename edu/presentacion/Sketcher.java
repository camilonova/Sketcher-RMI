package edu.presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Observer;

import edu.logica.SketchView;
import edu.repositorio.SketchModel;

/**
 * Clase principal de la aplicacion, inicia la aplicacion llamando a su
 * constructor o llamando al metodo main().
 * 
 * @author Camilo
 */
public class Sketcher extends WindowAdapter implements Serializable {
	
	/**
	 * Representa el modelo de datos de la aplicacion.
	 */
	private static SketchModel sketch;                     
	
	/**
	 * Representa la vista de la aplicacion.
	 */
	private SketchView view;     
	
	/**
	 * Representa la ventana de la aplicacion.
	 */
	private static SketchFrame window;
	
	/**
	 * Representa la aplicacion.
	 */
	private static Sketcher theApp;
	
	/**
	 * Metodo principal de la aplicacion, hace un llamado al constructor de la clase.
	 * @param args		Argumentos <b>Sin uso</b>.
	 */
	public static void main(String[] args) {
		new Sketcher();
	}
	
  	/**
  	 * Constructor de la clase, carga la aplicacion y la muestra.
  	 * @throws ServerNotActiveException
  	 * @throws RemoteException
  	 */
  	public Sketcher() {
		// Determinamos la seguridad en la conexion.
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

	    window = new SketchFrame("Sketcher", this);    // Create the app window
	    Dimension wndSize = window.getToolkit().getScreenSize();        // Get the window toolkit
    	
    	// Set the position to screen center & size to half screen size
    	window.setBounds(wndSize.width/4, wndSize.height/4,wndSize.width/2, wndSize.height/2);

    	window.addWindowListener(this);  			// Add window listener
    	
   		sketch = new SketchModel();
    	
    	view = new SketchView(this);              // Create the view
    	sketch.addObserver((Observer)view);       // Register the view with the model
    	window.getContentPane().add(view, BorderLayout.CENTER);
    	window.setVisible(true);
  	}
  	
  	/**
  	 * Metodo que retorna una referencia de la aplicacion.
  	 * @return	SketchFrame			Referencia de la aplicacion.
  	 */
  	public SketchFrame getWindow() { 
     	return window; 
  	}

  	/**
  	 * Metodo que retorna una referencia del modelo.
  	 * @return	SketchModel			Referencia del modelo.
  	 */
  	public SketchModel getModel() { 
     	return sketch; 
  	}
  	
  	/**
  	 * Metodo que retorna una referencia de la vista.
  	 * @return	SketchView			Referencia de la vista
  	 */
  	public SketchView getView() { 
     	return view; 
  	}
  	
 	/**
 	 * Metodo que es llamado al cerrar la ventana principal de la aplicacion.
 	 */
  	public void windowClosing(WindowEvent e) {
		System.out.println("Aplicacion Sketcher Cliente - Terminada.");
		super.windowClosing(e);
	}
}