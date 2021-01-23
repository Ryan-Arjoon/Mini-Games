

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame{

	//JLabel variables
	JLabel panel, titleName, instructions, groupNames;
	
	//JButton variables
	JButton game1, game2, game3, game4, game5;

	//title font
	Font titleFont = new Font("arial", Font.PLAIN, 70);
	
	//font for the games
	Font gameOptions = new Font("arial", Font.PLAIN, 30);
	
	//font for instructions
	Font instructionsFont = new Font("arial", Font.PLAIN, 20);


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MainMenu();

	}
	//Main Menu
	public MainMenu() {

		//main menu window
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(FlappyBird.WIDTH, FlappyBird.HEIGHT);//size of the window
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//
		mainFrame.getContentPane().setBackground(Color.black);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(null);
		mainFrame.getContentPane();


		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(null);
		mainFrame.add(panel);


		JLabel titleName = new JLabel("       Main Menu");
		titleName.setBounds(100, 100, 600, 100);
		titleName.setBackground(Color.black);
		titleName.setForeground(Color.white);
		titleName.setFont(titleFont);
		mainFrame.add(titleName);

		JLabel instructions = new JLabel("Please Click One Of The Following");
		instructions.setBounds(252, 100, 600, 210);
		instructions.setBackground(Color.black);
		instructions.setForeground(Color.white);
		instructions.setFont(instructionsFont);
		mainFrame.add(instructions);

		JButton game1 = new JButton("Flappy Bird");
		game1.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 160, 400, 50);
		game1.setBackground(Color.black);
		game1.setForeground(Color.white);
		game1.setFont(gameOptions);
		mainFrame.add(game1);


		JButton game2 = new JButton("Snake");
		game2.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 90, 400, 50);
		game2.setBackground(Color.black);
		game2.setForeground(Color.white);
		game2.setFont(gameOptions);
		mainFrame.add(game2);

		JButton game3 = new JButton("Brick Breaker");
		game3.setBounds(FlappyBird.WIDTH/2 - 200, FlappyBird.HEIGHT/2 - 20, 400, 50);
		game3.setBackground(Color.black);
		game3.setForeground(Color.white);
		game3.setFont(gameOptions);
		mainFrame.add(game3);

		mainFrame.setVisible(true);
		titleName.setVisible(true);
		game1.setVisible(true);
		game2.setVisible(true);
		game3.setVisible(true);

		game1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				FlappyBirdLevel.main(null);
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

		game2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				SnakeLevel.main(null);
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
		
		game3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				MainBrickBreaker.main(null);
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


