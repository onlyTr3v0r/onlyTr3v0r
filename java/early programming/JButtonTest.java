import java.awt.*;
import javax.swing.*;

public class JButtonTest {
public static void main(String[] args) {

	private static void createPage() {

		//create window
		JFrame frame = new JFrame("First Own Java Window!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//create text label
		JLabel textLabel = new JLabel("Hello World!", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(500, 150));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		
		//Display the window.       
		frame.setLocationRelativeTo(null);       
		frame.pack();       
		frame.setVisible(true);    }      	
		createPage();

		//JButton
		JToggleButton();
		setVisible(true);
		setSize(100, 100);

	
	}
}
