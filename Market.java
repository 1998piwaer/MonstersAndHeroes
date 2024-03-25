import java.util.*;
public class Market {
    private List<Item> catalog;

    public Market() {
        catalog = new ArrayList<>();
        ArmorFactory af = new ArmorFactory();
        PotionFactory pf = new PotionFactory();
        SpellFactory sf = new SpellFactory();
        catalog.add(af.createItem());
        catalog.add(pf.createItem());
        catalog.add(sf.createItem());
    }

    public void displayItems() {
        for (Item i : catalog) {
            System.out.println("----------------");
            i.displayItemInformation();
        }
    }
}
