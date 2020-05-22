/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javapong;


import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 *
 * @author beatl
 */
public class Game {
    private int playerScore;
    private int botScore;
    private boolean playing;
   
    
    public Game(){
        playerScore = 0;
        botScore = 0;
        playing = false;
       
    }
    
    public boolean getPlaying(){
        return playing;
    }
    public void playerScored(){
        playerScore += 1;
    }
    
    public void botScored(){
        botScore += 1;
    }
    
    public void checkIfOver(){
        if(playerScore == 10 || botScore == 10)
            playing = false;
    }
    
    public void resetGame(){
        playerScore = 0;
        botScore = 0;
    }
    
    
    public void startGame(){
        playing = true;
        
    }
    
    
}
