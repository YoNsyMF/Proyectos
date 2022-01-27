package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Patrocinador;

public class Gestion_Patrocinadores extends Conexion implements Constantes{

	public Gestion_Patrocinadores() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Metodo para insertar patrocinador
	public static int insertarPatrocinador(Patrocinador patrocinador) {
		
		int id_insertado = 0;
		if (!existePatrocinadorNombre(patrocinador.getNombre())) {
			Connection conexion = null;

			conexion = crearConexion();

			try {

				Statement st = conexion.createStatement();
				st.executeUpdate("INSERT INTO patrocinador (nombre, dinero_aporta) VALUES ('" + patrocinador.getNombre() + "'," + patrocinador.getDinero_aporta() + ")");
				
				id_insertado = obtenerPatrocinador(patrocinador.getNombre());
				st.close();

			} catch (SQLException e) {
				System.err.println(ERROR_SQL);
			} finally {
				if (conexion != null) {
					cerrarConexion(conexion);
				}
			}
		}else {
			System.out.println("Ya existe un patrocinador con ese nombre");
			
		}
		
		return id_insertado;
		
	}
	
	//Metodo para buscar si existe un patrocinador mediante su id
	public static boolean existePatrocinador(int patrocinador_id) {
		
		Connection conexion = null;

		conexion = crearConexion();
		
		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM patrocinador WHERE id_patrocinador = " + patrocinador_id);

			if (resultado.next()) {
				existe = true;
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
	
	//Metodo para buscar si existe patrocinador mediante su nombre
	public static boolean existePatrocinadorNombre(String nombrePatrocinador) {
		
		Connection conexion = null;

		conexion = crearConexion();
		
		boolean existe = false;

		try {
			Statement st = conexion.createStatement();

			ResultSet resultado = st.executeQuery("SELECT * FROM patrocinador WHERE nombre LIKE '" + nombrePatrocinador + "'");

			if (resultado.next()) {
				existe = true;
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
	
	//Metodo para obtener patrocinador mediante su nombre
	public static int obtenerPatrocinador (String nombrePatrocinador) {
		
		Connection conexion = null;
		try {
			conexion = crearConexion();

			// Crea un prepredstatement
			PreparedStatement pst = conexion.prepareStatement("SELECT id_patrocinador FROM patrocinador WHERE nombre LIKE ?");
			
			pst.setString(1 ,nombrePatrocinador);

			// Ejecuta la insercción
			ResultSet resultado = pst.executeQuery();

			 if(resultado.next()) {
				 return resultado.getInt(1);
				 
			 }
			
			// Cierra el statement
			pst.close();

		} catch (SQLException e) {
			System.err.println("ERROR: al obtener el id del patrocinador cuyo nombre es" + nombrePatrocinador);
			e.printStackTrace();
		} finally {
			if (conexion != null) {
				cerrarConexion(conexion);
			}
		}
		return 0;
	}

}
