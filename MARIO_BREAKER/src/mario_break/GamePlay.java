package mario_break;
import javax.swing.JFrame;

public class GamePlay extends JFrame {
 
	public GamePlay() {
		initUI();		
	}
	
	public void initUI() {
		
		 add(new Main()); 
		 
		 setUndecorated(true);              //기본상단바 없애기
		 setSize(Play.SCREEN_W,Play.SCREEN_H);		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		 
		 setLocationRelativeTo(null);       // 게임 실행시 가운데에서 창이 실행되게 함	 
		 setVisible(true);
		 

		 
	}
	
	public static void main(String[] args) {
		
		new GamePlay();
	
	}
}


