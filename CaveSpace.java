public class CaveSpace implements LoVSpace {
    public int getSpaceType() {
        return Settings.CAVE_SPACE_TYPE;
    }

    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new CaveCombatBehavior());
    }

    public void interact(Hero h) {

    }
}