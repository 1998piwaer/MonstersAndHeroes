import java.util.*;

public class HeroParty implements Party {
    List<Hero> party;
    int row;
    int col;
    

    public void addPartyMember(String name, CombatBehavior cb) {
        party.add(new Hero(name, cb));
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

    public void initPartyCoordinates(int boardRow, int boardCol) {
        row = (int) (Math.random() * boardRow);
        col = (int) (Math.random() * boardCol);
    }
}
