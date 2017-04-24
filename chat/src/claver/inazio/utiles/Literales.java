package claver.inazio.utiles;

import java.util.Random;

public class Literales {

	public static final String BOTON_ENVIAR = "Enviar";
	public static final String MENSAJE_LIMPIO = "";
	public static final String ERROR = "Error";
	public static final String TITULO_CHAT = "Chat | Programando a pasitos | ";
	public static final String USUARIO_DEFAULT = "Programador" + generarNumeroAleatorio();
	public static final String TITULO_DIALOGO = "Escribe un nombre de usuario";
	public static final String LABEL_NOMBRE = "Nombre:";
	public static final String OK_BUTTON = "Continuar";
	
	// Ruta del archivo properties
	public static final String PROPERTIES_PATH = "configuracionSocket.properties";
	
	/**
	 * Genera un número aleatorio para el usuario por defecto
	 * @return Cadena del texto del número aleatorio
	 */
	private static String generarNumeroAleatorio() {
		return String.valueOf(new Random().nextInt(100) + 1);
	}
}
