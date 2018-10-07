import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

//BOARD DIMENTIONS
//X: 22x40 = 880 pixels
//Y: 17x40 = 680 pixels
//For some reason 0,0 on the grid is actually 3,35?

class GraphicsClass1 extends JPanel
{
	//Variable declaration
	Color mintGreen = new Color(170,255,179);

	private Timer upTimer;
	private Timer downTimer;
	private Timer leftTimer;
	private Timer rightTimer;
	JFrame gameFrame;
	
	//Dotsize is 40 pixels so the actual snake size is 38x38 to show the edges
	int snakeHeight=38, snakeWidth=38, length = 3, temp, speed=100, score = length/3-1;
	
	int[] xArray = new int[1000];
	int[] yArray = new int[1000];
	

	Random r = new Random();
	
	File file = new File("Score");
	
	//Must increment by 1 to align with the snake head
	int xFood = 40*r.nextInt(22)+1;
	int yFood = 40*r.nextInt(17)+1;

	boolean tracing=false, newFood=true;
	
	//Constructor (Recieves GameFrame from other class, and calls it gf)
	GraphicsClass1(JFrame gf)
	{
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//Date date = new Date();

		//Fixing the 0,0 draw bug
		for (int i = 0; i<xArray.length; i++)
			xArray[i]=-100;
		
		for (int i = 0; i<yArray.length; i++)
			yArray[i]=-100;

		//Now that we have recieved gameFrame, we can set the title of it and can use our private variables
		gameFrame=gf;
		gameFrame.setTitle("Game");
		
		//Initial snake 
		for (int i=0;i<length;i++)
		{
			xArray[i] = 40*(length-i-1)+41;
			yArray[i] = 41;
		}
		
		//Read the high score
		//Display the high score
		
    //-------------------------------------------------------------------------------------------------------------------//
	//----------------------------------------------UP TIMER-------------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//

		
		upTimer = new Timer(speed, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
    		{
				// if no collision, then move and repaint
    			if(collision()==false) 
    			{
    				// Starting the index at the end of the array, moving each element 'up the chain', 
    				// erasing the data in the last element and leaving the first element untouched (rewritten later)
	    			for (int i=length;i>0;i--)
	    			{
	    				xArray[i] = xArray[i-1];
	    				yArray[i] = yArray[i-1];
	    			}
	    			
	    			// Rewriting the first element (based on which direction the timer is)
	    			yArray[0]=yArray[1]-40;	
	    			xArray[0]=xArray[1];
	    			repaint();
    			}
    			
    			else //Collision was triggered
    			{
    				endGame();
    				System.out.print("collision");
    				System.out.println("score" +score);
    				upTimer.stop();
    				
    			}
    			
    			if(foundFood()) //checking every tick for if foundFood is true
    			{
    				newFood(); //Spawn new food
    				length+=3; //Increase length
    				repaint();  
    			}
    		
    		}
    	});


    //-------------------------------------------------------------------------------------------------------------------//
    //--------------------------------------------RIGHT TIMER------------------------------------------------------------//
    //-------------------------------------------------------------------------------------------------------------------//


		rightTimer = new Timer(speed, new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			
    			if(collision()==false)
    			{
    				for (int i=length;i>0;i--)
        			{
        				xArray[i] = xArray[i-1];
        				yArray[i] = yArray[i-1];
        			}
        				
        			yArray[0]=yArray[1];	
        			xArray[0]=xArray[1]+40;
        			repaint();
        			
    			}
    			else
    			{
    				System.out.print("collision");
    				System.out.println("score" +score);
    				rightTimer.stop(); // I dont want it to repaint here cuz then snake goes into body
    			}
    				
    			if(foundFood()) //checking every tick for if foundFood is true
    			{
    				newFood();
    				length+=3;
    				repaint(); 
    			}
    			
    		}
    	});

		downTimer = new Timer(speed, new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			if(collision()==false)
    			{
	    			for (int i=length;i>0;i--)
	    			{
	    				xArray[i] = xArray[i-1];
	    				yArray[i] = yArray[i-1];
	    			}
	    				
	    			yArray[0]=yArray[1]+40;	
	    			xArray[0]=xArray[1];
	    			repaint();
    			}
    			else
    			{
    				System.out.print("collision");
    				System.out.println("score" +score);
    				downTimer.stop();
    			}
    			
    			if(foundFood()) //checking every tick for if foundFood is true
    			{
    				newFood();
    				length+=3;
    				repaint(); 
    			}

    		}
    	});

		leftTimer = new Timer(speed, new ActionListener()
		{
    		public void actionPerformed(ActionEvent e)
    		{
    			if(collision()==false)
    			{
	    			for (int i=length;i>0;i--)
	    			{
	    				xArray[i] = xArray[i-1];
	    				yArray[i] = yArray[i-1];
	    			}
	    				
	    			yArray[0]=yArray[1];	
	    			xArray[0]=xArray[1]-40;
	    			repaint();
    			}
    			else
    			{
    				System.out.println("collision");
    				System.out.println("SCORE" +score);
    				
    				
    				
    				leftTimer.stop();
    			}
    			
    			if(foundFood()) //checking every tick for if foundFood is true
    			{
    				newFood();
    				length+=3;
    				repaint(); 
    				if(length>=xArray.length)
    					System.out.println("Out of storage");
    			}
    			
    		}
    	});

	}

    //-------------------------------------------------------------------------------------------------------------------//
	//----------------------------------------START TIMER METHODS--------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//


	//These methods make sure only 1 time runs at once
	public void startUpTimer()
	{
		if(downTimer.isRunning()==false) //So that the snake doesnt run into itself
		{
			stopAllTimers();
			upTimer.start();
		}
		
	}

	public void startDownTimer()
	{
		if(upTimer.isRunning()==false)
		{
			stopAllTimers();
			downTimer.start();
		}
	}

	public void startLeftTimer()
	{
		if(rightTimer.isRunning()==false)
		{
			stopAllTimers();
			leftTimer.start();
		}
	}

	public void startRightTimer()
	{
		if(leftTimer.isRunning()==false)
		{
			stopAllTimers();
			rightTimer.start();
		}
	}

	public void stopAllTimers()
	{
		upTimer.stop();
		downTimer.stop();
		leftTimer.stop();
		rightTimer.stop();
		
	}


    //-------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------RESART METHOD----------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//


	//Restart method (if pressed r)
	public void restart()
	{
		//Erase current snek
		for (int i=0;i<length;i++)
		{
			xArray[i] = -40;
			yArray[i] = -40;
		}
		
		length=3;

		//Stopping timers so snake doesnt keep going after restart is activated
		upTimer.stop();
		downTimer.stop();
		leftTimer.stop();
		rightTimer.stop();
		
		//Initial snake 
		for (int i=0;i<length;i++)
		{
			xArray[i] = 40*(length-i-1)+41;
			yArray[i] = 41;
		}

		repaint();
	}

	public boolean collision()
	{
		//Checking collision with body
		for (int i=1;i<=length;i++)
		{
			if(xArray[i]==xArray[0] && yArray[i]==yArray[0])
				return true;
		}
		
		//Checking collision with walls
		if(xArray[0]==40*22+1 || yArray[0]==40*17+1 || xArray[0]==1-40 || yArray[0]==1-40)
			return true;
		
		//if none are triggered
		return false;
	}

	public void endGame()
	{
		System.out.println("/n/n/nGAME OVER/n/n/n");
		
		//Calculating High Score
		LinkedList<Integer> scores = new LinkedList<Integer>();
		//scores = ReaderClass.read(file);
		Integer highscore = Collections.max(scores);
		
		System.out.println("Your score was: "+score);
		System.out.println("The High Score was: "+highscore);
		if(score>highscore)
			System.out.println("YOU ARE THE NEW HIGH SCORE!");
		ReaderClass.writeStat(Integer.toString(score),file);
	}
	
	public boolean foundFood()
	{
		if(xFood==xArray[0] && yFood==yArray[0])
			return true;
		else
			return false;
	}
		
	public void newFood()
	{
		// draw a square at random coords and record these coords
		do
		{
			xFood = 40*r.nextInt(22)+1;
			yFood = 40*r.nextInt(16)+1;
		}while(foodInSnake()); //If food spawned in the snake, loop again
		
		newFood=true;
		System.out.print("NEW FOOD SPAWNED at "+xFood/40+" "+yFood/40);
		repaint();
		
	}
	
	public boolean foodInSnake() //to avoid food spawning on the snake itself (Implicit bug)
	{
		//Checking if food in body
		for (int i=1;i<=length;i++)
		{
			//if food coords match any body coords, return true (because food in snake)
			if(xArray[i]==xFood && yArray[i]==yFood)
				return true;
		}
		
		//if it was not triggered
		return false;
	}

    //-------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------PAINT METHOD-----------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//


	//Paint method
	public void paint(Graphics g)
	{
		//Background
		g.setColor(mintGreen);
		g.fillRect(0,0,getWidth(),getHeight());

		//GRIDLINES
		g.setColor(mintGreen.brighter());

		//Length Gridlines
		for(int xGridline=39; xGridline<840; xGridline=xGridline+40)
			g.fillRect(xGridline,0,2,800);

		//Width Gridlines
		for(int yGridline=39; yGridline<840; yGridline=yGridline+40)
			g.fillRect(0,yGridline,900,2);


		if(tracing)
		{
			//NUMBERS
			g.setColor(Color.black);

			//Length Numbers
			for (int xNum=0, xNumCoord=5; xNum<22; xNum++, xNumCoord=xNumCoord+40)
				g.drawString(""+xNum,xNumCoord,10);

			//Width Numbers (I HAVE TO USE ARRAYS FOR FULL MAKRS YAY ITS VERY IMPRACTICAL)
			int[] yNumCoord  = new int [17];
			for (int i=0; i < yNumCoord.length; i++)
			{
				yNumCoord[i] = yNumCoord[i] + 40*i +11;
				g.drawString(""+i,5,yNumCoord[i]);
			}

		}
		
		//Snake
		g.setColor(Color.black);
		
		for(int i = 0; i < length; i++)
			g.fillRect(xArray[i], yArray[i], snakeHeight, snakeWidth);
		
		score = length/3-1;
		
		g.drawString("Score: "+score,20*40,16*40+10);
		
		g.setColor(Color.red);
		g.fillRect(xFood,yFood,snakeHeight,snakeWidth);
		

	}
}

