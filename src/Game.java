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
	private static final long BUTTON_DELAY_TIME = 500;
	
	// TODO ubah jadi array of tile
	public static Tile[][] peta;

	// panel-panel mode persiapan
    public WelcomeScreen start;
    public PlayScreen play;
    public HighScore topplayer;
    public HowToPlay help;
    public Credits credits;

    private long lastUpdate, elapsedTime;
	private long tmpTime;
	public static JFrame frame;
	private Graphics g;
	public GameObjectManager _gameObjectManager;
    public Player P;
    public Owner O;
    
    private static Game _game;
    
    public static GameObjectManager GetGameObjectManager(){
    	return _game._gameObjectManager;
    }

    private SimpleTiledPic bg;
    private SimpleTiledPic layer1;
    
    public JPanel active_panel;
    public Utilities.StateType state_now;
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

        bg=new SimpleTiledPic();
        bg.Load("img/Level1/level1_back+furniture.png");
        bg.SetPosition(0, 0);
        bg.GetSprite().SetImageSize(Utilities.TILE_SIZE_X*Utilities.VIEW_COL_COUNT/*
         						*/, Utilities.TILE_SIZE_Y*Utilities.VIEW_ROW_COUNT);
        layer1=new SimpleTiledPic();
        layer1.Load("img/Level1/level1_hideable.png");
        layer1.SetPosition(0, 0);
        layer1.GetSprite().SetImageSize(Utilities.TILE_SIZE_X*Utilities.VIEW_COL_COUNT/*
         						*/, Utilities.TILE_SIZE_Y*Utilities.VIEW_ROW_COUNT);
    }
	
	public static void main(String[] args) throws InterruptedException {
        frame = new JFrame("Agoy Soft");
        new Game();
        //frame.add(game);
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
		bg.GetSprite().SetOffset(Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X, Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y);
		layer1.GetSprite().SetOffset(Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X, Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y);
		bg.SetPosition(Utilities.VIEW_TILE_X, Utilities.VIEW_TILE_Y);
		layer1.SetPosition(Utilities.VIEW_TILE_X, Utilities.VIEW_TILE_Y);
		_gameObjectManager.UpdateAll(elapsedTime);
	}
	
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
			/*System.out.println(P.GetPosition().x+" "+P.GetPosition().y);
			for (int i=-2;i<2;i++){
				for (int j=-2;j<2;j++){
					int b=(Game.peta[j+P.GetPosition().x][i+P.GetPosition().y].IsWalkable()?0:1);
					System.out.print(b+" ");
				}
				System.out.println();
			}*/
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
        //P.arah = 0;
	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
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
}
