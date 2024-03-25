public class InaccessibleSpace implements SpaceFactory {
    public void enter() {

    }

    public void interact() {
        System.out.println("There is no market here!");
    }

    public int getSpaceType() {
        return Settings.INACCESSIBLE;
    }
}
