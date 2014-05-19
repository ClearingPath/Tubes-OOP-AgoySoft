/*File : item.java */
/*Author : 13512093 - Jonathan Sudibya */

import java.awt.Point;
import java.util.Vector;

public class item extends VisibleGameObject{
    public static item ItemKosong = initKosong();
    
    /* Attribute */
    public boolean Broken; /* udah dirusak / belum */
    private long waktu;
    private Utilities.ItemType jenis;
    public Point posisi; 
    public Point ukuran;

    /* Constructor */
    item()
    {
        waktu = 1000;
    }
    
    public static item initKosong() {
        item i = new item();
        Game.GetGameObjectManager().Add("ItemKosong", ItemKosong);
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

    public void setPosisi(Point posisi) {
        this.posisi = posisi;
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

    public Point getPosisi() {
        return posisi;
    }

    public Point getUkuran() {
        return ukuran;
    }
    
    public boolean isWalkable() {
        return jenis.IsWalkable();
    }

    public void Update(long elapsedTime) {
        
    }
}

