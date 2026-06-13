package gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

private boolean play = false;
private Timer timer;
private int delay = 4;
private int ballposX = 120;
private int ballposY = 350;
private int ballXdir = -1;
private int ballYdir = -2;
private int playerX = 350;
private int playerY = 60;
private int playerWidthX = 90;
private int playerWidthY = 90;

private boolean gameover = false;
private boolean gameover2 = false;
private int randomly2 = (int)(Math.random() < 0.5 ? 0 : 600); 
private int randomly1 = (int)(Math.random() * 600) + 1;
private int  giftDir;
private long nextgiftTime = System.currentTimeMillis() + 25000;
private long power = System.currentTimeMillis() + 8000;
private boolean sh1 = false;
private boolean sh2 = false;
private boolean mov_PlayerX = true;
private boolean mov_PlayerY = true;
private int activePower = 0;
private int powerOwner = 0;
private boolean bigpaddle1 = false;
private boolean bigpaddle2 = false;
private int giftcolor;
private boolean giftVisible = false;
private long power2 = System.currentTimeMillis() + 5000;
private boolean f1 = false;
private boolean f2 = false;
private boolean r1 = true;
private boolean r2 = true;
private boolean msg = false;

public GamePlay(){

addKeyListener(this);
setFocusable(true);
setFocusTraversalKeysEnabled(true);
repaint();
timer = new Timer(delay,this);
}



public void paint(Graphics g){

super.paint(g);
//canvas
g.setColor(Color.black);
g.fillRect(1,1,692,592);

//border
g.setColor(Color.yellow);
g.fillRect(0,0,692,3);
g.fillRect(0,3,3,592);//left
g.fillRect(getWidth()-3, 0, 3, 592);//right
g.fillRect(691,3,3,592);

//panel
g.setColor(Color.red);
g.fillRect(playerX,550,playerWidthX,23);//width,height

//panel2
g.setColor(Color.white);
g.fillRect(playerY,5,playerWidthY,13);

//ball
g.setColor(Color.green);
g.fillOval(ballposX,ballposY,30,30);



//message
if(gameover==true && !sh2 == true){
g.setFont(new Font("roboto",Font.BOLD,30));
g.setColor(Color.RED);
g.drawString("PLAYER 1 WIN", 200, 250);
play = false;
mov_PlayerX = false;
mov_PlayerY = false;
msg = true;
timer.stop();
}

if(gameover2==true && !sh1 == true){
g.setFont(new Font("roboto",Font.BOLD,30));
g.setColor(Color.WHITE);
g.drawString("PLAYER 2 WIN", 200, 250);
play = false;
mov_PlayerX = false;
mov_PlayerY = false;
msg = true;
timer.stop();
}

if(msg){
g.setFont(new Font("roboto",Font.BOLD,20));
g.setColor(Color.ORANGE);
g.drawString("____PRESS ENTER FOR RESTART____",125,210);

}


if(sh2){
    g.setFont(new Font("roboto",Font.BOLD,25));
    g.setColor(Color.RED);
    g.drawString("Shield Active player 1",200,360);
}

if(sh1){
    g.setFont(new Font("roboto",Font.BOLD,25));
 g.setColor(Color.WHITE);
    g.drawString("Shield Active player 2",200,360);
}
if(f1){
    g.setFont(new Font("roboto",Font.BOLD,25));
 g.setColor(Color.RED);
    g.drawString("Frezz Active player 2",200,310);
}
if(f2){
    g.setFont(new Font("roboto",Font.BOLD,25));
 g.setColor(Color.WHITE);
    g.drawString("Frezz Active player 1",200,310);
}

if(!r1){
    g.setFont(new Font("roboto",Font.BOLD,25));
 g.setColor(Color.RED);
    g.drawString("player 2 key Changed ",200,340);
}
if(!r2){
    g.setFont(new Font("roboto",Font.BOLD,25));
 g.setColor(Color.WHITE);
    g.drawString("player 1 key Changed ",200,340);
}


if(bigpaddle2){
    g.setFont(new Font("roboto",Font.BOLD,25));
    g.setColor(Color.WHITE);
    g.drawString("BigPaddle Active player 2",200,280);
    g.setColor(Color.BLACK);
}


if(bigpaddle1){
    g.setFont(new Font("roboto",Font.BOLD,25));
    g.setColor(Color.RED);
    g.drawString("BigPaddle Active player 1",200,280);
    g.setColor(Color.BLACK);
}


if(giftcolor==1){
g.setColor(Color.RED);//fast ball
}
else if(giftcolor==3){
g.setColor(Color.BLUE);//bigpaddle
}
else if(giftcolor==2){
g.setColor(Color.YELLOW);//shield
}
else if(giftcolor==4){
g.setColor(Color.WHITE);//rewind
}
else if(giftcolor==5){
g.setColor(Color.LIGHT_GRAY);//freeze
}
else if(giftcolor==6){
g.setColor(Color.CYAN);//key changed
}


if(giftVisible){
g.fillRect(randomly1, randomly2, 30, 30);
}
}
private void moveLeft(){

startGame();
play = true;
playerY -= 30;
}

private void moveRight(){

startGame();
play = true;
playerY += 30;
}

private void moveLeft2(){

startGame();
play = true;
playerX -= 30;
}

private void moveRight2(){

startGame();
play = true;
playerX += 30;
}


@Override
public void actionPerformed(ActionEvent e) {
repaint();
nxtgft();


if(activePower == 1){
    power_Ball_Speed();
}
else if(activePower == 2){
    power_Shield();
}
else if(activePower == 3){
    power_BigPaddle();
}
else if(activePower == 4){
    power_ReWind();
}
else if(activePower == 5){
    power_Freeze();
}
else if(activePower == 6){
    power_ReChange();
}



if(play){
randomly2 += giftDir;
if(ballposX<=0){
ballXdir =-ballXdir;
}

if(ballposX>=670){
ballXdir =-ballXdir;
}

if(ballposY <= 0){
    if(!sh1){
        gameover = true;
    }
}

if(ballposY >= 670){
    if(!sh2){
        gameover2 = true;
    }
}

Rectangle ballRect = new Rectangle(ballposX,ballposY,30,30);
Rectangle paddleRect = new Rectangle(playerX,550,playerWidthX,8);
Rectangle paddle2 = new Rectangle(playerY,5,playerWidthY,8);
Rectangle giftRect = new Rectangle(randomly1, randomly2, 30, 30);

if(ballRect.intersects(paddleRect) || ballRect.intersects(paddle2)){
ballYdir=-ballYdir;
}

if(giftVisible && (giftRect.intersects(paddleRect) || giftRect.intersects(paddle2))){
playGiftSound();
giftVisible = false;
power2 = System.currentTimeMillis() + 5000;
if(giftRect.intersects(paddleRect)){
powerOwner = 1;
activePower =(int) (Math.random()*6) +1;
activePower = giftcolor; 
    power = System.currentTimeMillis() + 15000;
}
else if(giftRect.intersects(paddle2)){
powerOwner = 2;
activePower =(int) (Math.random()*4) +1;
activePower = giftcolor; 
    power = System.currentTimeMillis() + 15000;
}
randomly2 = -100;
}




ballposX += ballXdir;
ballposY += ballYdir;

repaint();
}
}



public void gft(){
giftcolor = (int)(Math.random() * 6) + 1;
 randomly1 = (int)(Math.random() * 600) + 1;
if(Math.random() < 0.5){
    randomly2 = 0;
    giftDir = 1;   // down
}else{
    randomly2 = 600;
    giftDir = -1;  // up
}
}




public void nxtgft(){
if(play){
if(System.currentTimeMillis() >= nextgiftTime){
gft();
giftVisible = true;
nextgiftTime = System.currentTimeMillis() + 25000;
}
}
}


public void power_Ball_Speed(){
if(activePower == 1){
delay =1;
timer.setDelay(delay);
if(System.currentTimeMillis() >= power){
delay = 5;
timer.setDelay(delay);
activePower = 0;
powerOwner = 0;
}
}
}

public void power_Shield(){
if(System.currentTimeMillis() < power){
if(powerOwner == 1){
sh2 = true;

if(ballposY>=560) ballYdir =-ballYdir;

}else{
sh1 = true;

if(ballposY<=0) ballYdir =-ballYdir;

}

}else{
sh1 = false;
sh2 = false;
 activePower = 0;
 powerOwner = 0;
}
}




public void power_BigPaddle(){

    if(System.currentTimeMillis() < power){

        if(powerOwner == 1){
            playerWidthX = 200;
            playerWidthY = 65;
            bigpaddle1 = true;
           
        }
        else if(powerOwner == 2){
            playerWidthY = 200;
            playerWidthX = 65;
            bigpaddle2 = true;
        }

    }else{

        playerWidthX = 90;
        playerWidthY = 90;
       
        bigpaddle1 = false;
        bigpaddle2 = false;
        activePower = 0;
        powerOwner = 0;
    }
}



public void power_ReWind(){
ballXdir =-ballXdir;
activePower = 0;
powerOwner = 0;
}

public void power_Freeze(){
if(System.currentTimeMillis() < power2){
if(powerOwner == 1) {
 mov_PlayerX = false;
f1 = true;
}
if(powerOwner == 2){
  mov_PlayerY = false;
f2 =true;
}
}
else{
mov_PlayerY = true;
mov_PlayerX = true;
f1 = false;
f2 = false;
activePower = 0;
powerOwner = 0;
}
}

public void power_ReChange(){

    if(System.currentTimeMillis() < power){

        if(powerOwner == 1){
            r1 = false;
        }
        else if(powerOwner == 2){
            r2 = false;
        }

    }else{

        r1 = true;
        r2 = true;
        activePower = 0;
        powerOwner = 0;
    }
}


public void playGiftSound() {
    try {
        File soundFile = new File("eat.wav");

        AudioInputStream audio =
                AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}




public void restart(){
    // game state
    play = false;
    gameover = false;
    gameover2 = false;
    msg = false;

    // ball
    ballposX = 120;
    ballposY = 350;
    ballXdir = -1;
    ballYdir = -2;

    // paddles
    playerX = 350;
    playerY = 60;

    playerWidthX = 90;
    playerWidthY = 90;

    // movement
    mov_PlayerX = true;
    mov_PlayerY = true;

    // powers
    activePower = 0;
    powerOwner = 0;

    sh1 = false;
    sh2 = false;

    f1 = false;
    f2 = false;

    r1 = true;
    r2 = true;

    bigpaddle1 = false;
    bigpaddle2 = false;

    // gift
    giftVisible = false;
    giftcolor = 0;
    randomly2 = -100;

    // timer speed reset
    delay = 4;
    timer.setDelay(delay);

    // reset timings
    nextgiftTime = System.currentTimeMillis() + 25000;
    power = System.currentTimeMillis() + 8000;
    power2 = System.currentTimeMillis() + 5000;

    // focus
    requestFocusInWindow();

    // timer restart
    timer.start();

    repaint();

}






private void startGame(){
    if(!play){
        play = true;  
       gft();     
        timer.start(); 
    }
}




@Override
public void keyTyped(KeyEvent e) {

}

@Override
public void keyPressed(KeyEvent e) {

if(e.getKeyCode() == KeyEvent.VK_ENTER){
//restart();
   Container parent = getParent();

    parent.remove(this);

    GamePlay gp = new GamePlay();
    parent.add(gp);
   gp.requestFocusInWindow();
 
    parent.revalidate();
    parent.repaint();
}


if( r2 ){

if(e.getKeyCode() == KeyEvent.VK_LEFT){
if(playerX<=0){
playerX = 0;
}else{
if(mov_PlayerY) moveLeft2();
}
repaint();
}


if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
if(playerX>=600){
playerX = 600;
}else{
if(mov_PlayerY) moveRight2();
}
//repaint();
}

}else{


if(e.getKeyCode() == KeyEvent.VK_LEFT){
if(playerX<=0){
playerX = 0;
}else{
if(mov_PlayerY) moveRight2();
}
repaint();
}



if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
if(playerX>=600){
playerX = 600;
}else{
if(mov_PlayerY) moveLeft2();
}
//repaint();
}



}



if(r1 ){

if(e.getKeyCode() == KeyEvent.VK_PAGE_UP ){
if(playerY>=600){
playerY = 600;
}else{
if(mov_PlayerX) moveRight();
}
//repaint();
}



if(e.getKeyCode() == KeyEvent.VK_HOME){
if(playerY<=0){
playerY = 0;
}else{
if(mov_PlayerX) moveLeft();
}
//repaint();
}


}else{

if(e.getKeyCode() == KeyEvent.VK_PAGE_UP ){
if(playerY>=600){
playerY = 600;
}else{
if(mov_PlayerX) moveLeft();
}
//repaint();
}



if(e.getKeyCode() == KeyEvent.VK_HOME){
if(playerY<=0){
playerY = 0;
}else{
if(mov_PlayerX) moveRight();
}
//repaint();
}

}


}

@Override
public void keyReleased(KeyEvent e) {

}



}
//javac brickBracker/Main.java gameplay/GamePlay.java

