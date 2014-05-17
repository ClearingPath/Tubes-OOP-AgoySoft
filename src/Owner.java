

/**
 *
 * @author Afik
 */

import java.util.*;
import java.awt.Point;

public class Owner extends VisibleGameObject{
    private boolean isWalking;
    private long sisaWaktu;
    private Point position;
    private Queue<Point> path;
    private Deque<act> activities;
    
    public void Owner() {
        //Load("sesuatu.png");
        //initSize();
        //addAnimType();
        path = new LinkedList<Point>();
        activities = new LinkedList<act>();
    }
    
    public void setPosition(Point _pos) {
        position.x = _pos.x;
        position.y = _pos.y;
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
         
    }
    
    public void Update(long elapsedTime){
        Point posTemp = new Point();
        sisaWaktu -=elapsedTime;
        if (sisaWaktu==0) {
            isWalking = true;
            if (!path.isEmpty()){    
                posTemp = path.remove();
                if(posTemp.x>position.x) {
                    //ChangeAnimType(kanan);
                }
                if (position.x<posTemp.x) {
                    //ChangeAnimType(kiri);
                }
                if (posTemp.y>position.y){
                    //ChangeAnimType(atas);
                }
                if (posTemp.y<position.y){
                    //ChangeAnimType(bawah);
                }
                position = posTemp;
            }
            else {
                isWalking = false;
                act _actNow = new act();
                _actNow = activities.remove();
                position = _actNow.actPos; 
                sisaWaktu = _actNow.actTime;
            }
        }
        else {
            sisaWaktu -= elapsedTime;
        }
    }

    private static class act {
        public long actTime;
        public Point actPos;
        public String actName;
    }
}
