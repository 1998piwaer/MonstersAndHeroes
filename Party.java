public interface Party {
    void addPartyMember(String name, CombatBehavior cb);
    void removePartyMember(int index);
}
