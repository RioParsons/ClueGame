package player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import game.AccuseDialog;
import game.CardDeck;
import game.ClueBoard;
import game.Envelope;
import game.GuessDialog;
import game.ProveWrongDialog;

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
        //Find all cards that could prove guess wrong
        ArrayList<String> possibleProof = new ArrayList<String>();
        for (String card : cards){
            for (String guess : guesses){
                if (card.equals(guess)){
                    possibleProof.add(card);
                }
            }
        }

        //Randomly select a card to use as proof
        if(possibleProof.size() == 0){
            JOptionPane.showMessageDialog(null, "You can't prove them wrong");
            return null;
        } else {
            ProveWrongDialog dialog = new ProveWrongDialog((JFrame) SwingUtilities.getWindowAncestor(board), possibleProof);
            dialog.setVisible(true);
            while(dialog.getProof() == null){

            }
            return dialog.getProof();
        }    
    }

    public ArrayList<String> makeSuggestion(){
        ArrayList<String> empty = new ArrayList<String>();
        GuessDialog dialog = new GuessDialog((JFrame) SwingUtilities.getWindowAncestor(board), board);
        dialog.setVisible(true);
        return empty;
    }

    public boolean makeAccusation(String Person, String Weapon, String Room, Envelope envelope){
        boolean correct = envelope.checkAccusation(Person, Weapon, Room);
        if(correct == true){
            JOptionPane.showMessageDialog(null, "Congratulations! You are the winner!");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong Accusation! You Lose!");
            this.madeFalseAccusation();
        }

        return correct;

    }
    
}
