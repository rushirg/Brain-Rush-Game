import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;

public class RGgAmE1 extends JPanel implements KeyListener
{
	int x = 0;
	int y = 20;
	int flg = 0;
	int rectCompare = 0;
	int ovalCompare = 0;	
	static int threadDelay = 6;
	int level = 0;
	int score = 0;	
	String scoreis;
	JLabel s = new JLabel("Score ");
	JLabel speed = new JLabel("");

	URL url = RGgAmE1.class.getResource( "buzz.wav" );
	URL url2 = RGgAmE1.class.getResource( "abc.wav" );
	
	AudioClip clip = Applet.newAudioClip( url );
	AudioClip overClip = Applet.newAudioClip( url2 );
	
	RGgAmE1()
	{
		addKeyListener( this );
		super.add(s);
		super.add( speed );
	}

	public void moveBall()
	{		
		if( y>=390 )
		{
			y = 20;
			
			if( x == 1 )
			{	x = 0;
				clip.play();
				score++;
				level++;		
			}
			else
			{	x = 1 ;	
				clip.play();
				score++;
				level++;
			}
			if( flg == 0 && rectCompare == 1 )
			{	
				overClip.play();
				JOptionPane.showMessageDialog( null, "Game Over\n\n\t\n\nTry Again LOOSER !!!" );		
				System.exit( 0 );
			
			}
			if( flg == 1 && ovalCompare == 1 )
			{
				overClip.play();
				JOptionPane.showMessageDialog( null, "Game Over\n\n\t\n\nTry Again LOOSER !!!" );
				System.exit( 0 );
			}
			if( level >= 5 ){
				threadDelay = 5;
			}
		
			if (level == 6 || level == 7 ){
				threadDelay = 3;
			}
		
			if( level<= 10  || level>= 8 ){
				threadDelay = 4;
			}
			
			if( level == 13 || level == 14 ){
				threadDelay = 2;
			}
						
			if( level>= 11 && level<= 12 || level >=15 && level <= 20 ){
				threadDelay = 3;
				speed.setText("__SpeedUp++__");
			}
							
			if( level>= 21 && level<= 30 ){
				if(level > 28)	
					speed.setText("_It's_Booster_Now_");
				else
					speed.setText("");
				threadDelay = 2;
				
			}
								
			if( level>= 31 && level<= 50 ){
				speed.setText("_It's_N2O_Speed");
				threadDelay = 1;
			}
	
			if( level>50 ){
				threadDelay = 0;
			}
			
			Random random = new Random();
			x = random.nextInt(2);
		}
		
		y += 1;
	}
	
	public void paint( Graphics g )
	{
		super.paint( g );
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
	 	scoreis = ""+ score;
		s.setText( "Score :- "+scoreis);
		s.setForeground(Color.RED);	

		ovalCompare = 0;
		rectCompare = 0;	
		
		g2d.setColor( Color.CYAN);
		
		switch( x )
		{
			case 0:	
				g2d.fillOval( 220, y + 10 , 50, 50 );
				ovalCompare = 1;
				break;
			case 1:
				g2d.fillRect( 220, y, 50, 50 );
				rectCompare = 1;
				break;	
		}	
		if( flg == 1 )
		{
			g2d.setColor( Color.GREEN );
			g2d.fillRect( 220, 400, 50, 50 );
		}
		g2d.setColor( Color.GREEN );
		g2d.fillOval( 220, 400, 50, 50 );
	}
	
	public void keyReleased( KeyEvent e )
	{
		if( e.getKeyCode() == KeyEvent.VK_UP )
		{
	  		flg = 0;
			repaint();
		} 
	}

	public void keyTyped( KeyEvent e )
	{ }

	public void keyPressed( KeyEvent e )
	{
		
		if( e.getKeyCode() == KeyEvent.VK_UP )
		{	
	  		flg = 1;
			repaint();
		}
	}
	

	public static void main( String [] args ) throws Exception
	{
		JDialog.setDefaultLookAndFeelDecorated(true);
    		
		String [] buttons = { "Play", "About", "Exit" };

		final ImageIcon icon = new ImageIcon("21.png");
		
		while( true ){
		int response = JOptionPane.showOptionDialog(null,"\t","Infinity",JOptionPane.INFORMATION_MESSAGE ,0,icon,buttons,buttons[2]);
		    		
			if (response == 2) {
      				System.out.println("Come Again");
				System.exit(0);
    			} else if (response == 0) {
      				System.out.println("Here You Go...");
				JOptionPane.showMessageDialog(null,"How To Play :-\n\n\t Press/Release UP arrow Key to change the shape of the object\n\n\nAnd Enjoy");
				break;
			} else if (response == 1) {
   			JOptionPane.showMessageDialog(null,"☻ It's Really GREAT That You Are Looking For Us ☻\n\n\tRushikesh Gaidhani","About Us",JOptionPane.INFORMATION_MESSAGE);
				
    			}		
		}


		JFrame frame = new JFrame("Infinity");
		RGgAmE1 game = new RGgAmE1();
		

		try{
			BufferedImage myPicture = ImageIO.read(new File("3.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			game.add(picLabel);
		}
		catch( Exception e){}	
	
		URL url3 = RGgAmE1.class.getResource( "1.wav" );
	
		AudioClip clip = Applet.newAudioClip( url3 );
		
		
		clip.play();
		
		frame.add( game );
		
		frame.setSize( 500, 500 );
		frame.setVisible( true );

		game.addKeyListener( game );
        	game.setFocusable(true);

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		while( true )
		{
			game.moveBall();
			game.repaint();
			Thread.sleep( threadDelay );
		}
	}
	
}




