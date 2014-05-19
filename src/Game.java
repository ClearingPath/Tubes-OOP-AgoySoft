import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable,MouseListener, MouseMotionListener,KeyListener{
    private static final long serialVersionUID = 7921782677990497038L;
    public static JFrame frame;
    private static Game _game;
    private long lastUpdate, elapsedTime;
    private long tmpTime;
    private Graphics g;
    public GameObjectManager _gameObjectManager;

    //delay antar tiap pergerakan
    private static final long BUTTON_DELAY_TIME = 500;

    // panel-panel mode persiapan
    public WelcomeScreen start;
    public PlayScreen play;
    public HighScore topplayer;
    public HowToPlay help;
    public Credits credits;

    //panel yang aktif
    public JPanel active_panel;
    //Status game saat ini
    public Utilities.StateType state_now;
    
    public Player P;
    public Owner O;
   // TODO ubah jadi array of tile
    public static Tile[][] peta;
    
    //Background
    private Sprite bg;
    //Layer background yang ada di atas sebagian besar objek
    private Sprite layer1;
    
    /** 
     * Mendapatkan GameObjectManager milik singleton game
     * @return GameObjectManager GameObjectManager milik singleton game
    */
    public static GameObjectManager GetGameObjectManager(){
    	return _game._gameObjectManager;
    }
    /** 
     * Mengubah state dari game
     * @param in State tujuan dari pengubahan
    */
    public static void ChangeState(Utilities.StateType in){
    	if (_game.state_now!=in){
    		_game.active_panel.setVisible(false);
            frame.remove(_game.active_panel);
            _game.state_now=in;
    		if (in==Utilities.StateType.WelcomeScreen){
            	_game.active_panel=_game.start;
                _game.start.setVisible(true);
                frame.add(_game.start);
            } else if (in==Utilities.StateType.StartScreen){
            	_game.active_panel=_game.play;
                _game.play.setVisible(true);
                frame.add(_game.play);
            } else if (in==Utilities.StateType.HighScore){
            	_game.active_panel=_game.topplayer;
                _game.topplayer.setVisible(true);
                frame.add(_game.topplayer);
            } else if (in==Utilities.StateType.Help){
            	_game.active_panel=_game.help;
                _game.help.setVisible(true);
                frame.add(_game.help);
            } else if (in==Utilities.StateType.Credits){
            	_game.active_panel=_game.credits;
                _game.credits.setVisible(true);
                frame.add(_game.credits);
            } else if (in==Utilities.StateType.Playing){
            	_game.active_panel=_game;
                _game.P.setName(Utilities.mediator_string);
            	_game.setVisible(true);
                frame.add(_game);
                _game.addMouseListener(_game);
            	_game.addMouseMotionListener(_game);
        		_game.addKeyListener(_game);
        		_game.setFocusable(true);
        		_game.requestFocusInWindow();
            } else if (in==Utilities.StateType.Quit){
            	frame.dispose();
            }
    	}
    }
    
    /** 
     * Constructor
    */
    private Game() {
            _game=this;
            _gameObjectManager=new GameObjectManager();
	    setSize(700, 700);
            setBackground(Color.white);
            tmpTime = -1;
	    init();
            Thread thread = new Thread(this);
            thread.start();
	}

    /** 
     * Inisialisasi semua objek yang ada di game
    */
    private void init(){
        // TODO create all object here
        // contoh init
        // ObjTest turunan dari VisibleGameObject
        //ObjTest obj=new ObjTest(5);
        //_gameObjectManager.Add("nama", obj);
        //Objv1 obj=new Objv1();
        P = new Player();
        O = new Owner();
        _gameObjectManager.Add("player", P);
        _gameObjectManager.Add("owner", O);
        Builder.BuildLevel1();
        // contoh panggil Object
        // kalo salah kelas, exception keluar
        //ObjTest bcd;
        //bcd=(ObjTest)g.Get("nama");

        // panel construction
        start = new WelcomeScreen();
        play = new PlayScreen();
        topplayer = new HighScore();
        help = new HowToPlay();
        credits = new Credits();

        bg=new Sprite();
        layer1=new Sprite();
        try {
                bg.Load("img/Level1/level1_back+furniture.png");
                layer1.Load("img/Level1/level1_hideable.png");
	    } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        bg.SetPosition(Utilities.VIEW_POS_X, Utilities.VIEW_POS_Y);
        bg.SetImageSize(Utilities.TILE_SIZE_X*Utilities.VIEW_COL_COUNT/*
         						*/, Utilities.TILE_SIZE_Y*Utilities.VIEW_ROW_COUNT);
        layer1.SetPosition(Utilities.VIEW_POS_X, Utilities.VIEW_POS_Y);
        layer1.SetImageSize(Utilities.TILE_SIZE_X*Utilities.VIEW_COL_COUNT/*
         						*/, Utilities.TILE_SIZE_Y*Utilities.VIEW_ROW_COUNT);
    }
    
	public static void main(String[] args) throws InterruptedException {
        frame = new JFrame("Agoy Soft");
        new Game();
        frame.add(_game.start);
        frame.setVisible(true);
        _game.active_panel=_game.start;
        _game.state_now=Utilities.StateType.WelcomeScreen;
        Game.ChangeState(Utilities.StateType.Playing);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2-20);
	}
	/** 
     * Method update yang meng-update semua objek yang ada di game
     * Dijalankan setiap interval tertentu (60 fps)
     * @param g Screen yang mungkin akan diubah atau dilihat statusnya
    */
    public void update(Graphics g){
		elapsedTime = System.currentTimeMillis() - lastUpdate;
		lastUpdate = System.currentTimeMillis();
		
		if (tmpTime>=0){
			tmpTime+=elapsedTime;
			if (tmpTime>=BUTTON_DELAY_TIME){
				tmpTime=-1;
			}
		}
		
		int tmp,tp2;
		tp2=(int)(Utilities.VIEW_COL_COUNT/2);
		Utilities.VIEW_TILE_X=P.GetPosition().x-tp2;
		if (Utilities.VIEW_TILE_X<0)Utilities.VIEW_TILE_X=0;
		tmp=Utilities.VIEW_TILE_X+Utilities.VIEW_COL_COUNT;
		if (tmp>=Utilities.MAP_COL_COUNT)Utilities.VIEW_TILE_X=Utilities.MAP_COL_COUNT-Utilities.VIEW_COL_COUNT;
		Utilities.VIEW_TILE_Y=P.GetPosition().y-(int)(Utilities.VIEW_ROW_COUNT/2);
		if (Utilities.VIEW_TILE_Y<0)Utilities.VIEW_TILE_Y=0;
		tmp=Utilities.VIEW_TILE_Y+Utilities.VIEW_ROW_COUNT;
		if (tmp>=Utilities.MAP_ROW_COUNT)Utilities.VIEW_TILE_Y=Utilities.MAP_ROW_COUNT-Utilities.VIEW_ROW_COUNT;
		
		bg.SetOffset(Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X, Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y);
		bg.SetPosition(Utilities.VIEW_POS_X, Utilities.VIEW_POS_X);
		
		layer1.SetOffset(Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X, Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y);
		layer1.SetPosition(Utilities.VIEW_POS_X, Utilities.VIEW_POS_X);
		
		_gameObjectManager.UpdateAll(elapsedTime);
	}
    /** 
     * Method menggambar semua objek ke screen
     * Dijalankan setiap interval tertentu (60 fps)
     * @param g Screen yang akan digambar
    */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.g = g;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		bg.Draw(g2d, this);
		_gameObjectManager.DrawAll(g2d,this);
		layer1.Draw(g2d, this);
		
		g2d.setColor(Color.white);
	}
    /** 
     * Method inti dari game yang dijalankan pada thread berbeda
     * Method ini menjalankan update dan paintComponent pada interval tertentu (60 fps)
    */
    @Override
	public void run() {
		while(state_now!=Utilities.StateType.Quit){
			update(g);
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
    /** 
     * Method Listener yang dijalankan ketika ada tombol yang ditekan
     * @param key Informasi tentang tombol yang ditekan
    */
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
			} else if (key.getKeyCode() == KeyEvent.VK_SPACE){
				//tmpTime=0;
				P.PickItem();
			}
		}
	}
    /** 
     * Method Listener yang dijalankan ketika ada tombol yang dilepas
     * @param key Informasi tentang tombol yang dilepas
    */
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
        //P.arah = 0;
	}
    /** 
     * Method Listener yang dijalankan ketika ada tombol yang diketik
     * atau dalam kata lain, ditekan lalu dilepas
     * @param key Informasi tentang tombol yang diketik
    */
    @Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika mouse digerakkan
     * @param event Informasi tentang mouse saat ini
    */
    @Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika mouse di drag
     * Di drag adalah ketika mouse digerakkan ketika tombol kiri mouse ditekan
     * @param arg0 Informasi tentang mouse saat ini
    */
    @Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika ada tombol mouse yang ditekan
     * @param event Informasi tentang mouse saat ini
    */
    @Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika ada tombol mouse yang dilepas
     * @param arg0 Informasi tentang mouse saat ini
    */
    @Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika tombol mouse di-klik
     * di-klik maksudnya adalah ditekan lalu dilepas
     * @param event Informasi tentang mouse saat ini
    */
    @Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika pointer mouse masuk ke screen
     * @param arg0 Informasi tentang mouse saat ini
    */
    @Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    /** 
     * Method Listener yang dijalankan ketika pointer mouse keluar ke screen
     * @param arg0 Informasi tentang mouse saat ini
    */
    @Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
