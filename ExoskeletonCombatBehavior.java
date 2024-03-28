public class ExoskeletonCombatBehavior implements CombatBehavior {
    public int attack(int dmg) {
        return (int) (dmg * Settings.AFFINITY_MULTIPLIER);
    }
    
    //Monsters can't cast spells
    public int castSpell(int dmg) {
        return dmg;
    }

    public int tank(int dmg, int defense, double dodgeChance) {
        double roll = Math.random();
        if (roll < dodgeChance) {
            System.out.println("The monster managed to dodge the attack!");
            return 0;
        } else {
            return (int) (dmg / Settings.AFFINITY_MULTIPLIER) > 0 ? (int) (dmg / Settings.AFFINITY_MULTIPLIER) : 0;
        }
    }

    public String getType() {
        return "Exoskeleton";
    }

    public void applyLevelUpBonus(Hero hero) {
        System.out.println("[Debug]: Monsters can't level up! This shouldn't be printing");
    }
}
