package game;

import java.util.ArrayList;

public class Room {
    String name;
    Weapon weapon;
    Room connectedRoom;

    public Room(String name){
        this.name = name;
    }

    public void setConnectedRoom( ArrayList<Room> rooms){
        switch (this.name){
            case "Kitchen":
                this.connectedRoom = findRoom("Study", rooms);
                break;
            case "Study":
                this.connectedRoom = findRoom("Kitchen", rooms);
                break;
            case "Lounge":
                this.connectedRoom = findRoom("Conservatory", rooms);
                break;
            case "Conservatory":
                this.connectedRoom = findRoom("Lounge", rooms);
                break;
            default:
                this.connectedRoom = null;
        }
    }

    public Room findRoom(String name, ArrayList<Room> rooms){
        Room foundRoom = null;
        for (Room room : rooms){
            if (room.getName().equals(name)){
                foundRoom = room;
            }
        }
        return foundRoom;
    }

    public String getName(){
        return this.name;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }
}



