package vista;

import gestion.Gestion_Naciones;
import gestion.Util;
import modelo.Nacionalidad;

public class Vista_Nacionalidad extends Vista_Principal {

	public static void menu_nacionalidad() {

		int opcion = 0;
		Nacionalidad nacion;
		do {
			muestra_menu();
			opcion = Util.leerOpcion();

			switch (opcion) {
			case 1:
				nacion = leerDatosNacion();
				if (nacion != null) {
					int ha_insertado = Gestion_Naciones.insertarNacion(nacion);
					if(ha_insertado == VALOR_ERROR_INSERCCION) {
						System.err.println("Error en la insercción de la nacion");
					}else {
						System.out.println("Se ha insertado la nacion correctamente");
					}
				} else {
					System.err.println("Error en la lectura de la nacion");
				}
				break;
			case 2:
				System.out.println(TEXTO_VOLVER);
				break;
			default:
				System.out.println("Elige una opción entre 1 y 2");
			}

		} while (opcion != SALIR_GESTION_NACIONALIDAD);
	}

	public static void muestra_menu() {

		System.out.println("---- MENÚ GESTIÓN NACIONALIDAD ----");
		System.out.println("1. Insertar nueva nacion en la base de datos.\n");
		System.out.println("2. Volver.\n");
		System.out.print("Escoja una opcion: ");
	}

	public static Nacionalidad leerDatosNacion() {
		String nombre_pais;

		Nacionalidad nacion;

		nombre_pais = leerCampo(MENSAJE_NOMBRE_NACION, PATRON_NOMBRES);

		nacion = new Nacionalidad(nombre_pais);

		return nacion;

	}

}
