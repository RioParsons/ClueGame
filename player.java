import java.util.ArrayList;

public abstract class Player {
    String type;
    String name;
    ArrayList<String> cards;

    public Player(String Name){
        this.name = Name;
        cards = new ArrayList<String>();
    }

    public void giveCard(String card){
        cards.add(card);
    }

    public ArrayList<String> getCards(){
        return this.cards;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public abstract int rollDice();
    public abstract void move();
    public abstract void makeGuess();
    
}
