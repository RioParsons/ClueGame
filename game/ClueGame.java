package game;

import game.CardDeck;
import game.Weapon;
import main.ClueGUI;
import player.AIPlayer;
import player.Player;
import player.UserPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
public class ClueGame {
    CardDeck cards;
    ArrayList<Player> players;
    ArrayList<String> suspects;
    ArrayList<Room> rooms;
    ArrayList<Weapon> weapons;
    Envelope finalEnvelope;
    Player Winner;
    ClueBoard board;
    UserPlayer user;
    int numPlayers;

    // There are separate players and suspects lists because all of the characters, even those not used by a player, can be a murderer.
    public void startGame(int numPlayers, String userPlayer){
        this.numPlayers = numPlayers;
        setupGame(userPlayer);
    }

    public void setupGame(String UserPlayer){
        this.cards = CardDeck.getInstance();
        addSuspects();
        addWeapons();
        addRooms();
        addPlayers(UserPlayer);
        this.finalEnvelope = Envelope.getInstance(cards);
        dealOutCards();
        addBoard();
    }

    // Setup Functions
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

    public void addPlayers(String userPlayer){
        this.players = new ArrayList<Player>();
        players.add(new UserPlayer(userPlayer));
        addAIPlayers(userPlayer);
    }

    public void addAIPlayers(String userName){
        ArrayList<String> possiblePlayers = new ArrayList<String>(suspects);
        possiblePlayers.remove(userName);
        while (players.size() < this.numPlayers){
            Random n = new Random();
            int characterInd = n.nextInt(possiblePlayers.size());
            String name = possiblePlayers.get(characterInd);
            players.add(new AIPlayer(name));
            possiblePlayers.remove(characterInd);
        }
    }

    public void dealOutCards(){
        cards.dealCards(players);

        //Remove cards dealt from player's guesssheet
        ArrayList<String> cards;
        for (Player player : players){
            cards = player.getCards();
            for (int i = 0; i < cards.size(); i++){
                if (player.getType().equals("User")){
                    player.getSheet().removeItem(cards.get(i));
                }   
            }
        }
    }

    public void addBoard(){
        this.board = new ClueBoard(players, this);
        board.initializeBoard();
        System.out.println("\n ---Game starts---\n");
    }


    //Play Game functions

    public void AIPlayersTakeTurns(){
        //Starting at second player because first player is always the user
        for (int i=1; i < players.size(); i++){
            if (Winner != null){
                break;
            }
            System.out.println(players.get(i).getName()+ " takes their turn");
            AITurn(players.get(i));
        }

        if (Winner == null){
            userTakesTurn();
        }
    }

    public void AITurn(Player p){

        p.move(rollDice());
        ArrayList<String> guesses = p.makeSuggestion();
        if (guesses.get(0) == null){
            JOptionPane.showMessageDialog(null, p.getName()+" was not in a room so they did not make a guess");
        } else {
            JOptionPane.showMessageDialog(null,p.getName()+" guessed: "+ guesses.get(0) + ", " + guesses.get(1) +", "+ guesses.get(2));
            playersProveWrong(guesses, p);
        }
    }

    public void playersProveWrong(ArrayList<String> guesses, Player p){
        // Make copy of Players without the player making a guess
        ArrayList<Player> playersProve = new ArrayList<Player>();
        for (Player player : players){
            if ((player.getName().equals(p.getName())) == false){
                playersProve.add(player);
            }
        }

        // Have other players prove wrong
        String proof = null;
        int i = 0; 
        while(proof == null && i < playersProve.size()){
            proof = playersProve.get(i).proveWrong(guesses);
            i++;
        }

        if(p.getType().equals("User")){
            JOptionPane.showMessageDialog(null, playersProve.get(i-1).getName()+" showed you the " + proof +" card");
        } else {
            JOptionPane.showMessageDialog(null, p.getName() + " was proven wrong by " +playersProve.get(i-1).getName());

        }

        // If no players could prove suggestion wrong, make accusation.
        if (proof == null){
            if (p.getType().equals("AI")){
                if(p.makeAccusation(guesses.get(0), guesses.get(1), guesses.get(2), finalEnvelope)){
                    Winner = p;
                }
            }
        } else {
            p.getSheet().removeItem(proof);
        }   
    }

    public void userTakesTurn(){
        board.renderUserTurn();
        System.out.println("User takes their turn");
    }

    // public void makeAccusation(String Murderer, String Weapon, String Room, Player player){
    //     boolean correct = finalEnvelope.checkAccusation(Murderer, Weapon, Room);
    //     if (correct == true){
    //         Winner = player;
    //         System.out.println(player.getName() + " Won the game!");
    //         System.out.println("They correctly guessed that "+ Murderer + " killed Mr. John Boddy with a " + Weapon + " in the " + Room );
    //         //End Game
    //     } else {
    //         player.madeFalseAccusation();   
    //     }
    // }

    public int rollDice(){
        Random n = new Random();
        int firstDie = n.nextInt(6)+ 1;
        int secondDie = n.nextInt(6) + 1;
        int roll = firstDie + secondDie;
        return roll;
    }

    public void endPlayerTurn(){
        AIPlayersTakeTurns();
    }

    // Getters and Setters
}
