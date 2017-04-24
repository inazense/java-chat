package claver.inazio.utiles;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class LectorProperties {

	// Propiedades
	private static String path = Literales.PROPERTIES_PATH;
	
	// Métodos
	/**
	 * Devuelve la propiedad especificada de un archivo de configuración determinado
	 * @param path
	 * @param miClave
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPropiedad(String miClave) throws FileNotFoundException, IOException {
		
		String resultado = null;
		
		Properties propiedades = new Properties();
		propiedades.load(new FileReader(path));
		
		Enumeration<Object> claves = propiedades.keys();
		
		while (claves.hasMoreElements()) {
			Object clave = claves.nextElement();
			if (clave.toString().equals(miClave)) {
				resultado = propiedades.get(clave).toString();
			}
		}
		
		return resultado;
	}
}