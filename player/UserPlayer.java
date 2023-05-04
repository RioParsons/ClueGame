package player;

import java.util.ArrayList;
import java.util.Random;

import game.ClueBoard;

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

    public void pickMove(ArrayList<int[]> moves, ClueBoard board){
        board.showPossibleMoves(moves);
    }

    

    public void move(int spaces){

        notifyObservers(name + " moved " + spaces + " spaces.");


    }

    public void makeSuggestion(ArrayList<Player> players){
        //TODO make suggestion
    }

    public String proveWrong(ArrayList<String> guesses){
        //TODO proveWrong
        String proof = null;
        return proof;
    }

    public ArrayList<String> makeSuggestion(){
        ArrayList<String> guesses = new ArrayList<String>();
        //TODO make suggestion
        return guesses;
    }
    
}
