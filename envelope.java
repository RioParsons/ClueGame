import java.util.ArrayList;
import java.util.Random;

public class Envelope {
    String murderer;
    Room room;
    Weapon weapon;
    private static Envelope uniqueInstance = null;

    private Envelope(CardDeck cards){
        generateMurderer(cards);
        generateRoom(cards);
        generateWeapon(cards);
    }

    //Singleton pattern
    public static Envelope getInstance(CardDeck cards){
        if(uniqueInstance == null){
            uniqueInstance = new Envelope(cards);
        }
        return uniqueInstance;
    }

    public boolean checkAccusation(String murderer, Room room, Weapon weapon){
        if ((this.murderer.equals(murderer)) && (this.room == room) && this.weapon == weapon) {
            return true;
        } else {
            return false;
        }
    }  
    
    public void generateMurderer(CardDeck cards){
        ArrayList<String> suspects = cards.getSuspects();
        int i = suspects.size();
        Random n = new Random();
        int murdererIndex = n.nextInt(i);
        this.murderer = suspects.get(murdererIndex); 
        cards.removeSuspect(murdererIndex);
    }

    public void generateRoom(CardDeck cards){
        ArrayList<Room> rooms = cards.getRooms();
        int i = rooms.size();
        Random n = new Random();
        int roomIndex = n.nextInt(i);
        this.room = rooms.get(roomIndex); 
        cards.removeRoom(roomIndex);
    }

    public void generateWeapon(CardDeck cards){
        ArrayList<Weapon> weapons = cards.getWeapons();
        int i = weapons.size();
        Random n = new Random();
        int weaponIndex = n.nextInt(i);
        this.weapon = weapons.get(weaponIndex); 
        cards.removeWeapon(weaponIndex);
    }
}
