package vista;

import java.io.IOException;

import gestion.Constantes;
import gestion.Gestion_Equipos;
import gestion.Gestion_Jugadores;
import gestion.Gestion_Naciones;
import gestion.Gestion_Patrocinadores;
import gestion.Gestion_Representantes;
import gestion.Util;

public class Vista_Principal implements Constantes{
	
	public static Gestion_Jugadores gJ = new Gestion_Jugadores();
	public static Gestion_Equipos gE = new Gestion_Equipos();
	public static Gestion_Representantes gR = new Gestion_Representantes();
	public static Gestion_Naciones gN = new Gestion_Naciones();
	public static Gestion_Patrocinadores gP = new Gestion_Patrocinadores();
	
	public static void main(String[] args) {
		
		int opcion = 0;
		do {
			
			muestra_menu();
			opcion = Util.leerOpcion();
			switch (opcion) {
			case 1:
				Vista_Jugador.menu_jugador();
				break;
			case 2:
				Vista_Equipo.menu_equipo();
				break;
			case 3:
				Vista_Representante.menu_representante();
				break;
			case 4:
				Vista_Nacionalidad.menu_nacionalidad();
				break;
			case 5:
				Vista_Patrocinador.menu_patrocinador();
				break;
			case 6:
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Escoge una opcion del 1 al 6.");
			}
			
		}while(opcion != SALIR_MENU_PRINCIPAL);
	}
	
	//Menu principal
	public static void muestra_menu() {
		System.out.println("Bienvenidos. Aqui podras realizar la gestion\n "
				+ "del mundo del futbol en nuestra base de datos.\n");
		System.out.println("------ MENÚ PRINCIPAL -----");
		System.out.println("1.Gestion de los jugadores");
		System.out.println("2.Gestion de los equipos");
		System.out.println("3.Gestion de los representantes");
		System.out.println("4.Gestion de las nacionalidades");
		System.out.println("5.Gestion de los patrocinadores");
		System.out.println("6.Salir\n");
		System.out.print("Escoja una opcion: ");
	}
	
	//Lectura de campos
	protected static String leerCampo(String mensaje, String patron) {
		String campo = null;
		boolean valido = false;
		System.out.print(mensaje);
		try {
			do {
				campo = Util.teclado.readLine();
				valido = Util.validar(campo, patron);
				
				if(!valido) {
					System.err.println("Patron erroneo. Sigue las instruciones del patron.");
				}
			} while (!valido);
		} catch (IOException e) {
			System.err.println(ERROR_ENTRADA_SALIDA);
		}
		return campo;
	}

}
