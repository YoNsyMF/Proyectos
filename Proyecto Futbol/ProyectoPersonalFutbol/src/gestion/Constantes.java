package gestion;

//Clase con constantes que utilizaremos a lo largo del proyecto
public interface Constantes {
	
	final public int SALIR_MENU_PRINCIPAL = 6;
	final public int SALIR_GESTION_JUGADOR = 5;
	final public int SALIR_GESTION_EQUIPO = 3;
	final public int SALIR_GESTION_REPRESENTANTE = 2;
	final public int SALIR_GESTION_PATROCINADOR = 2;
	final public int SALIR_GESTION_NACIONALIDAD = 2;
	
	final public String ERROR_ENTRADA_SALIDA = "Error en la entrada y salida de datos.";
	final public String ERROR_FORMATO_NUMERICO = "Error, se necesita un formato numerico.";
	
	final public String TEXTO_VOLVER = "Volviendo al menu principal...\n";
	
	public String MENSAJE_CODIGO_EQUIPO = "Codigo de su equipo(de 1 a 15 digitos enteros): ";
	public String MENSAJE_CODIGO_REPRESENTANTE = "Codigo de su representante(de 1 a 15 digitos enteros): ";
    public String MENSAJE_CODIGO_NACIONALIDAD = "Codigo de la nacionalidad a la que pertenece(de 1 a 15 digitos enteros): ";
    public String MENSAJE_CODIGO_PATROCINADOR = "Codigo del patrocinador del equipo (de 1 a 15 digitos enteros): ";
    
    public String MENSAJE_NOMBRE_JUGADOR = "Nombre del jugador (de 4 a 50 caracteres): ";
    public String MENSAJE_NOMBRE_REPRESENTANTE = "Nombre del representante (de 4 a 50 caracteres): ";
    public String MENSAJE_NOMBRE_EQUIPO = "Nombre del equipo (de 4 a 50 caracteres): ";
    public String MENSAJE_NOMBRE_PATROCINADOR = "Nombre del patrocinador (de 4 a 50 caracteres): ";
    public String MENSAJE_NOMBRE_NACION = "Nombre del pais (de 4 a 50 caracteres): ";
    public String MENSAJE_SALARIO = "Salario del jugador(solo numeros): ";
    public String MENSAJE_HA_SIDO_INTERNACIONAL = "¿Ha sido internacional? (Si o No): ";
    public String MENSAJE_FONDOS_EQUIPO = "Fondos del equipo(solo numeros): ";
    public String MENSAJE_DINERO_APORTA = "Dinero que aporta el patrocinador (solo numeros): ";
    public String MENSAJE_COMISION = "Comision que cobra el representante a su jugador (solo numeros): ";
    
    public String PATRON_CODIGOS = "[0-9]{1,15}";
    public String PATRON_NOMBRES = "[A-Za-z ]{2,50}";
    public String PATRON_DINERO = "[0-9]+([/.][0-9]+)?";
    public String PATRON_HA_SIDO_INTERNACIONAL = "^(?:Si|No|no|si|nO|sI|SI|NO)$";
    
    public String ERROR_SQL = "Error, la consulta SQL no se pudo hacer";
    
    public int VALOR_ERROR_INSERCCION = 0;
	
}
