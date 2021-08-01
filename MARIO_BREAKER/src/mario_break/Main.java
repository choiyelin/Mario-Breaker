package mario_break;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main extends JPanel {
	private Timer timer;
	private Mushroom ball;
	private Bar bar;
	private Block[] block;
	private boolean inGame = false;                                                   //시작버튼 누르기 전까지 게임 시작 안되게 함
	
	private Image SI = new ImageIcon("src/pic/start.jpg").getImage();                 //시작 배경
	private Image BG = new ImageIcon("src/pic/mario world.jpg").getImage();           //배경이미지 
	private Image WI = new ImageIcon("src/pic/wingame.jpg").getImage();               //이겼을때 배경
	private Image LI = new ImageIcon("src/pic/gameover.png").getImage();              //졌을때 배경
	private JLabel menu = new JLabel(new ImageIcon("src/pic/상단바1.jpg"));
    private JButton close= new JButton(new ImageIcon("src/pic/closebutton.png"));     //상단 x버튼
    private JButton start= new JButton(new ImageIcon("src/pic/press.png"));           //시작 화면 스타트버튼
    private Clip backgroundClip,backgroundClip1,backgroundClip2,backgroundClip3;        // 배경음악들
   
    private int MX,MY;                                                                 //마우스좌표
   
    private boolean gamescreen =false ;                                               //true 일때 메인화면 - false 일때 시작화면
    private boolean gamescreen1 =false ;                                              //true 일때 게임오버 화면
    private boolean gamescreen2 =false ;                                              //true 일때 게임클리어 화면
    
    
	public Main() {
		
		  
		 setMain();
		 
		 
		//JBUTTON, JLABEL
		//스타트버튼
		  start.setBounds(150, 400, 300, 100);
		  start.setBorderPainted(false);                                                //버튼을 아이콘 자체로 나타내기
		  start.setContentAreaFilled(false);
		  start.setFocusPainted(false);
		  start.addMouseListener(new MouseAdapter() { 
			  public void mousePressed(MouseEvent e) {                                  //시작 버튼 눌렀을 때 이번트
				  backgroundClip3.stop();                                               //시작 배경음악 끄고 메인 배경음악 재생하기
				  backgroundClip.start();
				  start.setVisible(false);                                              //스타트버튼 누르면 화면에서 보이지 않게 함
				  BG = new ImageIcon("src/pic/mario world.jpg").getImage();
				  inGame=true;                                                          //화면에 게임 세트
				  gamescreen= true;                                                     //메인 화면으로 체인지
				  
				  setGame();
				  				  
				  } 
			  }); 

		// 상단 메뉴 x버튼 (마리오 모양)
		  close.setBounds(560, 2, 30, 30);
		  close.setBorderPainted(false);
		  close.setContentAreaFilled(false);
		  close.setFocusPainted(false);
		  close.addMouseListener(new MouseAdapter() {
			  public void mousePressed(MouseEvent e) {
				  System.exit(0);                                                        //x버튼 누르면 창 종료
				  } 
			  }); 
	  	  
		//상단 메뉴바
		menu.setBounds(0, 0, 600, 35);

	}
	
 
	private void setMain() {
		setFocusable(true);                                                              //화면에 포커싱
		addKeyListener(new TAdapter());	                                                 //방향키 먹게함
		setPreferredSize(new Dimension(Play.SCREEN_W, Play.SCREEN_H));
		setLayout(null);
		
		
		try {
			
		  File file = new File("src/music/main.wav");                      // 게임 배경음향
		  backgroundClip = AudioSystem.getClip();
		  backgroundClip.open(AudioSystem.getAudioInputStream(file));	  
		  
		  File file1 = new File("src/music/gameclear.wav");                // 이겼을 때 배경음향
		  backgroundClip1 = AudioSystem.getClip();
		  backgroundClip1.open(AudioSystem.getAudioInputStream(file1));	  
		  
		  File file2 = new File("src/music/gameover.wav");                 // 졌을 때 배경음향
		  backgroundClip2 = AudioSystem.getClip();
		  backgroundClip2.open(AudioSystem.getAudioInputStream(file2));	  
		  
		  File file3 = new File("src/music/startbgm.wav");                 // 메인게임 배경음향
		  backgroundClip3 = AudioSystem.getClip();
		  backgroundClip3.open(AudioSystem.getAudioInputStream(file3));
		  
		  } catch (Exception e) { 
			  System.out.println("음향 파일 로딩 실패"); 
			  }
		  
		  backgroundClip3.loop(Clip.LOOP_CONTINUOUSLY);                   //배경음악 반복재생
		  backgroundClip3.start();                                        //시작화면일때 자동으로 배경음악 재생
		  
	}

	private void setGame() {                                            
		
		block = new Block[Play.NUMBER_BLOCK];                             //블럭개수만큼의 배열인덱스 생성
		ball = new Mushroom();
		bar = new Bar();

		int k = 0;

		for (int i = 0; i < 4; i++) {                                      //블럭 32개 세팅
			for (int j = 0; j < 8; j++) {
				block[k] = new Block(j * 60 + 60, i * 70 + 90);            //블럭 간격 , 블럭 세팅 x,y좌표
				k++;
			}
		}
	
		timer=new Timer (10, new GameCycle());                              //10밀리초 마다 이동
	    timer.start();
		
	}
	 @Override
	public void paintComponent(Graphics g) {
		 
		super.paintComponent(g);	
		g.drawImage(SI,0,0,null);      //기본 배경화면 그리기
		
		if(gamescreen) {
			g.drawImage(BG, 0, 0, null);
		}
			
		   add(start);                  //시작버튼
		   add(close);                  //닫기버튼
     	   add(menu);                  //상단 메뉴바
	
		var g2d = (Graphics2D) g;

		if (inGame) {                   //inGame = true 일때 화면에 이미지 그리기
			drawObjects(g2d);
			
		} else {		
			gameDone(g2d);
		}
		
		

	}
	 

	private void drawObjects(Graphics2D g2d) {                     
		g2d.drawImage(ball.shapepic(), ball.finalX(), ball.finalY(), ball.shapewidth(), ball.shapeheight(), this);
		g2d.drawImage(bar.shapepic(), bar.finalX(), bar.finalY(), bar.shapewidth(), bar.shapeheight(), this);

		for (int i = 0; i < Play.NUMBER_BLOCK; i++) {             //블럭개수만큼 그리기 진행
			if (!block[i].broken()) {
				
				g2d.drawImage(block[i].shapepic(), block[i].finalX(), block[i].finalY(), block[i].shapewidth(),
						block[i].shapeheight(), this);

			}
		}
	}

	private void gameDone(Graphics2D g2d) {
		
		if(gamescreen1) {                                   //게임 오버 배경 gamescreen1 값이 true 일때 나타남
			
            g2d.drawImage(LI, 0, 0, null);
        }
		else if(gamescreen2) {                             //게임 클리어 배경 gamescreen2 값이 true 일때 나타남
			
			g2d.drawImage(WI, 0, 0, null);
			
		}
			
		
	
	}
	private class TAdapter extends KeyAdapter {                //방향키로 바 움직이기

        @Override
        public void keyReleased(KeyEvent e) {
            bar.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) { 
            bar.keyPressed(e);
        }
    }

	private class GameCycle implements ActionListener {	
		@Override
		public void actionPerformed(ActionEvent e) {
			doGameCycle();
			
		}
	}

	private void doGameCycle() {	
		ball.move();
		bar.move();
		checkConllision();         
		repaint();
	}

	public void stopGame() {
		backgroundClip.stop();	                               //메인 배경음악 끄기
		inGame = false;                                        //이미지 다 지우기
		timer.stop();
		
	}
	private void checkConllision() {

		if(ball.getRect().getMinX()<0) {                        //공이 왼쪽벽에 닿았을떄
			ball.ballXdir(5);		
		}
		if(ball.getRect().getMaxX()>Play.SCREEN_W) {            //공이 오른쪽벽에 닿았을떄
			ball.ballXdir(-5);
		}
		if(ball.getRect().getMinY()<35) {                       //공이 윗쪽벽에 닿았을떄 
			ball.ballYdir(5);
		}
		if(ball.getRect().getMaxY()>Play.MAGINO_LINE) {         //공이 아래 마지노 라인에 닿아서 실패
			gamescreen1=true;
			stopGame();
			backgroundClip2.start();
			
			
		}
		
		for(int i=0,j=0;i<Play.NUMBER_BLOCK;i++) {                
			if(block[i].broken()) {                          //블럭 개수만큼 블럭깨기 진행
				j++;
			}
			
			if(j==Play.NUMBER_BLOCK) {  
				                                              //ice를 다 깼을때!
				gamescreen2=true;
				stopGame();
				backgroundClip1.start();                      //게임 멈추고 승리음악 start
				
			}
		}
		
		if((ball.getRect()).intersects(bar.getRect())) {        // 공과 바가 부딫혔을때 충돌 체크
			
			int girlLeftP=(int)bar.getRect().getMinX();         // 바의 왼쪽 
			int ballLeftP=(int)ball.getRect().getMinX();        // 공의 왼쪽 
			
	     	//  바랑 부딪혔을 때 공을 위아래로만 튕기게 하니까 천장이랑 닿았을때 계속해서 같은곳만 튕기게 되었음
		    //  그래서 바x길이를 세개로 나눠서 각각 x,y방향을 틀어줌	
			int left = girlLeftP + 40;                          
			int right = girlLeftP + 60;                         
			
			if (ballLeftP < left) {
				ball.ballXdir(-5);
				ball.ballYdir(-5);
			}
		
			if (ballLeftP>=left && ballLeftP<=right) {
				ball.ballXdir(0);
				ball.ballYdir(-5-ball.getYdir());               
			}
			
			if (ballLeftP > right) {
				ball.ballXdir(5);
				ball.ballYdir(-5);
			}
			
		}			
			
		
		for(int i=0; i<Play.NUMBER_BLOCK; i++) {
			if((ball.getRect().intersects(block[i].getRect()))) {     //공이랑 블럭이랑 부딪혔을떄 
				
				int ballL=(int) ball.getRect().getMinX();             //공의 맨왼쪽
				int ballT=(int) ball.getRect().getMinY();             //공의 맨위
				int ballH=(int) ball.getRect().getHeight();           //공 높이
				int ballW=(int) ball.getRect().getWidth();            //공 너비	
				
				//공을 사각형으로 놓고 충돌처리를 해줄거니까 두면이 겹치는 꼭짓점보다는 각 면에서의 좌표를 1만큼 벗어난 포인트로 계산
				var LeftP= new Point(ballL-1,ballT);                  //왼쪽면에서 x좌표-1이라 생각하기
				var RightP=new Point(ballL+ballW+1, ballT);           //오른쪽면에서 x좌표+1
				var TopP= new Point(ballL,ballT-1);                   //윗면에서 y좌표-1
				var BottomP= new Point(ballL,ballT+ballH+1);          //아랫면에서 y좌표 +1
				
				if(!block[i].broken()) {                                 
					if(block[i].getRect().contains(RightP)) {          //공이랑 블럭이랑 부딫혔는데 RightP와 닿았다면.......
						ball.ballXdir(-5);                             //블럭이랑 부딪혔는데  RightP가 닿았으면 왼쪽방향으로 틀기 
					}
					else if(block[i].getRect().contains(LeftP)) {
						ball.ballXdir(5);                              
					}
					
					if(block[i].getRect().contains(TopP)) {          
						ball.ballYdir(5);                              //블럭이랑 부딪혔는데  TopP가 닿았으면 아래방향으로 틀기 
					}
					else if(block[i].getRect().contains(BottomP)) {
						ball.ballYdir(-5);                              
					}
					block[i].setbroke(true);                          //블럭 없애기
				}
					
			}
		}
		
	}

}

