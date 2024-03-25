import java.util.*;

public class HeroParty {
    List<Hero> party;
    int row;
    int col;

    public HeroParty() {
        party = new ArrayList<>();
    }
    
    public void displayPartyInformation() {
        for (int i = 0; i < party.size(); i++) {
            Hero currentHero = party.get(i);
            System.out.println("----------------------");
            System.out.println("Hero " + i + " information:");
            System.out.println("Name: " + currentHero.getName());
            System.out.println("HP: " + currentHero.getHealth());
            System.out.println("Strength: " + currentHero.getStrength());
            System.out.println("Dexterity: " + currentHero.getDexterity());
            System.out.println("Agility: " + currentHero.getAgility());
            System.out.println("Gold: " + currentHero.getGold());
        }
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
