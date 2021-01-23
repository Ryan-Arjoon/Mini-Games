

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlappyBirdLevel {

	
	//font for the games
	static Font titleFont = new Font("arial", Font.PLAIN, 50);
	static Font levelFont = new Font("arial", Font.PLAIN, 30);



	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new FlappyBirdLevel();
	

	//main menu window
	JFrame levelFrame = new JFrame();
	levelFrame.setSize(FlappyBird.WIDTH, FlappyBird.HEIGHT);//size of the window
	levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
	levelFrame.getContentPane().setBackground(Color.black);
	levelFrame.setResizable(false);
	levelFrame.setLocationRelativeTo(null);
	levelFrame.setLayout(null);
	levelFrame.getContentPane();


	JPanel panel = new JPanel();
	panel.setBackground(Color.black);
	panel.setLayout(null);
	levelFrame.add(panel);


	JLabel levelName = new JLabel("Select a Level");
	levelName.setBounds(235, 125, 600, 100);
	levelName.setBackground(Color.black);
	levelName.setForeground(Color.white);
	levelName.setFont(titleFont);
	levelFrame.add(levelName);




	JButton levelEasy = new JButton("Easy");
	levelEasy.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 160, 400, 50);
	levelEasy.setBackground(Color.black);
	levelEasy.setForeground(Color.white);
	levelEasy.setFont(levelFont);
	levelFrame.add(levelEasy);


	JButton levelMedium = new JButton("Medium");
	levelMedium.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 90, 400, 50);
	levelMedium.setBackground(Color.black);
	levelMedium.setForeground(Color.white);
	levelMedium.setFont(levelFont);
	levelFrame.add(levelMedium);
	
	JButton levelHard = new JButton("Hard");
	levelHard.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 20, 400, 50);
	levelHard.setBackground(Color.black);
	levelHard.setForeground(Color.white);
	levelHard.setFont(levelFont);
	levelFrame.add(levelHard);




	levelFrame.setVisible(true);
	levelName.setVisible(true);
	levelEasy.setVisible(true);
	levelMedium.setVisible(true);
	levelHard.setVisible(true);
	
	

	levelEasy.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			FlappyBird.main(null);
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	});

	levelMedium.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			FlappyBirdMedium.main(null);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	});

	levelHard.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			FlappyBirdHard.main(null);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	});
}
}