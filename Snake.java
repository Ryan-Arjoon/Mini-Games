
//Import all relevant built in functions
import java.awt.Dimension;   
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener{

	//Initialize/Declare all variables and constants

	//Assign the class 'Snake' to variable 'snake'
	public static Snake snake;

	//Assign the variable 'object' to have the JFrame function
	public JFrame object;

	//Assign class 'RenderPanel' to the variable 'renderPanel'
	public RendererSnake renderPanel;

	//Create an object of timer
	public Timer timer = new Timer(20,this);

	//Create an array list for the parts of the snake 
	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	//Constant integers
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 15;

	//Create integer variables
	public int ticks = 0, direction, score, tailLength = 10, time, highscore =0,difference;

	//Set the Variables for the 'head' of the snake point and the 'cherry' point
	public Point head, cherry;

	//Assign the variable 'random' to the 'Random' function
	public Random random;

	//Create a boolean variable to check for when the game is paused and when the user hasn't started the game yet
	public boolean paused,begin = false;

	//Set the dimensions through the object 'dim'
	public Dimension dim;

	//Create the boolean variable gameOver to check for instances when the user loses the game
	public boolean gameOver = false;

	//Create 'Snake' method to store JFrame settings/parameters
	public Snake()
	{		
		dim = Toolkit.getDefaultToolkit().getScreenSize();

		//JFrame Title 'Snake'
		object = new JFrame("Snake");
		//Set JFrame visibility and size
		object.setVisible(true);
		object.setSize(805,700);
		object.setResizable(false);
		//Set location
		object.setLocation(dim.width/2 - object.getWidth()/2, dim.height/2 - object.getHeight()/2);
		object.add(renderPanel = new RendererSnake());
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.addKeyListener(this);
		//Call startGame Method
		startGame();

	}

	//Create method 'startGame' so that 
	public void startGame()
	{
		//Initialize variables for the beginning of the game
		//Game over will be false until user loses
		gameOver = false;
		//Game is not paused
		paused = false;
		//Set all value tracking variables to zero except for length
		time = 0;
		score = 0;
		tailLength = 1;
		ticks = 0;
		//Snake will start from top corner and move down the JFrame
		if(begin == true)
			direction = DOWN;
		head = new Point(0,-1);
		random = new Random();
		//Clear the array list 
		snakeParts.clear();
		//Create a random location for the cherry to spawn
		cherry = new Point(random.nextInt(53),random.nextInt(44));
		//Begin Timer
		timer.start();
	}



	@Override
	//Method for when any action is performed
	public void actionPerformed(ActionEvent e) {
		//Use the 'RenderPanel' class to repaint
		renderPanel.repaint();
		ticks++;

		// Set the rate at which tiles are painted and start the game
		if (ticks % 2 == 0 && head != null && !gameOver && !paused )
		{
			//Time added every tick
			time++;
			//Create a new snake part at the coordinates  
			snakeParts.add(new Point(head.x, head.y));
			//If the direction is up and there is no border or tail of the snake at that location create new snake part
			if (direction == UP)
				if (head.y -1 >= 0 && noTailAt(head.x, head.y - 1))
					head = new Point(head.x, head.y-1);
				//Else the snake runs into itself or the borders and dies
				else 
					gameOver = true;
			//If the direction is down and there is no border or tail of the snake at that location create new snake part
			if (direction == DOWN)
				if (head.y +1 < 44.3 && noTailAt(head.x, head.y +1))
					head = new Point(head.x, head.y+1);
				//Else the snake runs into itself and dies
				else 
					gameOver = true;
			//If the direction is left and there is no border or tail of the snake at that location create new snake part
			if (direction == LEFT)
				if (head.x -1 >= 0 && noTailAt(head.x - 1, head.y))
					head = new Point(head.x-1, head.y);
				//Else the snake runs into itself and dies
				else 
					gameOver = true;
			//If the direction is right and there is no border or tail of the snake at that location create new snake part
			if (direction == RIGHT)
				if (head.x +1 < 53.2 && noTailAt(head.x + 1, head.y))
					head = new Point(head.x+1, head.y);
				//Else the snake runs into itself and dies
				else 
					gameOver = true;
			if (snakeParts.size() > tailLength)
				snakeParts.remove(0);
			if (cherry != null)
			{
				if (head.equals(cherry))
				{
					score += 10;
					tailLength++;
					cherry.setLocation(random.nextInt(53),random.nextInt(44));
					//Check if the score is higher than high score and reassign the high score value 
					if (score > highscore)
					{
						highscore = score;
					}

				}		 
			}
		}
	}


	public boolean noTailAt(int x, int y) 
	{
		for (Point point : snakeParts)
		{
			if (point.equals(new Point(x,y)))
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		snake = new Snake();

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	//Method for when a key is pressed
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		//If the input is A the direction is set to left
		if (i == KeyEvent.VK_A && direction != RIGHT)
			direction = LEFT;
		//If the input is D the direction is set to right
		if (i == KeyEvent.VK_D && direction != LEFT)
			direction = RIGHT;
		//If the input is W the direction is up
		if (i == KeyEvent.VK_W && direction != DOWN)
			direction = UP;
		//If the input is S the direction is down
		if (i == KeyEvent.VK_S && direction != UP)
			direction = DOWN;
		//If the input is space bar 
		if (i == KeyEvent.VK_SPACE)
			//If begin is false then set it to true and start the game
			if (begin == false)
			{
				begin = true;
				startGame();
			}
			//If game over is true space restarts the game
			else if (gameOver)
				startGame();
			//Else if the game is paused it will be unpaused 
			else 
				paused = !paused;
	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

}
