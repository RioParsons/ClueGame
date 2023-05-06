package player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import game.ClueBoard;
import game.Envelope;

/* Strategy pattern - AI Player */
public class AIPlayer extends Player {

    public AIPlayer(String Name){
        super(Name);
        type = "AI";
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
        Random n = new Random();
        int ind = n.nextInt(moves.size());
        position = moves.get(ind);
    }

    public ArrayList<String> makeSuggestion(){
        // Compile suggestion
        ArrayList<String> guesses = new ArrayList<String>();
        String room = currentRoom();
        if (room == null){
            guesses.add(null);
            guesses.add(null);
            guesses.add(null);
        } else {
            String suspect = findSuspect();
            String weapon = findWeapon();

            guesses.add(suspect);
            guesses.add(weapon);
            guesses.add(room);
        }

        return guesses;
        
    }

    public String findSuspect(){
        Random n = new Random();
        ArrayList<String> suspects = guessSheet.getSuspects();
        int index = n.nextInt(suspects.size());
        return suspects.get(index);
    }

    public String findWeapon(){
        Random n = new Random();
        ArrayList<String> weapons = guessSheet.getWeapons();
        int index = n.nextInt(weapons.size());
        return weapons.get(index);
    }

    public String currentRoom(){
        String room;
        int[] pos = this.position;
        if((board.isRoomTile(pos[0], pos[1])) == true){
            room = board.getRoomName(pos[0], pos[1]);
        } else {
            room = null;
        }

        return room;
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
            return null;
        } else {
            Random n = new Random();
            int index = n.nextInt(possibleProof.size());
            System.out.println(this.name + " has card " + possibleProof.get(index));
            return possibleProof.get(index);
        }    
    }

    public boolean makeAccusation(String Person, String Weapon, String Room, Envelope envelope) {
        boolean correct = envelope.checkAccusation(Person, Weapon, Room);
        if(correct == true){
            JOptionPane.showMessageDialog(null, this.name + " won the game! \n They correctly guessed that "+ Person + " killed Mr. John Boddy with a " + Weapon + " in the " + Room);
        } else {
            JOptionPane.showMessageDialog(null, this.name + " made a false accusation and lost.");
            this.madeFalseAccusation();
        }

        return correct;
        
    }
}
