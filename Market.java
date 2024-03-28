import java.util.*;
public class Market {
    private List<Item> catalog;

    public Market() {
        catalog = new ArrayList<>();
        ArmorFactory af = new ArmorFactory();
        PotionFactory pf = new PotionFactory();
        SpellFactory sf = new SpellFactory();
        WeaponFactory wf = new WeaponFactory();
        catalog.add(af.createItem());
        catalog.add(pf.createItem());
        catalog.add(sf.createItem());
        catalog.add(wf.createItem());
    }

    public void displayItems() {
        for (int i = 0; i < catalog.size(); i++) {
            Item currItem = catalog.get(i);
            System.out.println("----------------");
            currItem.displayItemInformationMarket(i);
        }
    }

    public boolean buyItem(Hero h, int index) {
        Item selectedItem = catalog.get(index);
        if (h.getGold() < selectedItem.getCost()) {
            System.out.println("Hero " + h.getName() + " doesn't have enough gold!");
            return false;
        } else {
            h.deductGold(selectedItem.getCost());
            h.addItem(selectedItem);
            catalog.remove(index);
            return true;
        }
    }

    public int getCatalogSize() {
        return catalog.size();
    }
}
