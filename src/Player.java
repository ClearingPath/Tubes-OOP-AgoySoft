import java.awt.Point;
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
    private final int MaxInventory;
    private static enum Hadap {
        Atas, Bawah, Kiri, Kanan
    }
    //atribut buat highscore
    public Date scoredate;
    public String name;
    public int score;
    
    public int arah;
    
    private double tmpTime;
    private static final double walkTime=500;
    
    public Player() {
        super();
        Load("player.png");
        GetSprite().SetRotation(0);
        SetPosition(16,22);
        GetSprite().SetImageSize(32, 32);
        GetSprite().AddAnimType(0,0,1,0,2,-1); //inisiasi
        GetSprite().AddAnimType(1,0,1,0,1,-1); //bawah
        GetSprite().AddAnimType(2,1,1,1,1,-1); //kiri
        GetSprite().AddAnimType(3,2,1,2,1,-1); //kanan
        GetSprite().AddAnimType(4,3,1,3,1,-1); //atas
        
        GetSprite().AddAnimType(5,0,0,0,2,100); //bawah jalan
        GetSprite().AddAnimType(6,1,0,1,2,100); //kiri jalan
        GetSprite().AddAnimType(7,2,0,2,2,100); //kanan jalan
        GetSprite().AddAnimType(8,3,0,3,2,100); //atas jalan
        try {
            GetSprite().ChangeAnimType(0);
        } catch (AnimTypeNotFoundException e) {
            e.printStackTrace();
        }
        
        Attaching = false;
        SilentWalk = false;
        Hide = false;
        TimeRemaining = 60;
        MaxInventory = 5;
        Inventory = new ArrayList<>(MaxInventory);
        scoredate = new Date();
        //name = Pname;
        arah = 0;
        score = 0;
        tmpTime=-1;
    }
    public void setName(String Pname){
    	name=Pname;
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
    public void PickItem() {
        if(Inventory.size()<MaxInventory) {
            switch(arah) {
                case 1:
                    if(Game.peta[GetPosition().x][GetPosition().y+1].getItemOnTop().getJenis()!=item.ItemKosong.getJenis()) {
                        Inventory.add(Game.peta[GetPosition().x][GetPosition().y+1].getItemOnTop());
                        Game.peta[GetPosition().x][GetPosition().y+1].putItem(item.ItemKosong);
                    }
                    break;
                case 2:
                    if(Game.peta[GetPosition().x-1][GetPosition().y].getItemOnTop().getJenis()!=item.ItemKosong.getJenis()) {
                        Inventory.add(Game.peta[GetPosition().x-1][GetPosition().y].getItemOnTop());
                        Game.peta[GetPosition().x-1][GetPosition().y].putItem(item.ItemKosong);
                    }
                    break;
                case 3:
                    if(Game.peta[GetPosition().x+1][GetPosition().y].getItemOnTop().getJenis()!=item.ItemKosong.getJenis()) {
                        Inventory.add(Game.peta[GetPosition().x+1][GetPosition().y].getItemOnTop());
                        Game.peta[GetPosition().x+1][GetPosition().y].putItem(item.ItemKosong);
                    }
                    break;
                case 4:
                    if(Game.peta[GetPosition().x][GetPosition().y-1].getItemOnTop().getJenis()!=item.ItemKosong.getJenis()) {
                        Inventory.add(Game.peta[GetPosition().x][GetPosition().y-1].getItemOnTop());
                        Game.peta[GetPosition().x][GetPosition().y-1].putItem(item.ItemKosong);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public void MoveUp() {
        arah = 4;
        if(Game.peta[GetPosition().x][GetPosition().y-1].IsWalkable()) {
            tmpTime=0;
            try {
                //Update(1000);
                SetPosition(GetPosition().x, GetPosition().y-1);
                GetSprite().ChangeAnimType(8);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                GetSprite().ChangeAnimType(arah);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void MoveDown() {
        arah = 1;
        if(Game.peta[GetPosition().x][GetPosition().y+1].IsWalkable()) {
            tmpTime=0;
            try {
                //Update(1000);
                SetPosition(GetPosition().x, GetPosition().y+1);
                GetSprite().ChangeAnimType(5);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                GetSprite().ChangeAnimType(arah);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void MoveRight() {
        arah = 3;
        if(Game.peta[this.GetPosition().x+1][this.GetPosition().y].IsWalkable()) {
            tmpTime=0;
            try {
                //Update(1000);
                SetPosition(GetPosition().x+1, GetPosition().y);
                GetSprite().ChangeAnimType(7);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                GetSprite().ChangeAnimType(arah);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void MoveLeft() {
        arah = 2;
        if(Game.peta[this.GetPosition().x-1][this.GetPosition().y].IsWalkable()) {
            tmpTime=0;
            try {
                //Update(1000);
                SetPosition(GetPosition().x-1, GetPosition().y);
                GetSprite().ChangeAnimType(6);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
                GetSprite().ChangeAnimType(arah);
            } catch (AnimTypeNotFoundException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public item AttachItem(item i) {
        Inventory.remove(i);
        return i;
    }
    @Override
    public void Update(long elapsedTime) {
        System.out.println(Inventory);
    	if (tmpTime!=-1){
    		int dx[]={0,-1,1,0};
    		int dy[]={1,0,0,-1};
    		Point n1=GetPosition();
    		n1.x+=dx[(5-arah)-1];
    		n1.y+=dy[(5-arah)-1];
    		Point n=TiletoReal(n1);
    		double nx=n.x+dx[arah-1]*Utilities.TILE_SIZE_X*tmpTime/walkTime;
    		double ny=n.y+dy[arah-1]*Utilities.TILE_SIZE_Y*tmpTime/walkTime;
    		GetSprite().SetPosition(nx, ny);
    		tmpTime+=elapsedTime;
    		if (tmpTime>walkTime){
    			tmpTime=-1;
    			try {
					GetSprite().ChangeAnimType(arah);
				} catch (AnimTypeNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	/*try{
	        switch(arah) {
	            case 1:
	                GetSprite().ChangeAnimType(5);
	                SetPosition(GetPosition().x, GetPosition().y+1);
	                break;
	            case 2:
	                GetSprite().ChangeAnimType(6);
	                SetPosition(GetPosition().x-1, GetPosition().y);
	                break;
	            case 3:
	                GetSprite().ChangeAnimType(7);
	                SetPosition(GetPosition().x+1, GetPosition().y);
	                break;
	            case 4:
	                GetSprite().ChangeAnimType(8);
	                SetPosition(GetPosition().x, GetPosition().y-1);
	                break;
	        }
    	} catch (AnimTypeNotFoundException e){
    		System.out.println(e.getStackTrace());
    	}*/
    }
}
