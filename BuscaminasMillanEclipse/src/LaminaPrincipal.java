import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *Clase de la lamina principal del buscaminas
 *
 *@author Yoel Millan
 *@version 1.0
 *@since 1.8
 *@see JPanel
 */
class LaminaPrincipal extends JPanel {

	private JFrame frame;
	private static JButton Casillas[][]; 			// Array biimensional de casillas
	private static int probabilidadMina; 			// Probabilidad de minas en tablero seleccionando la dificultad
	private static int tamaño; 						// Tamaño del tablero
	private static int CantidadBombas;	
	
	/** 
	* Constructor de la LaminaPricipal
	*  
	* @param frame Frame de la lamina principal , creado en {@link #frame}
	* @since 1.0
	*/  
	public LaminaPrincipal(JFrame frame) {
		this.frame = frame;

		// Panel superior
		setLayout(new BorderLayout());
		JPanel superior = new JPanel();
		superior.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Bienvenido al Buscaminas");
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		titulo.setBorder(new EmptyBorder(10, 0, 10, 10));

		superior.add(titulo);

		JComboBox combo = new JComboBox(); 			//Combo Box para seleccionar dificultad
			combo.addItem("Principiante");
			combo.addItem("Intermedio");
			combo.addItem("Experto");
		superior.add(combo);

		JButton Comenzar = new JButton("Comenzar"); //Boton comenzar
		superior.add(Comenzar);
		add(superior, BorderLayout.NORTH);
		
		// Panel central
		JPanel Central = new JPanel();
		add(Central, BorderLayout.CENTER);

		Comenzar.addActionListener(new ActionListener() {
			
			/**
			 * Accion del boton comenzar
			 * 
			 * @param e Evento lanzado por el boton comenzar
			 * @since 1.0
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				// Eliminamos el contenido del panel central
				Central.removeAll();

				CantidadBombas = 0;
				int tam = dificultad(combo.getSelectedItem());
				insertarCasillas(Central, tam, probabilidadMina);

				// actualiazamos el panel central
				Central.validate();
				Central.repaint();
			}

		});

		// Añadimos el footer
		add(new footer(), BorderLayout.SOUTH);

	}

	/**
	 * Selecciona el tamaño medinte la dificultad del combo box
	 * 
	 * @param item Item de dificultad
	 * @return tamaño Tamaño del panel de juego
	 * @since 1.0
	 */
	public int dificultad(Object item) {

		if (item.equals("Principiante")) {
			tamaño = 10;
			probabilidadMina = 10;
			frame.setSize(650, 650);
			frame.setLocationRelativeTo(null);
		}
		if (item.equals("Intermedio")) {
			tamaño = 15;
			probabilidadMina = 20;
			frame.setSize(850, 750);
			frame.setLocationRelativeTo(null);
		}
		if (item.equals("Experto")) {
			tamaño = 20;
			probabilidadMina = 30;
			frame.setSize(1150, 850);
			frame.setLocationRelativeTo(null);
		}

		return tamaño;
	}

	/**
	 * Insertamos las casillas en el panel central
	 * 
	 * @param JPanelCentro Panel del centro
	 * @param tam Tamaño del panel
	 * @param dificultad Dificultad del juego
	 * @see LaminaPrincipal#tamaño
	 * @see LaminaPrincipal#dificultad(Object)
	 * @since 1.0
	 */
	public void insertarCasillas(JPanel JPanelCentro, int tam, int dificultad) {
		JPanelCentro.setLayout(new GridLayout(tam, tam));

		Casillas = new JButton[tam][tam];

		for (int i = 0; i < Casillas.length; i++) {
			for (int j = 0; j < Casillas.length; j++) {
				Casillas[i][j] = new JButton();
				Casillas[i][j].putClientProperty("id", 0);
				insertarBombas(Casillas[i][j], dificultad);
				JPanelCentro.add(Casillas[i][j]);
				funciones(Casillas[i][j], i, j);

			}
		}

	}
	
	/**
	 * Insertar bombas de forma aleatorias dependiendo de la dificultad
	 * 
	 * @param apellidos Apellidos del empleado
	 * @see LaminaPrincipal#dificultad(Object)
	 * @see LaminaPrincipal#Casillas
	 * @throws IllegalArgumentException si las minas son menor que cero
	 * @since 1.0
	 */
	public void insertarBombas(JButton Casillas, int dificultad) {

		if (dificultad > (int) (Math.random() * 100)) {
			Casillas.putClientProperty("id", 1);
			CantidadBombas++;

			if(CantidadBombas <0) {
				throw new IllegalArgumentException("No pueden haber 0 minas en el tablero");
			}
			
		}
	}

	/**
	 * Funciones del juego , añadir y quitar banderas , seleccionar casillas...
	 * 
	 * @param casillasPulsadas Numero de casillas pulsadas en el tablero
	 * @param i Primera posicion del array bidimensional
	 * @param j Segunda posicion del array bidimensional
	 * @since 1.0
	 */
	private void funciones(JButton casillasPulsadas, int i, int j) {

		casillasPulsadas.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				boolean banderaON = false;

				// Seleccionar casilla sin bomba
				if (e.getButton() == MouseEvent.BUTTON1 && !(casillasPulsadas.getClientProperty("id").equals(1))
						&& casillasPulsadas.getIcon() == (null) && casillasPulsadas.isEnabled()) {

					mostrarCasillas(i, j);
					juegoTerminado();

				}

				// Seleccionar casilla con bomba
				if (e.getButton() == MouseEvent.BUTTON1 && casillasPulsadas.getClientProperty("id").equals(1)
						&& casillasPulsadas.getIcon() == (null)) {
					ImageIcon icon = new ImageIcon("src/mina.png");
					casillasPulsadas.setIcon(icon);
					casillasPulsadas.setBackground(Color.red);
					mostrarBombas();
					JOptionPane.showMessageDialog(null, "Has pisado una mina. ¡Has perdido!");

				}

				// Poner bandera
				if (e.getButton() == MouseEvent.BUTTON3 && casillasPulsadas.getIcon() == (null)) {
					ImageIcon icon = new ImageIcon("src/bandera.png");
					casillasPulsadas.setIcon(icon);
					banderaON = true;
				}

				// Quitar banera
				if (e.getButton() == MouseEvent.BUTTON3
						&& casillasPulsadas.getIcon().toString().equals("src/bandera.png") && banderaON == false) {
					casillasPulsadas.setIcon(null);
				}

			}

		});

	}
	
	/**
	 * Metodo para mostrar casillas
	 * 
	 * @param i posicion 1 del array bidimensional
	 * @param j posicion 2 del array bidimensional
	 * @since 1.0
	 */
	public void mostrarCasillas(int i, int j) {

		Casillas[i][j].setEnabled(false);

		int valorA1 = i - 1 < 0 ? 0 : i - 1;
		int valorA2 = i + 1 > Casillas.length - 1 ? Casillas.length - 1 : i + 1;
		int valorP1 = j - 1 < 0 ? 0 : j - 1;
		int valorP2 = j + 1 > Casillas.length - 1 ? Casillas.length - 1 : j + 1;

		comprobarBombas(i, j);

		if ((Casillas[i][j].getIcon() == (null))) {

			for (int a = valorA1; a <= valorA2; a++) {
				for (int p = valorP1; p <= valorP2; p++) {

					if (Casillas[a][p].isEnabled()) {
						if (Casillas[a][p].getIcon() == (null)) {
							mostrarCasillas(a, p);
						}

					}

				}

			}
		}
	}

	/**
	 * Muestra todas las bombas una vez perdida la partida o ganada
	 * 
	 * @deprecated Pruebo deprecated con este metodo
	 * @since 1.0
	 */
	private static void mostrarBombas() {

		ImageIcon bomb = new ImageIcon("src/mina.png");

		for (int i = 0; i < Casillas.length; i++) {
			for (int j = 0; j < Casillas.length; j++) {
				Casillas[i][j].setEnabled(false);

				if (Casillas[i][j].getClientProperty("id").equals(1)) {
					Casillas[i][j].setBackground(Color.red); //Al finalizar el juego , las bombas se ponen en rojo
					Casillas[i][j].setIcon(bomb);
					Casillas[i][j].setDisabledIcon(bomb);
				}
			}
		}

	}
	
	/**
	 * Comprueba si hay una mina alrededor y cuantas
	 * 
	 * @param a Primera posicion del array bidimensional
	 * @param a Segunda posicion del array bidimensional
	 * @since 1.0
	 */
	public static void comprobarBombas(int a, int p) {
		int minas = 0;
		int valorA1 = a - 1 < 0 ? 0 : a - 1;
		int valorA2 = a + 1 > Casillas.length - 1 ? Casillas.length - 1 : a + 1;
		int valorP1 = p - 1 < 0 ? 0 : p - 1;
		int valorP2 = p + 1 > Casillas.length - 1 ? Casillas.length - 1 : p + 1;

		if (!Casillas[a][p].getClientProperty("id").equals(1)) {

			for (int f = valorA1; f <= valorA2; f++) {
				for (int j = valorP1; j <= valorP2; j++) {
					if (Casillas[f][j].getClientProperty("id").equals(1)) {
						minas++;
					}

					if (minas != 0) {
						ImageIcon numeroMinasAlrededor = new ImageIcon("src/" + minas + ".png");
						Casillas[a][p].setIcon(numeroMinasAlrededor);
						Casillas[a][p].setDisabledIcon(numeroMinasAlrededor);

					}

				}

			}
		}

	}

	/**
	 * Comprueba que el juego a terminado y si se ha perdido o ganado
	 * 
	 * @since 1.0
	 */
	public static void juegoTerminado() {
		
		int cantidadCasillas = tamaño * tamaño;
		int casillasDesactivadas = 0;

		for (int i = 0; i < Casillas.length; i++) {
			for (int j = 0; j < Casillas.length; j++) {
				if (Casillas[i][j].isEnabled() == false) {
					casillasDesactivadas++;

				}
			}
		}

		if (casillasDesactivadas + CantidadBombas == cantidadCasillas) {
			JOptionPane.showMessageDialog(null, "Enhorabuena, !Has ganado!");
			mostrarBombas();
		}
	}

}