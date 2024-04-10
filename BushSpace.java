public class BushSpace implements LoVSpace {
    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new BushCombatBehavior());
    }

    public void interact(Hero h) {

    }

    public int getSpaceType() {
        return Settings.PLAIN_SPACE_TYPE;
    }
}
