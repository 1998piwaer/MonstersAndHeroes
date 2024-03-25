public class CommonSpace implements SpaceFactory {
    public void enter(HeroParty heroParty) {
        double r = Math.random();
        if (r < Settings.ENCOUNTER_CHANCE) {
            Combat combat = new Combat(heroParty);
            while (combat.isCompleted() == 0) {
                combat.combatTurn();
            }
        }
    }
    public void interact(HeroParty heroParty) {
        System.out.println("There is no market here!");
    }

    public int getSpaceType() {
        return Settings.COMMON;
    }
}
