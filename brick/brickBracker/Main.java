package brickBracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gameplay.GamePlay;

public class Main extends JFrame {

public Main(String str){
super(str);
}
Main(){
}




public static void main(String[] args) {



//instruction window

JFrame Ins_Frame = new JFrame();
GamePlay.Instruction Ins = new GamePlay.Instruction();
Ins_Frame.add(Ins);
 Ins_Frame.setSize(400,450);
Ins_Frame.getContentPane().setBackground(Color.WHITE);
   Ins_Frame.setLocation(720,0); 
    Ins_Frame.setVisible(true);
   Ins_Frame.setResizable(false);



//Game window

Main g1 = new Main("BrickBracker");
GamePlay pan = new GamePlay();
g1.add(pan);
g1.setVisible(true);
pan.requestFocusInWindow();
g1.setSize(700,600);
g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
g1.setResizable(false);

}

}