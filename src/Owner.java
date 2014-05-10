/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Afik
 */

import java.util.*;
import javax.swing.*;

public class Owner {
    private int sisaWaktu;
    private Queue posisi = new LinkedList();
    private Deque activities = new LinkedList();
    
    public void setSisaWaktu(int sisaWaktu) {
        this.sisaWaktu = sisaWaktu;
    }

    public void setPosisi(Queue posisi) {
        this.posisi = posisi;
    }

    public void setActivities(Deque activities) {
        this.activities = activities;
    }

    public int getSisaWaktu() {
        return sisaWaktu;
    }

    public Queue getPosisi() {
        return posisi;
    }

    public Deque getActivities() {
        return activities;
    }
    
    public Queue CariPath(int[] tujuan) {
        Queue Q = new LinkedList();
        
        return Q;
    }
    
    public void Draw(){
        
    }
    
    public void Update(long elapsedTime){
        
    }
}
