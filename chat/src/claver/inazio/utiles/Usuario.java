package claver.inazio.utiles;

public class Usuario {

	// Propiedades
	private String nombre;
	
	// Constructores
	public Usuario() {
		this.nombre = Literales.USUARIO_DEFAULT;
	}
	
	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	// Métodos
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.equals("") || nombre == null) {
			this.nombre = Literales.USUARIO_DEFAULT;
		}
		else {
			this.nombre = nombre;
		}
	}
}
