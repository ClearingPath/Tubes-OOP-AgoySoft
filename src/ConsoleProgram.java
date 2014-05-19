import java.util.ArrayList;
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
public class ConsoleProgram {

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
            System.out.println("Playing...");
        }
    }
    public static void HighScore() {
        XMLData temp = new XMLData();
        Queue<Data> Stream;
        temp.ReadFile("highscore.xml");
        temp.ImportData();
        Data player = new Data();
        Stream = temp.ExportData();
        System.out.println("------------- Agoy the Naughty Neighbour -------------");
            // Show highscore
            for (int i = 0;i < 20;i++){
                player = Stream.poll();
                System.out.println((i+1)+"."+player.Name+"\t\t"+player.Score+"\t\t"+player.time);
        }
        do {    
            System.out.println("Input 0 to back...");
        } while(input.nextInt()!=0);
    }
    public static void Help() {
        do {
            System.out.println("------------- Agoy the Naughty Neighbour -------------");
            System.out.println("how to play...");
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
}
