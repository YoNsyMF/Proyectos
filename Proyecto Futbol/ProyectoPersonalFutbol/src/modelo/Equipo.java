package modelo;

public class Equipo {
	int id_equipo;
	String nombre;
	double fondos;
	int nacionalidad;
	int patrocinador;
	
	public Equipo(int id_equipo, String nombre, double fondos, int nacionalidad, int patrocinador) {
		super();
		this.id_equipo = id_equipo;
		this.nombre = nombre;
		this.fondos = fondos;
		this.nacionalidad = nacionalidad;
		this.patrocinador = patrocinador;
	}

	public Equipo(String nombre, double fondos, int nacionalidad, int patrocinador) {
		super();
		this.nombre = nombre;
		this.fondos = fondos;
		this.nacionalidad = nacionalidad;
		this.patrocinador = patrocinador;
	}

	public Equipo(String nombre, double fondos) {
		super();
		this.nombre = nombre;
		this.fondos = fondos;
	}

	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getFondos() {
		return fondos;
	}

	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	public int getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(int patrocinador) {
		this.patrocinador = patrocinador;
	}

	@Override
	public String toString() {
		return "Equipo: Id_equipo = " + id_equipo + ", Nombre = " + nombre + ", Fondos = " + fondos + ", Nacionalidad = "
				+ nacionalidad + ", Patrocinador = " + patrocinador;
	}
	
	
}
