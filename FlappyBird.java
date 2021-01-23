

import java.awt.Color; 
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener{



	//assigning the class to a variable
	public static FlappyBird flappyBird;

	//constant variables for width and height of the frame
	public static final int WIDTH = 800, HEIGHT = 800;

	//create an instance of Renderer
	public Renderer render;

	//little red square that acts as the bird in the game
	public Rectangle bird;

	//create columns (ArrayList containing Rectangle) (<> means declaring the type)
	public ArrayList<Rectangle> pipes;

	//ticks yMotion (up and down) and the score
	public int ticks, yMotion, score;

	//high score variable
	public static int highscore;

	//ends the game if gameOver is equal to true
	public boolean gameOver, started;

	//random order for the pipes/columns coming next
	public Random random;



	//constructor
	public FlappyBird()
	{

		JFrame frame = new JFrame();

		//so it repaints our program (also wants an int and an action listener)
		Timer timer = new Timer(20, this);


		//making Renderer so it's not null so it doesn't crash
		render = new Renderer();

		//random order
		random = new Random();

		frame.add(render);
		frame.setTitle("Flappy Bird ISU");//title at top of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes and stops program when closing the tab of the frame
		frame.setSize(WIDTH, HEIGHT);//constant variables for width and height
		frame.addMouseListener(this);//MouseListener import
		frame.addKeyListener(this);//KeyListener import
		frame.setResizable(false);//cannot resize the frame
		frame.setVisible(true);//makes the frame visible
		frame.setLocationRelativeTo(null);//frame is in the middle of the screen

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		pipes = new ArrayList<Rectangle>();


		addColumn(true);//adds 4 pipes/columns in total
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();//starts the timer

	}//end of FlappyBird method


	public void addColumn(boolean start) {

		int space = 300;//space between gap of pipe
		int width = 100;//width of pipe

		int height = 50 + random.nextInt(300);//minimum height 50, max height 300


		if (start)
		{
			//
			pipes.add(new Rectangle(WIDTH + width + pipes.size() * 300, HEIGHT - height - 120, width, height));
			pipes.add(new Rectangle(WIDTH + width + (pipes.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}

		else
		{
			//
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, width, HEIGHT - height - space));
		}

	}//end of addColumn method




	//columns are the green pipes
	public void paintColumn(Graphics g, Rectangle column){

		//color and location of column
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);


	}//end of paintColumn method


	//beginning of jump method (later incorporate the KeyListener to make space bar = jump)
	public void jump() {

		if (gameOver)
		{	
			//x and y coordinates of the square and size (minus 10 makes it in the center of the screen)
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			pipes.clear();
			yMotion = 0;
			score = 0;


			addColumn(true);//adds the 4 pipes continuously
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}

		else if (!gameOver)
		{
			if (yMotion > 0)
			{
				yMotion = 0;
			}

			yMotion -= 10;//Determines how fast the pipes will come (has a set speed)
		}

	}//end of jump method



	@Override //need to add this because we are implementing the ActionListener
	public void actionPerformed(ActionEvent e) {

		int speed = 10;

		ticks ++;//ticks = ticks + 1

		if (started)
		{

			for (int i = 0; i < pipes.size(); i++)
			{
				Rectangle column = pipes.get(i);
				column.x -= speed;
			}


			//if the remainder of ticks is equal to 0 and yMotion is less than 15
			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;//yMotion = yMotion + 2
			}


			for (int i = 0; i < pipes.size(); i++)
			{
				Rectangle column = pipes.get(i);

				if (column.x + column.width < 0)
				{
					pipes.remove(column);//if the pipes are to the left and off the screen it will remove the pipe

					if (column.y == 0)
					{
						addColumn(false);			
					}
				}
			}//end of for loop

			bird.y += yMotion;


			//to check if the bird hits anything and if it does, it acts accordingly
			for (Rectangle column : pipes)
			{

				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;//score will go up by 1 once the bird crosses over the pipe
				}


				//highscore counter
				if (score > highscore)
				{
					highscore = score;
				}


				if (bird.intersects(column))//if the bird hits the column the game will end
				{
					gameOver = true;

					if (bird.x <= column.x)
					{
						//makes the bird stay behind while the screen keeps moving
						bird.x = column.x - bird.width;
					}

					else
					{
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}

						else if (bird.y < column.height)
						{
							bird.y = column.height;

						}
					}
				}
			}//end of for loop

			//if the bird dies it will just fall
			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{

				gameOver = true;

			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				//makes the bird drag across the grass
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}

			//so that the bird doesn't go up off the screen (sets a boundary at the top)
			if (bird.y < 0)
			{
				speed = 0;
				bird.y = 0;		
			}



		}//end of if started

		render.repaint();

	}//end of actionPerformed method




	//auto generated method from repaint
	public void repaint(Graphics g) {


		g.setColor(Color.cyan);//color of background screen
		g.fillRect(0, 0, WIDTH, HEIGHT);//dimensions of the color fill


		g.setColor(Color.red);//color of bird
		g.fillRect(bird.x, bird.y, bird.width, bird.height);//dimensions of the color fill


		g.setColor(Color.orange);//color of ground/sand
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);//dimensions of ground color fill


		g.setColor(Color.green);//color of grass
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);//dimensions of color fill

		//for each rectangle in columns
		for (Rectangle column : pipes)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.white);//white color
		g.setFont(new Font("arial", 1, 100));//arial font

		if (!started)//if the game has not started it will display a message
		{
			g.drawString("Click to Start!", WIDTH / 2 - 315, HEIGHT / 2 - 50);//directions for the user

			g.setFont(new Font("arial", 1, 30));//font
			g.drawString("Press the SPACEBAR to jump", WIDTH / 2 - 210, HEIGHT / 2 - 15);//directions for the user
		}


		if (gameOver)//if the game is over display...
		{
			g.drawString("Game Over!", WIDTH / 2 - 275, HEIGHT / 2 - 75);//tells the user that the game has ended

			g.setFont(new Font("arial", 1, 30));//font
			g.drawString("Press the SPACEBAR to play again", WIDTH / 2 - 240, HEIGHT / 2 - 30);//directions for the user

			g.drawString("Score: "+score+"", WIDTH / 2 - 50, HEIGHT / 2 + 25);//score displayed and location
			g.drawString("High Score: "+highscore+"", WIDTH / 2 - 85, HEIGHT / 2 + 60);//highscore displayed and location

			g.drawRoundRect(100, 150, WIDTH / 2 + 225, HEIGHT / 2 + 20, 50, 50);

		}


		if (!gameOver && started)//if the game is not over and the game has started...
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);//display the score with a big font at the top centre

			g.setFont(new Font("arial", 1, 30));//font
			g.drawString("High Score: "+highscore+"", 5, 25);//displays the highscore at the top left
		}



	}//end of repaint method



	//main method
	public static void main(String[] args){

		//creating a new instance of flappy bird
		flappyBird = new FlappyBird();

	}//end of main method


	//auto-generated methods for MouseListener and KeyListener imports
	@Override
	public void mouseClicked(MouseEvent e) {


	}


	@Override
	public void mousePressed(MouseEvent e) {
		jump();//if the mouse is pressed it will run the jump method
	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}


	@Override
	public void mouseEntered(MouseEvent e) {

	}


	@Override
	public void mouseExited(MouseEvent e) {

	}


	@Override
	public void keyTyped(KeyEvent e) {

	}


	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE)//if the user presses the space bar it will run the jump class
		{
			jump();
		}

	}


	@Override
	public void keyReleased(KeyEvent e) {


	}



}//end of program
