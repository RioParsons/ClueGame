package game;

import java.util.ArrayList;
import java.util.Random;

public class Envelope {
    String murderer;
    String weapon;
    String room;
    private static Envelope uniqueInstance = null;

    private Envelope(CardDeck cards){
        generateMurderer(cards);
        generateWeapon(cards);
        generateRoom(cards);
        System.out.println("Generated Envelope : "+this.murderer+" "+this.weapon+" "+this.room);
    }

    /* Singleton pattern */
    public static Envelope getInstance(CardDeck cards){
        if(uniqueInstance == null){
            uniqueInstance = new Envelope(cards);
        }
        return uniqueInstance;
    } 
    
    public void generateMurderer(CardDeck cards){
        ArrayList<String> suspects = cards.getSuspects();
        int i = suspects.size();
        Random n = new Random();
        int murdererIndex = n.nextInt(i);
        this.murderer = suspects.get(murdererIndex); 
        cards.removeSuspect(murdererIndex);
    }

    public void generateWeapon(CardDeck cards){
        ArrayList<Weapon> weapons = cards.getWeapons();
        int i = weapons.size();
        Random n = new Random();
        int weaponIndex = n.nextInt(i);
        this.weapon = weapons.get(weaponIndex).getName(); 
        cards.removeWeapon(weaponIndex);
    }

    public void generateRoom(CardDeck cards){
        ArrayList<Room> rooms = cards.getRooms();
        int i = rooms.size();
        Random n = new Random();
        int roomIndex = n.nextInt(i);
        this.room = rooms.get(roomIndex).getName(); 
        cards.removeRoom(roomIndex);
    }

    public boolean checkAccusation(String murderer, String weapon, String room){
        if ((this.murderer.equals(murderer)) && (this.room.equals(room)) && (this.weapon.equals(weapon))) {
            return true;
        } else {
            return false;
        }
    } 
}
