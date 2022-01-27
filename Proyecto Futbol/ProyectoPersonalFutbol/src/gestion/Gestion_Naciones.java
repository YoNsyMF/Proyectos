package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Nacionalidad;

public class Gestion_Naciones extends Conexion implements Constantes {

	public Gestion_Naciones() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Metodo para insertar una nacion
	public static int insertarNacion(Nacionalidad nacion) {
		
		int id_insertado = 0;
		if (existeNacionNombre(nacion.getNombre_pais()) == false) {
			Connection conexion = null;

			conexion = crearConexion();

			try {
				Statement st = conexion.createStatement();
				st.executeUpdate("INSERT INTO nacionalidad (nombre_pais) VALUES ('" + nacion.getNombre_pais() + "')");
				
				id_insertado = obtenerNacion(nacion.getNombre_pais());
				
				st.close();

			} catch (SQLException e) {
				System.err.println("1." + ERROR_SQL);
			} finally {
				if (conexion != null) {
					cerrarConexion(conexion);
				}
			}
		}else {
			System.out.println("Ya existe una nacion con ese nombre");
		}
		
		return id_insertado;
	}

	//Metodo para buscar si existe o no una nacion mediante su id
	public static boolean existeNacion(int nacion_id) {

		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM nacionalidad WHERE id_pais = " + nacion_id);

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

	//Metodo para buscar si existe o no una nacion mediante su nombre
	public static boolean existeNacionNombre(String nombreNacion) {

		Connection conexion = null;

		conexion = crearConexion();

		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st
					.executeQuery("SELECT * FROM nacionalidad WHERE nombre_pais LIKE '" + nombreNacion + "'");

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
	
	//Metodo para buscar una nacion mediante su nombre
	public static int obtenerNacion (String nombreNacion) {
		
		Connection conexion = null;
		try {
			conexion = crearConexion();

			// Crea un prepredstatement
			PreparedStatement pst = conexion.prepareStatement("SELECT id_pais FROM nacionalidad WHERE nombre_pais LIKE ?");
			
			pst.setString(1 ,nombreNacion);

			// Ejecuta la insercción
			ResultSet resultado = pst.executeQuery();

			 if(resultado.next()) {
				 return resultado.getInt(1);
				 
			 }
			
			// Cierra el statement
			pst.close();

		} catch (SQLException e) {
			System.err.println("ERROR: al obtener el id del pais cuyo nombre es" + nombreNacion);
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return 0;
	}

}
