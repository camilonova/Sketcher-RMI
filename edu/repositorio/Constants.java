package edu.repositorio;

import java.awt.Color;
import java.awt.Font;

/**
 * Esta interface define las constantes globales de la aplicacion
 * 
 * @author Camilo
 */
public interface Constants { 
	
	/**
	 * Constante que define el tipo de elemento linea <b>101</b>.
	 */
	static int LINE      = 101;
	
	/**
	 * Constante que define el tipo de elemento rectangulo <b>102</b>.
	 */
 	static int RECTANGLE = 102;
 	
 	/**
 	 * Constante que define el tipo de elemento circulo <b>103</b>.
 	 */
  	static int CIRCLE    = 103;
  	
  	/**
  	 * Constante que define el tipo de elemento curva <b>104</b>.
  	 */
  	static int CURVE     = 104;
  	
  	/**
  	 * Constante que define el tipo de elemento texto <b>105</b>.
  	 */
  	static int TEXT      = 105;
  
  	/**
  	 * Constante que define el minimo tamano de fuente <b>8</b>.
  	 */
  	static int pointSizeMin  = 8;
  	
  	/**
  	 * Constante que define el maximo tamano de fuente <b>24</b>.
  	 */ 
  	static int pointSizeMax  = 24;   
  	
  	/**
  	 * Constante que define el tamano del step <b>2</b>.
  	 */
  	static int pointSizeStep = 2;    

	/**
	 * Condicion inicial del tipo de elemento <b>Linea</b>.
	 */
  	static int DEFAULT_ELEMENT_TYPE = LINE;
  	
  	/**
  	 * Condicion inicial del color del elemento  <b>Azul</b>.
  	 */
  	static Color DEFAULT_ELEMENT_COLOR = Color.BLUE;
  	
  	/**
  	 * Condicion inicial de la fuente <b>Times New Roman</b>.
  	 */
  	static Font DEFAULT_FONT = new Font("Times New Roman",Font.PLAIN, 12);

  	/**
  	 * Constante que define el tipo de operacion normal  <b>0</b>.
  	 */
  	static int NORMAL = 0;
  	
  	/**
  	 * Constante que define el tipo de operacion mover  <b>1</b>.
  	 */
  	static int MOVE   = 1;
  	
  	/**
  	 * Constante que define el tipo de operacion rotar  <b>2</b>.
  	 */
  	static int ROTATE = 2;
  	
	/**
	 * Constante que define la direccion IP del servidor de la aplicacion <b>localhost</b>.
	 */
	static String SERVER_IP_ADDRESS = "//127.0.0.1/";
  	
	/**
	 * Constante que define el nombre de la aplicacion en el registro RMI <b>Sketcher</b>.
	 */
	static String SKETCHER_RMI_NAME = "Sketcher";
}