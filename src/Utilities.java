/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Afik
 */

public class Utilities {
    //ukuran asli peta
    public static int MAP_ROW_COUNT;
    public static int MAP_COL_COUNT;

    //ukuran peta yang dikasih lihat
    public static final int VIEW_ROW_COUNT=12;
    public static final int VIEW_COL_COUNT=12;

    //posisi tile peta yang ada di kiri atas peta yang dikasih lihat
    public static int VIEW_TILE_X=0;
    public static int VIEW_TILE_Y=0;

    //posisi ujung kiri atas peta
    public static final int VIEW_POS_X=50;
    public static final int VIEW_POS_Y=50;

    //ukuran tile
    public static final int TILE_SIZE_X=32;
    public static final int TILE_SIZE_Y=32; 
        
    public enum TileType {
       Walkable, UnWalkable,  Hideable;
    }
    
    public enum ItemType {
        Tissue(true, true, "Tissue", "kosong"), WC(false, false, "WC", "Tissue"), Key(true, true, "Key", "kosong"), Knife(true, true, "Knife", "kosong"), 
        Painting(false, false, "Painting", "Spidol"), Manekin(false, false, "Manekin","Knife"), ManekinR(false, false, "ManekinR", "kosong"),
        PaintingR(false, false, "PaintingR", "kosong"), WCR(false, false, "WCR", "kosong"),
        DoorClosed(false, false, "DoorClosed", "Key"), Spidol(true, true, "Spidol", "kosong"), ItemKosong(false, false, "kosong", "kosong");
        
        public boolean walkable;
        public String pic;
        public String it;
        public boolean getable;
        
        private ItemType(boolean walk, boolean ga, String pic, String itm) {
            walkable = walk;
            this.pic = pic;
            it = itm;
            getable = ga;
        }
        
        public boolean IsWalkable() {
            return walkable;
        }
        
        public boolean IsGetable() { return getable; }
        public String getPic() {return pic;}
        
        public String getIt() {return it;}
    }
	
    public enum StateType{
    	WelcomeScreen, StartScreen, Playing, HighScore, Help, Credits, Quit;
    }
    
    public static String mediator_string;
}

class AnimTypeNotFoundException extends Exception{
	private static final long serialVersionUID = 1897174443650738740L;
}

class ObjectNameNotFoundException extends Exception{
	private static final long serialVersionUID = 8872217230896936215L;
}
