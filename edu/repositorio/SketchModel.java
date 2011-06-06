package edu.repositorio;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Iterator;
import java.util.Observable;

import edu.logica.abstractFactory.Element;
import edu.rmi.ISketcherServer;

/**
 * Clase que hereda de Observable y su responsabilidad es la de
 * manejar todos los eventos internos de la aplicacion e informar
 * a la aplicacion que fue ejecutado cierto evento.
 * 
 * @author Camilo Nova.
 */
public class SketchModel extends Observable implements Serializable, Constants {

	/**
	 * Representa la lista de elementos que la aplicacion crea.
	 * El cual se encuentra en el servidor RMI.
	 */
	private ISketcherServer elementList;
	
	/**
	 * Constructor de la clase, se conecta al servidor RMI, para
	 * obtener la instancia de los elementos en el servidor.
	 */
	public SketchModel() {
		try {
			elementList = (ISketcherServer) Naming.lookup(SERVER_IP_ADDRESS + SKETCHER_RMI_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
  	/**
  	 * Metodo que agrega un elemento a la lista remota.
  	 * @param element			Elemento a agregar.
  	 */
  	public void add(Element element) {
		try {
			elementList.add(element);
			System.out.println(element);
			System.out.println("*" + elementList.getData());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
	    setChanged();
		notifyObservers(element.getBounds());
  	}
  	
	/**
	 * Metodo que elimina un elemento de la lista remota.
	 * @param element			Elemento a eliminar.
	 * @return	Resultado de la eliminacion.
	 */
	public boolean remove(Element element) {
		boolean removed = false;
		try {
			removed = elementList.remove(element);
			System.out.println(element);
			System.out.println("*" + elementList.getData());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
		if(removed) {
      		setChanged();
     		notifyObservers(element.getBounds());
    	}
    	return removed;
  	}
  
  	/**
  	 * Retorna el iterador de la lista, el cual sirve para acceder
  	 * a los miembros de la lista mediante el patron Iterator.
  	 * @return	Iterador de la lista.
  	 */
  	public Iterator getIterator() {
  		Iterator iterator = null;
  		try {
			iterator = elementList.getData().listIterator();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}
		return iterator;
  	}
}