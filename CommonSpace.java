public class CommonSpace implements SpaceFactory {
    Input input = Input.getSingletonInput();
    public void enter(HeroParty heroParty) {
        double r = Math.random();
        if (r < Settings.ENCOUNTER_CHANCE) {
            Settings.clearTerminal();
            System.out.println("The party runs into an encounter!");
            Combat combat = new Combat(heroParty);
            while (combat.isCompleted() == 0) {
                combat.combatTurn();
            }
            if (combat.isCompleted() == 1) {
                System.out.println("You've successfully defeated all the monsters!");
                combat.heroVictory();
                System.out.println("Input any key to continue");
                input.getString();
            } else if (combat.isCompleted() == 2) {
                System.out.println("You've been defeated by the monsters.");
                System.out.println("Game over!");    
                System.out.println("Input any key to continue");
                input.getString();
                input.setQuit(true);
            }
        }
    }
    public void interact(HeroParty heroParty) {
        System.out.println("There is no market here!");
        System.out.println("Input any key to continue");
        input.getString();
    }

    public int getSpaceType() {
        return Settings.COMMON;
    }
}
