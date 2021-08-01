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
	private boolean inGame = false;                                                   //���۹�ư ������ ������ ���� ���� �ȵǰ� ��
	
	private Image SI = new ImageIcon("src/pic/start.jpg").getImage();                 //���� ���
	private Image BG = new ImageIcon("src/pic/mario world.jpg").getImage();           //����̹��� 
	private Image WI = new ImageIcon("src/pic/wingame.jpg").getImage();               //�̰����� ���
	private Image LI = new ImageIcon("src/pic/gameover.png").getImage();              //������ ���
	private JLabel menu = new JLabel(new ImageIcon("src/pic/��ܹ�1.jpg"));
    private JButton close= new JButton(new ImageIcon("src/pic/closebutton.png"));     //��� x��ư
    private JButton start= new JButton(new ImageIcon("src/pic/press.png"));           //���� ȭ�� ��ŸƮ��ư
    private Clip backgroundClip,backgroundClip1,backgroundClip2,backgroundClip3;        // ������ǵ�
   
    private int MX,MY;                                                                 //���콺��ǥ
   
    private boolean gamescreen =false ;                                               //true �϶� ����ȭ�� - false �϶� ����ȭ��
    private boolean gamescreen1 =false ;                                              //true �϶� ���ӿ��� ȭ��
    private boolean gamescreen2 =false ;                                              //true �϶� ����Ŭ���� ȭ��
    
    
	public Main() {
		
		  
		 setMain();
		 
		 
		//JBUTTON, JLABEL
		//��ŸƮ��ư
		  start.setBounds(150, 400, 300, 100);
		  start.setBorderPainted(false);                                                //��ư�� ������ ��ü�� ��Ÿ����
		  start.setContentAreaFilled(false);
		  start.setFocusPainted(false);
		  start.addMouseListener(new MouseAdapter() { 
			  public void mousePressed(MouseEvent e) {                                  //���� ��ư ������ �� �̹�Ʈ
				  backgroundClip3.stop();                                               //���� ������� ���� ���� ������� ����ϱ�
				  backgroundClip.start();
				  start.setVisible(false);                                              //��ŸƮ��ư ������ ȭ�鿡�� ������ �ʰ� ��
				  BG = new ImageIcon("src/pic/mario world.jpg").getImage();
				  inGame=true;                                                          //ȭ�鿡 ���� ��Ʈ
				  gamescreen= true;                                                     //���� ȭ������ ü����
				  
				  setGame();
				  				  
				  } 
			  }); 

		// ��� �޴� x��ư (������ ���)
		  close.setBounds(560, 2, 30, 30);
		  close.setBorderPainted(false);
		  close.setContentAreaFilled(false);
		  close.setFocusPainted(false);
		  close.addMouseListener(new MouseAdapter() {
			  public void mousePressed(MouseEvent e) {
				  System.exit(0);                                                        //x��ư ������ â ����
				  } 
			  }); 
	  	  
		//��� �޴���
		menu.setBounds(0, 0, 600, 35);

	}
	
 
	private void setMain() {
		setFocusable(true);                                                              //ȭ�鿡 ��Ŀ��
		addKeyListener(new TAdapter());	                                                 //����Ű �԰���
		setPreferredSize(new Dimension(Play.SCREEN_W, Play.SCREEN_H));
		setLayout(null);
		
		
		try {
			
		  File file = new File("src/music/main.wav");                      // ���� �������
		  backgroundClip = AudioSystem.getClip();
		  backgroundClip.open(AudioSystem.getAudioInputStream(file));	  
		  
		  File file1 = new File("src/music/gameclear.wav");                // �̰��� �� �������
		  backgroundClip1 = AudioSystem.getClip();
		  backgroundClip1.open(AudioSystem.getAudioInputStream(file1));	  
		  
		  File file2 = new File("src/music/gameover.wav");                 // ���� �� �������
		  backgroundClip2 = AudioSystem.getClip();
		  backgroundClip2.open(AudioSystem.getAudioInputStream(file2));	  
		  
		  File file3 = new File("src/music/startbgm.wav");                 // ���ΰ��� �������
		  backgroundClip3 = AudioSystem.getClip();
		  backgroundClip3.open(AudioSystem.getAudioInputStream(file3));
		  
		  } catch (Exception e) { 
			  System.out.println("���� ���� �ε� ����"); 
			  }
		  
		  backgroundClip3.loop(Clip.LOOP_CONTINUOUSLY);                   //������� �ݺ����
		  backgroundClip3.start();                                        //����ȭ���϶� �ڵ����� ������� ���
		  
	}

	private void setGame() {                                            
		
		block = new Block[Play.NUMBER_BLOCK];                             //��������ŭ�� �迭�ε��� ����
		ball = new Mushroom();
		bar = new Bar();

		int k = 0;

		for (int i = 0; i < 4; i++) {                                      //�� 32�� ����
			for (int j = 0; j < 8; j++) {
				block[k] = new Block(j * 60 + 60, i * 70 + 90);            //�� ���� , �� ���� x,y��ǥ
				k++;
			}
		}
	
		timer=new Timer (10, new GameCycle());                              //10�и��� ���� �̵�
	    timer.start();
		
	}
	 @Override
	public void paintComponent(Graphics g) {
		 
		super.paintComponent(g);	
		g.drawImage(SI,0,0,null);      //�⺻ ���ȭ�� �׸���
		
		if(gamescreen) {
			g.drawImage(BG, 0, 0, null);
		}
			
		   add(start);                  //���۹�ư
		   add(close);                  //�ݱ��ư
     	   add(menu);                  //��� �޴���
	
		var g2d = (Graphics2D) g;

		if (inGame) {                   //inGame = true �϶� ȭ�鿡 �̹��� �׸���
			drawObjects(g2d);
			
		} else {		
			gameDone(g2d);
		}
		
		

	}
	 

	private void drawObjects(Graphics2D g2d) {                     
		g2d.drawImage(ball.shapepic(), ball.finalX(), ball.finalY(), ball.shapewidth(), ball.shapeheight(), this);
		g2d.drawImage(bar.shapepic(), bar.finalX(), bar.finalY(), bar.shapewidth(), bar.shapeheight(), this);

		for (int i = 0; i < Play.NUMBER_BLOCK; i++) {             //��������ŭ �׸��� ����
			if (!block[i].broken()) {
				
				g2d.drawImage(block[i].shapepic(), block[i].finalX(), block[i].finalY(), block[i].shapewidth(),
						block[i].shapeheight(), this);

			}
		}
	}

	private void gameDone(Graphics2D g2d) {
		
		if(gamescreen1) {                                   //���� ���� ��� gamescreen1 ���� true �϶� ��Ÿ��
			
            g2d.drawImage(LI, 0, 0, null);
        }
		else if(gamescreen2) {                             //���� Ŭ���� ��� gamescreen2 ���� true �϶� ��Ÿ��
			
			g2d.drawImage(WI, 0, 0, null);
			
		}
			
		
	
	}
	private class TAdapter extends KeyAdapter {                //����Ű�� �� �����̱�

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
		backgroundClip.stop();	                               //���� ������� ����
		inGame = false;                                        //�̹��� �� �����
		timer.stop();
		
	}
	private void checkConllision() {

		if(ball.getRect().getMinX()<0) {                        //���� ���ʺ��� �������
			ball.ballXdir(5);		
		}
		if(ball.getRect().getMaxX()>Play.SCREEN_W) {            //���� �����ʺ��� �������
			ball.ballXdir(-5);
		}
		if(ball.getRect().getMinY()<35) {                       //���� ���ʺ��� ������� 
			ball.ballYdir(5);
		}
		if(ball.getRect().getMaxY()>Play.MAGINO_LINE) {         //���� �Ʒ� ������ ���ο� ��Ƽ� ����
			gamescreen1=true;
			stopGame();
			backgroundClip2.start();
			
			
		}
		
		for(int i=0,j=0;i<Play.NUMBER_BLOCK;i++) {                
			if(block[i].broken()) {                          //�� ������ŭ ������ ����
				j++;
			}
			
			if(j==Play.NUMBER_BLOCK) {  
				                                              //ice�� �� ������!
				gamescreen2=true;
				stopGame();
				backgroundClip1.start();                      //���� ���߰� �¸����� start
				
			}
		}
		
		if((ball.getRect()).intersects(bar.getRect())) {        // ���� �ٰ� �΋H������ �浹 üũ
			
			int girlLeftP=(int)bar.getRect().getMinX();         // ���� ���� 
			int ballLeftP=(int)ball.getRect().getMinX();        // ���� ���� 
			
	     	//  �ٶ� �ε����� �� ���� ���Ʒ��θ� ƨ��� �ϴϱ� õ���̶� ������� ����ؼ� �������� ƨ��� �Ǿ���
		    //  �׷��� ��x���̸� ������ ������ ���� x,y������ Ʋ����	
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
			if((ball.getRect().intersects(block[i].getRect()))) {     //���̶� ���̶� �ε������� 
				
				int ballL=(int) ball.getRect().getMinX();             //���� �ǿ���
				int ballT=(int) ball.getRect().getMinY();             //���� ����
				int ballH=(int) ball.getRect().getHeight();           //�� ����
				int ballW=(int) ball.getRect().getWidth();            //�� �ʺ�	
				
				//���� �簢������ ���� �浹ó���� ���ٰŴϱ� �θ��� ��ġ�� ���������ٴ� �� �鿡���� ��ǥ�� 1��ŭ ��� ����Ʈ�� ���
				var LeftP= new Point(ballL-1,ballT);                  //���ʸ鿡�� x��ǥ-1�̶� �����ϱ�
				var RightP=new Point(ballL+ballW+1, ballT);           //�����ʸ鿡�� x��ǥ+1
				var TopP= new Point(ballL,ballT-1);                   //���鿡�� y��ǥ-1
				var BottomP= new Point(ballL,ballT+ballH+1);          //�Ʒ��鿡�� y��ǥ +1
				
				if(!block[i].broken()) {                                 
					if(block[i].getRect().contains(RightP)) {          //���̶� ���̶� �΋H���µ� RightP�� ��Ҵٸ�.......
						ball.ballXdir(-5);                             //���̶� �ε����µ�  RightP�� ������� ���ʹ������� Ʋ�� 
					}
					else if(block[i].getRect().contains(LeftP)) {
						ball.ballXdir(5);                              
					}
					
					if(block[i].getRect().contains(TopP)) {          
						ball.ballYdir(5);                              //���̶� �ε����µ�  TopP�� ������� �Ʒ��������� Ʋ�� 
					}
					else if(block[i].getRect().contains(BottomP)) {
						ball.ballYdir(-5);                              
					}
					block[i].setbroke(true);                          //�� ���ֱ�
				}
					
			}
		}
		
	}

}

