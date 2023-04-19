import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
    String type;
    String name;
    ArrayList<String> cards;
    GuessSheet guessSheet;
    boolean madeAccusation;

    public Player(String Name){
        this.name = Name;
        cards = new ArrayList<String>();
        guessSheet = new GuessSheet();
        madeAccusation = false;
    }

    public void giveCard(String card){
        cards.add(card);
    }

    public ArrayList<String> getCards(){
        return this.cards;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public GuessSheet getSheet(){
        return this.guessSheet;
    }

    public void madeFalseAccusation(){
        madeAccusation=true;
    }

    public int rollDice(){
        Random n = new Random();
        int firstDie = n.nextInt(6)+ 1;
        int secondDie = n.nextInt(6) + 1;
        int roll = firstDie + secondDie;
        return roll;
    }

    public abstract void move();
    public abstract ArrayList<String> makeSuggestion();
    public abstract String proveWrong(ArrayList<String> guesses);
    
}
