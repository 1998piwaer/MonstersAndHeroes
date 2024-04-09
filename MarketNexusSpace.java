 /*
  * MarketSpace.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Implements the factory pattern from SpaceFactory.
  * enter() triggers when a hero walks into a space and interact() triggers
  * when a hero presses M on a space. MarketSpace is in charge of holding a market
  * and handling inputs regarding a market. Internals of the market is stored in the Market
  * object.
  */

import java.util.*;
public class MarketNexusSpace implements MaHSpace, LoVSpace {
    private Market market;
    private Input input = Input.getSingletonInput();
    private Entity entity;

    
    public MarketNexusSpace() {
        market = new Market();
    }

    public void setOwnership(Entity e) {
        entity = e;
    }

    
    public void enter(HeroParty heroParty) {
        // Nothing happens as it's a safe space.
    }
    public void interact(HeroParty heroParty) {
        List<Hero> heroList = heroParty.getParty();
        
        Set<String> hs = new HashSet<>();
        hs.add("e");
        hs.add("b");
        hs.add("s");
        hs.add("n");
        String userInput = "";
        while (!userInput.equals("e")) {
            for (Hero h: heroList) {
                Settings.clearTerminal();
                market.displayItems();
                if (market.getCatalogSize() == 0) {
                    System.out.println("The market is out of items!");
                    System.out.println("Input any key to continue");
                    input.getString();
                    userInput = "e";
                    break;
                }
                System.out.println("Will hero " + h.getName() + " (Gold: " + h.getGold() + ") like to buy [B], sell [S],"
                + " move on to the next hero [N] or exit [E]?");
                userInput = input.getString(hs);
                if (userInput.equals("b")) {
                    buy(h);
                } else if (userInput.equals("s")) {
                    sell(h);
                } else if (userInput.equals("n")) {
                    continue;
                } else if (userInput.equals("e")){
                    break;
                }
            }
        }
    }

    private void sell(Hero h) {
        int itemIndex = 0;

        while (itemIndex != -1 || h.getInventory().size() > 0) {
            h.displayInventory();
            System.out.println("Which item (or none [-1]) would you like to sell?");
            System.out.println("Hero " + h.getName() + " has " + h.getGold() + " gold.");
            itemIndex = input.getInt(-1, h.getInventory().size() - 1);
            if (itemIndex != -1) {
                h.sellItem(itemIndex);
            } 
            
        } 
    }

    private void buy(Hero h) {
        int itemIndex = -1;
        do {
            Settings.clearTerminal();
            market.displayItems();
            System.out.println("Which item (or none [-1]) would you like to buy?");
            System.out.println("Hero " + h.getName() + " has " + h.getGold() + " gold.");

            itemIndex = input.getInt(-1, market.getCatalogSize() - 1);
            if (itemIndex == -1) {
                break;
            }
        } while (!market.buyItem(h, itemIndex));
    }

    public int getSpaceType() {
        return Settings.MARKET;
    }

    public void enter(Entity e) {
        e.setSpaceCombatBehavior(new PlainCombatBehavior());
    }

    public void interact(Entity e) {
        
    }
}
