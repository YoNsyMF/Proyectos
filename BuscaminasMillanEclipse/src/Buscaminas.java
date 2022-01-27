import javax.swing.JFrame;

/**
 *Clase en la cual creamos la ventana del buscaminas y le añadimos a dicha ventana
 *@author Yoel Millan
 *@version 1.0
 */
public class Buscaminas {
	
	/**
	 * Metodo main que ejecuta el buscaminas
	 * 
	 * @param args Argumentos del main
	 * @see LaminaPrincipal
	 * @since 1.0
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setTitle("Buscaminas");
		
		frame.setSize(650, 650);
		
		frame.setLocationRelativeTo(null);
		
		frame.setResizable(false);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		LaminaPrincipal laminaPrincipal = new LaminaPrincipal(frame);
		
		frame.add(laminaPrincipal);
		
		frame.setVisible(true);
	}

}
