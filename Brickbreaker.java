import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Action implements ActionListener {
		JFrame frame = new JFrame();
		JButton button = new JButton("Start Game");
		JTextArea rules = new JTextArea("RULES:\n\n\n1. Mouse Movement Controls the Paddle \n\n2. Don't let the ball touch the floor\n\n3. Centre of the Paddle will slow the ball to MinSpeed\n    Sides of the Paddle will accelerate ball to MaxSpeed\n\n4. Brick Levels :\n     Green=1 hit\n     Magenta=2 hit\n     Yellow=3 hit\n     Cyan=4 hit");
		JPanel panel = new JPanel();
		JButton restartb = new JButton("Restart");




		Action(){
			prepGUI();
			buttonproperties();
			restartprop();
		}

		public void prepGUI(){
			frame.setTitle("BrickBreaker");
			frame.setResizable(false);
			frame.setSize(800,600);
			panel.setLayout(null);
			panel.setBackground(Color.YELLOW);
			frame.add(panel);
			panel.add(rules);
			panel.setBounds(5,5,790,600);
			rules.setBounds(10,10,300,250);
			rules.setVisible(true);
			frame.setVisible(true);
			rules.setEditable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			

		}

		public void restartprop(){
			panel.add(restartb);
			restartb.setBounds(300,490,200,20);
			restartb.setVisible(true);
			restartb.addActionListener(this);


		}

		public void buttonproperties(){
			panel.add(button);
			
			button.setBounds(300,300,200,40);
			button.addActionListener(this);

		}
		@Override
		public void actionPerformed(ActionEvent e){
				if(e.getSource()==button){
				button.setVisible(false);

				panel.setBackground(Color.WHITE);
				restartb.setVisible(false);
				rules.setVisible(false);


				frame.add(new gamepanel());

				}
				else if(e.getSource()==restartb){
					frame.dispose();
					new Action();
				}
		}
	
}



public class Brickbreaker  {
	public static void main(String[] args){
	
		new Action();

	}
}

