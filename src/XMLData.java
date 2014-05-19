
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonathan
 */
public class XMLData {
    private ArrayList<String> Buffer;
    private Queue<Data> Stream;
    public static Comparator<Data> ScoreComparator = new Comparator<Data>(){
        @Override
        public int compare(Data d1,Data d2){
            if (d1.Score == d2.Score){
                return (int) (d1.time.compareTo(d2.time));
            }
            else{
                return (int) -(d1.Score - d2.Score);
            }
        }
    };
    public XMLData(){
        Stream = new PriorityQueue<Data>(11,ScoreComparator);
        Buffer = new ArrayList<String>();
    }
    public void ReadFile(String FileName){
        Buffer = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            String line = br.readLine();
            while (line != null)
            {
                Buffer.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /* import data from buffer*/
    public void ImportData(){
        Data temp = new Data();
        int flag = 0;
        for (String coba:Buffer)
        {
            if (coba.startsWith("<nama>"))
            {
                temp.Name = coba.substring(6);
                flag++;
            }
            else if(coba.startsWith("<score>"))
            {
                temp.Score = Integer.parseInt(coba.substring(7));
                flag++;
            }
            else if(coba.startsWith("<date>"))
            {
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE MMM dd HH:mm:ss yyyy");		

                try {
                        Date date = formatter.parse(coba.substring(6,33));
                        //System.out.println(date);
                        //System.out.println(formatter.format(date));
                        temp.time = date;
                        flag++;
                } catch (ParseException e) {
                        e.printStackTrace();
                }
            }
            if (flag == 3)
            {
                flag = 0;
                Stream.add(temp);
                temp = new Data();
            }
        }
    }
    public void AddData(String Name,int Score,Date time){
        Stream.add(new Data(Name,Score,time));
    }
    public void AddData(Queue<Data> input){
        Stream = input;
    }
    /* top data */
    public Queue<Data> ExportData(){
        return Stream;
    }
    public void WriteData(String FileName){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FileName));
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            bw.write("<highscore>");
            bw.newLine();
            while (!Stream.isEmpty())
            {
                Data temp = Stream.poll();
                bw.write("<player>");
                bw.newLine();
                bw.write("<nama>"+temp.Name);
                bw.newLine();
                bw.write("</nama>");
                bw.newLine();
                bw.write("<score>"+temp.Score);
                bw.newLine();
                bw.write("</score>");
                bw.newLine();
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE MMM dd HH:mm:ss yyyy");
                bw.write("<date>" + formatter.format(temp.time));
                bw.newLine();
                bw.write("</date>");
                bw.newLine();
                bw.write("</player>");
                bw.newLine();
            } 
            bw.write("</highscore>");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(XMLData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
