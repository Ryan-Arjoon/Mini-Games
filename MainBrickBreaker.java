

// Importing anything needed
import java.awt.Color;
import javax.swing.JFrame;

public class MainBrickBreaker {
	public static void main(String[] args) {
		JFrame screen=new JFrame();
		BrickBreaker gamePlay = new BrickBreaker();
		
		//Setting the properties for the JFrame
		screen.setBounds(10, 10, 700, 600);
		screen.setTitle("Brick Breaker");		
		screen.setResizable(false);
		screen.setVisible(true);
		screen.setLocationRelativeTo(null);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.add(gamePlay);
        screen.setVisible(true);
		
	}

}



