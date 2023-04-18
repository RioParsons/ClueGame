import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    ArrayList<Weapon> weapons;
    ArrayList<Room> rooms;
    ArrayList<String> suspects;
    ArrayList<String> cardNames;
    private static CardDeck uniqueInstance = null;

    private CardDeck(){
        addWeapons();
        addRooms();
        addSuspects();
    }

    //Singleton pattern
    public static CardDeck getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new CardDeck();
        }
        return uniqueInstance;
    }

    public void addWeapons(){
        this.weapons = new ArrayList<Weapon>();
        weapons.add(new Weapon("Revolver"));
        weapons.add(new Weapon("Knife"));
        weapons.add(new Weapon("Lead Piping"));
        weapons.add(new Weapon("Rope"));
        weapons.add(new Weapon("Wrench"));
        weapons.add(new Weapon("Candlestick"));
    }

    public void addRooms(){
        this.rooms = new ArrayList<Room>();
        rooms.add(new Room("Kitchen"));
        rooms.add(new Room("Dining Room"));
        rooms.add(new Room("Lounge"));
        rooms.add(new Room("Hall"));
        rooms.add(new Room("Study"));
        rooms.add(new Room("Library"));
        rooms.add(new Room("Billiard Room"));
        rooms.add(new Room("Conservatory"));
        rooms.add(new Room("Ballroom"));
    }

    public void addSuspects(){
        this.suspects = new ArrayList<String>();
        suspects.add("Miss Scarlett");
        suspects.add("Professor Plum");
        suspects.add("Mrs Peacock");
        suspects.add("Reverend Green");
        suspects.add("Colonel Mustard");
        suspects.add("Dr Orchid");  
    }

    public ArrayList<Weapon> getWeapons(){
        return this.weapons;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }

    public ArrayList<String> getSuspects(){
        return this.suspects;
    }

    public void removeWeapon(int weaponIndex){
        weapons.remove(weaponIndex);
    }

    public void removeRoom(int roomIndex){
        rooms.remove(roomIndex);
    }

    public void removeSuspect(int suspectIndex){
        suspects.remove(suspectIndex);
    }

    public void dealCards(ArrayList<Player> players){
        shuffleCards();
        int playerIndex = 0;
        Player p;
        for (String c : cardNames){
            p = players.get(playerIndex);
            p.giveCard(c);
            playerIndex++;
            if(playerIndex == players.size()){
                playerIndex = 0;
            }
        }

        //Testing
        for (Player player : players){
            ArrayList<String> pcards = player.getCards();
            System.out.println("New Player");
            System.out.println(pcards);            
        }
    }

    public void shuffleCards(){
        makeCardNames();

        ArrayList<String> shuffledCards = new ArrayList<String>();

        while (cardNames.size() > 0){
            Random n = new Random();
            int i = n.nextInt(cardNames.size());
            shuffledCards.add(cardNames.get(i));
            cardNames.remove(i);
        }

        this.cardNames = shuffledCards;
    } 

    public void makeCardNames(){
        this.cardNames = new ArrayList<String>();
        for(Weapon w: weapons){
            cardNames.add(w.getName());
        }

        for(Room r : rooms){
            cardNames.add(r.getName());
        }

        cardNames.addAll(suspects);
    }

}
