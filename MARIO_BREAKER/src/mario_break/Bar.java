package mario_break;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Bar extends RectangleShape{
	
	private int BarX;
	
	public Bar() {		
		shape();
		shapesize();
		barreset();
	}
	
	private void shape() {
		var bar = new ImageIcon("src/pic/bar.png");
		shape = bar.getImage();	
		}
	
	void move() {
		x += BarX; 
		
		if(x<=0) {                       //ĳ���Ͱ� ���� ������� ���߱�
			x=0;
		}
		
		if(x>=Play.SCREEN_W-shapeW) {    //������ ���� ����� �� ���߱�
			x=Play.SCREEN_W-shapeW;
		}
	}
	
	void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {		
			BarX = -7;
		}	
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {		
			BarX = 7;
		}
	}
		
	void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT) {	
			BarX = 0;
		}
		
		if (key == KeyEvent.VK_RIGHT) {		
			BarX = 0;
		}	
	}
	   
         private void barreset() {            // �ʱ�ȭ �޼ҵ�
        	 x=Play.BAR_SETX;
        	 y=Play.BAR_SETY;	
	}

}
