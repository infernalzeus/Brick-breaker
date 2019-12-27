import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



public class gamepanel extends JPanel{

	private float ballx, bally, paddlex, ballspeedx, ballspeedy;
	private static final int balldia = 10, paddlewid = 60, paddleht = 10, fps=60;
	private int endleveltally;
	private static final int brickthickness = 75, brickheight=20;
	
	public brick[] b ;

	public gamepanel(){
		endleveltally=0;
		
		int xpos, ypos=0, zlevel;
		b = new brick[40];
		for(int row=1; row<=4; row++){
			xpos = 0;
			for(int column=0; column<10; column++){
				Random r = new Random();
				zlevel = r.nextInt(4)+1;
				b[(10*(row-1))+column] = new brick(xpos,ypos,zlevel);
				xpos=xpos+brickthickness+5;
			}
			ypos=ypos+brickheight+2;
		}

		ballx=200;
		bally=200;
		ballspeedx=2;
		ballspeedy=2;

		Thread runpgm = new Thread(){
			public void run(){
					while(true){
						ballx+=ballspeedx;
						bally+=ballspeedy;


	               		if(((ballx+(balldia/2)<(paddlex+(paddlewid/4)) && (ballx+(balldia/2)>(paddlex-(paddlewid/4))) && (bally+(balldia)==400)))) //paddle collision centre, centre slows down horizontal speed to a minimum of 2
	               		{
	               			ballspeedy=-ballspeedy;
	               			if(ballspeedx==3){
	               				ballspeedx=2;
	               			}
	               			else if(ballspeedx==-3){
	               				ballspeedx=-2;
	               			}
	               		}
	               		else if (((ballx+(balldia/2)<(paddlex+(paddlewid/2+3)) && (ballx+(balldia/2)>(paddlex+(paddlewid/4))) && (bally+(balldia)==400)))) //right side paddle collision, max speed 3
	               		{
	               			ballspeedy=-ballspeedy;
	               			if(ballspeedx==2){
	               				ballspeedx=3;
	               			}
	               		}
	               		else if (((ballx+(balldia/2)<(paddlex-(paddlewid/4)) && (ballx+(balldia/2)>(paddlex-(paddlewid/2+3))) && (bally+(balldia)==400)))) //left side paddle collision, max speed 3
	               		{	
	               			ballspeedy=-ballspeedy;
	               			if(ballspeedx==-2){
	               				ballspeedx=-3;
	               			}
	               		} 

	               		//wall collisions
	               		if(ballx==0) ballspeedx=-ballspeedx;
	               		if(ballx+balldia==790) ballspeedx=-ballspeedx;
	               		if(bally==0) ballspeedy=-ballspeedy;

	               		//ball out of bounds
	               		if(bally+balldia==570) {
                		JOptionPane.showMessageDialog(null,"You let the ball fall, GAME OVER!. Thank You for playing. The Game will now exit");
	               		System.exit(0);
	               		}

	               		//brick collisions
	               		for(int i=0;i<=39; i++){
	               			if((ballx<b[i].getx()+brickthickness)&&((ballx+balldia)>b[i].getx())&&(bally<b[i].gety()+brickheight)&&((bally+balldia)>b[i].gety())){
	               				b[i]=new brick(b[i].getx(), b[i].gety(), b[i].getz()-1);
	               				ballspeedy=-ballspeedy;
	               			}
	               			if(b[i].getz()==0){
	               				b[i]=new brick(-100,-100,-50);
	               			}
	               		}
	               		//bricks finishing

	               		for(int i=0;i<=39;i++){
	               			if(b[i].getz()==-50){
	               				endleveltally+=b[i].getz();
	               				b[i]=new brick(-100,-100,-40);
	               			}
	               			if(endleveltally==-2000){
	               				JOptionPane.showMessageDialog(null,"Thank You for playing, you have finished the game. The game will now exit");
	               				System.exit(0);
	               			}
	               		}
						
						repaint();
						try {
	                  		Thread.sleep(1000 / fps);  
	               			} 
	               		catch (InterruptedException ex) {}						
					}

			}

		};

		runpgm.start();
		
	
		addMouseMotionListener(new MouseAdapter() {

            public void mouseMoved(MouseEvent e)

            {

                paddlex = e.getPoint().x;

                repaint();

            }

        });

	}
	@Override

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);

		g.setColor(Color.BLUE);
		g.fillOval((int)ballx, (int)bally, balldia, balldia);

		g.setColor(Color.RED);
		g.fillRect((int)paddlex-(paddlewid/2), 400, paddlewid, paddleht);

		for(int j=0; j<=39; j++){
			if(b[j].getz()==1){
				g.setColor(Color.GREEN);
				g.fillRect(b[j].getx(), b[j].gety(), brickthickness, brickheight);
			}
			if(b[j].getz()==2){
				g.setColor(Color.MAGENTA);
				g.fillRect(b[j].getx(), b[j].gety(), brickthickness, brickheight);
			}
			if(b[j].getz()==3){
				g.setColor(Color.YELLOW);
				g.fillRect(b[j].getx(), b[j].gety(), brickthickness, brickheight);
			}
			if(b[j].getz()==4){
				g.setColor(Color.CYAN);
				g.fillRect(b[j].getx(), b[j].gety(), brickthickness, brickheight);
			}
		}



	}
}