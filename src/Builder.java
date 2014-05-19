/**
 *
 * @author Afik
 */

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Builder {
    public static void BuildLevel1(GameObjectManager GOM) {
        Map<String, Utilities.ItemType> ITMap = new HashMap<>();
        ITMap.put("Key", Utilities.ItemType.Key);
        ITMap.put("WC", Utilities.ItemType.WC);
        ITMap.put("Tissue", Utilities.ItemType.Tissue);
        ITMap.put("Knife", Utilities.ItemType.Knife);
        ITMap.put("Painting", Utilities.ItemType.Painting);
        ITMap.put("DoorClosed", Utilities.ItemType.DoorClosed);
        ITMap.put("Spidol", Utilities.ItemType.Spidol);
        ITMap.put("Manekin", Utilities.ItemType.Manekin);
        Scanner input=null;
        try {
            input = new Scanner(new File ("doc/Level1.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        }
       int baris = input.nextInt();
       int kolom = input.nextInt();
       Utilities.MAP_ROW_COUNT = kolom;
       Utilities.MAP_COL_COUNT = baris;
       int TileKode;
       Tile arr[][];
       arr = new Tile[baris][kolom];
       for (int i=0; i<baris; i++) {
           for (int j=0; j<kolom; j++) {
               arr[j][i] = new Tile();
               TileKode = input.nextInt();
               if (TileKode==0)
                   arr[j][i].setJenis(Utilities.TileType.UnWalkable);
               else if (TileKode == 1)
                   arr[j][i].setJenis(Utilities.TileType.Walkable);
               else if (TileKode == 2)
                   arr[j][i].setJenis(Utilities.TileType.Hideable);               
               GOM.Add("Tile" + j + " " + i, arr[j][i]);
           }
       }
       int NItem = input.nextInt();
       String type;
       int x, y, pjg, lbr;
       for (int i = 0; i<NItem; i++) {
           item _item = new item();
            type = input.next();
            x = input.nextInt();
            y = input.nextInt();
            pjg = input.nextInt();
            lbr = input.nextInt();
            Point uk = new Point(pjg, lbr);
            _item.setJenis(ITMap.get(type));
            _item.Load("img/Level1/" + _item.getJenis().getPic() +".png");
            _item.setBroken(false);
            _item.SetPosition(x, y);
            _item.setUkuran(uk);
            GOM.Add(type, _item);
            arr[x][y].putItem(_item);
            while(pjg>1) {
                arr[x+1][y].putItem(_item);
                pjg--;
                x++;
            }
            while(lbr>1) {
                arr[x][y+1].putItem(_item);
                lbr--;
                y++;
            }
       }
       Deque<Owner.act> OwnAct = new LinkedList<Owner.act>();
       int NAct = input.nextInt();
       for (int i = 0; i<NAct; i++) {
           Owner.act keg = new Owner.act();
           String IType = input.next();
           int kx = input.nextInt();
           int ky = input.nextInt();
           long time = input.nextLong();
           
           try {
                if (IType == "ItemKosong")
                    keg.ItemTerlibat = (item)GOM.Get(IType);
                else
                    keg.ItemTerlibat = item.ItemKosong;
            } catch (ObjectNameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           keg.actPos = new Point(kx, ky);
           keg.actTime = time;
           OwnAct.add(keg);
       }
       Owner ow=null;
       try {
    	   ow=(Owner) Game.GetGameObjectManager().Get("owner");
       } catch (ObjectNameNotFoundException e) {
    	   // TODO Auto-generated catch block
    	   e.printStackTrace();
       }
       ow.setActivities(OwnAct);
       Game.peta=arr;
    }
}
