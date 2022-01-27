package gestion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Equipo;
import modelo.Nacionalidad;
import modelo.Patrocinador;
import vista.Vista_Nacionalidad;
import vista.Vista_Patrocinador;

public class Gestion_Equipos extends Conexion implements Constantes {

	public Gestion_Equipos() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Metodo para insertar equipos en la bse de datos
	public static int insertarEquipo(Equipo equipo) {

		int id_insertado = 0;
		if (!existeEquipoNombre(equipo.getNombre())) {

			Connection conexion = null;

			conexion = crearConexion();

			int nacion_id = equipo.getNacionalidad();
			int patrocinador_id = equipo.getPatrocinador();

			if (Gestion_Naciones.existeNacion(nacion_id) == false) {
				System.out.println("No existe el pais indicado en la base de datos. Introduzca uno nuevo.\n");
				Nacionalidad nacion = Vista_Nacionalidad.leerDatosNacion();
				nacion_id = Gestion_Naciones.insertarNacion(nacion);
			}

			if (Gestion_Patrocinadores.existePatrocinador(patrocinador_id) == false) {
				System.out.println("No existe el patrocinador indicado en la base de datos. Introduzca uno nuevo.\n");
				Patrocinador patrocinador = Vista_Patrocinador.leerDatosPatrocinador();
				patrocinador_id = Gestion_Patrocinadores.insertarPatrocinador(patrocinador);
			}

			try {

				Statement st = conexion.createStatement();
				st.executeUpdate(
						"INSERT INTO equipo (nombre,fondos, nacionalidad, patrocinador) VALUES ('" + equipo.getNombre()
								+ "'," + equipo.getFondos() + "," + nacion_id + "," + patrocinador_id + ")");

				id_insertado = obtenerEquipo(equipo.getNombre());

				st.close();

			} catch (SQLException e) {
				System.err.println(ERROR_SQL);
			} finally {
				if (conexion != null) {
					cerrarConexion(conexion);
				}
			}
		} else {
			System.out.println("Ya existe un equipo con ese nombre");

		}

		return id_insertado;

	}

	//Metodo para buscar el id de un equipo y ver si existe o no
	public static boolean existeEquipo(int equipo_id) {

		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM equipo WHERE id_equipo = " + equipo_id);

			if (resultado.next()) {
				existe = true;
			}
			st.close();

		} catch (SQLException e) {
			System.out.println(ERROR_SQL);
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return existe;
	}

	//Metodo para buscar si un equipo existe mediante su nombre
	public static boolean existeEquipoNombre(String nombreEquipo) {
		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM equipo WHERE nombre LIKE '" + nombreEquipo + "'");

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

	//Metodo para buscar un equipo mediante su nombre
	public static int obtenerEquipo(String nombreEquipo) {

		Connection conexion = null;
		try {
			conexion = crearConexion();

			// Crea un prepredstatement
			PreparedStatement pst = conexion.prepareStatement("SELECT id_equipo FROM equipo WHERE nombre LIKE ?");

			pst.setString(1, nombreEquipo);

			// Ejecuta la insercción
			ResultSet resultado = pst.executeQuery();

			if (resultado.next()) {
				return resultado.getInt(1);

			}

			// Cierra el statement
			pst.close();

		} catch (SQLException e) {
			System.err.println("ERROR: al obtener el id del equipo cuyo nombre es" + nombreEquipo);
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return 0;
	}
	
	//Metodo que busca la cantidad de equipos que tiene un patrocinador introduciendo su id
	public static void numeroEquiposMismoPatrocinador(int id_patrocinador) {
		// Para llamar a un procedimiento almacenado:
		CallableStatement cs;
		Connection conexion = null;
		try {

			conexion = crearConexion();

			cs = conexion.prepareCall("CALL NUMEROEQUIPOSPATROCINADOR(?)");
			cs.setInt(1, id_patrocinador);

			ResultSet rs = cs.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getInt(1));
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
