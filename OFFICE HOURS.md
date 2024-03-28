Is SpaceFactory a factory??

Almost every instance of HeroParty requires to iterate through List<Hero> stored in HeroParty. Is this ok?

HeroGetPotion, etc all do similar things. Is there a better way to do things? Can make equippable, but we  still need to ensure that the type we get are Potion or Armor, etc. Generic types??
Same thing applies for equipArmor or usePotion in Combat.java

equip method (doesn't seem good)