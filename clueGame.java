import java.util.ArrayList;
import java.util.Random;
public class ClueGame {
    CardDeck cards;
    ArrayList<Player> players;
    ArrayList<String> suspects;
    ArrayList<Room> rooms;
    ArrayList<Weapon> weapons;
    Envelope finalEnvelope;
    Player Winner;

    // There are seperate players and suspects lists because all of the characters, even those not used by a player, can be a murderer.
    public void playGame(){
        setupGame();
        while (Winner == null){
            playersMove();
        }
    }

    public void setupGame(){
        this.cards = CardDeck.getInstance();
        addWeapons();
        addSuspects();
        addRooms();
        addPlayers();
        this.finalEnvelope = Envelope.getInstance(cards);
        cards.dealCards(players);
    }

    public void addWeapons(){
        this.weapons = cards.getWeapons();
    }

    public void addSuspects(){
        this.suspects = cards.getSuspects();
    }

    public void addRooms(){
        this.rooms = cards.getRooms();
        putWeaponsInRooms();
        buildSecretPassages();
    }

    public void putWeaponsInRooms(){
        ArrayList<Room> availableRooms = new ArrayList<Room>();
        for (Room r : rooms){
            availableRooms.add(r);
        }
        
        for (Weapon weapon : weapons){
            //Find random room
            int i = availableRooms.size();
            Random n = new Random();
            int roomIndex = n.nextInt(i);

            //Assign weapon to room
            availableRooms.get(roomIndex).setWeapon(weapon);

            //Remove room from availableRooms list
            availableRooms.remove(roomIndex);
        }
    }

    public void buildSecretPassages(){
        for (Room room : rooms){
            room.setConnectedRoom(rooms);
        }
    }

    
    public void addPlayers(){
        //TODO
        players = new ArrayList<Player>();
        players.add(new UserPlayer("Test1"));
        players.add(new UserPlayer("Test 2"));
        players.add(new UserPlayer("Test 3"));
        players.add(new UserPlayer("Test 4"));

    }

    public void playersMove(){

    }


    
}
