package game;

import player.Player;

import java.util.ArrayList;
import java.util.Random;

/* Card Deck - Suspects, Weapons, rooms */
public class CardDeck {
    ArrayList<String> suspects;
    ArrayList<Weapon> weapons;
    ArrayList<Room> rooms;
    ArrayList<String> cardNames;
    private static CardDeck uniqueInstance = null;

    private CardDeck(){
        addSuspects();  
        addWeapons();
        addRooms();
    }

    /* Singleton pattern - Lazy instantiation */
    public static CardDeck getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new CardDeck();
        }
        return uniqueInstance;
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

    public ArrayList<String> getSuspects(){
        return this.suspects;
    }

    public ArrayList<Weapon> getWeapons(){
        return this.weapons;
    }

    public ArrayList<Room> getRooms(){
        return this.rooms;
    }
    
    public void removeSuspect(int suspectIndex){
        suspects.remove(suspectIndex);
    }

    public void removeWeapon(int weaponIndex){
        weapons.remove(weaponIndex);
    }

    public void removeRoom(int roomIndex){
        rooms.remove(roomIndex);
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
        System.out.println("---player.Player's cards-----");
        for(Player player: players){
            System.out.println(player.getName()+"'s cards: ");
            System.out.println(player.getCards());
        }
    }

    /* Shuffle cards randomly */
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

    /* Get card image to display player cards */
    public String getCardImage(String card) {
        String image = "";

        switch (card) {
            case "Revolver":
                image = "resources/revolver.jpg";
                break;

            case "Knife":
                image = "resources/dagger.jpg";
                break;

            case "Lead Piping":
                image = "resources/leadpipe.jpg";
                break;

            case "Rope":
                image = "resources/rope.jpg";
                break;

            case "Wrench":
                image = "resources/wrench.jpg";
                break;

            case "Candlestick":
                image = "resources/candlestick.jpg";
                break;

            case "Kitchen":
                image = "resources/kitchen.jpg";
                break;

            case "Dining Room":
                image = "resources/dining.jpg";
                break;

            case "Lounge":
                image = "resources/lounge.jpg";
                break;

            case "Hall":
                image = "resources/hall.jpg";
                break;

            case "Study":
                image = "resources/study.jpg";
                break;

            case "Library":
                image = "resources/library.jpg";
                break;

            case "Billiard Room":
                image = "resources/billiard.jpg";
                break;

            case "Conservatory":
                image = "resources/conservatory.jpg";
                break;

            case "Ballroom":
                image = "resources/ballroom.jpg";
                break;

            case "Miss Scarlett":
                image = "resources/scarlet_card.jpg";
                break;

            case "Professor Plum":
                image = "resources/plum_card.jpg";
                break;

            case "Reverend Green":
                image = "resources/green_card.jpg";
                break;

            case "Colonel Mustard":
                image = "resources/mustard_card.jpg";
                break;

            case "Mrs Peacock":
                image = "resources/peacock_card.jpg";
                break;

            case "Dr Orchid":
                image = "resources/orchid_card.jpg";
                break;
        }

        return image;
    }
}
