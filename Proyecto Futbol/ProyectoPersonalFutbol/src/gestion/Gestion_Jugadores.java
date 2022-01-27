package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Equipo;
import modelo.Jugador;
import modelo.Nacionalidad;
import modelo.Representante;
import vista.Vista_Equipo;
import vista.Vista_Nacionalidad;
import vista.Vista_Representante;

public class Gestion_Jugadores extends Conexion implements Constantes {

	public Gestion_Jugadores() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Metodo para insertar un jugador en la base de datos
	public static int insertarJugador(Jugador jugador) {

		int id_insertado = 0;
		if (!existeJugador(jugador.getNombre())) {

			Connection conexion = null;

			conexion = crearConexion();

			int equipo_id = jugador.getEquipo();
			int nacion_id = jugador.getNacionalidad();
			int representante_id = jugador.getRepresentante();
			int ha_sido_internacional = Util.ha_sido_internacionalAbit(jugador.isHa_sido_internacional());

			if (Gestion_Equipos.existeEquipo(equipo_id) == false) {
				System.out.println("No existe el equipo indicado en la base de datos. Introduzca uno nuevo.\n");
				Equipo equipo = Vista_Equipo.leerDatosEquipo();
				equipo_id = Gestion_Equipos.insertarEquipo(equipo);
			}

			if (Gestion_Naciones.existeNacion(nacion_id) == false) {
				System.out.println("No existe el pais indicado en la base de datos. Introduzca uno nuevo.\n");
				Nacionalidad nacion = Vista_Nacionalidad.leerDatosNacion();
				nacion_id = Gestion_Naciones.insertarNacion(nacion);
			}

			if (Gestion_Representantes.existeRepresentante(representante_id) == false) {
				System.out.println("No existe el representante en la base de datos. Introduzca uno nuevo.\n");
				Representante representante = Vista_Representante.leerDatosRepresentante();
				representante_id = Gestion_Representantes.insertarRepresentante(representante);
			}

			try {
				Statement st = conexion.createStatement();
				st.executeUpdate(
						"INSERT INTO jugador (nombre,equipo,nacionalidad,salario,representante, ha_sido_internacional ) VALUES "
								+ "('" + jugador.getNombre() + "'," + equipo_id + "," + nacion_id + ","
								+ jugador.getSalario() + "," + representante_id + "," + ha_sido_internacional + ")");

				id_insertado = obtenerJugador(jugador.getNombre());

				st.close();

			} catch (SQLException e) {
				System.err.println(ERROR_SQL);
			} finally {
				if (conexion != null) {
					cerrarConexion(conexion);
				}
			}
		} else {
			System.out.println("Ya existe un jugador con ese nombre");
		}

		return id_insertado;
	}

	//Metodo para buscar si un jugador existe mediante su nombre
	public static boolean existeJugador(String nombre_jug) {

		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM jugador WHERE nombre LIKE '" + nombre_jug + "'");

			if (resultado.next()) {
				return existe;
			}

			st.close();

		} catch (SQLException e) {
			System.out.println();
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return existe;

	}

	//Metodo para buscar un jugador mediante su nombre
	public static boolean existeJugadorNombre(String nombre_jug) {

		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM jugador WHERE nombre LIKE '" + nombre_jug + "'");

			if (resultado.next()) {
				existe = true;
			}

			st.close();

		} catch (SQLException e) {
			System.err.println(ERROR_SQL);
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return existe;

	}

	//Metodo para obtener jugador mediante su nombre
	public static int obtenerJugador(String nombre_jug) {

		Connection conexion = null;
		try {
			conexion = crearConexion();

			// Crea un prepredstatement
			PreparedStatement pst = conexion.prepareStatement("SELECT id_jugador FROM jugador WHERE nombre LIKE ?");

			pst.setString(1, nombre_jug);

			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				return resultado.getInt(1);

			}

			// Cierra el statement
			pst.close();

		} catch (SQLException e) {
			System.err.println("ERROR: al obtener el id del jugador cuyo nombre es" + nombre_jug);
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return 0;
	}

	//Metodo para buscar jugadores del mismo equipo
	public static void listaDeJugadoresDeEquipo(int id_equipo) {
		// Para llamar a un procedimiento almacenado:
		CallableStatement cs;
		Connection conexion = null;
		try {

			conexion = crearConexion();

			cs = conexion.prepareCall("CALL DAMEJUGADOR(?)");
			cs.setInt(1, id_equipo);

			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("id_jugador") + ", Nombre: " + rs.getString("nombre")
						+ ", Nacionalidad: " + rs.getInt("nacionalidad") + ", Representante: " + rs.getInt("representante")
						+ ", Salario: " + rs.getDouble("salario") + ", Ha sido internacional: "
						+ rs.getInt("ha_sido_internacional"));
			}

			rs.close();
			cs.close();

		} catch (SQLException e) {
			System.err.println(ERROR_SQL);
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}

	}
	
	//Metodo para buscar jugadores con el mismo patrocinador
	public static void listaDeJugadoresMismoRepresentante(int id_representante) {
		// Para llamar a un procedimiento almacenado:
		CallableStatement cs;
		Connection conexion = null;
		try {

			conexion = crearConexion();

			cs = conexion.prepareCall("CALL DAMEJUGADORMISMOREPRESENTANTE(?)");
			cs.setInt(1, id_representante);

			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("id_jugador") + ", Nombre: " + rs.getString("nombre")
						+ ", Nacionalidad: " + rs.getInt("nacionalidad") + ", Equipo: " + rs.getInt("equipo")
						+ ", Salario: " + rs.getDouble("salario") + ", Ha sido internacional: "
						+ rs.getInt("ha_sido_internacional"));
			}

			rs.close();
			cs.close();

		} catch (SQLException e) {
			System.err.println(ERROR_SQL);
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}

	}
	
	//Metodo para buscar jugadores que han sido internacionales
	public static void listaDeJugadoresInternacionales() {
		// Para llamar a un procedimiento almacenado:
		CallableStatement cs;
		Connection conexion = null;
		try {

			conexion = crearConexion();

			cs = conexion.prepareCall("CALL DAMEJUGADORINTERNACIONAL(?)");
			cs.setInt(1, 1);

			ResultSet rs = cs.executeQuery();

			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("id_jugador") + ", Nombre: " + rs.getString("nombre")
						+ ", Nacionalidad: " + rs.getInt("nacionalidad") + ", Equipo: " + rs.getInt("equipo")
						+ ", Salario: " + rs.getDouble("salario") + ", Representante: "
						+ rs.getInt("representante"));
			}

			rs.close();
			cs.close();

		} catch (SQLException e) {
			System.err.println(ERROR_SQL);
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}

	}

}