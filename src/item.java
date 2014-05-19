/*File : item.java */
/*Author : 13512093 - Jonathan Sudibya */

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class item extends VisibleGameObject{
    public static item ItemKosong = initKosong();
    
    /* Attribute */
    public boolean Broken; /* udah dirusak / belum */
    private long waktu;
    private Utilities.ItemType jenis;
    public Point ukuran;

    /* Constructor */
    public item(){
        waktu = 1000;
    }
    
    public static item initKosong() {
        item i = new item();
        //Game.GetGameObjectManager().Add("ItemKosong", i);
        i.setJenis(Utilities.ItemType.ItemKosong);
        return i;
    }
    /* method */
    
    public void setBroken(boolean Broken) {
        this.Broken = Broken;
    }

    public void setWaktu(long waktu) {
        this.waktu = waktu;
    }

    public void setJenis(Utilities.ItemType jenis) {
        this.jenis = jenis;
    }

    public void setUkuran(Point ukuran) {
        this.ukuran = ukuran;
    }

    public boolean isBroken() {
        return Broken;
    }

    public float getWaktu() {
        return waktu;
    }

    public Utilities.ItemType getJenis() {
        return jenis;
    }

    public Point getUkuran() {
        return ukuran;
    }
    
    public boolean isWalkable() {
        return jenis.IsWalkable();
    }

    public void Update(long elapsedTime) {
        SetPosition(GetPosition().x, GetPosition().y);
    }
    public void Draw(Graphics2D g, ImageObserver IO){
    	if (getJenis()!=Utilities.ItemType.ItemKosong){
			if(IsLoaded()) {
				// rectangle untuk peta yang kelihatan
				Rectangle a=new Rectangle();
				a.x=Utilities.VIEW_TILE_X*Utilities.TILE_SIZE_X;
				a.y=Utilities.VIEW_TILE_Y*Utilities.TILE_SIZE_Y;
				a.height=Utilities.VIEW_ROW_COUNT*Utilities.TILE_SIZE_X;
				a.width=Utilities.VIEW_COL_COUNT*Utilities.TILE_SIZE_Y;
				// rectangle untuk object bersangkutan
				Rectangle b=new Rectangle();
				b.x=GetPosition().x*Utilities.TILE_SIZE_X;
				b.y=GetPosition().y*Utilities.TILE_SIZE_Y;
				b.height=GetSprite().getTileHeight()*Utilities.TILE_SIZE_X;
				b.width=GetSprite().getTileWidth()*Utilities.TILE_SIZE_Y;
				if (a.intersects(b)){
					Rectangle c=a.intersection(b);
					c.x=c.x-b.x;
					c.y=c.y-b.y;
					GetSprite().SetCrop(c);
					GetSprite().Draw(g, IO);
				}
			}
		}
	}
}
