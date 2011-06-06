package edu.logica;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.MouseInputAdapter;

import edu.logica.abstractFactory.AbstractFactory;
import edu.logica.abstractFactory.Circle;
import edu.logica.abstractFactory.CircleFactory;
import edu.logica.abstractFactory.Curve;
import edu.logica.abstractFactory.CurveFactory;
import edu.logica.abstractFactory.Element;
import edu.logica.abstractFactory.Line;
import edu.logica.abstractFactory.LineFactory;
import edu.logica.abstractFactory.RectangleFactory;
import edu.logica.abstractFactory.Text;
import edu.logica.abstractFactory.TextFactory;
import edu.presentacion.Sketcher;
import edu.repositorio.Constants;

/**
 * Esta clase hereda de JComponent e implementa Observer, Serializable, Constants y ActionListener.
 * Su responsabilidad es la de crear y agregar las caracteristicas del panel interno
 * de la aplicacion donde se pintan los elementos que el usuario desea (el canvas).<p>
 * 
 * Esta clase implementa la interface <i>Observer</i> la cual le permite observar a una
 * clase que herede de <i>Observable</i> que en este caso es la clase <b>SketchModel</b>
 * cuando es informado por la clase SketchModel, esta clase ejecuta su metodo <pre>Update(Observable, Object)</pre>
 * el cual llama el metodo <i>paint(Graphics)</i> que repinta el area del canvas.<p>
 * 
 * @author Camilo Nova
 */
public class SketchView extends JComponent implements Observer, Serializable, Constants, ActionListener {
	
	/**
	 * Representa el elemento seleccionado actualmente.
	 */
	private Element selectedElement;
	
	/**
	 * Representa el modo de operacion actual.
	 */
	private int mode = NORMAL;

	/**
	 * Representa el cuadro de dialogo que muestra los tipos de elementos.
	 */
	private JPopupMenu elementPopup = new JPopupMenu("Element");
	
	/**
	 * Representa los items que estan el el cuadro de dialogo.
	 */
	private JMenuItem moveItem, deleteItem, rotateItem, sendToBackItem;

	/**
	 * Representa la ventana principal de la aplicacion.
	 */
	private Sketcher theApp;           	
	
	/**
	 * Representa el elemento que actualmente esta seleccionado.
	 */
	private Element highlightElement;
	
	/**
	 * Representa la Fabrica Abstracta para la creacion de elementos.
	 */
	private AbstractFactory elementFactory;
	
	/**
	 * Constructor de la clase, agrega los escuchas a la aplicacion,
	 * crea el menu popup de click derecho.
	 * @param theApp		La aplicacion Sketcher
	 */
	public SketchView(Sketcher theApp) {
		this.theApp = theApp;
    	MouseHandler handler = new MouseHandler();
    	addMouseListener(handler);                
    	addMouseMotionListener(handler);          

    	moveItem = elementPopup.add("Move");
    	deleteItem = elementPopup.add("Delete");
    	rotateItem = elementPopup.add("Rotate");
    	sendToBackItem = elementPopup.add("Send-to-back");
	
    	moveItem.addActionListener(this);
    	deleteItem.addActionListener(this);
    	rotateItem.addActionListener(this);
    	sendToBackItem.addActionListener(this);
 	}
	
  	/**
  	 * Metodo que es llamado cuando hay una accion en el
  	 * componente en que halla sido agregado, como ha sido disenado
  	 * para el menu de opciones genera los eventos para el menu.
  	 */
  	public void actionPerformed(ActionEvent e ) {
	    Object source = e.getSource();
    	if(source == moveItem) {
      		mode = MOVE;
      		selectedElement = highlightElement;
	
	    } else if(source == deleteItem) {
   			if(highlightElement != null) {                 
        		theApp.getModel().remove(highlightElement);
        		highlightElement = null;                   
			}
    	} else if(source == rotateItem) {
      		mode = ROTATE;
      		selectedElement = highlightElement;
		} else if(source == sendToBackItem) {
      		if(highlightElement != null) {
        		theApp.getModel().remove(highlightElement);
        		theApp.getModel().add(highlightElement);
        		highlightElement.setHighlighted(false);
        		highlightElement = null;
        		repaint();
      		}
    	}
  	}
  	
  	/**
  	 * Metodo que pinta el panel de la aplicacion, en este caso
  	 * dibuja la coleccion de elementos que han sido creados por
  	 * el usuario, obteniendo el iterador de la coleccion de elementos.
  	 */
  	public void paint(Graphics g) {
  		Graphics2D g2D = (Graphics2D)g;                
    	Iterator elements = theApp.getModel().getIterator();
    	while(elements.hasNext())                      
    		((Element)elements.next()).draw(g2D);         
  	}

  	/**
  	 * Llama al metodo <i>paint(Graphics)</i> cada vez que es informado de un
  	 * cambio en la aplicacion.
  	 */
  	public void update(Observable o, Object obj) {
    	if(obj == null)
      		repaint();
    	else 
      		repaint((Rectangle)obj);
  	}
  	
  	/**
  	 * Esta Inner Class que extiende de MouseInputAdapter es un escucha de los eventos 
  	 * producidos por el raton y actua a los componentes a los que le han sido agregados.
  	 * 
  	 * @author Camilo Nova
  	 */
  	class MouseHandler extends MouseInputAdapter {
  		
  		/**
  		 * Punto de inicio del elemento a dibujar.
  		 */
		private Point start;
		
		/**
		 * Punto final del elemento a dibujar.
		 */
		private Point last;
		
		/**
		 * Elemento temporal, utilizado para operaiones sobre algun elemento
		 * en particular.
		 */
		private Element tempElement;        

		/**
		 * Bandera que indica si el boton 1 esta presionado.
		 */
		private boolean button1Down = false;
		
		/**
		 * Graphics2D que se utiliza para pintar los elementos.
		 */
		private Graphics2D g2D = null;      

  		/**
  		 * Evento que es activado cuando el boton 1 del mouse
  		 * es presionado.
  		 */
  		public void mousePressed(MouseEvent e) {
  			start = e.getPoint();   
      		if((button1Down = (e.getButton()==MouseEvent.BUTTON1))) { 
        		g2D = (Graphics2D)getGraphics(); 
        		g2D.setXORMode(getBackground()); 
        		g2D.setPaint(theApp.getWindow().getElementColor());    
      		}
    	}

    	/**
    	 * Evento que es activado cuando se arrastra el mouse.
    	 */
    	public void mouseDragged(MouseEvent e) {
      		last = e.getPoint();                              
	
      		if(button1Down && (theApp.getWindow().getElementType() != TEXT) && (mode == NORMAL)) {
        		if(tempElement == null) {                     
          			tempElement = createElement(start, last); 
        		} else {
          			tempElement.draw(g2D);                    
          			tempElement.modify(start, last);          
        		}
        		tempElement.draw(g2D);                        
	
      		} else if(button1Down && mode == MOVE && selectedElement != null) {
        		selectedElement.draw(g2D);                    
        		selectedElement.move(last.x-start.x, last.y-start.y);
        		selectedElement.draw(g2D);                   
        		start = last;                                

	     	} else if(button1Down && mode == ROTATE && selectedElement != null) {
    		    selectedElement.draw(g2D);                   
        		selectedElement.rotate(getAngle(selectedElement.getPosition(),start, last));
        		selectedElement.draw(g2D);                 
        		start = last;                             
        	}
    	}

    	/**
    	 * Evento que es activado cuando el boton del raton es soltado.
    	 */
    	public void mouseReleased(MouseEvent e) {
      		if(e.isPopupTrigger()) {
        		start = e.getPoint();

        		if(highlightElement==null)
	          		theApp.getWindow().getPopup().show((Component)e.getSource(), start.x, start.y);
    	    	else
        	  		elementPopup.show((Component)e.getSource(), start.x, start.y);
	
      		} else if((e.getButton()==MouseEvent.BUTTON1) && (theApp.getWindow().getElementType() != TEXT) && mode == NORMAL) {
        		button1Down = false;                 
        		if(tempElement != null) {
	     			theApp.getModel().add(tempElement); 
          			tempElement = null;                 
        		}
      		} else if((e.getButton()==MouseEvent.BUTTON1) && (mode == MOVE || mode == ROTATE)) {
        		button1Down = false;                   
        		if(selectedElement != null)
          			repaint();
        		mode = NORMAL;
      		}

      		if(g2D != null) {
        		g2D.dispose();                      
        		g2D = null;                         
      		}
      		start = last = null;                  
      		selectedElement = tempElement = null; 
    	}

    	/**
    	 * Crea un elemento dependiendo del valor que retorne el metodo
    	 * getElementType() y de los parametros pasados.<p>
    	 * Utiliza para crear los elementos la <b>Fabrica Abstracta</b>.
    	 * @param start				Punto de inicio.
    	 * @param end				Punto de meta.
    	 * @return	Element			El elemento creado.
    	 */
    	private Element createElement(Point start, Point end) {
      		switch(theApp.getWindow().getElementType()) {
        		case LINE:
        			elementFactory = new LineFactory(start, end, theApp.getWindow().getElementColor());
           			return (Line) elementFactory.getElement();
        		case RECTANGLE:
        			elementFactory = new RectangleFactory(start, end, theApp.getWindow().getElementColor());
           			return (edu.logica.abstractFactory.Rectangle) elementFactory.getElement();
        		case CIRCLE:
        			elementFactory = new CircleFactory(start, end, theApp.getWindow().getElementColor());
           			return (Circle) elementFactory.getElement();
        		case CURVE:
        			elementFactory = new CurveFactory(start, end, theApp.getWindow().getElementColor());
         			return (Curve) elementFactory.getElement();
        		default:
     		}
      		return null;
    	}

    	/**
    	 * Metodo para calcular el angulo con valores especiales para la aplicacion
    	 * @param position			Posicion del Elemento.
    	 * @param start				Punto de inicio.
    	 * @param last				Punto de meta.
    	 * @return
    	 */
    	double getAngle(Point position, Point start, Point last) {
      		// Get perpendicular distance from last to the line from position to start
      		double perp = Line2D.ptLineDist(position.x, position.y, last.x, last.y, start.x, start.y);
      		// Get the distance from position to start
      		double hypotenuse = position.distance(start);
      		if(hypotenuse == 0.0)                        // Make sure its
        		hypotenuse = 1.0;                          // non-zero

      		// Angle is the arc sine of perp/hypotenuse. Clockwise is positive angle
      		return -Line2D.relativeCCW(position.x, position.y, start.x, start.y, last.x, last.y)*Math.asin(perp/hypotenuse);
    	}

    	/**
    	 * Evento que es llamado cuando se hace click en el mouse.
    	 */
    	public void mouseClicked(MouseEvent e) {
      		if((e.getButton()== MouseEvent.BUTTON1) && (theApp.getWindow().getElementType() == TEXT)) {
		        start = e.getPoint();           
        		String text = JOptionPane.showInputDialog((Component)e.getSource(),"Enter Text:","Dialog for Text Element",JOptionPane.PLAIN_MESSAGE);          // No icon

        		if(text != null && text.length()!=0)  {   
          			g2D = (Graphics2D)getGraphics();
          			Font font = theApp.getWindow().getCurrentFont();
          			elementFactory = new TextFactory(font,text,start,theApp.getWindow().getElementColor(),new java.awt.font.TextLayout(text, font,g2D.getFontRenderContext()).getBounds().getBounds());
          			tempElement = (Text) elementFactory.getElement();

          			if(tempElement != null) {                 
            			theApp.getModel().add(tempElement);   
            			tempElement = null;                   
          			}
          			g2D.dispose();                            
          			g2D = null;
          			start = null;
        		}
      		}
    	}

    	/**
    	 * Evento que es activado cuando el mouse se mueve dentro del marco.
    	 */
    	public void mouseMoved(MouseEvent e) {
      		Point currentCursor = e.getPoint();
      		Iterator elements = theApp.getModel().getIterator();
      		Element element = null;            

      		while(elements.hasNext())  {                        	// Go through the list
        		element = (Element)elements.next();               	// Get the next element
        		if(element.getBounds().contains(currentCursor)) { 	// Under the cursor?
          			if(element==highlightElement)            		// If its already highlighted
            			return;                                		// we are done
          			g2D = (Graphics2D)getGraphics();         		// Get graphics context
          			if(highlightElement!=null)   {           		// If an element is highlighted
            			highlightElement.setHighlighted(false);		// un-highlight it and
            			highlightElement.draw(g2D);            		// draw it normal color
          			}
          			element.setHighlighted(true);           		// Set highlight for new element
          			highlightElement = element;             		// Store new highlighted element
          			element.draw(g2D);                      		// Draw it highlighted 
          			g2D.dispose();                      			// Release graphic context resources
          			g2D = null;
	
          			return;
        		}
      		}

      		if(highlightElement!=null)   {            				// If an element is highlighted
        		g2D = (Graphics2D)getGraphics();        			// Get graphics context
        		highlightElement.setHighlighted(false); 			// ...turn off highlighting
        		highlightElement.draw(g2D);             			// Redraw the element
        		highlightElement = null;                			// No element highlighted
        		g2D.dispose();                        				// Release graphic context resources
        		g2D = null;
      		}
    	}
  	}
}