package mario_break;
import javax.swing.JFrame;

public class GamePlay extends JFrame {
 
	public GamePlay() {
		initUI();		
	}
	
	public void initUI() {
		
		 add(new Main()); 
		 
		 setUndecorated(true);              //�⺻��ܹ� ���ֱ�
		 setSize(Play.SCREEN_W,Play.SCREEN_H);		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
		 setLocationRelativeTo(null);       // ���� ����� ������� â�� ����ǰ� ��	 
		 setVisible(true);
		 

		 
	}
	
	public static void main(String[] args) {
		
		new GamePlay();
	
	}
}


