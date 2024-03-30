 /*
  * Market.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class contains all methods regarding the state of the market.
  * Each MarketSpace has one Market that's created during initialization of the space, 
  * meaning if a market is out of items in in its catalog, it will remain empty.
  */

import java.util.*;
public class Market {
    private List<Item> catalog;
    private Input input = Input.getSingletonInput();

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
            System.out.println("Input any key to continue");
            input.getString();
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
