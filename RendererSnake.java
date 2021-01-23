
import java.awt.*;      
import javax.swing.*;

@SuppressWarnings("serial")
public class RendererSnake extends JPanel {
	
	public static Color green = new Color(65280);

	@Override
	protected void paintComponent(Graphics g) {
		//Using graphics set a color for the JFrame
		super.paintComponent(g);
		//Color the background of the game and its dimensions
		g.setColor(green);
		g.fillRect(0, 0, 800, 700);
		
		Snake snake = Snake.snake;
		g.setColor(Color.BLUE);
		
		//For every coordinate of the snake parts draw a rectangle to the scale of the snake
		for (Point point : snake.snakeParts)
		{
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE );
		}
		
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE );
		
		//Create a red rectangle to the scale of the cherry wherever it spawns
		g.setColor(Color.RED);
		if (snake.begin)
			g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE );
		
		//Display the score, length, time, and high score at the top of the game screen
		String string = "Score:" + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time/20 +", HighScore: "+snake.highscore;
		g.setFont(new Font("Arial",Font.BOLD,15));
		g.setColor(Color.black);
		g.drawString(string, 275,15);
		
		//If the game is over display a GAME OVER screen
		if(snake.gameOver && snake.begin)
		{
			g.setFont(new Font("Arial",Font.BOLD,100));
			g.setColor(Color.white);
			g.drawString("GAME OVER", 100, 350);
			
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.white);
			g.drawString("(PRESS SPACE TO RESTART)", 200, 400);
			
			
			//Display a point difference between user score and the high score 
			if(snake.score < snake.highscore)
			{
				g.setFont(new Font("Arial",Font.BOLD,20));
				g.setColor(Color.white);
				snake.difference = snake.highscore-snake.score;
				g.drawString("SCORE:"+snake.score+"  HIGHSCORE:"+snake.highscore+"  POINT DIFFERENCE:"+snake.difference, 170, 450);
			}
			else 
			{
				g.setFont(new Font("Arial",Font.BOLD,20));
				g.setColor(Color.white);
				snake.difference = snake.score-snake.highscore;
				g.drawString("SCORE:"+snake.score+"  HIGHSCORE:"+snake.highscore+"  POINT DIFFERENCE:"+snake.difference, 170, 450);
			}
		}
		
		//The game is paused display a paused screen
		if (snake.paused)
		{
			g.setFont(new Font("Arial",Font.BOLD,100));
			g.setColor(Color.white);
			g.drawString("PAUSED", 200, 350);
			
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.white);
			g.drawString("(PRESS SPACE TO RESUME)", 200, 400);
		}
		
		//If the game hasn't started display a start screen
		if(snake.begin == false)
		{
			g.setFont(new Font("Arial",Font.BOLD,100));
			g.setColor(Color.white);
			g.drawString("SNAKE GAME", 75, 350);
			
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.setColor(Color.white);
			g.drawString("(PRESS SPACE TO START)", 200, 400);
			
			g.setColor(Color.BLACK);
			g.fillRect(45, 420, 750, 240);
			
			g.setFont(new Font("Arial",Font.BOLD,18));
			g.setColor(Color.WHITE);
			g.drawString("INSTRUCTIONS: THE GOAL IS TO GET AS MANY POINTS AS POSSIBLE", 50, 450);
			g.drawString("YOU MOVE THE SNAKE USING W(UP), S(DOWN), A(LEFT), D(RIGHT), SPACE(PAUSE)", 50, 500);
			g.drawString("COLLECTING CHERRIES AWARDS POINTS AND INCREASES THE SNAKE LENGTH", 50, 550);
			g.drawString("THE GAME IS OVER WHEN YOUR SNAKE RUNS INTO A WALL OR ITSELF", 50, 600);
			g.drawString("GOOD LUCK", 50, 650);
		}
		
		
		
		
	}

}
