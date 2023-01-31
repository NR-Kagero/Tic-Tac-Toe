package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class GAMEENGINE {
    int[][] bord = {{9, 9, 9}, {9, 9, 9}, {9, 9, 9}};
    String[][] bord2 = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

    public GAMEENGINE(int m) {
        if (m == 1) {
            CPU cpu = new CPU('X');
            PLAYER player = new PLAYER(" ", "O");
            playGame_CPU(cpu,player,true);
        } else {
            /*in = new Scanner(System.in);
            System.out.println("please enter player 1 name: ");
            String player1Name=in.nextLine();
            System.out.println("please enter which character X or O ");
            String player1Char=in.nextLine();
            System.out.println("please enter player 2 name: ");
            String player2Name=in.nextLine();
            System.out.println("please enter which character X or O ");
            String player2Char=in.nextLine();
            PLAYER player1 = new PLAYER(player1Name,player1Char), player2 = new PLAYER(player2Name, player2Char);
            playGame_Player(player1, player2);*/
        }
    }

    public static ArrayList<int[]> seg(int[][] bord) {
        ArrayList<int[]> segmented = new ArrayList<>(8);
        int[] dum = new int[3];
        for (int i = 0; i < 3; i++) {
            dum = new int[3];
            for (int I = 0; I < 3; I++) {
                dum[I] = bord[i][I];
            }
            segmented.add(dum);
        }
        for (int i = 0; i < 3; i++) {
            dum = new int[3];
            for (int I = 0; I < 3; I++) {
                dum[I] = bord[I][i];

            }
            segmented.add(dum);
        }
        dum = new int[3];
        dum[0] = bord[0][0];
        dum[1] = bord[1][1];
        dum[2] = bord[2][2];
        segmented.add(dum);
        dum = new int[3];
        dum[0] = bord[0][2];
        dum[1] = bord[1][1];
        dum[2] = bord[2][0];
        segmented.add(dum);
        return segmented;
    }

    public static ArrayList<Integer> sumations(ArrayList<int[]> segs) {
        ArrayList<Integer> sums = new ArrayList<Integer>();
        for (int i = 0; i < segs.size(); i++) {
            int sum_row = 0;
            for (int I = 0; I < 3; I++) {
                sum_row += segs.get(i)[I];
            }
            sums.add(sum_row);
        }
        return sums;
    }

    public void playGame_CPU(CPU cpu, PLAYER player, boolean first) {
        int[] cpuPlace;
        int[] playerPlace = player.getPosition();
        boolean turn = first;
        //in = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            if (turn) {
                /*playerPlace[0] = in.nextInt();
                playerPlace[1] = in.nextInt();*/
                bord[playerPlace[0]][playerPlace[1]] = bord[playerPlace[0]][playerPlace[1]] + player.getWeight();
                turn = false;
            } else {
                cpuPlace = cpu.nextPlay(bord);
                bord[cpuPlace[0]][cpuPlace[1]] = bord[cpuPlace[0]][cpuPlace[1]] + cpu.getWeight();
                turn = true;
            }
            i = checkWin(sumations(seg(bord)));
            //printBord1();
            //printBord2();
        }
    }

    public void playGame_Player(PLAYER player1, PLAYER player2) {
        int[] player2Place = new int[2];
        int[] player1Place = new int[2];
        if (player1.getXorO().equalsIgnoreCase("X")) {
            player1.setWeight(-2);
            player2.setWeight(-5);
        } else {
            player2.setWeight(-2);
            player1.setWeight(-5);
        }
        boolean turn = true;
        //in = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            if (turn) {
                /*System.out.println("player 1 turn : ");
                player1Place[0] = in.nextInt();
                player1Place[1] = in.nextInt();*/
                bord[player1Place[0]][player1Place[1]] = bord[player1Place[0]][player1Place[1]] + player1.getWeight();
                bord2[player1Place[0]][player1Place[1]] = player1.getXorO();
                turn = false;
            } else {
                /*System.out.println("player 2 turn : ");
                player2Place[0] = in.nextInt();
                player2Place[1] = in.nextInt();*/
                bord[player2Place[0]][player2Place[1]] = bord[player2Place[0]][player2Place[1]] + player2.getWeight();
                bord2[player2Place[0]][player2Place[1]] = player2.getXorO();
                turn = true;
            }
            i = checkWin(sumations(seg(bord)));
            printBord_player();
            //printBord2();
        }
    }

    void printBord1() {
        for (int i = 0; i < 3; i++) {
            for (int I = 0; I < 3; I++) {
                if (bord[i][I] == 7) {
                    System.out.print(" X | ");
                } else if (bord[i][I] == 4) {
                    System.out.print(" O | ");
                } else {
                    System.out.print("   | ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    void printBord_player() {
        for (int i = 0; i < 3; i++) {
            for (int I = 0; I < 3; I++) {
                System.out.print(bord2[i][I] + " |");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    void printBord2() {
        for (int i = 0; i < 3; i++) {
            for (int I = 0; I < 3; I++) {
                System.out.print(bord[i][I] + " | ");
            }
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    static int checkWin(ArrayList<Integer> sums) {
        if (sums.indexOf(12) >= 0) {
            return 12;
        } else if (sums.indexOf(21) >= 0) {
            return 21;
        }
        return 0;
    }

    boolean available(int p1, int p2) {
        if (bord[p1][p2] == 9) {
            return true;
        }
        return false;
    }

}
