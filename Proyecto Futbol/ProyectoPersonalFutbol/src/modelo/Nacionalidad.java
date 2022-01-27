package modelo;

public class Nacionalidad {
	int id_pais;
	String nombre_pais;
	
	public Nacionalidad(int id_pais, String nombre_pais) {
		super();
		this.id_pais = id_pais;
		this.nombre_pais = nombre_pais;
	}
	
	public Nacionalidad(String nombre_pais) {
		super();
		this.nombre_pais = nombre_pais;
	}

	public int getId_pais() {
		return id_pais;
	}

	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public String getNombre_pais() {
		return nombre_pais;
	}

	public void setNombre_pais(String nombre_pais) {
		this.nombre_pais = nombre_pais;
	}

	@Override
	public String toString() {
		return "Nacionalidad: Id_pais = " + id_pais + ", Nombre del Pais = " + nombre_pais;
	}
	
	
	
	
}
