public class PlainSpace implements LoVSpace {
    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new PlainCombatBehavior());
    }

    public void interact(Hero h) {

    }

    public int getSpaceType() {
        return Settings.PLAIN_SPACE_TYPE;
    }
}
