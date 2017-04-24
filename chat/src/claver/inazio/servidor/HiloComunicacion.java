package claver.inazio.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Hilo que se lanza cuando comunica un cliente con el servidor
 * @author Inazio
 *
 */
@SuppressWarnings("rawtypes")
public class HiloComunicacion implements Runnable, ListDataListener {

	// Propiedades
	
	private DefaultListModel mensajes; // Lista que almacena los mensajes del chat
	@SuppressWarnings("unused")
	private Socket socket; // Socket al que se conecta el cliente
	private DataInputStream inputDatos; // Lee mensajes del socket
	private DataOutputStream outputDatos; // Escribe datos en el socket
	private boolean continuarHilo = true;
	
	// Constructor
	public HiloComunicacion(DefaultListModel mensajes, Socket socket) throws IOException {
		
		this.mensajes = mensajes;
		this.socket = socket;
		
		// Suscribo el socket a los cambios que se produzcan en la conversación
		inputDatos = new DataInputStream(socket.getInputStream());
		outputDatos = new DataOutputStream(socket.getOutputStream());
		mensajes.addListDataListener(this);
	}
	
	// Métodos
	
	/**
	 * Todo el mensaje que llegue lo agrega a la conversación
	 */
	@SuppressWarnings("unchecked")
	public void run() {
		try {
			while (this.continuarHilo) {
				String texto = inputDatos.readUTF();
				synchronized (mensajes) {
					mensajes.addElement(texto);
				}
			}
		}
		catch(Exception e) {
			//e.printStackTrace();
			this.detenerHilo();
		}
	}
	
	/**
	 * Detiene la ejecución del hilo actual
	 */
	public void detenerHilo() {
		this.continuarHilo = false;
	}
	
	/**
	 * Manda el último mensaje de la cnversación por el socket.
	 */
	public void intervalAdded(ListDataEvent evento) {
		
		String texto = (String)mensajes.getElementAt(evento.getIndex0());
		
		try {
			outputDatos.writeUTF(texto);
		} 
		catch (IOException e) {
			//e.printStackTrace();
			this.detenerHilo();
		}
	}
	
	/**
	 * Métodos no empleados pero obligatorios de implementar
	 */
	public void intervalRemoved(ListDataEvent evento) {}
	public void contentsChanged(ListDataEvent evento) {}
}
