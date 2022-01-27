package modelo;

public class Representante {
	int id_representante;
	String nombre;
	double comision;
	
	public Representante(int id_representante, String nombre, double comision) {
		super();
		this.id_representante = id_representante;
		this.nombre = nombre;
		this.comision = comision;
	}

	public Representante(String nombre, double comision) {
		super();
		this.nombre = nombre;
		this.comision = comision;
	}

	public int getId_representante() {
		return id_representante;
	}

	public void setId_representante(int id_representante) {
		this.id_representante = id_representante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	@Override
	public String toString() {
		return "Representante: Id_representante = " + id_representante + ", Nombre = " + nombre + ",Comision = " + comision;
	}
	
}
