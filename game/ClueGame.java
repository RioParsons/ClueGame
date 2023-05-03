package game;

import game.CardDeck;
import game.Weapon;
import player.AIPlayer;
import player.Player;

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

    // There are separate players and suspects lists because all of the characters, even those not used by a player, can be a murderer.
    public void playGame(){
        setupGame();
        int i = 1; //Temporary, until the game can decide a winner
        System.out.println("\n -------Making guesses------");
        while (Winner == null){
            System.out.println("Turn " + i ); //Temorary, testing
            playersTakeTurns();
            i++;
            if (i > 50){
                break;
            }
        }
    }

    public void setupGame(){
        this.cards = CardDeck.getInstance();
        addSuspects();
        addWeapons();
        addRooms();
        addPlayers();
        this.finalEnvelope = Envelope.getInstance(cards);
        dealCards();
    }

    public void addSuspects(){
        this.suspects = cards.getSuspects();
    }

    public void addWeapons(){
        this.weapons = cards.getWeapons();
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
        //TODO add players
        players = new ArrayList<Player>();
        players.add(new AIPlayer("Test1"));
        players.add(new AIPlayer("Test 2"));
        players.add(new AIPlayer("Test 3"));
        players.add(new AIPlayer("Test 4"));

    }

    public void dealCards(){
        cards.dealCards(players);

        //Remove cards dealt from player's guesssheet
        ArrayList<String> cards;
        for (Player player : players){
            cards = player.getCards();
            for (int i = 0; i < cards.size(); i++){
                player.getSheet().removeItem(cards.get(i));
            }
        }
    }

    public void playersTakeTurns(){
        for(Player p: players){
            if (Winner != null){
                break;
            }
            p.move(0);
            ArrayList<String> guesses = p.makeSuggestion();
            System.out.println(p.getName()); //Temporary, testing
            System.out.println(guesses);
            playersProveWrong(guesses, p);
        }
    }

    public void playersProveWrong(ArrayList<String> guesses, Player p){
        // Have other players prove wrong
        String proof = null;
        int i = 0; 
        while(proof == null && i < players.size()){
            proof = players.get(i).proveWrong(guesses);
            i++;
        }

        // If no players could prove suggestion wrong, make accusation.
        if (proof == null){
            makeAccusation(guesses.get(0), guesses.get(1), guesses.get(2), p);
        } else {
            p.getSheet().removeItem(proof);
        }   
    }

    public void makeAccusation(String Murderer, String Weapon, String Room, Player player){
        boolean correct = finalEnvelope.checkAccusation(Murderer, Weapon, Room);
        if (correct == true){
            Winner = player;
            System.out.println(player.getName() + " Won the game!");
            System.out.println("They correctly guessed that "+ Murderer + " killed Mr. John Boddy with a " + Weapon + " in the " + Room );
            //End Game
        } else {
            player.madeFalseAccusation();   
        }


    }
}
