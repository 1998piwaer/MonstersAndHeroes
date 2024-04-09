public class KoulouSpace implements LoVSpace {
    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new KoulouCombatBehavior());
    }

    public void interact(Entity e) {

    }

    public int getSpaceType() {
        return Settings.KOULOU_SPACE_TYPE;
    }
}