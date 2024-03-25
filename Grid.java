public class Grid {
    private SpaceFactory space;
    public Grid(SpaceFactory space) {
        this.space = space;
    }
    
    public int getType() {
        return space.getSpaceType();
    }

    public void printGrid() {
        if (space instanceof CommonSpace) {
            System.out.print(" ");
        } else if (space instanceof MarketSpace) {
            System.out.print("M");
        } else if (space instanceof InaccessibleSpace) {
            System.out.print("X");
        } else {
            // Here for debugging purposes
            System.out.print("?");
        }
    }
    public SpaceFactory getSpace() {
        return space;
    }
}
