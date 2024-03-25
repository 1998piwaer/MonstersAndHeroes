public class MarketSpace implements SpaceFactory {
    Market market;

    public MarketSpace() {
        market = new Market();
    }
    public void enter() {

    }
    public void interact() {
        market.displayItems();
    }
    public int getSpaceType() {
        return Settings.MARKET;
    }
}
