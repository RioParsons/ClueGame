package game;

import java.util.ArrayList;

public class GuessSheet {
    ArrayList<String> suspects;
    ArrayList<String> weapons;
    ArrayList<String> rooms;

    public GuessSheet(){
        addSuspects();
        addWeapons();
        addRooms();
    }

    public void addWeapons(){
        this.weapons = new ArrayList<String>();
        weapons.add("Revolver");
        weapons.add("Knife");
        weapons.add("Lead Piping");
        weapons.add("Rope");
        weapons.add("Wrench");
        weapons.add("Candlestick");
    }

    public void addRooms(){
        this.rooms = new ArrayList<String>();
        rooms.add("Kitchen");
        rooms.add("Dining Room");
        rooms.add("Lounge");
        rooms.add("Hall");
        rooms.add("Study");
        rooms.add("Library");
        rooms.add("Billiard Room");
        rooms.add("Conservatory");
        rooms.add("Ballroom");
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

    public void removeItem(String name){
        boolean found = false;
        for (String weapon : weapons){
            if (weapon.equals(name)){
                found = true;
                weapons.remove(name);
                break;
            } 
        }

        for (String room : rooms){
            if (room.equals(name)){
                found = true;
                rooms.remove(name);
                break; 
            }
        }

        for (String suspect : suspects){
            if (suspect.equals(name)){
                found = true;
                suspects.remove(name);
                break; 
            } 
        }

        if (found == false){
            System.out.println("Something went wrong with removing options in guessSheet");
        }

    }

    public void removeWeapon(String name){
        suspects.remove(name);
    }

    public void removeRoom(String name){
        suspects.remove(name);
    }

    public void removeSuspect(String name){
        suspects.remove(name);
    }

    public ArrayList<String> getWeapons(){
        return weapons;
    }

    public ArrayList<String> getRooms(){
        return rooms;
    }

    public ArrayList<String> getSuspects(){
        return suspects;
    }
}


