package gestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Representante;

public class Gestion_Representantes extends Conexion implements Constantes {

    public Gestion_Representantes() {
        super();

    }

	//Metodo para insertar representantes
    public static int insertarRepresentante(Representante representante) {

        int id_insertado = 0;

        if (!existeRepresentanteNombre(representante.getNombre())) {
            Connection conexion = null;

            conexion = crearConexion();

            try {

                Statement st = conexion.createStatement();
                st.executeUpdate("INSERT INTO representante (nombre, comision) VALUES ('" + representante.getNombre()
                        + "'," + representante.getComision() + ")");
                id_insertado = obtenerRepresentante(representante.getNombre());
                st.close();

            } catch (SQLException e) {
                System.err.println(ERROR_SQL);
            } finally {
                if (conexion != null) {
                    cerrarConexion(conexion);
                }
            }
        } else {
            System.out.println("Ya existe un representante con ese nombre");
        }
        return id_insertado;

    }

	//Metodo para obtener un representante mediante su nombre
    public static int obtenerRepresentante(String nombreRepresentante) {

        Connection conexion = null;
        try {
            conexion = crearConexion();

            // Crea un prepredstatement
            PreparedStatement pst = conexion
                    .prepareStatement("SELECT id_representante FROM representante WHERE nombre LIKE ?");

            pst.setString(1, nombreRepresentante);

            // Ejecuta la insercción
            ResultSet resultado = pst.executeQuery();

            if (resultado.next()) {
                return resultado.getInt(1);

            }

            // Cierra el statement
            pst.close();

        } catch (SQLException e) {
            System.err.println("ERROR: al obtener el id del representante cuyo nombre es" + nombreRepresentante);
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                cerrarConexion(conexion);
            }
        }
        return 0;
    }

	//Metodo para obtener si un representante existe o no mediante su id
    public static boolean existeRepresentante(int representante_id) {

        Connection conexion = null;

        conexion = crearConexion();

        boolean existe = false;

        try {
            Statement st = conexion.createStatement();

            ResultSet resultado = st
                    .executeQuery("SELECT * FROM representante WHERE id_representante = " + representante_id);

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

	//Metodo para obtener si un representante existe o no mediante su nombre
    public static boolean existeRepresentanteNombre(String nombreRepresentante) {

        Connection conexion = null;

        conexion = crearConexion();

        boolean existe = false;

        try {
            Statement st = conexion.createStatement();

            ResultSet resultado = st
                    .executeQuery("SELECT * FROM representante WHERE nombre LIKE '" + nombreRepresentante + "'");

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

}
