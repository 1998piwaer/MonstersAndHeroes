public class LoVGrid implements Grid {
    private LoVSpace space;
    public LoVGrid(LoVSpace space) {
        this.space = space;
    }

    public int getType() {
        return space.getSpaceType();
    }

    public void printGrid() {
        if (space instanceof InaccessibleSpace) {
            System.out.print("I");
        } else if (space instanceof PlainSpace) {
            System.out.print("P");
        } else if (space instanceof MarketNexusSpace) {
            System.out.print("N");
        } else if (space instanceof CaveSpace) {
            System.out.print("C");
        } else if (space instanceof BushSpace) {
            System.out.print("B");
        } else if (space instanceof KoulouSpace) {
            System.out.print("K");
        }
    }

    public LoVSpace getSpace() {
        return space;
    }
}
