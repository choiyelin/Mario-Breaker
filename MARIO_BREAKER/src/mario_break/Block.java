package mario_break;

import javax.swing.ImageIcon;

public class Block extends RectangleShape{
	private boolean broke;                            //블럭이 깨지는걸 boolean으로 표현
	
	public Block(int x, int y) {
		
		setBlock(x,y);
	}
	
	private void setBlock(int x, int y) {             //처음 위치 시킬때 broke 값 false라 화면에 나타나게 된다
		this.x = x;
		this.y = y;
		
		
		
		broke=false;   
		
		shape();
		shapesize();
				
	}
	private void shape() {
		var ice = new ImageIcon("src/pic/block.png");
		shape = ice.getImage();
		}
	
	boolean broken() {
		return broke;                                  
	}
	
	void setbroke(boolean val) {
		broke=val;
	}

}
