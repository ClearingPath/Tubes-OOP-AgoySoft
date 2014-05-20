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
    private double tmpTime;
    private final double walkSpeed=100;
    private int arah;
    public Owner() {
        super();
        Load("img/owner.png");
        SetPosition(4,7);
        GetSprite().SetImageSize(32, 32);
        GetSprite().AddAnimType(0,0,0,0,2,-1); //diam
        GetSprite().AddAnimType(1,0,0,0,2,100); //bawah jalan
        GetSprite().AddAnimType(2,1,0,1,2,100); //kiri jalan
        GetSprite().AddAnimType(3,2,0,2,2,100); //kanan jalan
        GetSprite().AddAnimType(4,3,0,3,2,100); //atas jalan
        
        GetSprite().AddAnimType(5,0,0,0,2,-1); //bawah
        GetSprite().AddAnimType(6,1,0,1,2,-1); //kiri
        GetSprite().AddAnimType(7,2,0,2,2,-1); //kanan
        GetSprite().AddAnimType(8,3,0,3,2,-1); //atas
        try {
                GetSprite().ChangeAnimType(1);
        } catch (AnimTypeNotFoundException e) {
                // AnimType not found
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        tmpTime=0;
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
    	SetPosition(GetPosition().x, GetPosition().y);
    	Player p=null;
    	try {
			p=(Player)Game.GetGameObjectManager().Get("player");
		} catch (ObjectNameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	int jrk=1;
        if (((-jrk<=GetPosition().x - p.GetPosition().x) &&
            (GetPosition().x - p.GetPosition().x <= jrk)) &&
            ((-jrk<=GetPosition().y - p.GetPosition().y) &&
            (GetPosition().y - p.GetPosition().y <= jrk))) {
            System.out.println("kalah");
        	//end game
        }
        else {
        	if (isWalking) {
                tmpTime+=elapsedTime;
        		if (tmpTime>=walkSpeed){
        			tmpTime=0;
	        		if (!path.isEmpty()){
	                    try{
	                        Point posTemp = new Point();
	                        posTemp = path.remove();
	                    	int dx[]={0,-1,1,0};
	                		int dy[]={1,0,0,-1};
	                		for (int i=0;i<4;i++){
	                			if ((GetPosition().x+dx[i]==posTemp.x)&&(GetPosition().y+dy[i]==posTemp.y)){
	                				arah=i;
	                				GetSprite().ChangeAnimType(i+1);
		                        }
	                		}
	                    	SetPosition(posTemp.x, posTemp.y);
                        }
	                    catch(AnimTypeNotFoundException e) {
	                        // AnimType not found
	                        e.printStackTrace();
	                    }
	                } else {
        				try {
							GetSprite().ChangeAnimType(arah+4);
						} catch (AnimTypeNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                    isWalking = false;
	                }
                } else {
                	int dx[]={0,-1,1,0};
            		int dy[]={1,0,0,-1};
                	Point n1=GetPosition();
            		n1.x+=dx[3-arah];
            		n1.y+=dy[3-arah];
            		Point n=TiletoReal(n1);
            		double nx=n.x+dx[arah]*Utilities.TILE_SIZE_X*tmpTime/walkSpeed;
            		double ny=n.y+dy[arah]*Utilities.TILE_SIZE_Y*tmpTime/walkSpeed;
            		GetSprite().SetPosition(nx, ny);
                }
            }
            else {
                sisaWaktu -=elapsedTime;
                if (sisaWaktu<=0) {
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
    	int dx[]={0,-1,1,0};
    	int dy[]={1,0,0,-1};
    	int arah_t[][]=new int[Row_C][Col_C];
    	for (int i=0;i<Row_C;i++){
    		for (int j=0;j<Col_C;j++){
    			arah_t[i][j]=-1;
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
    				stats=Game.peta[nex.x][nex.y];
    				if (stats.IsWalkable()){//cek bisa lewat/tidak
    					if (arah_t[nex.x][nex.y]==-1){//cek udah dipake/blum
    						arah_t[nex.x][nex.y]=3-i;
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
    	int tpx,tpy;
    	s_tp.add(new Point(cur.x,cur.y));
    	while((cur.x!=from.x)||(cur.y!=from.y)){
    		tpx=cur.x;tpy=cur.y;
    		tpx+=dx[arah_t[cur.x][cur.y]];
    		tpy+=dy[arah_t[cur.x][cur.y]];
    		s_tp.add(new Point(tpx,tpy));
    		cur.x=tpx;cur.y=tpy;
    	}
    	s_tp.pop();
    	while (!s_tp.empty()){
    		q.add(s_tp.pop());
    	}
    	path=q;
    }
}
