/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;

import java.util.ArrayList;

/**
 *
 * @author beatl
 */
public class Computer {
    
    private double ballX;
    private double ballY;
    private double topOfPaddle;
    private double bottomOfPaddle;
    Paddle paddle;
    
    public Computer(Paddle paddle){
        this.paddle = paddle;
    }
    
    
    public void changeBallY(double y){
        ballY = y;
    }
    /**
     * Updates the paddle's position on the screen
     * @param topOfPaddle yValue for the top of the paddle
     * @param bottomOfPaddle yValue for the bottom of the paddle
     */
    public void changePosition(double topOfPaddle, double bottomOfPaddle){
        this.topOfPaddle = topOfPaddle;
        this.bottomOfPaddle = bottomOfPaddle;
    }
    
    /**
     * moves the paddle based on the position of the ball
     * @param minY the minimum value of y of the scene
     * @param maxY the minimum value of y of the scene
     */
    public void movePaddle(double minY, double maxY){
        if(topOfPaddle > ballY){
            paddle.moveUp(minY);
        }
        
        if(bottomOfPaddle < ballY){
            paddle.moveDown(maxY);
        
        }
    }
    
}
