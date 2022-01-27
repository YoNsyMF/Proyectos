package modelo;

public class Patrocinador {
	int id_patrocinador;
	String nombre;
	double dinero_aporta;
	
	public Patrocinador(int id_patrocinador, String nombre, double dinero_aporta) {
		super();
		this.id_patrocinador = id_patrocinador;
		this.nombre = nombre;
		this.dinero_aporta = dinero_aporta;
	}

	public Patrocinador(String nombre, double dinero_aporta) {
		super();
		this.nombre = nombre;
		this.dinero_aporta = dinero_aporta;
	}

	public int getId_patrocinador() {
		return id_patrocinador;
	}

	public void setId_patrocinador(int id_patrocinador) {
		this.id_patrocinador = id_patrocinador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDinero_aporta() {
		return dinero_aporta;
	}

	public void setDinero_aporta(double dinero_aporta) {
		this.dinero_aporta = dinero_aporta;
	}

	@Override
	public String toString() {
		return "Patrocinador: Id_patrocinador = " + id_patrocinador + ", Nombre = " + nombre + ", Dinero que aporta = "
				+ dinero_aporta;
	}
	
	
	
}
