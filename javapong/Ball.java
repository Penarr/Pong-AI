/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author beatl
 */
public class Ball {
    private double xVelocity;
    private double yVelocity;
    private Circle circle;
    private Paint fill = Color.RED;
    private int radius = 5;
    
    /**
     * 
     */
    public Ball(){
        circle = new Circle(radius, fill);
        resetVelocity();
    }
    /**
     * 
     * @return xVelocity
     */
    public double getXVelocity(){
        return xVelocity;
    }
    /**
     * 
     * @return yVelocity 
     */
    public double getYVelocity(){
        return yVelocity;
    }
    
   
    /**
     * Reverses the direction of X and changes the value of Y based on where the ball hits the
     * paddle
     * @param pointOfImpact the value used to change the value of yVelocity
     */
    public void rallyBack(double pointOfImpact){
        xVelocity = - xVelocity;
        yVelocity = pointOfImpact  % 26;
    }
    /**
     * get the center of the ball
     * @return double
     */
    public double getLayoutX(){
        return circle.getLayoutX();
    }
    /**
     * returns the value of the radius of the ball
     * @return radius of the ball
     */
    public double getRadius(){
        return circle.getRadius();
    }
    /**
     * 
     * @return 
     */
    public double getLayoutY(){
        return circle.getLayoutY();
    }
    /**
     * Reverses the direction of y
     */
    public void reverseYVelocity(){
        yVelocity = -yVelocity;
    }
    /**
     * 
     * @param pane 
     */
    public void setLocation(Pane pane){
        circle.relocate(pane.getWidth() / 2, pane.getHeight() / 2);
    }
    /**
     * moves the ball across the screen
     */
    public void moveBall(){
        circle.setLayoutX(circle.getLayoutX() + xVelocity);
        circle.setLayoutY(circle.getLayoutY() + yVelocity);
    }
    /**
     * 
     * @return 
     */
    public Circle getCircle(){
        return circle;
    }
    /**
     * sets the x and y value to a random number between 1 and 0.1
     */
    public void resetVelocity(){
        xVelocity = Math.random() + 1 - 0.1 + 1;
        
        yVelocity = Math.random() + 1 - 0.1 + 1;
    }
}
