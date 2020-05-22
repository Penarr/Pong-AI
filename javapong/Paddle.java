/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author beatl
 */
public class Paddle {

    private Rectangle rect;
    private Paint fill;
    private double width;
    private double height;
    private int score;

    /**
     *
     * @param width
     * @param height
     * @param fill
     */
    public Paddle() {
        this.width = 5;
        this.height = 50;
        this.fill = Color.BLACK;
        rect = new Rectangle(width, height, fill);
        score = 0;
    }

    public Paint getFill() {
        return fill;
    }

    /**
     * sets the location of the paddle on the screen
     * @param x
     * @param y
     */
    public void setLocation(double x, double y) {
        rect.relocate(x, y);
    }

    /**
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public Rectangle getRectangle() {
        return rect;
    }

    /**
     *
     * @return
     */
    public double getLayoutX() {
        return rect.getLayoutX();
    }

    public double getHeight() {
        return height;
    }

    public double getLayoutY() {
        return rect.getLayoutY();
    }

    /**
     * Move paddle up the screen, but not off the screen
     *
     * @param minY the minimum y value so that the paddle does not go off screen
     */
    public void moveUp(double minY) {
        if (rect.getLayoutY() >= minY) {
            rect.setLayoutY(rect.getLayoutY() - 50);
        }
    }

    /**
     * Move paddle down the screen, but not off the screen
     *
     * @param maxY the maximum y value so that the paddle does not go off screen
     */

    public void moveDown(double maxY) {
        if (rect.getLayoutY() + rect.getHeight() <= maxY) {
            rect.setLayoutY(rect.getLayoutY() + 50);
        }
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     * add a point to a paddle's score
     */
    public void addPoint() {
        score += 1;
    }
}
