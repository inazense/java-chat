package claver.inazio.cliente;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import claver.inazio.utiles.Literales;
import claver.inazio.utiles.Usuario;

/**
 * Interfaz gráfica del chat
 * @author Inazio
 *
 */
public class GUI {

	// Propiedades
	private JScrollPane scroll;
	private JTextArea areaConversacion;
	private JTextField txtMensaje;
	private JButton btnEnviar;
	private Usuario usuario;
	
	// Constructor
	
	/**
	 * Inicialza los componentes de la interfaz gráfica
	 * @param contenedor Es el contenedor donde se agregan todos los elementos
	 */
	public GUI(Container contenedor, Usuario usuario) {
		
		this.usuario = usuario;
		contenedor.setLayout(new BorderLayout());
		this.areaConversacion = new JTextArea();
		this.areaConversacion.setEditable(false);
		this.scroll = new JScrollPane(this.areaConversacion);
		
		JPanel panel = new JPanel(new FlowLayout());
		this.txtMensaje = new JTextField(50);
		this.btnEnviar = new JButton(Literales.BOTON_ENVIAR);
		panel.add(this.txtMensaje);
		panel.add(this.btnEnviar);
		
		contenedor.add(this.scroll, BorderLayout.CENTER);
		contenedor.add(panel, BorderLayout.SOUTH);
	}
	
	// Métodos
	
	/**
	 * Agrego un escuchador cuando se pulse:
	 * -> Intro en la caja de texto
	 * -> El botón de Enviar
	 * @param accion
	 */
	public void addActionListener(ActionListener accion) {
		this.txtMensaje.addActionListener(accion);
		this.btnEnviar.addActionListener(accion);
	}
	
	/**
	 * Agrego el texto al textarea para mostrar la conversación
	 * @param texto
	 */
	public void agregarTexto(String texto) {
		if (!texto.equals("\n")) {
			this.areaConversacion.append(texto);
		}
	}
	
	/**
	 * Recojo el texto de la caja y borro el mensaje
	 * @return Texto del txtMensaje
	 */
	public String recogerMensaje() {
		String texto = "";
		if (!this.txtMensaje.getText().equals("")) {
			texto = usuario.getNombre() + ": " + this.txtMensaje.getText();
		}
		
		this.txtMensaje.setText(Literales.MENSAJE_LIMPIO);
		
		return texto;
	}
}
