
import java.util.*; 
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener 
{
	//Implementing any variables 
	private boolean play = false;
	private int score = 0, highScore = 0, playerX = 310, totalBricks = 40, count = 0;
	
	private Timer timer;
	private int delay=8;
	
	private int ballPosX = 60, ballPosY = 350, ballDirX = -2, ballDirY = -3;
	private BrickGenerator bricks;
	
	public BrickBreaker()
	{	
		//Adding the JFrame and Bricks
		bricks = new BrickGenerator(4, 10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer= new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{    		
		//The background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		bricks.draw((Graphics2D) g);
		
		//The borders
		g.setColor(Color.red.darker());
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//The score (top right)
		g.setColor(Color.white);
		g.setFont(new Font("times new roman",Font.BOLD, 25));
		g.drawString(""+ score, 630,30);
		
		//Making the paddle
		g.setColor(Color.red);
		g.fillRect(playerX, 550, 100, 8);
		
		//Setting the ball
		g.setColor(Color.gray);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		
		if (count == 0)
		{
			g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 25));
            g.drawString("Press the Spacebar To Begin", 190,300);
            
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 25));
            g.drawString("Move with the Left and Right Arrow Keys ", 130, 450);
		}
		
	
		//When you win
		if(totalBricks <= 0)
		{
			if (score > highScore)
			{
				highScore = score;
			}
			play = false;
            ballDirX = 0;
     		ballDirY = 0;
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 30));
            g.drawString("You Won! Your Final Score: " + score, 155,300);
            
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 30));
            g.drawString("High Score: " + highScore, 263, 340);
             
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 20));           
            g.drawString("Press (Enter) to Restart", 248,380);  
            
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 20));           
            g.drawString("Press (Escape) to Go to the Main Menu", 188,410);
		}
		
		// When you lose the game
		if(ballPosY > 570)
        {
			if (score > highScore)
			{
				highScore = score;
			}
			
			play = false;
            ballDirX = 0;
     		ballDirY = 0;
     		
     		//Output messages when you lose
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 30));
            g.drawString("Game Over! Your Score: "+score, 170,300);
            
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 30));
            g.drawString("High Score: " + highScore, 250, 340);
             
            g.setColor(Color.white);
            g.setFont(new Font("times new roman",Font.BOLD, 20));           
            g.drawString("Press (Enter) to Restart", 238,380);
            
        }
		
		g.dispose();
	}	

	//Not used Methods
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void keyPressed(KeyEvent e) 
	{
		
		//If they press the spacebar, the game begins
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{          
			if(!play)
			{
				count += 1;
				play = true;
			}
			
		}
		
		//If the right arrow key is pressed, the paddle moves right but only if it's in bounds
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
	    }
			
		//If the left arrow key is pressed, the paddle moves left but only if it's in bounds
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
	    }	
		
		//If they press the enter button
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				count = 0;
				ballPosX = 470;
				ballPosY = 350;
				ballDirX = +2;
				ballDirY = -3;
				playerX = 310;
				score = 0;
				totalBricks = 40;
				bricks = new BrickGenerator(4, 10);
					
				repaint();
			}
	    }
		
	}

	//The paddle moving right
	public void moveRight()
	{
		count += 1;
		playerX += 15;	
	}
	
	//The paddle moving left
	public void moveLeft()
	{
		count += 1;
		playerX -= 15;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballDirY = -ballDirY;
				ballDirX = -3;
			}
			else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballDirY = -ballDirY;
				ballDirX = ballDirX + 2;
			}
			else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballDirY = -ballDirY;
			}
			
			// Checks the ball's collision with the brick		
			A: for(int i = 0; i<bricks.bricks.length; i++)
			{
				for(int k =0; k<bricks.bricks[0].length; k++)
				{				
					if(bricks.bricks[i][k] > 0)
					{
						// The scores (++)
						int brickX = k * bricks.brickWidth + 80;
						int brickY = i * bricks.brickHeight + 50;
						int brickWidth = bricks.brickWidth;
						int brickHeight = bricks.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							bricks.BrickValue(0, i, k);
							score += 5;	
							totalBricks--;
							
							//When the ball hits the right or left of the brick
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width)	
							{
								ballDirX = -ballDirX;
							}
							//When the ball hits the top or bottom of the brick
							else
							{
								ballDirY = -ballDirY;				
							}
							
							break A;
						}
					}
				}
			}
			
			// The ball moving after hitting a brick
			ballPosX += ballDirX;
			ballPosY += ballDirY;
			
			if(ballPosX < 0)
			{
				ballDirX = -ballDirX;
			}
			if(ballPosY < 0)
			{
				ballDirY = -ballDirY;
			}
			if(ballPosX > 670)
			{
				ballDirX = -ballDirX;
			}		
			
			repaint();		
		}
	}
}




