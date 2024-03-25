public class InaccessibleSpace implements SpaceFactory {
    public void enter(HeroParty heroParty) {
        //This should never be entered (Debug print added just in case)
        System.out.println("[Debug]: This is inaccessible!");
    }

    public void interact(HeroParty heroParty) {
        System.out.println("There is no market here!");
    }

    public int getSpaceType() {
        return Settings.INACCESSIBLE;
    }
}
