package gestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Clase utill con una serie de utilidades que usaremos a lo largo del proyecto
public class Util implements Constantes {
	public static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

	public static int leerOpcion() {
		int opcion = 0;
		try {
			opcion = Integer.parseInt(teclado.readLine());
		} catch (NumberFormatException e) {
			System.err.println(ERROR_FORMATO_NUMERICO);
		} catch (IOException e) {
			System.err.println(ERROR_ENTRADA_SALIDA);
		}
		return opcion;
	}

	public static boolean validar(String campo, String patron) {
		
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(campo);

		if (m.matches()) {
			return true;
		}

		return false;
	}
	
	public static boolean StringABooleanInternacional(String stringInternacional) {
		boolean internacional = false;
		
		if (stringInternacional.compareToIgnoreCase("Si") == 0) {
			internacional = true;
		} else if (stringInternacional.compareToIgnoreCase("No") == 0) {
			internacional = false;
		}
		
		return internacional;
		
	}
	
	public static int ha_sido_internacionalAbit(boolean internacional) {
		if (internacional) {
			return 1;
		}
		return 0;
	}
	
}
