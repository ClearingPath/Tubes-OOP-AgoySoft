/**
 *
 * @author Afik
 */

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Builder {
    public static void BuildLevel1() {
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
       int TileKode;
       Tile[][] arr = new Tile[baris][kolom];
       for (int i=0; i<baris; i++) {
           for (int j=0; j<kolom; j++) {
               TileKode = input.nextInt();
               if (TileKode==0)
                   arr[i][j].setJenis(Utilities.TileType.UnWalkable);
               else if (TileKode == 1)
                   arr[i][j].setJenis(Utilities.TileType.Walkable);
               else if (TileKode == 2)
                   arr[i][j].setJenis(Utilities.TileType.Hideable);
               Game.GetGameObjectManager().Add("Tile" + i + j, arr[i][j]);
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
            Point poss = new Point(x,y);
            pjg = input.nextInt();
            lbr = input.nextInt();
            Point uk = new Point(pjg, lbr);
            _item.setJenis(ITMap.get(type));
            _item.setBroken(false);
            _item.setPosisi(poss);
            _item.setUkuran(uk);
            Game.GetGameObjectManager().Add(type, _item);
            arr[poss.x][poss.y].putItem(_item);
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
				keg.ItemTerlibat = (item)Game.GetGameObjectManager().Get(IType);
			} catch (ObjectNameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           keg.actPos = new Point(kx, ky);
           keg.actTime = time;
           OwnAct.add(keg);
       }
       Game.peta=arr;
    }
}
