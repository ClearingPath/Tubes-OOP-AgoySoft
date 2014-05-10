package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author Rakhmatullah Yoga Sutrisna - 13512053
 */
public class Player extends JFrame implements KeyListener {
    private boolean Attaching;
    private boolean SilentWalk;
    private boolean Hide;
    private int x,y;
    private int TimeRemaining;
    private ArrayList<Integer> Inventory;
    
    public Player() {
        super();
        JPanel p = new JPanel();
        JLabel label = new JLabel();
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
        
        Attaching = false;
        SilentWalk = false;
        Hide = false;
        x = 0;
        y = 0;
        TimeRemaining = 60;
        Inventory = new ArrayList<>(5);
    }
    public void Move(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_DOWN) {
            y--;
            //System.out.println("Walk Down");
        }
        if (k.getKeyCode() == KeyEvent.VK_UP) {
            y++;
            //System.out.println("Walk Up");
        }
        if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
            //System.out.println("Walk Right");
        }
        if (k.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
            //System.out.println("Walk Left");
        }
    }
    public void SetWalk(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_CONTROL) {
            SilentWalk = true;
            //System.out.println("Silent Walk");
        }
    }
    public void Update() {
        System.out.println("Posisi = ["+x+","+y+"]");
        System.out.println("Silent mode = "+SilentWalk);
        System.out.println("List inventory = "+Inventory);
    }
    public void Draw() {
        
    }
    public static void main(String[] args) {
        Player P = new Player();
        P.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Move(e);
        SetWalk(e);
        Update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            SilentWalk = false;
            System.out.println("Normal Walk");
        }
    }
    
}
