import java.util.*;
public interface PartyInterface {
    public Coordinate getPartyCoordinate(int index);
    public void setPartyCoordinate(int index, Coordinate coord);
    public List<? extends Entity> getParty();
    public Set<Coordinate> getAllCoordinates();
}