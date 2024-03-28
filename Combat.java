import java.util.*;
public class Combat {
    private HeroParty heroParty;
    private MonsterParty monsterParty;
    private List<Hero> heroList;
    private List<Monster> monsterList;
    private int potentialExp;
    private int potentialGold;
    private static final String ACTION_INCOMPLETED = "not completed";

    private Input input = Input.getSingletonInput();
    public Combat(HeroParty heroParty) {
        this.heroParty = heroParty;
        this.heroList = heroParty.getHeroParty();
        int maxHeroLevel = 0;
        for (Hero h : heroList) {
            maxHeroLevel = Math.max(h.getLevel(), maxHeroLevel);
        }
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
            if (currHero.isFainted()) {
                continue;
            }
            Set<String> hs = new HashSet<>();
            hs.add("a");
            hs.add("s");
            hs.add("p");
            hs.add("e");
            hs.add("i");
            printCombatState();
            String action = "";
            do {
                if (action == ACTION_INCOMPLETED) {
                    Settings.clearTerminal();
                }
                System.out.println("Pick an action to choose for Hero " + currHero.getName());
                System.out.println("Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E],"
                        + " Check Party Information [I]");
                action = input.getString(hs);
                if (action.equals("a")) {
                    if(!performAttack(currHero)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("s")) {
                    if (!castSpell(currHero)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("p")) {
                    if (!useOrEquipItem(currHero, Potion.class)) {
                        action = ACTION_INCOMPLETED;
                    }
                } else if (action.equals("e")) {
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
                }
            } while (action.equals("i") || action.equals(ACTION_INCOMPLETED));
            if (isCompleted() != 0) {
                break;
            }
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
        System.out.println("Heroe Party's State:");
        for (Hero h : heroList) {
            System.out.println(h.getName() + ": HP=" + h.getHealth() + "; MP=" + h.getMana());
            System.out.println("Available Items & Spells:"); 
            h.displayInventory();
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

    private <T extends Item> void printAvailableItems(List<T> items, String typeName) {
        for (int i = 0; i < items.size(); i++) {
            T p = items.get(i);
            System.out.println(typeName + " [" + i + "]");
            p.displayItemInformationInventory();
        }
    }

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

    private boolean castSpell(Hero hero) {
        CombatBehavior heroCb = hero.getCombatBehavior();
        List<Spell> availableSpells = hero.getItemsOfType(Spell.class);
        
        if (availableSpells.isEmpty()) {
            System.out.println("Hero " + hero.getName() + " has no spells available!");
            return false;
        } else {
                System.out.println("Which spell would you like to cast? Or go back? [-1]");
                System.out.println("Available spells for Hero " + hero.getName());
                printAvailableSpells(availableSpells);
                int selectedSpellIndex = input.getInt(-1, availableSpells.size() - 1);
                if (selectedSpellIndex == -1) {
                    return false;
                }
                Spell selectedSpell = availableSpells.get(selectedSpellIndex);
                System.out.println("Which monster would you like to attack? Or go back? [-1]");
                monsterParty.printMonsterParty();
                int target = input.getInt(-1, monsterParty.size() - 1);
                if (target == -1) {
                    return false;
                }
                Monster targetMonster = monsterParty.getMonsterParty().get(target);
                CombatBehavior monsterCb = targetMonster.getCombatBehavior();

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
                int damage = monsterCb.tank(heroCb.attack(herosSpellDamage), targetMonster.getDefense(),
                        targetMonster.getDodgeChance() * Settings.DODGE_CHANCE_RATIO);
                
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

    private void printAvailableSpells(List<Spell> spells) {
        for (int i = 0; i < spells.size(); i++) {
            Spell s = spells.get(i);
            System.out.println("Spell [" + i + "]");
            s.displayItemInformationInventory();
        }
    }

    private boolean performAttack(Hero hero) {
        Settings.clearTerminal();
        CombatBehavior heroCb = hero.getCombatBehavior();
        System.out.println("Which monster would you like to attack? Or go back? [-1]");
        monsterParty.printMonsterParty();
        int target = input.getInt(-1, monsterParty.size() - 1);
        if (target == -1) {
            return false;
        }
        Monster targetMonster = monsterParty.getMonsterParty().get(target);
        CombatBehavior monsterCb = targetMonster.getCombatBehavior();
        int herosAttackDamage;
        int weaponDamage = 0;
        Settings.clearTerminal();
        if (hero.getWeapon() != null) {
            weaponDamage = (int) (hero.getWeapon().getDamageAmplification() * Settings.STRENGTH_TO_ATTACK_DAMAGE_RATIO);
            herosAttackDamage = hero.getStrength();
        } else {
            herosAttackDamage = hero.getStrength();
        }
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

    private void performAttack(Monster monster) {
        CombatBehavior monsterCb = monster.getCombatBehavior();
        Random random = new Random();
        int target = random.nextInt(heroParty.size());
        Hero targetHero = heroParty.getHeroParty().get(target);
        CombatBehavior heroCb = targetHero.getCombatBehavior();
        int damage = heroCb.tank(monsterCb.attack(monster.getAttackDamage()), targetHero.getDefense(),
                targetHero.getAgility() * Settings.AGILITY_TO_DODGE_CHANCE_RATIO);
        targetHero.takeDamage(damage);
        System.out.println("Monster " + monster.getName() + " attacked Hero " + targetHero.getName() 
                + " for " + damage + " damage!");
        if (targetHero.isFainted()) {
            System.out.println("Hero " + targetHero.getName() + " has fainted!");
        }
    }

    public void heroVictory() {
        System.out.println("Alive party members gained " + potentialGold + " gold");
        heroParty.gainGold(potentialGold);
        heroParty.endBattleRegainHpMp();
        heroParty.reviveIfFainted();
        System.out.println("The whole party gained " + potentialExp + " exp");
        heroParty.gainExp(potentialExp);
    }
}
