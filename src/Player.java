import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Rakhmatullah Yoga Sutrisna - 13512053
 */
public class Player extends VisibleGameObject {
    private boolean Attaching;
    private boolean SilentWalk;
    private boolean Hide;
    //private int x,y;
    private int TimeRemaining;
    private ArrayList<item> Inventory;
    private static enum Hadap {
        Atas, Bawah, Kiri, Kanan
    }
    public Date scoredate;
    public String name;
    public int score;

    public Player(String Pname) {
        super();
        Attaching = false;
        SilentWalk = false;
        Hide = false;
        TimeRemaining = 60;
        Inventory = new ArrayList<>();
        scoredate = new Date();
        name = Pname;
        score = 0;
        //super.Load("gambar");
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
    public void setHiding() {
        Hide = !Hide;
    }
    public void MoveUp() {
        //set hadap dulu
        //y--;
        SetPosition(GetPosition().x, GetPosition().y-1);
    }
    public void MoveDown() {
        //y++;
        SetPosition(GetPosition().x, GetPosition().y+1);
    }
    public void MoveRight() {
        //x++;
        SetPosition(GetPosition().x+1, GetPosition().y);
    }
    public void MoveLeft() {
        //x--;
        SetPosition(GetPosition().x-1, GetPosition().y);
    }
    public void PickItem(item i) {
        Inventory.add(i);
    }
    public item AttachItem(item i) {
        Inventory.remove(i);
        return i;
    }
    public void IsWalkable() {
        
    }
    @Override
    public void Update(long elapsedTime) {
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("Posisi = ["+GetPosition().x+","+GetPosition().y+"]");
        System.out.println("Silent mode = "+SilentWalk);
        System.out.println("List inventory = "+Inventory);
        System.out.println("Attaching trap = "+Attaching);
        System.out.println("Hide = "+Hide);
        System.out.println("\n\n");
    }
    
}
