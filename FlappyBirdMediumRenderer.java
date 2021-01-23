

import java.awt.Graphics;

import javax.swing.JPanel;

public class FlappyBirdMediumRenderer extends JPanel {

	
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void paintComponent(Graphics g) {

		//calling JPanel code and then our code
		super.paintComponent(g);
		
		FlappyBirdMedium.flappyBirdMedium.repaint(g);
	
		
	}
	
	
	
}//end of Renderer class
