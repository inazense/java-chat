package claver.inazio.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;

import claver.inazio.utiles.LectorProperties;

/**
 * Clase que crea un hilo para cada cliente que solicita una conexión
 * @author Inazio
 *
 */
public class Servidor {

	// Propiedades
	@SuppressWarnings("rawtypes")
	private DefaultListModel modeloConversacion = new DefaultListModel(); // Aquí guardamos toda la conversacion
	private ServerSocket servidor;
	private boolean continuarHilo = true;
	
	// Constructor
	public Servidor() {
		try {
		
			servidor = new ServerSocket(Integer.parseInt(LectorProperties.getPropiedad("servidor.puerto")));
			System.out.println("Servidor lanzado");
			// Creo un bucle infinito para quedar a la espera de las conexiones de clientes
			while (this.continuarHilo) {
				
				Socket cliente = servidor.accept();
				Runnable nuevoCliente = new HiloComunicacion(modeloConversacion, cliente);
				Thread hilo = new Thread(nuevoCliente);
				hilo.start();
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
			this.detenerHilo();
		}
	}
	
	// Métodos
	
	/**
	 * Para la ejecución del hilo cambiando el valor que permite el bucle
	 */
	private void detenerHilo() {
		this.continuarHilo = false;
	}
}
