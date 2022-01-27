package vista;

import gestion.Gestion_Representantes;
import gestion.Util;
import modelo.Representante;

public class Vista_Representante extends Vista_Principal{

	public static void menu_representante() {
		
		int opcion = 0;
		Representante representante;
		do {
			muestra_menu();
			opcion = Util.leerOpcion();
			
			switch (opcion) {
			case 1:
				representante = leerDatosRepresentante();
				if(representante != null) {
					int ha_insertado = Gestion_Representantes.insertarRepresentante(representante);
					if(ha_insertado == VALOR_ERROR_INSERCCION) {
						System.err.println("Error en la inserccion del representant");
					}else {
						System.out.println("Se ha insertado correctamente el representante");
					}
				}else {
					System.err.println("Error en la lectura del representante");
				}
				
				break;
			case 2:
				System.out.println(TEXTO_VOLVER);
				break;
			default:
				System.out.println("Elige una opción entre 1 y 2");
			}
			
		}while(opcion != SALIR_GESTION_REPRESENTANTE);
	}

	//Menu representantes
	public static void muestra_menu() {

		System.out.println("---- MENÚ GESTIÓN REPRESENTANTES ----");
		System.out.println("1. Insertar nuevo representante en la base de datos.\n");
		System.out.println("2. Volver.\n");
		System.out.print("Escoja una opcion: ");
	}
	
	//Lectura de datos de representantes
	public static Representante leerDatosRepresentante() {
		
		String nombre, comision;
		
		Representante representante;
		
		nombre = leerCampo(MENSAJE_NOMBRE_REPRESENTANTE, PATRON_NOMBRES);
		comision = leerCampo(MENSAJE_COMISION, PATRON_DINERO);
		
		representante = new Representante(nombre, Double.parseDouble(comision));
		
		return representante;
		
	}

}
