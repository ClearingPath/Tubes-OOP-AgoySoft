import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rakhmatullah
 */
public class ConsoleProgram implements KeyListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainMenu();
    }
    public static void MainMenu() {
        int option;
        do {
            System.out.println("------------- Agoy the Naughty Neighbour -------------");
            System.out.println("\n\n\n");
            System.out.println("\tMain Menu :");
            System.out.println("\t1. Start Game");
            System.out.println("\t2. High Score");
            System.out.println("\t3. How to Play?");
            System.out.println("\t4. Credits");
            System.out.println("\t5. Quit\n");
            System.out.print("input : ");
            option = input.nextInt();
            switch(option) {
                case 1:
                    PlayScreen();
                    break;
                case 2:
                    HighScore();
                    break;
                case 3:
                    Help();
                    break;
                case 4:
                    Credits();
                    break;
            }
        } while(option!=5);
    }
    public static void PlayScreen() {
        System.out.println("------------- Agoy the Naughty Neighbour -------------");
        String Pname;
        input.nextLine();
        System.out.println("Insert your name to play");
        System.out.println("Input 0 to back...");
        Pname = input.nextLine();
        if(!Pname.equals("0")) {
            P = new Player();
            P.setName(Pname);
            GamePlay();
        }
    }
    public static void GamePlay() {
        playingstate = true;
        // game start
        playingstate = false;
    }
    public static void HighScore() {
        XMLData temp = new XMLData();
        Queue<Data> Stream;
        temp.ReadFile("highscore.xml");
        temp.ImportData();
        Data playerlist = new Data();
        Stream = temp.ExportData();
        System.out.println("------------- Agoy the Naughty Neighbour -------------");
            // Show highscore
            for (int i = 0;i < 20;i++){
                playerlist = Stream.poll();
                System.out.println((i+1)+"."+playerlist.Name+"\t\t"+playerlist.Score+"\t\t"+playerlist.time);
        }
        do {    
            System.out.println("Input 0 to back...");
        } while(input.nextInt()!=0);
    }
    public static void Help() {
        do {
            System.out.println("------------- Agoy the Naughty Neighbour -------------");
            System.out.println("How to play...");
            System.out.println("Agoy adalah seorang anak yang sangat jahil!\n"
                    + "dalam game ini anda akan berperan sebagai Agoy\n"
                    + "yang akan menjahili tetangganya. Sang pemilik rumah\n"
                    + "sedang asyik menikmati hari liburnya. Anda dapat\n"
                    + "mengganggunya dengan memasuki rumahnya dan memasang\n"
                    + "berbagai jebakan dengan mengambil alat yang terletak\n"
                    + "di sudut rumah. Hati-hati jangan sampai anda diketahui\n"
                    + "oleh pemilik rumah!"
                    + "Controller :\n"
                    + "1. arah: ketik atas/bawah/kiri/kanan\n"
                    + "2. sembunyi: ketik hide\n"
                    + "3. ambil alat: ketik ambil\n");
            System.out.println("Input 0 to back...");
        } while(input.nextInt()!=0);
    }
    public static void Credits() {
        do {
            System.out.println("------------- Agoy the Naughty Neighbour -------------");
            System.out.println("Permainan \"Agoy the Naughty Neighbour\" ini\n"
                            + "dikerjakan oleh :\n"
                            + "AgoySoft group\n"
                            + "1. Muntaha Ilmi - 13512048\n"
                            + "2. Diah Fauziah - 13512049\n"
                            + "3. Rakhmatullah Yoga Sutrisna - 13512053\n"
                            + "4. Khoirunnisa Afifah - 13512077\n"
                            + "5. Jonathan Sudibya - 13512093\n"
                            + "Program ini dibuat untuk memenuhi tugas besar 2\n"
                            + "IF2210 - Pemrograman Berorientasi Objek\n");
            System.out.println("Input 0 to back...");
        } while(input.nextInt()!=0);
    }

    public static Scanner input = new Scanner(System.in);
    public static boolean playingstate = false;
    private static Player P;

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
