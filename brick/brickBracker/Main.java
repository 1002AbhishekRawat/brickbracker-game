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
Main g1 = new Main("BrickBracker");
GamePlay pan = new GamePlay();
g1.add(pan);
g1.setVisible(true);
g1.setSize(700,600);
g1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
g1.setResizable(false);
}



}