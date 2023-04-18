import java.util.ArrayList;

public class UserPlayer extends Player {
    String type;
    String name;
    ArrayList<String> cards;

    public UserPlayer(String Name){
        super(Name);
        type = "User";
        cards = new ArrayList<String>();
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


    public int rollDice(){
        int roll = 0;
        //TODO
        return roll;
    }
    public void move(){
        //TODO
    }
    public void makeGuess(){
        //TODO
    }
    
}
