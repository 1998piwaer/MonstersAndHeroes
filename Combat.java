 /*
  * Combat.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Handles all logic regarding combat, which is created from CommonSpace's enter() method.
  */

import java.util.*;
public class Combat {
    private HeroParty heroParty;
    private MonsterParty monsterParty;
    private List<Hero> heroList;
    private List<Monster> monsterList;
    private int potentialExp;
    private int potentialGold;
    private boolean run;
    private static final String ACTION_INCOMPLETED = "not completed";

    private Input input = Input.getSingletonInput();
    public Combat(HeroParty heroParty) {
        this.heroParty = heroParty;
        this.heroList = heroParty.getParty();
        this.run = false;
        int maxHeroLevel = 0;
        for (Hero h : heroList) {
            maxHeroLevel = Math.max(h.getLevel(), maxHeroLevel);
        }
        // Combat initalizes a new instance of monster party, meaning even if you cleared
        // this space from a encouter before, you could run into a encounter again.
        this.monsterParty = new MonsterParty(heroParty.size(), maxHeroLevel);
        this.monsterList = monsterParty.getMonsterParty();
        this.potentialExp = monsterParty.size() * 2;
        int maxMonsterLevel = 0;
        for (Monster m : monsterList) {
            maxMonsterLevel = Math.max(m.getLevel(), maxMonsterLevel);
        }
        this.potentialGold = maxMonsterLevel * 100;
    }

    public void combatTurn() {
        System.out.println("Hero's Turn!");
        for (Hero currHero : heroList) {
            if (isCompleted() != 0) {
                break;
            }
            if (currHero.isFainted()) {
                continue;
            }
            
            Set<String> hs = new HashSet<>();
            hs.add("a");
            hs.add("s");
            hs.add("p");
            hs.add("e");
            hs.add("i");
            hs.add("r");
            
            String action = "";
            do {
                // action is ACTION_INCOMPLETED if a user inputs a invalid action.
                // This means it won't consume a hero's turn if a user does a invalid input
                // such as trying to cast spells when the hero doesn't have any spells, etc.
                if (action == ACTION_INCOMPLETED) {
                    System.out.println("Input any key to continue");
                    input.getString();
                    Settings.clearTerminal();
                }
                printCombatState();
                System.out.println("Pick an action to choose for Hero " + currHero.getName());
                System.out.println("Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E],"
                        + " Check Party Information [I], Run Away! [R]");
                action = input.getString(hs);
                if (action.equals("a")) {
                    // All these actions return false if they do a invalid action as specified
                    // in the comment above (or press "-1" to go back)
                    if(!performAttack(currHero)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("s")) {
                    if (!castSpell(currHero)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("p")) {
                    // I pass in classes so that we can create a generic method of useOrEquipItem, 
                    // rather than doing multiple instanceof checks.
                    if (!useOrEquipItem(currHero, Potion.class)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("e")) {
                    // getItemsOfType is also a generic method
                    List<Armor> armorLs = currHero.getItemsOfType(Armor.class);
                    List<Weapon> weaponLs = currHero.getItemsOfType(Weapon.class);
                    if (!armorLs.isEmpty() || !weaponLs.isEmpty()) {
                        if (!equip(currHero)) {
                            action = ACTION_INCOMPLETED;
                        }
                    } else {
                        System.out.println("There's nothing to equip!");
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("i")) {
                    printInDepthCombatState();
                    Settings.clearTerminal();
                } else if (action.equals("r")) {
                    if (Math.random() < Settings.RUN_SUCCESS_CHANCE) {
                        run = true;
                    } else {
                        // Trying to run consumes a turn
                        Settings.clearTerminal();
                        System.out.println("The party couldn't escape");
                    }
                }
                if (isCompleted() != 0) {
                    break;
                }
            // Keep asking for said hero if hero checked party state with 'i' or did invalid action
            } while (action.equals("i") || action.equals(ACTION_INCOMPLETED));
        }
        if (isCompleted() == 0) {
            System.out.println("---------------");
            System.out.println("Monster's Turn!");
            for (Monster monsterHero : monsterList) {
                performAttack(monsterHero);
                if (isCompleted() != 0) {
                    break;
                }
            }
        }
    }

    private void printInDepthCombatState() {
        heroParty.displayPartyInformation();
        monsterParty.displayPartyInformation();
    }

    private void printCombatState() {
        System.out.println("-------------------");
        System.out.println("Hero Party's State:");
        for (Hero h : heroList) {
            if (!h.isFainted()) {
                System.out.println(h.getName() + ": HP=" + h.getHealth() + "; MP=" + h.getMana());
                System.out.println("Available Items & Spells:"); 
                h.displayInventory();
            }
        }
        System.out.println("-------------------");
        System.out.println("Monster Party's State:");
        for (Monster m : monsterList) {
            System.out.println(m.getName() + ": HP=" + m.getHealth());
        }
    }

    // Returns 1 if Heros win, returns 2 if Monsters win, returns 0 if still in progress
    // Returns -1 if both are dead (should not be possible [only for debugging])
    public int isCompleted() {
        if (run) {
            return 3;
        }
        boolean heroesAlive = false;
        boolean monstersAlive = false;

        for (Hero hero : heroList) {
            if (!hero.isFainted()) {
                heroesAlive = true;
            }
        }

        for (Monster monster : monsterList) {
            if (!monster.isFainted()) {
                monstersAlive = true;
            }
        }

        if (heroesAlive && monstersAlive) {
            return 0;
        } else if (heroesAlive) {
            return 1;
        } else if (monstersAlive) {
            return 2;
        } else {
            return -1;
        }
    }

    // Used Class to enable this generic method to work, otherwise I run into the issue of
    // the type being sent in being of type Item rather than the specific class.
    // This prevents the method having to be rewritten for each class that extends Item.
    private <T extends Item> boolean useOrEquipItem(Hero hero, Class<T> itemType) {
        List<T> availableItems = hero.getItemsOfType(itemType);
        String itemTypeName= itemType.getSimpleName();
        if (availableItems.isEmpty()) {
            System.out.println("Hero " + hero.getName() + " has no " + itemTypeName.toLowerCase() + " available!");
            return false;
        } else {
            System.out.println("Which " + itemTypeName.toLowerCase() + " would you like to use? Or go back? [-1]");
            System.out.println("Available " + itemTypeName.toLowerCase() + " for Hero " + hero);
            printAvailableItems(availableItems, itemTypeName);
            int selectedItemIndex = input.getInt(-1, availableItems.size() - 1);
            if (selectedItemIndex == -1) {
                return false;
            }
            T selectedItem = availableItems.get(selectedItemIndex);
            hero.useOrEquipItem(selectedItem);
            System.out.print("Hero " + hero.getName() + " used " + selectedItem.getName() + "!");
            selectedItem.printEffect();
            return true;
        }
    }

    // Same comment as above, prevents repetitive code.
    private <T extends Item> void printAvailableItems(List<T> items, String typeName) {
        for (int i = 0; i < items.size(); i++) {
            T p = items.get(i);
            System.out.println(typeName + " [" + i + "]");
            p.displayItemInformationInventory();
        }
    }

    // Note: Sometimes, a user inputs "-1" or "B" to go back, it depends on the type being asked for.
    // To make the user go back when "B" is typed in even though we're expecting a integer could be done
    // but would require a lot more code and thought as long as I specified how the user should go back,
    // it should be fine.
    private boolean equip(Hero hero) {
        System.out.println("Would you like to equip an armor [A] or weapon [W] or go back [B]?");
        Set<String> hs = new HashSet<>();
        hs.add("a");
        hs.add("w");
        hs.add("b");
        String userInput = input.getString(hs);
        if (userInput.equals("a")) {
            if (!useOrEquipItem(hero, Armor.class)) {
                return false;
            } else {
                return true;
            }
        } else if (userInput.equals("w")) {
            if (!useOrEquipItem(hero, Weapon.class)) {
                return false;
            } else {
                return true;
            }
        } else if (userInput.equals("b")) {
            return false;
        } else {
            System.out.println("[Debug]: Letter with no specified action selected");
            return false;
        }
    }

    // Cast spell is distinct from performAttack() as there are a lot more checks to be done compared
    // to simply doing a normal attack. This includes seeing if spells even exist in a hero's inventory
    // or if the mana costs are met, etc.
    private boolean castSpell(Hero hero) {
        CombatBehavior heroCb = hero.getClassCombatBehavior();
        List<Spell> availableSpells = hero.getItemsOfType(Spell.class);
        
        if (availableSpells.isEmpty()) {
            System.out.println("Hero " + hero.getName() + " has no spells available!");
            return false;
        } else {
            Settings.clearTerminal();
            System.out.println("Which spell would you like to cast? Or go back? [-1]");
            System.out.println("Available spells for Hero " + hero.getName());
            printAvailableSpells(availableSpells);
            int selectedSpellIndex = input.getInt(-1, availableSpells.size() - 1);
            if (selectedSpellIndex == -1) {
                return false;
            }
            Spell selectedSpell = availableSpells.get(selectedSpellIndex);
            if (selectedSpell.getRequiredLevel() > hero.getLevel()) {
                System.out.println(hero.getName() + " (lvl " + hero.getLevel() + ") is "
                + "underleveled for the item! Required Level: " + selectedSpell.getRequiredLevel());
                return false;
            }
            if (selectedSpell.getManaCost() > hero.getMana()) {
                System.out.println(hero.getName() + " (Mana: " + hero.getMana() + ") has "
                + "too little MP for the spell! Required Mana: " + selectedSpell.getManaCost());
                return false;
            }

            System.out.println("Which monster would you like to attack? Or go back? [-1]");
            monsterParty.printMonsterParty();
            int target = input.getInt(-1, monsterParty.size() - 1);
            if (target == -1) {
                return false;
            }
            Monster targetMonster = monsterParty.getMonsterParty().get(target);
            CombatBehavior monsterCb = targetMonster.getClassCombatBehavior();
            hero.deductMana(selectedSpell.getManaCost());
            String type = selectedSpell.getType();
            if (type.equals("Fire")) {
                targetMonster.tankFire();
            } else if (type.equals("Ice")) {
                targetMonster.tankIce();
            } else if (type.equals("Lightning")) {
                targetMonster.tankLightning();
            } else {
                System.out.println("[Debug]: Unknown spell type!");
            }
            
            int herosSpellDamage = (int) (selectedSpell.getDamage() + 
                    (hero.getDexterity() / 10000) * selectedSpell.getDamage());

            // We use the combat behaviors to calculate given a hero's flat spell damage, how much it should
            // deal given a hero's class (sorcerer does more spell damage). And given a monster's defense,
            // how much damage they should take.
            int damage = monsterCb.tank(heroCb.castSpell(herosSpellDamage), targetMonster.getDefense(),
                    targetMonster.getDodgeChance() * Settings.DODGE_CHANCE_RATIO);
            
            // After the calculation is done from the combatBehaviors, the monster takes the calculated damage
            targetMonster.takeDamage(damage);
            System.out.println("Hero " + hero.getName() + " cast " + selectedSpell.getName()
                    + " at Monster " + targetMonster.getName() + " for " + damage + " damage!");
    
            if (targetMonster.isFainted()) {
                System.out.println("Monster " + targetMonster.getName() + " has fainted!");
                monsterParty.removeMonster(target);
            }
        }
        return true;
        
    }

    // Seperate methods compared to other items as spells don't want to be listed as "Items" technically
    private void printAvailableSpells(List<Spell> spells) {
        for (int i = 0; i < spells.size(); i++) {
            Spell s = spells.get(i);
            System.out.println("Spell [" + i + "]");
            s.displayItemInformationInventory();
        }
    }

    private void performAttack(Monster monster) {
        CombatBehavior monsterCb = monster.getClassCombatBehavior();
        Random random = new Random();
        // We do hashset as even if a hero faints, they're not removed from the HeroParty list.
        // so we don't want to use a range of valid numbers as it could include a fainted hero.
        Set<Integer> hs = heroParty.getAliveHeroesIndices();

        // Source: https://www.geeksforgeeks.org/how-to-get-random-elements-from-java-hashset/
        Integer[] arrayNumbers = hs.toArray(new Integer[hs.size()]);
        int target = random.nextInt(hs.size()); 
        Hero targetHero = heroParty.getParty().get(arrayNumbers[target]);

        // Same logic of damage calculation as mentioned in castSpell()
        CombatBehavior heroCb = targetHero.getClassCombatBehavior();
        int damage = heroCb.tank(monsterCb.attack(monster.getAttackDamage()), targetHero.getDefense(),
                targetHero.getAgility() * Settings.AGILITY_TO_DODGE_CHANCE_RATIO);
        targetHero.takeDamage(damage);
        System.out.println("Monster " + monster.getName() + " attacked Hero " + targetHero.getName() 
                + " for " + damage + " damage!");
        if (targetHero.isFainted()) {
            System.out.println("Hero " + targetHero.getName() + " has fainted!");
        }
    }

    private boolean performAttack(Hero hero) {
        Settings.clearTerminal();
        CombatBehavior heroCb = hero.getClassCombatBehavior();
        System.out.println("Which monster would you like to attack? Or go back? [-1]");
        monsterParty.printMonsterParty();
        int target = input.getInt(-1, monsterParty.size() - 1);
        if (target == -1) {
            return false;
        }
        Monster targetMonster = monsterParty.getMonsterParty().get(target);
        CombatBehavior monsterCb = targetMonster.getClassCombatBehavior();
        int herosAttackDamage;
        int weaponDamage = 0;
        Settings.clearTerminal();
        if (hero.getWeapon() != null) {
            weaponDamage = (int) (hero.getWeapon().getDamageAmplification() * Settings.STRENGTH_TO_ATTACK_DAMAGE_RATIO);
        }
        herosAttackDamage = hero.getStrength();
        
        // Same logic of damage calculation as mentioned in castSpell()
        int damage = monsterCb.tank(heroCb.attack(herosAttackDamage + weaponDamage), targetMonster.getDefense(), 
                targetMonster.getDodgeChance()  * Settings.DODGE_CHANCE_RATIO);
        
        if (damage != 0) {
            System.out.println("Hero " + hero.getName() + " attacked Monster " + targetMonster.getName() 
                + " for " + damage + " damage!");
        } else {
            System.out.println("Hero " + hero.getName() + " attacked Monster " + targetMonster.getName() 
                + " for " + damage + " damage!");
        }
        targetMonster.takeDamage(damage);
        
        

        if (targetMonster.isFainted()) {
            System.out.println("Monster " + targetMonster.getName() + " has fainted!");
            monsterParty.removeMonster(target);
        }
        return true;
    }

    

    public void heroVictory() {
        System.out.println("Alive party members gained " + potentialGold + " gold");
        heroParty.gainGold(potentialGold);

        // Important this method occurs before revive as regain hp & mp only occurs to alive
        // party members. We don't want to revive dead members and also give them 10% HP & MP.
        heroParty.endBattleRegainHpMp();
        heroParty.reviveIfFainted();
        System.out.println("The whole party gained " + potentialExp + " exp");
        heroParty.gainExp(potentialExp);
    }
}
