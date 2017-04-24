package claver.inazio.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import claver.inazio.utiles.Literales;

/**
 * Atiende las peticiones de un usuario.
 * Muestra el contenido recibido en el textArea
 * y el contenido que escribe en el txtField lo envía por el socket
 * @author Inazio
 *
 */
public class Controlador implements ActionListener, Runnable {

	// Propiedades
	private DataInputStream inputDatos;
	private DataOutputStream outputDatos;
	private GUI interfaz;
	
	// Constructor
	public Controlador(Socket socket, GUI interfaz) {
		this.interfaz = interfaz;
		
		// Lanza un nuevo hilo para atender al socket
		try {
			this.inputDatos = new DataInputStream(socket.getInputStream());
			this.outputDatos = new DataOutputStream(socket.getOutputStream());
			this.interfaz.addActionListener(this);
			
			Thread hilo = new Thread(this);
			hilo.start();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Literales.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Atiende al socket. Todo lo que llegue por el socket lo muestra en la interfaz gráfica
	 */
	@Override
	public void run() {
		
		try {
			while (true) {
				String texto = this.inputDatos.readUTF();
				this.interfaz.agregarTexto(texto + "\n");
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Literales.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Recoge el mensaje y lo envía por el socket.
	 */
	@Override
	public void actionPerformed(ActionEvent evento) {
		
		try {
			this.outputDatos.writeUTF(this.interfaz.recogerMensaje());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Literales.ERROR, JOptionPane.ERROR_MESSAGE);
		}		
	}
	
}
