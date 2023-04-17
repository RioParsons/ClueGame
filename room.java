import java.util.ArrayList;

public class room {
    String name;
    weapon weapon;
    room connectedRoom;

    public room(String name){
        this.name = name;
    }

    public void findConnectedRoom( ArrayList<room> rooms){
        switch (this.name){
            case "Kitchen":
                this.connectedRoom = findRoom("Study", rooms);
            case "Study":
                this.connectedRoom = findRoom("Kitchen", rooms);
            case "Lounge":
                this.connectedRoom = findRoom("Conservatory", rooms);
            case "Conservatory":
                this.connectedRoom = findRoom("Lounge", rooms);
        }
    }

    public room findRoom(String name, ArrayList<room> rooms){
        room foundRoom = null;
        for (room room : rooms){
            if (room.getName().equals(name)){
                foundRoom = room;
            }
        }
        return foundRoom;
    }

    public String getName(){
        return this.name;
    }
}



