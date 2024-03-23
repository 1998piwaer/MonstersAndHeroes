public class Grid {
    private SpaceFactory space;
    public Grid(SpaceFactory space) {
        this.space = space;
    }
    
    public int getType() {
        return space.getSpaceType();
    }
}
