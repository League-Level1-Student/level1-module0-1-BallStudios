package _01_methods._5_FlappyBird;

import processing.core.PApplet;

import java.util.Random;

import processing.core.PImage;

public class FlappyBird extends PApplet {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    int pipeHeight = 330;
    int birdY = 300;
    int yVel = 0;
    int pipeX = WIDTH;
    int pipeYchange = 0;
    int Score = 0;
    boolean GameOver = false;
    boolean pipeScore = true;
    PImage bg;
    PImage bird;
    PImage tPipe;
    PImage bPipe;
    Random rand = new Random();

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
    	bg = loadImage("images/flappyBackground.jpg");
    	bg.resize(WIDTH, HEIGHT);
    	bird = loadImage("images/bird.png");
    	bird.resize(40, 40);
    	bPipe = loadImage("images/bottomPipe.png");
    	bPipe.resize(60, pipeHeight);
    	tPipe = loadImage("images/topPipe.png");
    	tPipe.resize(60, pipeHeight);
    	textAlign(CENTER,CENTER);
    }

    @Override
    public void draw() {
        background(0);
        image(bg,0,0);
        image(bird,50,birdY);
        image(tPipe,pipeX,pipeYchange-100);
        image(bPipe,pipeX,HEIGHT - pipeHeight - pipeYchange + 100);
        if(GameOver == false) {
        birdY += yVel;
        pipeX -= 7;
        yVel ++;
        }
        fill(255);
        textSize(48);
        if(pipeX < 50 && pipeScore) {
        	Score ++;
        	pipeScore = false;
        }
        if(pipeX < 0) {
        	pipeX = WIDTH;
        	pipeYchange = rand.nextInt(HEIGHT/2) - HEIGHT/4;
        	pipeScore = true;
        }
        text(Score,WIDTH/2,60);
        if(intersectsPipes() || birdY > 460) {
        	fill(255,0,0);
        	textSize(36);
        	text("GAME OVER",WIDTH/2,HEIGHT/2);
        	GameOver = true;
        }
        
    }
    
    @Override
    public void mousePressed() {
    	yVel = -12;
    }

    boolean intersectsPipes() { 
        if (birdY < pipeHeight - pipeYchange - 100 && 90 > pipeX && 90 < (pipeX+60)){
           return true; }
       else if (birdY > HEIGHT - pipeHeight - pipeYchange + 100 && 90 > pipeX && 90 < (pipeX+60)) {
           return true; }
       else { return false; }
    }
    
    static public void main(String[] args) {
        PApplet.main(FlappyBird.class.getName());
    }
}
