public class PlainSpace implements LoVSpace {
    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new PlainCombatBehavior());
    }

    public void interact(Entity e) {

    }

    public int getSpaceType() {
        return Settings.PLAIN_SPACE_TYPE;
    }
}
