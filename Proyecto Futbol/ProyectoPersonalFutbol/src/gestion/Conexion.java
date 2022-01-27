package gestion;
import java.sql.*;

public class Conexion {

	//Creamos la conexion
	public static Connection crearConexion() {
		
		Connection conexion = null;
		// Crea una conexion

		try {
			// Cargar el driver JDBC. Pasamos como parámetro al método forName
			// el nombre de la clase del driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establecemos la conexión con la BD

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/futbol", "root", "");

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("ERROR: dirección no válida o usuario/clave");
			e.printStackTrace();
		}

		 return conexion;

	}
	
	//Cerramos la conexion
	static void cerrarConexion(Connection conexion){
		try {
			conexion.close();
		} catch (SQLException e) {
			System.err.println("Error de SQL");
		}
	}

}
