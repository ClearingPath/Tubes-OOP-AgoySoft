import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rakhmatullah Yoga Sutrisna - 13512053
 */
public class Player extends VisibleGameObject {
    private boolean Attaching;
    private boolean SilentWalk;
    private boolean Hide;
    private int TimeRemaining;
    private ArrayList<item> Inventory;
    private static enum Hadap {
        Atas, Bawah, Kiri, Kanan
    }
    //atribut buat highscore
    public Date scoredate;
    public String name;
    public int score;
    
    public int arah;

    public Player(String Pname) {
        super();
        Load("player.png");
        GetSprite().SetRotation(0);
        SetPosition(5,5);
        GetSprite().SetImageSize(32, 32);
        GetSprite().AddAnimType(0,0,1,0,2,-1); //inisiasi
        GetSprite().AddAnimType(1,0,1,0,1,-1); //bawah
        GetSprite().AddAnimType(2,1,1,1,1,-1); //kiri
        GetSprite().AddAnimType(3,2,1,2,1,-1); //kanan
        GetSprite().AddAnimType(4,3,1,3,1,-1); //atas
        
        GetSprite().AddAnimType(5,0,0,0,2,100); //bawah
        GetSprite().AddAnimType(6,1,0,1,2,100); //kiri
        GetSprite().AddAnimType(7,2,0,2,2,100); //kanan
        GetSprite().AddAnimType(8,3,0,3,2,100); //atas
        try {
            GetSprite().ChangeAnimType(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Attaching = false;
        SilentWalk = false;
        Hide = false;
        TimeRemaining = 60;
        Inventory = new ArrayList<>();
        scoredate = new Date();
        name = Pname;
        arah = 0;
        score = 0;
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
        arah = 4;
        try {
            //Update(1000);
            SetPosition(GetPosition().x, GetPosition().y-1);
            GetSprite().ChangeAnimType(4);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MoveDown() {
        arah = 1;
        try {
            //Update(1000);
            SetPosition(GetPosition().x, GetPosition().y+1);
            GetSprite().ChangeAnimType(1);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MoveRight() {
        arah = 3;
        try {
            //Update(1000);
            SetPosition(GetPosition().x+1, GetPosition().y);
            GetSprite().ChangeAnimType(3);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MoveLeft() {
        arah = 2;
        try {
            //Update(1000);
            SetPosition(GetPosition().x-1, GetPosition().y);
            GetSprite().ChangeAnimType(2);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        switch(arah) {
            case 1:
                try {
                    GetSprite().ChangeAnimType(5);
                    SetPosition(GetPosition().x, GetPosition().y+1);
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    GetSprite().ChangeAnimType(6);
                    SetPosition(GetPosition().x-1, GetPosition().y);
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 3:
                try {
                    GetSprite().ChangeAnimType(7);
                    SetPosition(GetPosition().x+1, GetPosition().y);
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 4:
                try {
                    GetSprite().ChangeAnimType(8);
                    SetPosition(GetPosition().x, GetPosition().y-1);
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
