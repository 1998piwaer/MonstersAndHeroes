 /*
  * HeroParty.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * This class supports all methods that pertain to a group of heroes.
  * I create a class rather than just having a list of heroes so that
  * I can handle party wide methods and also have partywide state such as
  * their coordinates rather than having to store coordinates for each hero, etc.
  */

import java.util.*;

public class HeroParty implements PartyInterface {
    private List<Hero> party;
    private Input input = Input.getSingletonInput();
    private Map<Hero, Coordinate> heroCoordinates;
    private boolean win;

    public HeroParty() {
        party = new ArrayList<>();
        heroCoordinates = new HashMap<>();
        win = false;
    }

    public Hero get(int i) {
        return party.get(i);
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public Coordinate getPartyCoordinate(int index) {
        return heroCoordinates.get(party.get(index));
    }

    public void setPartyCoordinate(int index, Coordinate coord) {
        heroCoordinates.put(party.get(index), coord);
    }

    public void initPartyCoordinates(int boardRow, int boardCol) {
        int row = (int) (Math.random() * boardRow);
        int col = (int) (Math.random() * boardCol);
        for (Hero h : party) {
            heroCoordinates.put(h, new Coordinate(row, col));
        }
    }

    public Set<Coordinate> getAllCoordinates() {
        Set<Coordinate> hs = new HashSet<>();
        for (Hero h : party) {
            hs.add(heroCoordinates.get(h));
        }
        return hs;
    }

    
    // ----- Monsters And Heroes -----
    public void displayPartyInformation() {
        Settings.clearTerminal();
        for (int i = 0; i < party.size(); i++) {
            Hero currentHero = party.get(i);
            System.out.println("----------------------");
            System.out.println("Hero " + i + " information:");
            System.out.println("Name: " + currentHero.getClassCombatBehavior().getType() + " " 
                    + currentHero.getName() + " (Lvl " + currentHero.getLevel() + ")");
            System.out.println("HP: " + currentHero.getHealth() + "; MP:" + currentHero.getMana());
            System.out.println("Strength: " + currentHero.getStrength() + "; Dexterity: " 
                    + currentHero.getDexterity() + "; Agility: " + currentHero.getAgility());
            System.out.println("Gold: " + currentHero.getGold());
            if (currentHero.getWeapon() != null) {
                System.out.println("Weapon Equipped: " + currentHero.getWeapon().getName()
                        + "; Damage Amplification: " + currentHero.getWeapon().getDamageAmplification());
            } else {
                System.out.println("Weapon Equipped: None");
            }
            if (currentHero.getArmor() != null) {
                System.out.println("Armor Equipped: " + currentHero.getArmor().getName() 
                        + "; Damage Reduction: " + currentHero.getArmor().getDamageReduction());
            } else {
                System.out.println("Armor Equipped: None");
            }
            currentHero.displayInventory();
        }
        System.out.println("Input any key to continue");
        input.getString();
    }

    public Set<Integer> getAliveHeroesIndices() {
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < party.size(); i++) {
            if (!party.get(i).isFainted()) {
                hs.add(i);
            }
        }
        return hs;
    }

    public void endBattleRegainHpMp() {
        for (Hero h : party) {
            if (!h.isFainted()) {
                int oldHealth = h.getHealth();
                int oldMana = h.getMana();
                h.gainHealth((int) (h.getMaxHealth() * 0.1));
                h.gainMana((int) (h.getMaxMana() * 0.1));
                int newHealth = h.getHealth();
                int newMana = h.getMana();
                System.out.println("Non-fainted hero " + h.getName() + " gains back with 10% of their max HP [" 
                        + h.getMaxHealth() + "] & MP [" + h.getMaxMana() +"] HP: " + oldHealth + "->" + newHealth
                        + "MP: " + oldMana + "->" + newMana);
            }
        }
    }

    public void gainExp(int exp) {
        for (Hero h : party) {
            h.gainExp(exp);
        }
    }

    public void reviveIfFainted() {
        for (Hero h : party) {
            if (h.isFainted()) {
                int oldHealth = h.getHealth();
                int oldMana = h.getMana();
                h.revive();
                int newHealth = h.getHealth();
                int newMana = h.getMana();
                System.out.println("Fainted hero " + h.getName() + " revives with 50% of their max HP [" 
                        + h.getMaxHealth() + "] & MP [" + h.getMaxMana() +"]" + "HP: " + oldHealth + "->" + newHealth
                        + "MP: " + oldMana + "->" + newMana);
            }
        }
    }

    public void gainGold(int gold) {
        for (Hero h : party) {
            if (!h.isFainted()) {
                int oldGold = h.getGold();
                h.gainGold(gold);
                int newGold = h.getGold();
                System.out.println(h.getName() + "Gold: (" + oldGold + "->"+ newGold + ")");
            }
        }
    }

    public void equipOrUse(Hero h) {
        if (!h.displayInventory()) {
            return;
        }
        System.out.println("Which item would you like to equip? Or none [-1]");
        List<Item> inventory = h.getInventory();
        int userInput = input.getInt(-1, inventory.size() - 1);
        if (userInput == -1) {
            return;
        }
        
        Item selectedItem = inventory.get(userInput);
        if (selectedItem instanceof Spell) {
            System.out.println("You can't equip a spell!");
        } else if (selectedItem instanceof Armor) {
            boolean equipSuccess = h.useOrEquipItem((Armor) selectedItem);
            if (equipSuccess) {
                System.out.println("Successfully equipped armor " + selectedItem.getName());
            }
        } else if (selectedItem instanceof Weapon) {
            boolean equipSuccess = h.useOrEquipItem((Weapon) selectedItem);
            if (equipSuccess) {
                System.out.println("Successfully equipped weapon " + selectedItem.getName());
            }
        } else if (selectedItem instanceof Potion) {
            Potion selectedPotion = (Potion) selectedItem;
            if (h.useOrEquipItem(selectedPotion)) {
                System.out.println("Hero " + h.getName() + " used " + selectedPotion.getName()
                + " and increased " + selectedPotion.getAffectedAttributes() + " by " 
                + selectedPotion.getAttributeIncrease());
            }
            
        } else {
            System.out.println("[Debug]: No case specified for this item type!");
        }
        
        System.out.println("Input any key to continue");
        input.getString();
    
    }

    public void addPartyMember(Hero hero) {
        party.add(hero);
    }

    public void removePartyMember(int index) {
        party.remove(index);
    }

    public int size() {
        return party.size();
    }

    public List<Hero> getParty() {
        return party;
    }
}
