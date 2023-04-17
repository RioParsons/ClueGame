public abstract class player {
    String type;
    String name;

    public player(String Type, String Name){
        this.type = Type;
        this.name = Name;
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
