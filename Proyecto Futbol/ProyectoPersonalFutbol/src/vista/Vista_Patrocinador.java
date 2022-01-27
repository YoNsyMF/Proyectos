package vista;

import gestion.Gestion_Patrocinadores;
import gestion.Util;
import modelo.Patrocinador;

public class Vista_Patrocinador extends Vista_Principal{

	public static void menu_patrocinador() {
		
		int opcion = 0;
		Patrocinador patrocinador;
		do {
			muestra_menu();
			opcion = Util.leerOpcion();
			
			switch (opcion) {
			case 1:
				patrocinador = leerDatosPatrocinador();
				if(patrocinador != null) {
					int ha_insertado = Gestion_Patrocinadores.insertarPatrocinador(patrocinador);
					if(ha_insertado == VALOR_ERROR_INSERCCION) {
						System.err.println("Error en la inserccion del patrocinador");
					}else {
						System.out.println("Se ha insertado correctamente el patrocinador");
					}
				}else {
					System.err.println("Error ene la lectura de equipo");
				}
				break;
			case 2:
				System.out.println(TEXTO_VOLVER);
				break;
			default:
				System.out.println("Elige una opción entre 1 y 2");
			}
			
		}while(opcion != SALIR_GESTION_PATROCINADOR);
	}
	
	//Menu Patrocinador
	public static void muestra_menu() {
		
		System.out.println("---- MENÚ GESTIÓN PATROCINADOR ----");
		System.out.println("1. Insertar nuevo patrocinador en la base de datos.\n");
		System.out.println("2. Volver.\n");
		System.out.print("Escoja una opcion: ");
		
	}
	
	//Lectura de datos de patrocinador
	public static Patrocinador leerDatosPatrocinador() {
		String nombre, dinero_aporta;
		
		Patrocinador patrocinador;
		
		nombre = leerCampo(MENSAJE_NOMBRE_PATROCINADOR, PATRON_NOMBRES);
		dinero_aporta = leerCampo(MENSAJE_DINERO_APORTA, PATRON_DINERO);
		
		patrocinador = new Patrocinador(nombre, Double.parseDouble(dinero_aporta));
		
		return patrocinador;
	}
	
}
