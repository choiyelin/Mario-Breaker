package mario_break;

import javax.swing.ImageIcon;

public class Mushroom extends RectangleShape {
	private int ballX;
	private int ballY;
	
	
	
	public Mushroom() {
		setBall();
	}
	private void setBall() {
		ballX=5;
		ballY=-5;     // 대각선 위로 set
		
		shape();
		shapesize();
		ballreset();
		
	}
	private void shape() {
		var ball = new ImageIcon("src/pic/mushroom.png");
		shape = ball.getImage();
		
		}
	
	void move() {
		
		x += ballX;
		y += ballY;
		
	
	}
	private void ballreset() {
		x=Play.BALL_SETX;
		y=Play.BALL_SETY;
		
	}
	
	void ballXdir(int x) {
		ballX=x;
	}
	
	void ballYdir(int y) {
		ballY=y;
	}
 
	
	int getYdir() {
		return ballY;
	}
	
	
}
