import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
    String type;
    String name;
    ArrayList<String> cards;
    GuessSheet guessSheet;
    boolean madeAccusation;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    public Player(String Name){
        this.name = Name;
        cards = new ArrayList<String>();
        guessSheet = new GuessSheet();
        madeAccusation = false;
    }
    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (GameObserver observer : observers) {
            observer.update(this, message);
        }
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

    public abstract void move(int spaces);
    public abstract ArrayList<String> makeSuggestion();
    public abstract String proveWrong(ArrayList<String> guesses);
    
}
