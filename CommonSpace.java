public class CommonSpace implements SpaceFactory {
    public void enter() {
        double r = Math.random();
        if (r < Settings.ENCOUNTER_CHANCE) {
            
        }
    }
    public void interact() {
        System.out.println("There is no market here!");
    }

    public int getSpaceType() {
        return Settings.COMMON;
    }
}
