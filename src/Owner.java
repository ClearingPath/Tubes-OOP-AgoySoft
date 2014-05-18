/**
 *
 * @author Afik
 */

import java.util.*;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Owner extends VisibleGameObject{
    public static class act {
        public static act Angry = new act();
        public static void initAngry() {
            Angry.actTime = 2000;
        }
        public item ItemTerlibat;
        public long actTime;
        public Point actPos;
    }
    private boolean isWalking;
    private long sisaWaktu;
    private act _actNow;
    private Queue<Point> path;
    private Deque<act> activities;
    
    public Owner() {
        super();
        Load("img/owner.png");
        SetPosition(4,7);
        GetSprite().SetImageSize(32, 32);
        GetSprite().AddAnimType(1,0,0,0,2,500); //bawah
        GetSprite().AddAnimType(2,1,0,1,2,500); //kiri
        GetSprite().AddAnimType(3,2,0,2,2,500); //kanan
        GetSprite().AddAnimType(4,3,0,3,2,500); //atas
        try {
                GetSprite().ChangeAnimType(1);
        } catch (AnimTypeNotFoundException e) {
                // AnimType not found
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
    }
    
    public void setSisaWaktu(long sisaWaktu) {
        this.sisaWaktu = sisaWaktu;
    }

    public void setActivities(Deque activities) {
        this.activities = activities;
    }

    public long getSisaWaktu() {
        return sisaWaktu;
    }

    public Deque getActivities() {
        return activities;
    }
    
    public void Update(long elapsedTime){
    	Player p=null;
    	try {
			p=(Player)Game.GetGameObjectManager().Get("player");
		} catch (ObjectNameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        if ((GetPosition().x - p.GetPosition().x <= 3) ||
            (p.GetPosition().x - GetPosition().x <= 3) ||
            (GetPosition().y - p.GetPosition().y <= 3) ||
            (p.GetPosition().x - GetPosition().x <= 3)) {
            //end game
        }
        else {
            if (isWalking) {
                if (!path.isEmpty()){    
                    try{
                        Point posTemp = new Point();
                        posTemp = path.remove();
                        if(posTemp.x>GetPosition().x) {
                            GetSprite().ChangeAnimType(3);
                        }
                        if (GetPosition().x<posTemp.x) {
                            GetSprite().ChangeAnimType(2);
                        }
                        if (posTemp.y>GetPosition().y){
                            GetSprite().ChangeAnimType(4);
                        }
                        if (posTemp.y<GetPosition().y){
                            GetSprite().ChangeAnimType(2);
                        }
                        SetPosition(posTemp.x, posTemp.y);
                        
                    }
                    catch(AnimTypeNotFoundException e) {
                        // AnimType not found
                        e.printStackTrace();
                    }
                }
                else {
                    isWalking = false;
                }
            }
            else {
                sisaWaktu -=elapsedTime;
                if (sisaWaktu==0) {
                    isWalking = true;
                    if (_actNow!= act.Angry)
                        activities.addLast(_actNow);
                    _actNow = activities.remove();
                    SetPosition(_actNow.actPos.x, _actNow.actPos.y);
                    sisaWaktu = _actNow.actTime;
                    Point tuj = new Point();
                    tuj = activities.peekFirst().actPos;
                    CariPath(tuj);
                    if (_actNow.ItemTerlibat.Broken) {
                        _actNow = act.Angry;
                        sisaWaktu = _actNow.actTime;
                    }
                }
            }
        }
    }
    public void CariPath(Point to){
    	Point from=GetPosition();
        int Row_C=Utilities.MAP_ROW_COUNT;
        int Col_C=Utilities.MAP_COL_COUNT;
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
    	//boolean found=false;
    	Tile stats=null;
    	while(!q.isEmpty()){
    		cur=q.remove();
    		for (int i=0;i<4;i++){
    			nex.x=cur.x+dx[i];
    			nex.y=cur.y+dy[i];
    			//cek dalam boundary/tidak
    			if (((0<=nex.x)&&(nex.x<Col_C))/*
    			*/&&((0<=nex.y)&&(nex.y<Row_C))){
    				try {
						stats=(Tile)Game.GetGameObjectManager().Get("tile"+nex.x+nex.y);
					} catch (ObjectNameNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				if (stats.IsWalkable()){
    				//if (Stats[nex.x][nex.y]==0){//check bisa/tidak
    					if (arah[nex.x][nex.y]==-1){//cek udah dipake/blum
    						arah[nex.x][nex.y]=3-i;
    						if ((nex.x==to.x)&&(nex.y==to.y)){
    							//found=true;
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
    	//if (!found)throw new Exception();
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
    	path=q;
    }
}
