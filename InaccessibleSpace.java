 /*
  * InaccessibleSpace.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Implements the factory pattern from SpaceFactory.
  * enter() triggers when a hero walks into a space and interact() triggers
  * when a hero presses M on a space. InaccessibleSpace should never
  * have any of the methods called as a hero should not be able to enter in the first place.
  */

public class InaccessibleSpace implements MaHSpace, LoVSpace {
    public void enter(HeroParty heroParty) {
        //This should never be entered (Debug print added just in case)
        System.out.println("[Debug]: This is inaccessible!");
    }

    public void interact(HeroParty heroParty) {
        System.out.println("[Debug]: This is inaccessible!");
    }

    public int getSpaceType() {
        return Settings.INACCESSIBLE_SPACE_TYPE;
    }

    public void enter(Entity entity) {
        System.out.println("[Debug]: This is inaccessible!");
    }

    public void interact(Hero h) {
        System.out.println("[Debug]: This is inaccessible!");
    }
}
