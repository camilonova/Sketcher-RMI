package edu.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.repositorio.Constants;

/**
 * Esta clase define un dialogo para cambiar la fuente.
 * Implementa Constants, ActionListener, ChangeListener, ListSelectionListener
 * y hereda de JDialog.
 * 
 * @author Camilo Nova.
 */
public class FontDialog extends JDialog implements Constants, Serializable, ActionListener, ChangeListener, ListSelectionListener {
	
	/**
	 * Opciones de tamano para el tipo de fuente.
	 */
	private JSpinner chooseSize;                 
	
	/**
	 * Muestra la fuente seleccionada.
	 */
	private JLabel fontDisplay;                    

	/**
	 * Representa la lista de las fuentes disponibles.
	 */
	private JList fontList;                	
  
	/**
	 * Representa el boton OK.
	 */
	private JButton ok;       
	
	/**
	 * Representa el boton Cancelar.
	 */
	private JButton cancel;         

	/**
	 * Representa la fuente actual.
	 */
	private Font font;             
	
	/**
	 * Representa el estilo de fuente actual.
	 */
	private int fontStyle;             		
	
	/**
	 * Representa el tamano de fuente actual.
	 */
	private int fontSize;              		

	/**
	 * Constructor de la clase, muestra un dialogo para cambiar la fuente.
	 * @param window			Parent para mostrar la ventana.
	 */
	public FontDialog(SketchFrame window) {
    	super(window, "Font Selection", true);   

    	font = window.getCurrentFont();                // Obtiene la fuente actual
    	fontStyle = font.getStyle();                   // Obtiene el estilo de fuente
    	fontSize = font.getSize();                     // Obtiene el tamano

    	// Crea el panel del dialogo
    	JPanel buttonPane = new JPanel();

    	// Crea y anade los botones al panel
    	buttonPane.add(ok = createButton("OK"));
    	buttonPane.add(cancel = createButton("Cancel"));
    	getContentPane().add(buttonPane, BorderLayout.SOUTH);

    	// Crea el panel de entrada de datos
    	JPanel dataPane = new JPanel();
    	dataPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
    															BorderFactory.createEmptyBorder(5, 5, 5, 5)));  
    	GridBagLayout gbLayout = new GridBagLayout();         
    	dataPane.setLayout(gbLayout);                         
    	GridBagConstraints constraints = new GridBagConstraints();

    	JLabel label = new JLabel("Choose a Font");
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	constraints.gridwidth = GridBagConstraints.REMAINDER; 
    	gbLayout.setConstraints(label, constraints);
    	dataPane.add(label);
	
	    // Obtiene las fuentes disponibles en el sistema
	    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    String[] fontNames = e.getAvailableFontFamilyNames();
	
	    fontList = new JList(fontNames);                    
	    fontList.setValueIsAdjusting(true);                 
	    fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    fontList.setSelectedValue(font.getFamily(),true);
	    fontList.addListSelectionListener(this);
	    JScrollPane chooseFont = new JScrollPane(fontList); 
	    chooseFont.setMinimumSize(new Dimension(300,100));
	    chooseFont.setWheelScrollingEnabled(true);
	
	    JPanel display = new JPanel();
	    fontDisplay = new JLabel("Sample Size: Sample Font");
	    fontDisplay.setPreferredSize(new Dimension(300,100));
	    display.add(fontDisplay);
	
	    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,chooseFont,display);
    	gbLayout.setConstraints(splitPane, constraints);   
    	dataPane.add(splitPane);                           
	
	    JPanel sizePane = new JPanel();               
	    label = new JLabel("Choose point size");      
	    sizePane.add(label);                          
	 
	    chooseSize = new JSpinner(new SpinnerNumberModel(fontSize, pointSizeMin, pointSizeMax, pointSizeStep));
    	chooseSize.addChangeListener(this);  
    	sizePane.add(chooseSize);
	
	    gbLayout.setConstraints(sizePane, constraints);          
	    dataPane.add(sizePane);                                  
	
	    JRadioButton bold = new JRadioButton("Bold", (fontStyle & Font.BOLD) > 0);
	    JRadioButton italic = new JRadioButton("Italic", (fontStyle & Font.ITALIC) > 0);
	    bold.addItemListener(new StyleListener(Font.BOLD));      
	    italic.addItemListener(new StyleListener(Font.ITALIC));
    	JPanel stylePane = new JPanel();                         
    	stylePane.add(bold);                                     
    	stylePane.add(italic);                                   
	    gbLayout.setConstraints(stylePane, constraints);         
    	dataPane.add(stylePane);                                 

    	getContentPane().add(dataPane, BorderLayout.CENTER);
    	pack();
    	setVisible(false);
  	}
	
	/**
	 * Metodo que cambia la fuente cada vez que ocurre un evento en
	 * el dialogo.
	 */
  	public void stateChanged(ChangeEvent e) {
	    fontSize = ((Number)(((JSpinner)e.getSource()).getValue())).intValue();  
	    font = font.deriveFont((float)fontSize);
	    fontDisplay.setFont(font);
	    fontDisplay.repaint();    
  	}
	
	/**
	 * Metodo que cambia la fuente cada vez que ocurre un evento en
	 * el dialogo.
	 */
  	public void valueChanged(ListSelectionEvent e) {
	    if(!e.getValueIsAdjusting()) {
      		font = new Font((String)fontList.getSelectedValue(), fontStyle, fontSize);
      		fontDisplay.setFont(font);
      		fontDisplay.repaint();
    	}
  	}

	/**
	 * Metodo que cambia la fuente cada vez que ocurre un evento en
	 * el dialogo.
	 */
  	public void actionPerformed(ActionEvent e) {
	    Object source = e.getSource();          
    	if(source == ok)  {                     
      		((SketchFrame)getOwner()).setCurrentFont(font); 
       		setVisible(false);                             
    	}
    	else if(source == cancel)                     
      		setVisible(false);                        
  	}

  	/**
  	 * Metodo que crea un boton con el String pasado por parametro.
  	 * @param 	label				String que tendra el boton.
  	 * @return	JButton				Boton creado.
  	 */
  	JButton createButton(String label) {
		JButton button = new JButton(label);           
    	button.setPreferredSize(new Dimension(80,20)); 
    	button.addActionListener(this);                
    	return button;                                 
  	}
  	
  	/**
  	 * Inner Class que escucha los eventos en el dialogo de Fuente.
  	 * 
  	 * @author Camilo Nova.
  	 */
  	class StyleListener implements ItemListener {
  	
		private int style;                            
	
  		/**
  		 * Constructor de la clase.
  		 * @param style
  		 */
  		public StyleListener(int style) {  
      		this.style = style;  
    	}

		/**
		 * Metodo que cambia la fuente cada vez que ocurre un evento en
		 * el dialogo.
		 */
    	public void itemStateChanged(ItemEvent e) {
      		if(e.getStateChange()==ItemEvent.SELECTED)   
        		fontStyle |= style;                      
      		else
        		fontStyle &= ~style;                     
      		font = font.deriveFont(fontStyle);           
      		fontDisplay.setFont(font);                   
      		fontDisplay.repaint();                       
    	}    
  	}
}