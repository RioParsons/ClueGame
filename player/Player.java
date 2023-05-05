package player;

import game.CardDeck;
import game.ClueBoard;
import game.ClueGame;
import game.Envelope;
import game.GameObserver;
import game.GuessSheet;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
    String type;
    String name;
    public ArrayList<String> cards;
    GuessSheet guessSheet;
    boolean madeAccusation;
    int[] position;
    ClueBoard board;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    public Player(String Name){
        this.name = Name;
        cards = new ArrayList<String>();
        guessSheet = new GuessSheet();
        madeAccusation = false;
        this.getStartPos();
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

    public String getImage() {
        String image = "";

        switch (this.getName()) {
            case "Miss Scarlett":
                image = "resources/scarlet.png";
                break;

            case "Professor Plum":
                image = "resources/plum.png";
                break;

            case "Reverend Green":
                image = "resources/green.png";
                break;

            case "Colonel Mustard":
                image = "resources/mustard.png";
                break;

            case "Mrs. Peacock":
                image = "resources/peacock.png";
                break;

            case "Dr. Orchid":
                image = "resources/orchid.png";
                break;
            default:
                System.out.println("Could not find image for " + this.getName());
        }

        return image;
    }

    public void move(int roll){
        ArrayList<int[]> moves = board.generatePossibleMoves(this.position, roll);
        pickMove(moves);
        notifyObservers(" moved " + roll + " spaces.");
        board.movePlayerToken(this);
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

    public int[] getPos(){
        return this.position;
    }

    public void setBoard(ClueBoard board){
        this.board = board;
    }

    public void madeFalseAccusation(){
        madeAccusation=true;
    }

    public void getStartPos(){
        if (this.name.equals("Miss Scarlett")){
            this.position = new int[]{21, 13};
        } else if (this.name.equals("Professor Plum")){
            this.position=new int[]{10, 0};
        } else if (this.name.equals("Mrs. Peacock")){
            this.position=new int[]{0, 12};
        } else if (this.name.equals("Reverend Green")){
            this.position=new int[]{0, 7};
        } else if (this.name.equals("Colonel Mustard")){
            this.position=new int[]{13, 19};
        } else if (this.name.equals("Dr. Orchid")){
            this.position=new int[]{21, 7};
        } else {
            System.out.println("Could not find image for " + this.name);
            this.position = new int[]{-1, -1};
        }
    }

    public void setPosition(int[] pos){
        this.position=pos;
    }

    public abstract ArrayList<String> makeSuggestion();
    public abstract String proveWrong(ArrayList<String> guesses);
    public abstract void pickMove(ArrayList<int[]> moves);  
    public abstract boolean makeAccusation(String Person, String Weapon, String Room, Envelope envelope);  
}

