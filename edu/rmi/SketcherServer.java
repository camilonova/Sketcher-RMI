package edu.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import edu.logica.abstractFactory.Element;

/**
 * Esta clase representa el servidor Remoto RMI de la aplicacion sketcher
 * implementa la interface remota <i>ISketcherServer</i> que le provee los
 * metodos para acceder remotamente.
 * 
 * @author Camilo Nova.
 */
public class SketcherServer extends UnicastRemoteObject implements ISketcherServer {

	/**
	 * Representa la lista de elementos creados por los clientes.
	 */
	private static LinkedList elementData = new LinkedList();
	
	/**
	 * Constructor de la clase, llama al constructor de la super clase.
	 * @throws RemoteException
	 */
	private SketcherServer() throws RemoteException {
		super();
	}

	/**
	 * Metodo principal de la clase se encarga de crear seguridad
	 * en la conexion y de registrar en el RMIRegistry la aplicacion
	 * servidora.
	 * @param args	<i>Sin uso!!!</i>
	 */
	public static void main(String[] args) {
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new RMISecurityManager());
		}
		try{
			ISketcherServer sketchServer = new SketcherServer();
			Naming.rebind(SERVER_IP_ADDRESS+SKETCHER_RMI_NAME, sketchServer);
		}catch(Exception e){
			System.err.println("Sketcher exception: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Servidor Sketcher Cargado!!!");
	}

	/**
	 * Retorna la lista de elementos de la aplicacion.
	 * @return Lista de los elementos del servidor.
	 */
	public LinkedList getData() throws RemoteException, ServerNotActiveException {
		return elementData;
	}
	
	/**
	 * Retorna un elemento en la posicion especificada
	 * por el parametro pasado.
	 * @param int Elemento a retornar.
	 * @return Elemento en la posicion especificada.
	 */
	public Element getElementAt(int s) {
		LinkedList newList = (LinkedList) elementData.clone();
		return (Element) newList.get(s);
	}

	/**
	 * Agrega un elemento a la lista del sevidor.
	 */
	public void add(Element element) throws RemoteException, ServerNotActiveException {
		System.out.println("Elemento agregado: " + element);
		elementData.add(element);
		System.out.println("Elementos: " + elementData);
	}

	/**
	 * Quita un elemento de la lista del servidor.
	 * 
	 * @return	Resultado de la remocion del elemento.
	 */
	public boolean remove(Element element) throws RemoteException, ServerNotActiveException {
		System.out.println("Elemento retirado: " + element);
		boolean flag = elementData.remove(element); 
		System.out.println("Elementos: " + elementData);
		return flag;
	}
}