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
    private static class act {
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
        } catch (IOException e) {
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
    
    public void CariPath(Point tujuan) {
         //set path
    }
    
    public void Update(long elapsedTime){
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
                catch(IOException e) {
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
                activities.addLast(_actNow);
                _actNow = activities.remove();
                SetPosition(_actNow.actPos.x, _actNow.actPos.y);
                sisaWaktu = _actNow.actTime;
                Point tuj = new Point();
                tuj = activities.peekFirst().actPos;
                CariPath(tuj);
                if (_actNow.ItemTerlibat.Status) {
                    _actNow = act.Angry;
                    sisaWaktu = _actNow.actTime;
                }
            }
        }
    }
}
