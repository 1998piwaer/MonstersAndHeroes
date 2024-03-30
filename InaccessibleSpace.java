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

public class InaccessibleSpace implements Space {
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
