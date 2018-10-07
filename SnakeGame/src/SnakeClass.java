/*
 * Title: Snake Game
 * Description of Purpose: Snake Game Emulator
 * Author: Nicolas Franklin
 * Start: January 2018
 * End: TBD
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeClass implements KeyListener, ActionListener, MouseListener
{
	//JFrames, JPanels, JButtons, Variables [Global]
	
	/* TODO
	 * Add scorekeeper text file
	 * Make movement more user friendly
	 * Comment
	 */

	//Tracing
	boolean tracing=false;

	//My own Colour
	Color mintGreen = new Color(170,255,179);

	//Frames
	JFrame menuFrame = new JFrame("Main Menu");
	JFrame instFrame = new JFrame("Instructions");
	JFrame extrasFrame = new JFrame("Extras");
	JFrame gameFrame = new JFrame("Ball Puzzle");

	//Panels
	JPanel menuPanel = new JPanel();
	JPanel instPanel = new JPanel();
	JPanel extrasPanel = new JPanel(new GridLayout(2,2));
	JPanel extrasButtonPanel = new JPanel(new GridLayout(3,1));
	JPanel menuButtonPanel = new JPanel(new GridLayout(8,1));
	JPanel middleMenuPanel = new JPanel(new GridLayout(1,3));
	GraphicsClass1 graphicsPanel = new GraphicsClass1(gameFrame);

	//JButtons
	JButton playButton = new JButton("Play");
    JButton instButton = new JButton("Instructions");
    JButton extrasButton = new JButton("Extras");
    JButton exitButton = new JButton("Exit");
    JButton redButton = new JButton("RED");
    JButton blueButton = new JButton("BLUE");
    JButton greenButton = new JButton("MINT GREEN");

 	public static void main(String[] args)
	{
    	new SnakeClass();
	}

	public SnakeClass()
	{
		System.out.println("                          Welcome to the Snake Console window! \n");

		//Blank JLabels (I still want to know a way where I can re-use the same blank JLabel instead of creating 6)
	    JLabel blank1 = new JLabel("");
	    JLabel blank2 = new JLabel("");
	    JLabel blank3 = new JLabel("");
	    JLabel blank4 = new JLabel("");


    	//Environmental Text Area
    	JTextArea environmentArea = new JTextArea("Environmental Message: Make sure to lower your screeen brightness if possible to conserve energy   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    Emerging Technology FACTOID: Quantum computing seeks to accelerate the implementation of new technologies in the field of solar cells, teleportation and supercomputing using the qubit phenomenon. Quantum computing could even make teleportation a reality. The qubits are capable of holding both the 0 and 1 state at the same time. Scientists are just finding it tough to control qubit operations. But there are numerous opportunities for doing research in this field.");
    	environmentArea.setLineWrap(true);
		environmentArea.setWrapStyleWord(true);

		//Title Image (jpg)
    	JLabel titleImage = new JLabel("");
   		ImageIcon titleImageIcon = new ImageIcon("ballpuzzletitle.jpg");
	    titleImage.setIcon(titleImageIcon);

	    //Star Image (jpg)
    	JLabel starImage = new JLabel("");
   		ImageIcon starImageIcon = new ImageIcon("BallPuzzleStar.jpg");
	    starImage.setIcon(starImageIcon);

	    //Star2 Image (jpg)
    	JLabel star2Image = new JLabel("");
   		ImageIcon star2ImageIcon = new ImageIcon("BallPuzzleStar2.jpg");
	    star2Image.setIcon(star2ImageIcon);

	    //Instructions Image (jpg)
    	JLabel instPicLabel = new JLabel("");
    	JTextArea instArea = new JTextArea("Use the Arrow Keys to move and 'r' to reset. Get the food in RED and do not collide with yourself. Have fun!");
   		//ImageIcon InstImageIcon = new ImageIcon("BallPuzzleInstructions.jpg");
	    //instPicLabel.setIcon(InstImageIcon);
    	
    	//Snake Image
    	JLabel snkImageIcon = new JLabel("");
   		ImageIcon SnkImageIcon = new ImageIcon("snake.jpg");
	    instPicLabel.setIcon(SnkImageIcon);

    	//Adding Action Listeners
	    playButton.addActionListener(this);
	    instButton.addActionListener(this);
	    extrasButton.addActionListener(this);
	    redButton.addActionListener(this);
	    blueButton.addActionListener(this);
	    greenButton.addActionListener(this);
	    exitButton.addActionListener(this);

	    //Adding Mouse Listener
	    gameFrame.addMouseListener(this);

	    //Adding Key Listener
	    gameFrame.addKeyListener(this);

    	//menuButtonPanel
    	menuButtonPanel.add(blank2);
    	menuButtonPanel.add(playButton);
    	menuButtonPanel.add(blank3);
    	menuButtonPanel.add(instButton);
		menuButtonPanel.add(blank1);
		menuButtonPanel.add(extrasButton);
		menuButtonPanel.add(blank4);
    	menuButtonPanel.add(exitButton);
    	menuButtonPanel.setBackground(mintGreen);

    	//middleMenuPanel
		middleMenuPanel.add(starImage);
		middleMenuPanel.add(menuButtonPanel);
		middleMenuPanel.add(star2Image);

	    //Menu
	    menuPanel.setLayout(new GridLayout(3,1));
	    menuPanel.add(titleImage);
	    menuPanel.add(middleMenuPanel);
	    menuPanel.add(snkImageIcon);
	    menuPanel.setBackground(mintGreen);
	    menuFrame.setContentPane(menuPanel);
	    menuFrame.setSize(885,710);
	    menuFrame.setVisible(true);
	    menuFrame.setResizable(false);
	    menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //Instructions
	    instFrame.add(instPanel);
	    instPanel.add(instPicLabel);
	    instPanel.add(instArea);
	    instFrame.setSize(885,710);
	    instFrame.setVisible(false);
	    instFrame.setResizable(false);
	    instPanel.setBackground(mintGreen);

		//Extras Button Panel
		extrasButtonPanel.add(redButton);
	    extrasButtonPanel.add(blueButton);
	    extrasButtonPanel.add(greenButton);

	    //Extras
	    extrasFrame.setContentPane(extrasPanel);
	    extrasPanel.add(extrasButtonPanel);
	    extrasPanel.add(environmentArea);
	    extrasPanel.add(snkImageIcon);
	   	extrasFrame.setSize(800,400);
	    extrasFrame.setVisible(false);
	    extrasFrame.setResizable(false);


		//Game
		gameFrame.add(graphicsPanel);
		gameFrame.setSize(885,710);
		instFrame.setVisible(false);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//if(gameFrame=="Level 5")
		//	System.out.print("TESTETETSTSTTSTSTSTSTSTSTST");

	}




	//-------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------KEY LISTENER-----------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//

	public void keyPressed(KeyEvent e)  // use this method to perform actions when a key is pressed on the keyboard
	{
		if(tracing) System.out.println("Keycode "+e.getKeyCode());

		if(e.getKeyCode()==38) //up (movement)
			graphicsPanel.startUpTimer();

		if(e.getKeyCode()==40) //down (movement)
			graphicsPanel.startDownTimer();

		if(e.getKeyCode()==37) //left (movement)
			graphicsPanel.startLeftTimer();

		if(e.getKeyCode()==39) //right (movement)
			graphicsPanel.startRightTimer();

		if(e.getKeyCode()==82) //r (restart)
			graphicsPanel.restart();

	}

	public void keyReleased(KeyEvent e)  // use this method to perform actions when a key is released on the keyboard
	{

	}

	public void keyTyped(KeyEvent e)  // use this method to perform actions when a key is pressed AND released on the keyboard
	{

	}





	//-------------------------------------------------------------------------------------------------------------------//
	//-------------------------------------------ACTION LISTENER---------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//

	public void actionPerformed (ActionEvent e)
  	{
		if (e.getActionCommand().equals("Play"))
			{
				gameFrame.setVisible(true);
				menuFrame.setVisible(false);
			}

		else if(e.getActionCommand().equals("Instructions"))
		{
			instFrame.setVisible(true);
		}

		else if(e.getActionCommand().equals("Extras"))
		{
			extrasFrame.setVisible(true);
		}

		else if(e.getActionCommand().equals("RED"))
		{
			extrasPanel.setBackground(Color.red);
		}

		else if(e.getActionCommand().equals("BLUE"))
		{
			extrasPanel.setBackground(Color.blue);
		}

		else if(e.getActionCommand().equals("MINT GREEN"))
		{
			extrasPanel.setBackground(mintGreen);
		}

		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}

  	}




  	//-------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------MOUSE LISTENER---------------------------------------------------------//
	//-------------------------------------------------------------------------------------------------------------------//

	public void mouseClicked(MouseEvent e) // use this method to perform actions when// the mouse button is pressed AND released
	{

	}

  	public void mousePressed(MouseEvent e) // use this method to perform actions when the mouse button is pressed
	{
		if(tracing) System.out.println("X "+e.getX()+" Y "+e.getY());
	}

	public void mouseReleased(MouseEvent e) // use this method to perform actions when the mouse button is released
	{

	}

	public void mouseEntered(MouseEvent e)  // use this method to perform actions when the mouse enters a component
	{

	}

	public void mouseExited(MouseEvent e)  // use this method to perform actions when the mouse exits a component
	{

	}

}
