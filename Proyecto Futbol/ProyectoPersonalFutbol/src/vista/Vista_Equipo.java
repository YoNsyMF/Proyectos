package vista;

import gestion.Gestion_Equipos;
import gestion.Gestion_Jugadores;
import gestion.Gestion_Patrocinadores;
import gestion.Gestion_Representantes;
import gestion.Util;
import modelo.Equipo;

public class Vista_Equipo extends Vista_Principal {

	public static void menu_equipo() {

		int opcion = 0;
		Equipo equipo;
		do {
			muestra_menu();
			opcion = Util.leerOpcion();

			switch (opcion) {
			case 1:
				equipo = leerDatosEquipo();
				if (equipo != null) {
					int ha_insertado = Gestion_Equipos.insertarEquipo(equipo);
					if(ha_insertado == VALOR_ERROR_INSERCCION) {
						System.err.println("Error en la inserccion del equipo");
					}else {
						System.out.println("Se ha insertado el equipo correctamente");
					}
				}else {
					System.err.println("Error en la lectura de equipo");
				}
				
				break;
			case 2:
				String nombre_patrocinador = leerCampo(MENSAJE_NOMBRE_PATROCINADOR, PATRON_NOMBRES);
				if(!Gestion_Patrocinadores.existePatrocinadorNombre(nombre_patrocinador)) {
					System.out.println("No existe el nombre del patrocinador indicado");
				}else {
					System.out.print("Numero de equipos con el patrocinador " + nombre_patrocinador + ":");
					Gestion_Equipos.numeroEquiposMismoPatrocinador(Gestion_Patrocinadores.obtenerPatrocinador(nombre_patrocinador));
				}
				
				break;
			case 3:
				System.out.println(TEXTO_VOLVER);
				break;
			default:
				System.out.println("Elige una opción entre 1 y 3");
			}

		} while (opcion != SALIR_GESTION_EQUIPO);
	}

	//Menu equipos
	public static void muestra_menu() {

		System.out.println("---- MENÚ GESTIÓN EQUIPO ----");
		System.out.println("1. Insertar nuevo equipo en la base de datos.");
		System.out.println("2. Mostrar lista de equipos bajo el mismo patrocinador.\n");
		System.out.println("3. Volver.\n");
		System.out.print("Escoja una opcion: ");
	}

	//Lectura de datos de equipo
	public static Equipo leerDatosEquipo() {
		String nombre, fondos, nacionalidad, patrocinador;

		Equipo equipo;
		
		nombre = leerCampo(MENSAJE_NOMBRE_EQUIPO, PATRON_NOMBRES);
		fondos = leerCampo(MENSAJE_FONDOS_EQUIPO, PATRON_DINERO);
		nacionalidad = leerCampo(MENSAJE_CODIGO_NACIONALIDAD, PATRON_CODIGOS);
		patrocinador = leerCampo(MENSAJE_CODIGO_PATROCINADOR, PATRON_CODIGOS);

		equipo = new Equipo(nombre, Double.parseDouble(fondos), Integer.parseInt(nacionalidad),
				Integer.parseInt(patrocinador));

		return equipo;
	}

}
