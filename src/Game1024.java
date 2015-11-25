

import java.awt.*;
import javax.swing.*;
public class Game1024 extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public Game1024() 
	{
	this.setVisible(true);
    setTitle("1024");
    setSize(600, 600);
    setLocation(300, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new GridLayout(4, 4, 10, 10));
    new Algorithm(this); 
	}
	public static void main(String args[]) 
	{
    try
    {
    	 JFrame.setDefaultLookAndFeelDecorated(true);
    } 
    catch (Exception e)		
    {
      e.printStackTrace();
    }
   new Game1024();
	}
  
}
