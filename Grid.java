 /*
  * Grid.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods that pertain to a single grid.
  * This includes what type of space it is and how to print it.
  */

public class Grid {
    private Space space;
    public Grid(Space space) {
        this.space = space;
    }
    
    public int getType() {
        return space.getSpaceType();
    }

    public void printGrid() {
        if (space instanceof CommonSpace) {
            System.out.print(" ");
        } else if (space instanceof MarketSpace) {
            System.out.print(Settings.MARKET_COLOR);
            System.out.print("M");
            System.out.print(Settings.DEFAULT_COLOR);
        } else if (space instanceof InaccessibleSpace) {
            System.out.print(Settings.INACCESSIBLE_COLOR);
            System.out.print("X");
            System.out.print(Settings.DEFAULT_COLOR);
        } else {
            // Here for debugging purposes
            System.out.print("?");
        }
    }
    public Space getSpace() {
        return space;
    }
}
