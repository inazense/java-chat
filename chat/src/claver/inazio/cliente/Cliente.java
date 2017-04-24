package claver.inazio.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import claver.inazio.utiles.LectorProperties;
import claver.inazio.utiles.Literales;
import claver.inazio.utiles.Usuario;

/**
 * Establece la conexión con el servidor
 * Genera la interfaz gráfica y el controlador del socket
 * @author Inazio
 *
 */
public class Cliente {

	// Propiedades
	private Socket socket;
	private GUI interfaz;
	private Usuario usuario;
	
	// Constructor
	@SuppressWarnings("unused")
	public Cliente() {
		try {
			String url = LectorProperties.getPropiedad("servidor.ruta");
			int puerto = Integer.parseInt(LectorProperties.getPropiedad("servidor.puerto"));
			
			this.crearGUI();
			this.socket = new Socket(url, puerto); // Conecto al servidor
			Controlador controlador = new Controlador(socket, interfaz);
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Literales.ERROR, JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	// Métodos
	
	/**
	 * Genera la interfaz gráfica del cliente
	 */
	private void crearGUI() {
		usuario = new Usuario();
		this.crearLogin();
		JFrame frame = new JFrame(Literales.TITULO_CHAT + usuario.getNombre());
		interfaz = new GUI(frame.getContentPane(), this.usuario);
		frame.setSize(650, 400);
		//frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	/**
	 * Genera la interfaz gráfica para cargar un cliente
	 * @param frame
	 */
	private void crearLogin() {
		usuario = new Usuario();
		JPanel contentPane = new JPanel();
		JDialog dialogo = new JDialog();
		JTextField txtNombre;
		JLabel lblNombre;
		dialogo.setBounds(100, 100, 344, 128);
		dialogo.getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogo.getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			lblNombre = new JLabel(Literales.LABEL_NOMBRE);
			lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
			contentPane.add(lblNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			contentPane.add(txtNombre);
			txtNombre.setColumns(20);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			dialogo.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(Literales.OK_BUTTON);
				okButton.setActionCommand(Literales.OK_BUTTON);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						usuario.setNombre(txtNombre.getText());
						dialogo.dispose();
					}
				});
				buttonPane.add(okButton);
				dialogo.getRootPane().setDefaultButton(okButton);
			}
		}
		dialogo.setModal(true);
		dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogo.setLocationRelativeTo(null);
		dialogo.setVisible(true);
	}
}
