import java.util.ArrayList;
import java.util.Random;

public class envelope {
    player murderer;
    room room;
    weapon weapon;
    private static envelope uniqueInstance = null;

    private envelope(ArrayList<player> suspects, ArrayList<room> rooms, ArrayList<weapon> weapons){
        this.murderer = generateMurderer(suspects);
        this.room = generateRoom(rooms);
        this.weapon = generateWeapon(weapons);
    }

    //Singleton pattern
    public envelope getInstance(ArrayList<player> suspects, ArrayList<room> rooms, ArrayList<weapon> weapons){
        if(uniqueInstance == null){
            uniqueInstance = new envelope(suspects, rooms, weapons);
        }
        return uniqueInstance;
    }

    public boolean checkAccusation(player murderer, room room, weapon weapon){
        if ((this.murderer == murderer) && (this.room == room) && this.weapon == weapon) {
            return true;
        } else {
            return false;
        }
    }  
    
    public player generateMurderer(ArrayList<player> suspects){
        int i = suspects.size();
        Random n = new Random();
        int murdererIndex = n.nextInt(i) + 1;
        return suspects.get(murdererIndex); 
    }

    public room generateRoom(ArrayList<room> rooms){
        int i = rooms.size();
        Random n = new Random();
        int roomIndex = n.nextInt(i) + 1;
        return rooms.get(roomIndex); 
    }

    public weapon generateWeapon(ArrayList<weapon> weapons){
        int i = weapons.size();
        Random n = new Random();
        int weaponIndex = n.nextInt(i) + 1;
        return weapons.get(weaponIndex); 
    }
}
