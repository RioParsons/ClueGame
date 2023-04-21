import java.util.ArrayList;
import java.util.Random;

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
        return this.name;
    }

    public String getImage() {
        String image = "";

        switch (name) {
            case "Miss Scarlet":
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
        }

        return image;
    }

    public void move(){
        if (madeAccusation == false){
            int n = rollDice();
            //TODO move
        }
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
