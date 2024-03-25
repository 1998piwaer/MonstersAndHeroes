import java.util.*;
public class Combat {
    HeroParty heroParty;
    MonsterParty monsterParty;
    List<Hero> heroList;
    List<Monster> monsterList;
    Input input = Input.getSingletonInput();
    public Combat(HeroParty heroParty) {
        this.heroParty = heroParty;
        this.monsterParty = new MonsterParty(heroParty.size());
        this.heroList = heroParty.getHeroParty();
        this.monsterList = monsterParty.getMonsterParty();

    }

    public void combatTurn() {
        printCombatState();
        for (Hero currHero : heroList) {
            Set<String> hs = new HashSet<>();
            hs.add("a");
            System.out.println("Pick an action to choose for Hero " + currHero.getName());
            String action = input.getString(hs);
            if (action.equals("a")) {
                performAttack(currHero);
            }
            printCombatState();
        }
        for (Monster monsterHero : monsterList) {
            performAttack(monsterHero);
        }
        printCombatState();
    }

    private void printCombatState() {
        System.out.println("--------------");
        System.out.println("Heroes State:");
        for (Hero h : heroList) {
            System.out.println(h.getName() + ": HP=" + h.getHealth() + "; MP=" + h.getMana());
        }
        System.out.println("Monsters State:");
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

    public void performAttack(Hero hero) {
        CombatBehavior heroCb = hero.getCombatBehavior();
        System.out.println("Which monster would you like to attack?");
        monsterParty.printMonsterParty();
        int target = input.getInt(0, monsterParty.size() - 1);
        Monster targetMonster = monsterParty.getMonsterParty().get(target);
        CombatBehavior monsterCb = targetMonster.getCombatBehavior();
        int damage = monsterCb.tankAttack(heroCb.attack(hero.getStrength()));
        targetMonster.takeDamage(damage);
        System.out.println("Hero " + hero.getName() + " attacked Monster " + targetMonster.getName() 
                + " for " + damage + " damage!");
    }

    public void performAttack(Monster monster) {
        CombatBehavior monsterCb = monster.getCombatBehavior();
        Random random = new Random();
        int target = random.nextInt(heroParty.size());
        Hero targetHero = heroParty.getHeroParty().get(target);
        CombatBehavior heroCb = targetHero.getCombatBehavior();
        int damage = heroCb.tankAttack(monsterCb.attack(monster.getStrength()));
        targetHero.takeDamage(damage);
        System.out.println("Monster " + monster.getName() + " attacked Hero " + targetHero.getName() 
                + " for " + damage + " damage!");
    }
}
