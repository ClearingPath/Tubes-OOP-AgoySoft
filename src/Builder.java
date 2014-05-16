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
       int N = input.nextInt(); 
       int TileKode;
       Tile[][] arr = new Tile[N][N];
       for (int i=0; i<N; i++) {
           for (int j=0; j<N; j++) {
               TileKode = input.nextInt();
               if (TileKode==0)
                   arr[i][j].setJenis(Utilities.TileType.UnWalkable);
               else if (TileKode == 1)
                   arr[i][j].setJenis(Utilities.TileType.Walkable);
               else if (TileKode == 2)
                   arr[i][j].setJenis(Utilities.TileType.Hideable);
           }
       }
       return arr;
    }
}
