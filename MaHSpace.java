 /*
  * Space.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Utilizes Factory pattern and creates an instance of a space,
  * By making all spaces implement this, we can make a board of Spaces,
  * which all have methods that have distinct behavior.
  */

public interface MaHSpace extends Space {
    void enter(HeroParty heroParty);
    void interact(HeroParty heroParty);
}
