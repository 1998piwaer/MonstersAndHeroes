public class LoVGrid implements GridInterface {
    private Space space;
    public LoVGrid(Space space) {
        
    }

    public int getType() {
        return space.getSpaceType();
    }

    public void printGrid() {
        if (space instanceof InaccessibleSpace) {
            System.out.print(Settings.INACCESSIBLE_COLOR);
            System.out.print("X");
            System.out.print(Settings.DEFAULT_COLOR);
        }
    }

    public Space getSpace() {
        return space;
    }
}
