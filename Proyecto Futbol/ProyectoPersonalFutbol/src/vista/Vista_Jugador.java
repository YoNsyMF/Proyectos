package vista;

import gestion.Gestion_Equipos;
import gestion.Gestion_Jugadores;
import gestion.Gestion_Representantes;
import gestion.Util;
import modelo.Jugador;

public class Vista_Jugador extends Vista_Principal{

	public static void menu_jugador() {

		int opcion = 0;
		Jugador jugador;
		do {
			muestra_menu();
			opcion = Util.leerOpcion();

			switch (opcion) {
			case 1:
				jugador = leerDatosJugador();
				if (jugador != null) {
					int ha_insertado = Gestion_Jugadores.insertarJugador(jugador);
					if(ha_insertado == VALOR_ERROR_INSERCCION) {
						System.err.println("Error en la inserccion del jugador");
					}else {
						System.out.println("Se ha insertado correctamente el jugador");
					}
				}else {
					System.err.println("Error en la lectura del jugador");
				}
				break;
			case 2:
				String nombre_equipo = leerCampo(MENSAJE_NOMBRE_EQUIPO, PATRON_NOMBRES);
				
				if(!Gestion_Equipos.existeEquipoNombre(nombre_equipo)) {
					System.out.println("No existe el nombre del equipo indicado");
				}else {
					System.out.println("Lista de jugadores del " + nombre_equipo + ":");
					Gestion_Jugadores.listaDeJugadoresDeEquipo(Gestion_Equipos.obtenerEquipo(nombre_equipo));
				}
				break;
			case 3:
				String nombre_representante = leerCampo(MENSAJE_NOMBRE_REPRESENTANTE, PATRON_NOMBRES);
				if(!Gestion_Representantes.existeRepresentanteNombre(nombre_representante)) {
					System.out.println("No existe el nombre del representante indicado");
				}else {
					System.out.println("Lista de jugadores cuyo representante es " + nombre_representante + ":");
					Gestion_Jugadores.listaDeJugadoresMismoRepresentante(Gestion_Representantes.obtenerRepresentante(nombre_representante));
				}

				break;
			case 4:
				System.out.println("Lista de jugadores que han sido :");
				Gestion_Jugadores.listaDeJugadoresInternacionales();
				break;
			case 5:
				System.out.println(TEXTO_VOLVER);
				break;
			default:
				System.out.println("Elige una opción entre 1 y 5");
			}

		} while (opcion != SALIR_GESTION_JUGADOR);
	}

	//Menu jugadores
	public static void muestra_menu() {

		System.out.println("---- MENÚ GESTIÓN JUGADOR ----");
		System.out.println("1. Insertar nuevo jugador en la base de datos.");
		System.out.println("2. Mostrar lista de jugadores del mismo equipo.");
		System.out.println("3. Mostar lista de jugadores con el mismo representante.");
		System.out.println("4. Mostar lista de jugadores que ha sido internacionales.");
		System.out.println("5. Volver.\n");
		System.out.print("Escoja una opcion: ");
	}

	//Lectura de datos de jugador
	public static Jugador leerDatosJugador() {
		String nombre, equipo, nacionalidad, salario, representante, ha_sido_internacional;

		Jugador jugador;
		
		nombre = leerCampo(MENSAJE_NOMBRE_JUGADOR, PATRON_NOMBRES);
		equipo = leerCampo(MENSAJE_CODIGO_EQUIPO, PATRON_CODIGOS);
		nacionalidad = leerCampo(MENSAJE_CODIGO_NACIONALIDAD, PATRON_CODIGOS);
		salario = leerCampo(MENSAJE_SALARIO, PATRON_DINERO);
		representante = leerCampo(MENSAJE_CODIGO_REPRESENTANTE, PATRON_CODIGOS);
		ha_sido_internacional = leerCampo(MENSAJE_HA_SIDO_INTERNACIONAL, PATRON_HA_SIDO_INTERNACIONAL);

		boolean internacional = Util.StringABooleanInternacional(ha_sido_internacional);

		jugador = new Jugador(nombre, Integer.parseInt(equipo), Integer.parseInt(nacionalidad),
				Double.parseDouble(salario), Integer.parseInt(representante), internacional);

		return jugador;
	}

}
