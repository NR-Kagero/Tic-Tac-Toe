package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

public class GUI implements ActionListener {
    NewFrame frame1, frame2, frame3;
    JButton button1, button2, button3;
    int[][] bord = {{9, 9, 9}, {9, 9, 9}, {9, 9, 9}};
    CPU cpu;
    JButton button_1 = new JButton("1");
    JButton button_2 = new JButton("2");
    JButton button_3 = new JButton("3");
    JButton button_4 = new JButton("4");
    JButton button_5 = new JButton("5");
    JButton button_6 = new JButton("6");
    JButton button_7 = new JButton("7");
    JButton button_8 = new JButton("8");
    JButton button_9 = new JButton("9");
    PLAYER player1 = new PLAYER("", "");
    PLAYER player2 = new PLAYER("", "");
    JCheckBox char1 = new JCheckBox("X");
    JCheckBox char2 = new JCheckBox("O");
    JTextArea text1 = new JTextArea("Name");
    JTextArea text3 = new JTextArea("Name");
    int mode = 0;
    int turn = 1;
    int count = 0;

    GUI() {
        frame1 = new NewFrame("Tic-Tac-Toe", 300, 300);
        JLabel label1 = new JLabel("Welcome");
        label1.setBounds(120, 20, 100, 30);
        frame1.AddObject(label1);
        button1 = new JButton("One player");
        button1.setBounds(70, 100, 150, 40);
        button1.addActionListener(this);
        button2 = new JButton("Tow players");
        button2.setBounds(70, 150, 150, 40);
        button2.addActionListener(this);
        frame1.AddObject(button1);
        frame1.AddObject(button2);
        frame1.SetV_L();
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if (e.getSource() == button1) {
            frame1.disposeFrame();
            button3 = new JButton("Begin");
            button3.setForeground(Color.WHITE);
            button3.setBackground(Color.darkGray);
            frame2 = new NewFrame("player data", 500, 250);
            text1.setBounds(50, 50, 150, 20);
            text1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            char1.setBounds(300, 50, 50, 20);
            char2.setBounds(350, 50, 50, 20);
            button3.setBounds(200, 100, 100, 30);
            frame2.setColor(8);
            frame2.AddObject(text1);
            frame2.AddObject(char1);
            char1.setSelected(true);
            char1.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (char1.isSelected()) {
                        char2.setSelected(false);
                    } else {
                        char2.setSelected(true);
                    }
                }
            });
            char2.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (char2.isSelected()) {
                        char1.setSelected(false);
                    } else {
                        char1.setSelected(true);
                    }
                }
            });
            frame2.AddObject(char2);
            button3.addActionListener(this);
            frame2.AddObject(button3);
            frame2.SetV_L();
            mode = 1;
        } else if (e.getSource() == button2) {
            frame1.disposeFrame();
            frame2 = new NewFrame("player data", 500, 300);
            button3 = new JButton("Begin");

            button3.setForeground(Color.WHITE);
            button3.setBackground(Color.darkGray);
            text1.setBounds(50, 50, 150, 20);
            text1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            char1.setBounds(300, 50, 150, 20);
            text3.setBounds(50, 100, 150, 20);
            text3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            char2.setBounds(300, 100, 150, 20);
            button3.setBounds(200, 180, 100, 30);
            frame2.AddObject(text1);
            frame2.AddObject(char1);
            frame2.AddObject(char2);
            frame2.AddObject(text3);
            button3.addActionListener(this);
            frame2.AddObject(button3);
            frame2.SetV_L();
            mode = 2;
        } else if (e.getSource() == button3) {
            player1.setName(text1.getText());
            player2.setName(text3.getText());
            if (char1.isSelected()) {
                cpu = new CPU('O');
                player1.setXorO(char1.getText());
                player2.setXorO(char2.getText());
                player2.setWeight(-2);
            } else {
                cpu = new CPU('X');
                player1.setXorO(char2.getText());
                player2.setXorO(char1.getText());
                player2.setWeight(-2);
            }
            frame2.disposeFrame();
            frame3 = new NewFrame("Game");
            button_1.addActionListener(this);
            button_2.addActionListener(this);
            button_3.addActionListener(this);
            button_4.addActionListener(this);
            button_5.addActionListener(this);
            button_6.addActionListener(this);
            button_7.addActionListener(this);
            button_8.addActionListener(this);
            button_9.addActionListener(this);
            frame3.AddObject(button_1);
            frame3.AddObject(button_2);
            frame3.AddObject(button_3);
            frame3.AddObject(button_4);
            frame3.AddObject(button_5);
            frame3.AddObject(button_6);
            frame3.AddObject(button_7);
            frame3.AddObject(button_8);
            frame3.AddObject(button_9);
            frame3.setColor(8);
            frame3.setLayout();
        } else if (e.getSource() == button_1 && mode == 1) {
            button_1.setBackground(Color.green);
            button_1.setText(player1.getXorO());
            button_1.setEnabled(false);
            bord[0][0] = bord[0][0] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_2 && mode == 1) {
            button_2.setBackground(Color.green);
            button_2.setText(player1.getXorO());
            button_2.setEnabled(false);
            bord[0][1] = bord[0][1] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_3 && mode == 1) {
            button_3.setBackground(Color.green);
            button_3.setText(player1.getXorO());
            button_3.setEnabled(false);
            bord[0][2] = bord[0][2] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_4 && mode == 1) {
            button_4.setBackground(Color.green);
            button_4.setText(player1.getXorO());
            button_4.setEnabled(false);
            bord[1][0] = bord[1][0] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_5 && mode == 1) {
            button_5.setBackground(Color.green);
            button_5.setText(player1.getXorO());
            button_5.setEnabled(false);
            bord[1][1] = bord[1][1] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_6 && mode == 1) {
            button_6.setBackground(Color.green);
            button_6.setText(player1.getXorO());
            button_6.setEnabled(false);
            bord[1][2] = bord[1][2] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_7 && mode == 1) {
            button_7.setBackground(Color.green);
            button_7.setText(player1.getXorO());
            button_7.setEnabled(false);
            bord[2][0] = bord[2][0] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_8 && mode == 1) {
            button_8.setBackground(Color.green);
            button_8.setText(player1.getXorO());
            button_8.setEnabled(false);
            bord[2][1] = bord[2][1] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_9 && mode == 1) {
            button_9.setBackground(Color.green);
            button_9.setText(player1.getXorO());
            button_9.setEnabled(false);
            bord[2][2] = bord[2][2] + player1.getWeight();
            count++;
            checkWin();
            GuiNext(bord, cpu.getXorO());
            checkWin();
        } else if (e.getSource() == button_1 && mode == 2) {
            if (turn == 1) {
                button_1.setBackground(Color.green);
                button_1.setText(player1.getXorO());
                button_1.setEnabled(false);
                bord[0][0] = bord[0][0] + player1.getWeight();
                turn = 0;
            } else {
                button_1.setBackground(Color.CYAN);
                button_1.setText(player2.getXorO());
                button_1.setEnabled(false);
                bord[0][0] = bord[0][0] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_2 && mode == 2) {
            if (turn == 1) {
                button_2.setBackground(Color.green);
                button_2.setText(player1.getXorO());
                button_2.setEnabled(false);
                bord[0][1] = bord[0][1] + player1.getWeight();
                turn = 0;
            } else {
                button_2.setBackground(Color.CYAN);
                button_2.setText(player2.getXorO());
                button_2.setEnabled(false);
                bord[0][1] = bord[0][1] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_3 && mode == 2) {
            if (turn == 1) {
                button_3.setBackground(Color.green);
                button_3.setText(player1.getXorO());
                button_3.setEnabled(false);
                bord[0][2] = bord[0][2] + player1.getWeight();
                turn = 0;
            } else {
                button_3.setBackground(Color.CYAN);
                button_3.setText(player2.getXorO());
                button_3.setEnabled(false);
                bord[0][2] = bord[0][2] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_4 && mode == 2) {
            if (turn == 1) {
                button_4.setBackground(Color.green);
                button_4.setText(player1.getXorO());
                button_4.setEnabled(false);
                bord[1][0] = bord[1][0] + player1.getWeight();
                turn = 0;
            } else {
                button_4.setBackground(Color.CYAN);
                button_4.setText(player2.getXorO());
                button_4.setEnabled(false);
                bord[1][0] = bord[1][0] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_5 && mode == 2) {
            if (turn == 1) {
                button_5.setBackground(Color.green);
                button_5.setText(player1.getXorO());
                button_5.setEnabled(false);
                bord[1][1] = bord[1][1] + player1.getWeight();
                turn = 0;
            } else {
                button_5.setBackground(Color.CYAN);
                button_5.setText(player2.getXorO());
                button_5.setEnabled(false);
                bord[1][1] = bord[1][1] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_6 && mode == 2) {
            if (turn == 1) {
                button_6.setBackground(Color.green);
                button_6.setText(player1.getXorO());
                button_6.setEnabled(false);
                bord[1][2] = bord[1][2] + player1.getWeight();
                turn = 0;
            } else {
                button_6.setBackground(Color.CYAN);
                button_6.setText(player2.getXorO());
                button_6.setEnabled(false);
                bord[1][2] = bord[1][2] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_7 && mode == 2) {
            if (turn == 1) {
                button_7.setBackground(Color.green);
                button_7.setText(player1.getXorO());
                button_7.setEnabled(false);
                bord[2][0] = bord[2][0] + player1.getWeight();
                turn = 0;
            } else {
                button_7.setBackground(Color.CYAN);
                button_7.setText(player2.getXorO());
                button_7.setEnabled(false);
                bord[2][0] = bord[2][0] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();
        } else if (e.getSource() == button_8 && mode == 2) {
            if (turn == 1) {
                button_8.setBackground(Color.green);
                button_8.setText(player1.getXorO());
                button_8.setEnabled(false);
                bord[2][1] = bord[2][1] + player1.getWeight();
                turn = 0;
            } else {
                button_8.setBackground(Color.CYAN);
                button_8.setText(player2.getXorO());
                button_8.setEnabled(false);
                bord[2][1] = bord[2][1] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();

        } else if (e.getSource() == button_9 && mode == 2) {
            if (turn == 1) {
                button_9.setBackground(Color.green);
                button_9.setText(player1.getXorO());
                button_9.setEnabled(false);
                bord[2][2] = bord[2][2] + player1.getWeight();
                turn = 0;
            } else {
                button_9.setBackground(Color.CYAN);
                button_9.setText(player2.getXorO());
                button_9.setEnabled(false);
                bord[2][2] = bord[2][2] + player2.getWeight();
                turn = 1;
            }
            count++;
            checkWin();

        }
    }

    void checkWin() {
        int win = GAMEENGINE.checkWin(GAMEENGINE.sumations(GAMEENGINE.seg(bord)));
        printSumations(seg(bord),sumations(seg(bord)));
        if (win > 0) {
            if (player1.getWeight() * 3 + 27 == win) {
                showMessageDialog(frame3.getFrame(), player1.getName() + " wins");
                frame3.disposeFrame();
                System.exit(1);
            } else {
                showMessageDialog(frame3.getFrame(), "cpu wins");
                frame3.disposeFrame();
                System.exit(1);
            }
        }
        if (count >= 9) {
            showMessageDialog(frame3.getFrame(), "Draw");
            frame3.disposeFrame();
            System.exit(1);
        }
    }

    void GuiNext(int[][] bord, char ch) {
        int[] next = cpu.nextPlay(bord);
        count++;
        if (next[0] == 0 && next[1] == 0) {
            button_1.setBackground(Color.CYAN);
            button_1.setText(String.valueOf(ch));
            button_1.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 0 && next[1] == 1) {
            button_2.setBackground(Color.CYAN);
            button_2.setText(String.valueOf(ch));
            button_2.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 0 && next[1] == 2) {
            button_3.setBackground(Color.CYAN);
            button_3.setText(String.valueOf(ch));
            button_3.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 1 && next[1] == 0) {
            button_4.setBackground(Color.CYAN);
            button_4.setText(String.valueOf(ch));
            button_4.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 1 && next[1] == 1) {
            button_5.setBackground(Color.CYAN);
            button_5.setText(String.valueOf(ch));
            button_5.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 1 && next[1] == 2) {
            button_6.setBackground(Color.CYAN);
            button_6.setText(String.valueOf(ch));
            button_6.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 2 && next[1] == 0) {
            button_7.setBackground(Color.CYAN);
            button_7.setText(String.valueOf(ch));
            button_7.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else if (next[0] == 2 && next[1] == 1) {
            button_8.setBackground(Color.CYAN);
            button_8.setText(String.valueOf(ch));
            button_8.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        } else {
            button_9.setBackground(Color.CYAN);
            button_9.setText(String.valueOf(ch));
            button_9.setEnabled(false);
            bord[next[0]][next[1]] = bord[next[0]][next[1]] + cpu.getWeight();
        }
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
    void printSumations(ArrayList<int[]> seg, ArrayList<Integer> sums) {
        for (int i = 0; i < seg.size(); i++) {
            for (int I = 0; I < seg.get(i).length; I++) {
                System.out.print(seg.get(i)[I] + "|");
            }
            System.out.println(sums.get(i));
        }

    }


}

class NewFrame extends JComponent {
    JFrame frame;

    public NewFrame(String frameName) {
        frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        frame.setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }


    public NewFrame(String frameName, int width, int length) {
        frame = new JFrame(frameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, length);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        frame.setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    public void AddObject(Component obj) {
        frame.add(obj);
    }

    public void SetV_L() {
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void setColor(int color) {
        switch (color) {
            case 1:
                frame.getContentPane().setBackground(Color.CYAN);
                break;
            case 2:
                frame.getContentPane().setBackground(Color.darkGray);
                break;
            case 3:
                frame.getContentPane().setBackground(Color.RED);
                break;
            case 4:
                frame.getContentPane().setBackground(Color.green);
                break;
            case 5:
                frame.getContentPane().setBackground(Color.white);
                break;
            case 6:
                frame.getContentPane().setBackground(Color.YELLOW);
                break;
            case 7:
                frame.getContentPane().setBackground(Color.BLUE);
                break;
            case 8:
                frame.getContentPane().setBackground(Color.orange);
                break;
        }
    }

    public void setLayout() {
        frame.setLayout(new GridLayout(3, 3, 20, 20));
        frame.setVisible(true);
    }

    public void setVisable() {
        frame.setVisible(true);
    }

    public void disposeFrame() {
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }
}