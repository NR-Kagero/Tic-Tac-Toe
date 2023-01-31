package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CPU {
    private String name;
    private int wins;
    private int los;
    private int score;
    private char XorO;
    private int weight = -2;

    public CPU(char XorO) {
        this.name = "CPU1";
        this.wins = 0;
        this.los = 0;
        this.XorO = XorO;
        this.score = this.wins + this.los;
    }

    public ArrayList<int[]> seg(int[][] bord) {
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

    public ArrayList<Integer> sumations(ArrayList<int[]> segs) {
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

    public int[] nextPlay(int[][] bord) {
        int[] place;
        ArrayList<int[]> segs = seg(bord);
        ArrayList<Integer> sum_segs = sumations(segs);
        int smallest = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < sum_segs.size(); i++) {
            if (sum_segs.get(i) > 16 && sum_segs.get(i) != 15 && sum_segs.get(i) != 18) {
                if (smallest > sum_segs.get(i)) {
                    smallest = sum_segs.get(i);
                    index = i;
                }
            }
        }
        printSumations(segs,sum_segs);
        if (index <= 2) {
            place = findInRow(bord, index);
        } else if (index <= 5) {
            place = findInCol(bord, index);
        } else {
            place = findInDia(bord, index);
        }
        return place;
    }

    public int[] findInRow(int[][] bord, int index) {
        int[] pos = new int[2];
        pos[0] = index;
        for (int i = 0; i < 3; i++) {
            if (bord[index][i] == 9) {
                pos[1] = i;
                return pos;
            }
        }
        return pos;
    }

    public int[] findInCol(int[][] bord, int index) {
        int[] pos = new int[2];
        pos[1] = index - 3;
        for (int i = 0; i < 3; i++) {
            if (bord[i][index - 3] == 9) {
                pos[0] = i;
                return pos;
            }
        }
        return pos;
    }

    public int[] findInDia(int[][] bord, int index) {
        int[] pos = new int[2];
        if (index != 7) {
            for (int i = 0; i < 3; i++) {
                if (bord[i][i] == 9) {
                    pos[1] = i;
                    pos[0] = i;
                    return pos;
                }
            }
        } else {
            for (int i = 0, I = 2; i < 3; i++, I--) {
                if (bord[i][I] == 9) {
                    pos[1] = I;
                    pos[0] = i;
                    return pos;
                }
            }
        }
        return pos;
    }

    void printSumations(ArrayList<int[]> seg, ArrayList<Integer> sums) {
        for (int i = 0; i < seg.size(); i++) {
            for (int I = 0; I < seg.get(i).length; I++) {
                System.out.print(seg.get(i)[I] + "|");
            }
            System.out.println(sums.get(i));
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLos() {
        return los;
    }

    public void setLos(int los) {
        this.los = los;
    }

    public void addWin() {
        this.wins += 1;
        this.score = this.wins - this.los;
    }

    public void addLose() {
        this.los += 1;
        this.score = this.wins - this.los;
    }

    public int getScore() {
        return this.score;
    }

    public char getXorO() {
        return XorO;
    }

    public void setXorO(char XorO) {
        this.XorO = XorO;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", los=" + los +
                ", score=" + score +
                ", XorO=" + XorO +
                ", weight=" + weight +
                '}';
    }

}
