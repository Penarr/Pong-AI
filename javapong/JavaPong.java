/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;

import java.awt.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.management.Query.lt;

/**
 *
 * @author beatl
 */
public class JavaPong extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1000, 500);
        Ball ball = new Ball();
        ball.setLocation(pane);

        Button playButton = new Button();
        Game game = new Game();
        Bounds bounds = pane.getBoundsInLocal();
        playButton.setText("Play?");

        playButton.relocate(scene.getWidth() / 2, scene.getHeight() / 2);

        Label playerScoreLabel = new Label();
        playerScoreLabel.relocate(pane.getWidth() / 6 * 2, 0);

        Label botScoreLabel = new Label();
        botScoreLabel.relocate(pane.getWidth() / 6 * 4, 0);

        Paddle player = new Paddle();
        player.setLocation(scene.getWidth() / 10, scene.getHeight() / 2 - player.getHeight() / 2);

        Paddle computerPaddle = new Paddle();
        computerPaddle.setLocation(scene.getWidth() - scene.getWidth() / 10, scene.getHeight() / 2 - player.getHeight() / 2);

        Computer computer = new Computer(computerPaddle);

        pane.getChildren().add(playButton);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.getChildren().remove(playButton);
                pane.getChildren().addAll(ball.getCircle(), player.getRectangle(), computerPaddle.getRectangle()); //bad practice
                game.startGame();

                if (game.getPlaying() == true) {

                    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                            new EventHandler<ActionEvent>() {
                        int timerCount = 0;

                        @Override
                        public void handle(ActionEvent t) {

                            //If ball goes past the computer
                            //Modularize
                            if (ball.getLayoutX() <= bounds.getMinX()) {
                                game.botScored();
                                ball.resetVelocity();
                                ball.setLocation(pane);
                            }
                            //If ball goes past player
                            if (ball.getLayoutX() >= bounds.getMaxX()) {
                                game.playerScored();
                                ball.resetVelocity();
                                ball.setLocation(pane);
                            }
                            //move the ball
                            ball.moveBall();

                            //Update the computer on the ball's position
                            computer.changeBallY(ball.getLayoutY());
                            computer.changePosition(computerPaddle.getLayoutY(), computerPaddle.getLayoutY() + computerPaddle.getHeight());

                            //player hit
                            if (ball.getLayoutX() - ball.getRadius() <= ((player.getLayoutX() + player.getWidth()) - ball.getRadius()) && (ball.getLayoutY() >= player.getLayoutY()
                                    && ball.getLayoutY() <= player.getLayoutY() + player.getHeight()) && ball.getLayoutX() + ball.getRadius() >= player.getLayoutX()) {

                                ball.rallyBack(player.getLayoutY() + player.getHeight() - ball.getLayoutY() - (player.getHeight() / 2));
                                ball.getCircle().setLayoutX(ball.getLayoutX() + 1); //bad practice
                            } 
                            //computerPaddle hit
                            else if ((ball.getLayoutX() + ball.getRadius() >= computerPaddle.getLayoutX()) && (ball.getLayoutY() >= computerPaddle.getLayoutY()
                                    && ball.getLayoutY() <= computerPaddle.getLayoutY() + computerPaddle.getHeight()) && ball.getLayoutX() - ball.getRadius() <= computerPaddle.getLayoutX()) {

                                ball.rallyBack(computerPaddle.getLayoutY() + computerPaddle.getHeight() - ball.getLayoutY() - (computerPaddle.getHeight() / 2));
                                ball.getCircle().setLayoutX(ball.getLayoutX() - 1); //bad practice
                            }

                            //If ball hits top of bottom of screen, reverse y velocity
                            if ((ball.getLayoutY() >= (bounds.getMaxY() - ball.getCircle().getRadius()))
                                    || (ball.getLayoutY() <= (bounds.getMinY() + ball.getCircle().getRadius()))) {

                                ball.reverseYVelocity();

                            }
                            //So that the computer cannot just move every animation frame
                            if (timerCount % 2 == 0) {
                                computer.movePaddle(bounds.getMinY(), bounds.getMaxY());
                            }

                            timerCount += 1;
                            pane.requestLayout();
                        }

                    }));

                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }

            }
        });

        //Player controls
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                player.moveUp(bounds.getMinY());
            } else if (e.getCode() == KeyCode.S) {
                player.moveDown(bounds.getMaxY());
            }
        });

        scene.setOnMouseMoved(e -> {
            player.move(bounds.getMinY(), bounds.getMaxY(), e.getSceneY());
        });

        primaryStage.setResizable(false);
        primaryStage.setTitle("Pong AI");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
