package mario_break;

import javax.swing.ImageIcon;

public class Block extends RectangleShape{
	private boolean broke;                            //���� �����°� boolean���� ǥ��
	
	public Block(int x, int y) {
		
		setBlock(x,y);
	}
	
	private void setBlock(int x, int y) {             //ó�� ��ġ ��ų�� broke �� false�� ȭ�鿡 ��Ÿ���� �ȴ�
		this.x = x;
		this.y = y;
		int d;
		
		
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
