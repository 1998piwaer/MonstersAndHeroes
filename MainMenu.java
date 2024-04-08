 /*
  * MainMenu.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class represents the main menu. It contains the most basic
  * menu and asks users for which game to play, which determines
  * which game to initalize. By making all games implement Playable() interface,
  * we just need to make simple modifications to add or replace games.
  */

public class MainMenu {
    private Input input;
    private final String[] GAMES = {"Legends: Monsters and Heroes", "Legends of Valor"};

    public MainMenu() {
        input = Input.getSingletonInput();
    }
    public void start() {
        System.out.println("Welcome to the Main Menu!!");
        // Each iteration of this while loop can be thought of as
        // one instance of a game.
        System.out.println("Which game would you like to play? You can press [Q] to exit out any point!");
        for (int i = 0; i < GAMES.length; i++) {
            System.out.print("["+i+"] " + GAMES[i] + " ");
        }
        System.out.println();
        int gameSelection = input.getInt(0,GAMES.length - 1);
        Playable game = null;
        // Replace this portion with any game of your choosing
        if (gameSelection == 0) {
            game = new MonstersAndHeroes();
        } else if (gameSelection == 1) {
            game = new LegendsOfValor();
        }
        game.initalize();
        game.playGame();

        System.out.println("Thanks for playing!");
    }
}

