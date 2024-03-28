import java.util.*;

public class HeroParty {
    private List<Hero> party;
    private int row;
    private int col;
    private Input input = Input.getSingletonInput();

    public HeroParty() {
        party = new ArrayList<>();
    }
    
    public void displayPartyInformation() {
        Settings.clearTerminal();
        for (int i = 0; i < party.size(); i++) {
            Hero currentHero = party.get(i);
            System.out.println("----------------------");
            System.out.println("Hero " + i + " information:");
            System.out.println("Name: " + currentHero.getCombatBehavior().getType() + " " 
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

    public void endBattleRegainHpMp() {
        for (Hero h : party) {
            if (!h.isFainted()) {
                h.gainHealth((int) (h.getMaxHealth() * 0.1));
                h.gainMana((int) (h.getMaxMana() * 0.1));
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
                h.revive();
            }
        }
    }

    public void gainGold(int gold) {
        for (Hero h : party) {
            if (!h.isFainted()) {
                h.gainGold(gold);
            }
        }
    }

    public void equipOrUse() {
        for (Hero h: party) {
            if (!h.displayInventory()) {
                continue;
            }
            System.out.println("Which item would you like to equip? Or none [-1]");
            List<Item> inventory = h.getInventory();
            int userInput = input.getInt(-1, inventory.size() - 1);
            Item selectedItem = inventory.get(userInput);
            if (selectedItem instanceof Spell) {
                System.out.println("You can't equip a spell!");
            } else if (selectedItem instanceof Armor) {
                boolean equipSuccess = h.useOrEquipItem((Armor) selectedItem);
                if (!equipSuccess) {
                    System.out.println("You already have armor equipped!");
                } else {
                    System.out.println("Successfully equipped armor " + selectedItem.getName());
                }
            } else if (selectedItem instanceof Weapon) {
                boolean equipSuccess = h.useOrEquipItem((Weapon) selectedItem);
                if (!equipSuccess) {
                    System.out.println("You don't have enough free hands to equip this weapon!");
                } else {
                    System.out.println("Successfully equipped weapon " + selectedItem.getName());
                }
            } else if (selectedItem instanceof Potion) {
                Potion selectedPotion = (Potion) selectedItem;
                h.useOrEquipItem(selectedPotion);
                System.out.println("Hero " + h.getName() + " used " + selectedPotion.getName()
                    + " and increased " + selectedPotion.getAffectedAttributes() + " by " 
                    + selectedPotion.getAttributeIncrease());
            } else {
                System.out.println("[Debug]: No case specified for this item type!");
            }
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

    public int getHeroPartyRow() {
        return row;
    }
    public int getHeroPartyCol() {
        return col;
    }

    public void setHeroPartyRow(int r) {
        row = r;
    }

    public void setHeroPartyCol(int c) {
        col = c;
    }

    public void initPartyCoordinates(int boardRow, int boardCol) {
        row = (int) (Math.random() * boardRow);
        col = (int) (Math.random() * boardCol);
    }

    public int size() {
        return party.size();
    }

    public List<Hero> getHeroParty() {
        return party;
    }
}
