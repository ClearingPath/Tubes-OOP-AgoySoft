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
    public static int MAP_ROW_COUNT=24;
    public static int MAP_COL_COUNT=24;

    //ukuran peta yang dikasih lihat
    public static final int VIEW_ROW_COUNT=12;
    public static final int VIEW_COL_COUNT=12;

	//posisi tile peta yang ada di kiri atas peta yang dikasih lihat
	public static int VIEW_TILE_X=0;
	public static int VIEW_TILE_Y=0;
	
    //posisi ujung kiri atas peta
    public static final int VIEW_POS_X=0;
    public static final int VIEW_POS_Y=0;

    //ukuran tile
    public static final int TILE_SIZE_X=32;
    public static final int TILE_SIZE_Y=32; 
        
    public enum TileType {
       Walkable, UnWalkable,  Hideable;
    }
    
    public enum ItemType {
        Tissue(true), WC(false), Key(true), Knife(true), Painting(false), Manekin(false),
        DoorClosed(false), Spidol(true);
        
        public boolean walkable;
        
        private ItemType(boolean walk) {
            walkable = walk;
        }
        
        public boolean IsWalkable() {
            return walkable;
        }
        
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
