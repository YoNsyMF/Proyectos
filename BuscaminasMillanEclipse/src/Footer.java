import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase footer que extiendo de JPanel , en la cual , esta el nombre del programador del juego.
 *@author Yoel Millan
 *@version 1.0
 *@since 1.8
 *@see JPanel
 */
	class footer extends JPanel {

		public footer() {

			setLayout(new FlowLayout(FlowLayout.LEFT));

			setBackground(Color.BLACK);

			JLabel nombre = new JLabel("Autor: Yoel Millán Fernández");

			nombre.setFont(new Font("Arial", Font.ITALIC, 14));

			nombre.setForeground(Color.WHITE);

			add(nombre);

		}

	}