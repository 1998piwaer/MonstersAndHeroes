 /*
  * Grid.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods that pertain to a single grid.
  * This includes what type of space it is and how to print it.
  */

public interface Grid {
    int getType();
    void printGrid();
    Space getSpace();
}
