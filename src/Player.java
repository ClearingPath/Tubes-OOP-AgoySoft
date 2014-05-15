package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author Rakhmatullah Yoga Sutrisna - 13512053
 */
public class Player extends VisibleGameObject {
    private boolean Attaching;
    private boolean SilentWalk;
    private boolean Hide;
    private int x,y; //ga perlu
    private int TimeRemaining;
    private ArrayList<Integer> Inventory;
    
    public Player() {
        super();
        /*JPanel p = new JPanel();
        JLabel label = new JLabel();
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);*/
        
        Attaching = false;
        SilentWalk = false;
        Hide = false;
        x = 0;
        y = 0;
        TimeRemaining = 60;
        Inventory = new ArrayList<>(5);
        super.Load("gambar");
        
    }
    public boolean isAttaching() {
        return Attaching;
    }
    public boolean isSilent() {
        return SilentWalk;
    }
    public boolean isHiding() {
        return Hide;
    }
    public int getTime() {
        return TimeRemaining;
    }
    public void setAttaching(boolean attach) {
        Attaching = attach;
    }
    public void setSilent(boolean silent) {
        SilentWalk = silent;
    }
    public void setHiding(boolean hide) {
        Hide = hide;
    }
    public void MoveUp() {
        y--;
        super.SetPosition(x, y);
    }
    public void MoveDown() {
        y++;
        super.SetPosition(x, y);
    }
    public void MoveRight() {
        x++;
        super.SetPosition(x, y);
    }
    public void MoveLeft() {
        x--;
        super.SetPosition(x, y);
    }
    
    /*public void Move(KeyEvent k) {
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
    }*/
    public void SetWalk(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_CONTROL) {
            SilentWalk = true;
            //System.out.println("Silent Walk");
        }
    }
    public void Hiding(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            Hide = !Hide;
            //System.out.println("Silent Walk");
        }
    }
    /*public void Update() {
        System.out.println("Posisi = ["+x+","+y+"]");
        System.out.println("Silent mode = "+SilentWalk);
        System.out.println("List inventory = "+Inventory);
        System.out.println("Attaching trap = "+Attaching);
        System.out.println("Hide = "+Hide);
        System.out.println("\n\n");
    }
    public static void main(String[] args) {
        Player P = new Player();
        P.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }*/

    /*@Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Move(e);
        Hiding(e);
        SetWalk(e);
        Update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            SilentWalk = false;
            //System.out.println("Normal Walk");
        }
        Update();
    }*/

    @Override
    public void Update(double elapsedTime) {
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("Posisi = ["+x+","+y+"]");
        System.out.println("Silent mode = "+SilentWalk);
        System.out.println("List inventory = "+Inventory);
        System.out.println("Attaching trap = "+Attaching);
        System.out.println("Hide = "+Hide);
        System.out.println("\n\n");
    }
    
}
