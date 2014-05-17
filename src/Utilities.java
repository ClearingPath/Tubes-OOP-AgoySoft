import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

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
    public enum TileType {
       Walkable, UnWalkable,  Hideable;
    }
    
    public enum ItemType {
        Tissue, WC, Key, Knife, Painting, Manekin,
        DoorClosed, Spidol;
    }
    
    public Queue<Point> CariPath(Point from, Point to) throws Exception{
        int Row_C=10;
        int Col_C=10;
        int Stats[][]=new int[Row_C][Col_C];
    	Queue<Point> q=new ArrayDeque<>();
    	int dx[]={-1,0,0,1};
    	int dy[]={0,-1,1,0};
    	int arah[][]=new int[Row_C][Col_C];
    	for (int i=0;i<Row_C;i++){
    		for (int j=0;j<Col_C;j++){
    			arah[i][j]=-1;
    		}
    	}
    	q.add(from);
    	Point cur;
    	Point nex=new Point();
    	boolean found=false;
    	while(!q.isEmpty()){
    		cur=q.remove();
    		for (int i=0;i<4;i++){
    			nex.x=cur.x+dx[i];
    			nex.y=cur.y+dy[i];
    			//cek dalam boundary/tidak
    			if (((0<=nex.x)&&(nex.x<Col_C))/*
    			*/&&((0<=nex.y)&&(nex.y<Row_C))){
    				if (Stats[nex.x][nex.y]==0){//check bisa/tidak
    					if (arah[nex.x][nex.y]==-1){//cek udah dipake/blum
    						arah[nex.x][nex.y]=3-i;
    						if ((nex.x==to.x)&&(nex.y==to.y)){
    							found=true;
    							i=4;
    							while(!q.isEmpty())q.remove();
    						} else {
    							q.add(new Point(nex.x,nex.y));
    						}
    		    		}
    				}
    			}
    		}
    	}
    	if (!found)throw new Exception();
    	cur=new Point(to.x,to.y);
    	Stack<Point> s_tp=new Stack<>();
    	while((cur.x!=from.x)&&(cur.y!=from.y)){
    		s_tp.add(new Point(cur.x,cur.y));
    		cur.x+=dx[arah[cur.x][cur.y]];
    		cur.y+=dy[arah[cur.x][cur.y]];
        }
    	while (!s_tp.empty()){
    		q.add(s_tp.pop());
    	}
		return q;
    }
}
