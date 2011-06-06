package edu.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.LinkedList;

import edu.logica.abstractFactory.Element;
import edu.repositorio.Constants;

/**
 * Interface que define los metodos para el acceso remoto al servidor
 * RMI.
 * @author Camilo Nova.
 */
public interface ISketcherServer extends Remote, Serializable, Constants {
	
	public LinkedList getData() throws RemoteException, ServerNotActiveException;
	
	public Element getElementAt(int s) throws RemoteException, ServerNotActiveException;
	
	public void add(Element element) throws RemoteException, ServerNotActiveException;
	
	public boolean remove(Element element) throws RemoteException, ServerNotActiveException;
}