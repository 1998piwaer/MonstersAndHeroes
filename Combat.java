import java.util.*;
public class Combat {
    HeroParty heroParty;
    MonsterParty monsterParty;
    public Combat(HeroParty heroParty) {
        this.heroParty = heroParty;
        this.monsterParty = new MonsterParty(heroParty.size());
    }

    
}
