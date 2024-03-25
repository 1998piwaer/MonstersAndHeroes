public class MarketSpace implements SpaceFactory {
    Market market;

    public MarketSpace() {
        market = new Market();
    }
    public void enter(HeroParty heroParty) {

    }
    public void interact(HeroParty heroParty) {
        market.displayItems();
    }
    public int getSpaceType() {
        return Settings.MARKET;
    }
}
