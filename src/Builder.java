/**
 *
 * @author Afik
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Builder {
    public static Tile[][] BuildLevel1() {
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
           }
       }
       //init item dimana?
       //init owner activities?
       return arr;
    }
}
