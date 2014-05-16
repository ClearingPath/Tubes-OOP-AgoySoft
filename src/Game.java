import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable,MouseListener, MouseMotionListener,KeyListener{
	//delay antar tiap pergerakan
	private static final long BUTTON_DELAY_TIME = 1000;
	
	//ukuran asli peta
	public static int MAP_ROW_COUNT=15;
	public static int MAP_COL_COUNT=12;
	
	//ukuran peta yang dikasih lihat
	public static final int VIEW_ROW_COUNT=12;
	public static final int VIEW_COL_COUNT=12;
	
	//posisi ujung kiri atas peta
	public static final int VIEW_POS_X=0;
	public static final int VIEW_POS_Y=0;
	
	//ukuran tile
	public static final int TILE_SIZE_X=32;
	public static final int TILE_SIZE_Y=32;
	
	// TODO ubah jadi array of tile
	public VisibleGameObject[][] peta;
	
	private long lastUpdate, elapsedTime;
	private long tmpTime;
	private static JFrame frame;
	private Graphics g;
	public GameObjectManager _gameObjectManager;
        public Player P;
	
	public Game() {
		init();
		Thread thread = new Thread(this);
		thread.start();
	}

	public void init(){
		_gameObjectManager=new GameObjectManager();
                P = new Player();
		setSize(700, 700);
		setBackground(Color.white);
		tmpTime = -1;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		// TODO create all object here
		// contoh init
		// ObjTest turunan dari VisibleGameObject
		//ObjTest obj=new ObjTest(5);
		//_gameObjectManager.Add("nama", obj);
		Objv1 obj=new Objv1();
		_gameObjectManager.Add("nama", obj);
		// contoh panggil Object
		// kalo salah kelas, exception keluar
		//ObjTest bcd;
		//bcd=(ObjTest)g.Get("nama");
	}
	
	public static void main(String[] args) throws InterruptedException {
		frame = new JFrame("Agoy Soft");
		Game game = new Game();
		frame.add(game);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2-20);
        frame.setVisible(true);
	}
	
	public void update(Graphics g){
		elapsedTime = System.currentTimeMillis() - lastUpdate;
		lastUpdate = System.currentTimeMillis();
		if (tmpTime>=0){
			tmpTime+=elapsedTime;
			if (tmpTime>=BUTTON_DELAY_TIME){
				tmpTime=-1;
			}
		}
		_gameObjectManager.UpdateAll(elapsedTime);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.g = g;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		_gameObjectManager.DrawAll(g2d,this);
		g2d.setColor(Color.white);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while(true){
			update(g);
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (tmpTime==-1){
			if (key.getKeyCode() == KeyEvent.VK_LEFT){
				tmpTime=0;
				P.MoveLeft();
			} else if (key.getKeyCode() == KeyEvent.VK_UP){
				tmpTime=0;
				P.MoveUp();
			} else if (key.getKeyCode() == KeyEvent.VK_RIGHT){
				tmpTime=0;
				P.MoveRight();
			} else if (key.getKeyCode() == KeyEvent.VK_DOWN){
				tmpTime=0;
				P.MoveDown();
			} else if (key.getKeyCode() == KeyEvent.VK_CONTROL){
				tmpTime=0;
				P.setSilent(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if ((key.getKeyCode() == KeyEvent.VK_LEFT)/*
		*/||(key.getKeyCode() == KeyEvent.VK_UP)/*
		*/||(key.getKeyCode() == KeyEvent.VK_RIGHT)/*
		*/||(key.getKeyCode() == KeyEvent.VK_DOWN)){
			tmpTime=-1;
		} else if (key.getKeyCode() == KeyEvent.VK_CONTROL){
                        tmpTime=-1;
                        P.setSilent(false);
                }
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}
}
