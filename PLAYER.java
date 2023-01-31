package com.company;

public class PLAYER {
    private String name;
    private int win;
    private int lose;
    private int score;
    private String XorO;
    private int weight = -5;
    private int[]position=new int[2];

    public PLAYER(String name, String XorO) {
        this.name = name;
        this.win = win;
        this.lose = lose;
        this.score = score;
        this.XorO = XorO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getXorO() {
        return XorO;
    }

    public void setXorO(String xorO) {
        XorO = xorO;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "PLAYER{" +
                "name='" + name + '\'' +
                ", win=" + win +
                ", lose=" + lose +
                ", score=" + score +
                ", XorO=" + XorO +
                ", weight=" + weight +
                '}';
    }
}
