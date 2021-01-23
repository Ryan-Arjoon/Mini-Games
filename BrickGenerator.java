
import java.awt.BasicStroke; 
import java.awt.Color;
import java.awt.Graphics2D;

public class BrickGenerator 
{
	//Creating the variables for the bricks
	public int bricks[][];
	public int brickHeight;
	public int brickWidth;
	
	
	public BrickGenerator (int row, int col)
	{
		bricks = new int[row][col];		
		for(int i = 0; i<bricks.length; i++)
		{
			for(int j =0; j<bricks[0].length; j++)
			{
				bricks[i][j] = 1;
			}			
		}
		
		brickHeight = 150/row;
		brickWidth = 540/col;
	}	
	
	// Drawing/editing the bricks
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<bricks.length; i++)
		{
			for(int j =0; j<bricks[0].length; j++)
			{
				if(bricks[i][j] > 0)
				{
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	// Giving the bricks an actual value/number 
	public void BrickValue(int value, int row, int col)
	{
		bricks[row][col] = value;
	}
} // End of program
