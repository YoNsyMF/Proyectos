package modelo;

public class Jugador {
	int id_jug;
	String nombre;
	int equipo;
	int nacionalidad;
	double salario;
	int representante;
	boolean ha_sido_internacional;
	
	public Jugador(int id_jug, String nombre, int equipo, int nacionalidad, double salario, int representante,
			boolean ha_sido_internacional) {
		super();
		this.id_jug = id_jug;
		this.nombre = nombre;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.salario = salario;
		this.representante = representante;
		this.ha_sido_internacional = ha_sido_internacional;
	}

	public Jugador(String nombre, int equipo, int nacionalidad, double salario, int representante,
			boolean ha_sido_internacional) {
		super();
		this.nombre = nombre;
		this.equipo = equipo;
		this.nacionalidad = nacionalidad;
		this.salario = salario;
		this.representante = representante;
		this.ha_sido_internacional = ha_sido_internacional;
	}

	public Jugador(int id_jug, String nombre, double salario, boolean ha_sido_internacional) {
		super();
		this.id_jug = id_jug;
		this.nombre = nombre;
		this.salario = salario;
		this.ha_sido_internacional = ha_sido_internacional;
	}

	public int getId_jug() {
		return id_jug;
	}

	public void setId_jug(int id_jug) {
		this.id_jug = id_jug;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEquipo() {
		return equipo;
	}

	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}

	public int getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getRepresentante() {
		return representante;
	}

	public void setRepresentante(int representante) {
		this.representante = representante;
	}

	public boolean isHa_sido_internacional() {
		return ha_sido_internacional;
	}

	public void setHa_sido_internacional(boolean ha_sido_internacional) {
		this.ha_sido_internacional = ha_sido_internacional;
	}

	@Override
	public String toString() {
		return "Jugador: Id_jug = " + id_jug + ", Nombre = " + nombre + ", Equipo = " + equipo + ", Nacionalidad = "
				+ nacionalidad + ", Salario = " + salario + ", Representante = " + representante
				+ ", Ha sido internacional = " + ha_sido_internacional;
	}
	
	
}
