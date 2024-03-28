public class MainMenu {
    private Input input;
    private final String[] GAMES = {"Legends: Monsters and Heroes"};

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
        }
        game.initalize();
        game.playGame();

        System.out.println("Thanks for playing!");
    }
}

