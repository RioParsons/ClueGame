package player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.ClueBoard;
import game.GuessDialog;

/* Strategy Pattern - UserPlayer */
public class UserPlayer extends Player {

    public UserPlayer(String Name){
        super(Name);
        type = "User";
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return name;
    }

    public void pickMove(ArrayList<int[]> moves){
        board.showPossibleMoves(moves);
    }

    public String proveWrong(ArrayList<String> guesses){
        //TODO proveWrong
        String proof = null;
        return proof;
    }

    public ArrayList<String> makeSuggestion(){
        ArrayList<String> empty = new ArrayList<String>();
        GuessDialog dialog = new GuessDialog((JFrame) SwingUtilities.getWindowAncestor(board), board);
        dialog.setVisible(true);
        return empty;
    }
    
}
