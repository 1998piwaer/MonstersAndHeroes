Student information:
    First Name: Simon
    Last Name: Kye
    BU ID: U91699718
    Email: simonkye@bu.edu

Design Patterns Used:
    Input: Singleton
    CombatBehavior: Strategy Pattern
    Space: ???
    ItemFactory: Factory Pattern

File information
    Main.java: Starts program by creating MainMenu
    MainMenu.java: Serves as main menu and game selection page
    Playable.java: Interface that is implemented by MonstersAndHeroes.java and for future games
        that so that no extra work needs to be done on MainMenu
    MonstersAndHeroes.java: Contains operations related to the board and controls
        when the map is in view.
    Board.java: Runs all general operations to the board itself such as 
        initializing width and height and populating the board with spaces (INACCESSIBLE, MARKET, COMMON).
    Grid.java: Runs all operations related to a single grid such as printing and getting its type.
    Input.java: Singleton class that handles all input related operations. By making it singleton,
        we can check at any point in the if a system-wide command is selected.
    Settings.java: Holds all constants to avoid repetitive initializing and settings values so we don't
        have to change a value for everywhere when we want to change something that utilizes a constants
        in multiple places
    CombatBehavior.java: Interface that requires implementation of how a hero/monster class will 
        deal and take damage. This also allows for heroes to gain extra attributes on level up.
        By putting it in a interface like this, all heroes and monsters have different calculations 
        of how damage is dealt to them. Utilizies the Strategy Pattern.
    [MonsterType/HeroType]CombatBehavior.java: As listed above, we can implement class specific combat behavior.
        For example, Spirits & Warriors have a higher chance of dodging and Sorcerers deal more damage when casting
        spells, etc.
    Item.java: Abstract class extended by anything that can stored in a hero's inventory (spells, potions,
        armor, weapon).
    Armor/Spells/Potions/Weapon.java: Class that extends Item and contains unique traits such as
        Ddmage amplification for weapons, spell types for spells, etc.
    Entity.java: Abstract class for all attributes shared by monsters and heroes
    Monster.java: Has all monster specific attributes and methods, and a static method that can read
        from the text documents and make a new monster
    Hero.java: Contains all hero specific attributes and methods. Whether it be from equipping to
        to taking damage to selling and buying items, and a static method that can read from
        txt files and make a new hero. 
    SpaceFactory: Utilizes Factory Pattern. This interface has a enter(), interact(), and getSpaceType()
        method. This allows for all grids on a board to simply contain a SpaceFactory attribute and depending
        on the factory installed, it will have unique behaviors and visuals.
    [Common/Inaccessible/Market]Space.java: Contains the specifications of what to do if a user enters this
        space (walks into it) or interacts with it (presses M). Note: MarketSpace has a attribute of
        type Market, meaning a market has one instance of Market and saves even if the player leaves and
        reenters.
    ItemFactory.java: Interface that has a create() method so that a market can create unique types of items.
        By making create() return type Item, a market can have multiple ItemFactories and simply run create
        on all of them to fill up its catalog of items, which is a list of Item type.
    [Armor/Potion/Weapon/Spell]Factory.java: Simply implements the ItemFactory interface. Reads from txt files
        in the folder.
    Market.java: Utilizes the multiple ItemFactory objects to fill up its catalogs. And has methods
        pertaining to a market.
    HeroParty.java: Contains a list of Heroes and contains methods that modify or needs access to the 
        whole party.
    MonsterParty.java: Contains a list of monsters and mainly used for combat, where it is one HeroParty
        vs one MonsterParty
    Combat.java: Contains all logic regarding a combat, where it contains one MonsterParty and one HeroParty.
        Combat is created in CommonSpace and keeps calling combatTurn() until combat's isCompleted() no longer
        returns 0.
    

Notes
    - All balancing related things are stored in Settings.java. If the game is too hard or easy,
        or if you want to test specific portions, modify Settings.java.
    - Can quit out at any point with "Q"
    - If game doesn't fit on screen (especially if there are a lot of items in the inventory), 
        If you want to see previous outputs change the contents of Settings.java's 
        clearTerminal() method to simply System.out.println();
    - You can run away from encounters as well, where heroes won't be revived.

Compilation and run directions for the terminal
    cd to folder containing Main.java
    Either run "./make.sh" or "javac *.java && java Main"

Source citations
    Reading and parsing from txt files:
    - Used ChatGPT
    How to get random elements from a hashset
    - https://www.geeksforgeeks.org/how-to-get-random-elements-from-java-hashset/

I/O example
    Example: ./make.sh
    Welcome to the Main Menu!!
    Which game would you like to play? You can press [Q] to exit out any point!
    [0] Legends: Monsters and Heroes 
    0
    Do you want to play with default settings? [T/F]
    t
    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | M |   | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | H |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a MARKET space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    a
    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | M |   | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X | H | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a COMMON space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    w
    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | M |   | H | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a MARKET space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    a
    The party runs into an encounter!
    Hero's Turn!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=100; MP=400
    Available Items & Spells:
    No items in inventory for Eunoia Cyn
    Garl Glittergold: HP=100; MP=100
    Available Items & Spells:
    No items in inventory for Garl Glittergold
    Segojan Earthcaller: HP=100; MP=900
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    TheWeatherbe: HP=800
    Melchiresas: HP=700
    Andrealphus: HP=200
    Pick an action to choose for Hero Eunoia Cyn
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    a
    Which monster would you like to attack? Or go back? [-1]
    TheWeatherbe [0]: HP = 800
    Melchiresas [1]: HP = 700
    Andrealphus [2]: HP = 200
    0
    Hero Eunoia Cyn attacked Monster TheWeatherbe for 600 damage!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=100; MP=400
    Available Items & Spells:
    No items in inventory for Eunoia Cyn
    Garl Glittergold: HP=100; MP=100
    Available Items & Spells:
    No items in inventory for Garl Glittergold
    Segojan Earthcaller: HP=100; MP=900
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    TheWeatherbe: HP=200
    Melchiresas: HP=700
    Andrealphus: HP=200
    Pick an action to choose for Hero Garl Glittergold
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    a
    Which monster would you like to attack? Or go back? [-1]
    TheWeatherbe [0]: HP = 200
    Melchiresas [1]: HP = 700
    Andrealphus [2]: HP = 200
    0
    Hero Garl Glittergold attacked Monster TheWeatherbe for 450 damage!
    Monster TheWeatherbe has fainted!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=100; MP=400
    Available Items & Spells:
    No items in inventory for Eunoia Cyn
    Garl Glittergold: HP=100; MP=100
    Available Items & Spells:
    No items in inventory for Garl Glittergold
    Segojan Earthcaller: HP=100; MP=900
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    Melchiresas: HP=700
    Andrealphus: HP=200
    Pick an action to choose for Hero Segojan Earthcaller
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    a
    Which monster would you like to attack? Or go back? [-1]
    Melchiresas [0]: HP = 700
    Andrealphus [1]: HP = 200
    0
    Hero Segojan Earthcaller attacked Monster Melchiresas for 725 damage!
    Monster Melchiresas has fainted!
    ---------------
    Monster's Turn!
    Monster Andrealphus attacked Hero Eunoia Cyn for 650 damage!
    Hero Eunoia Cyn has fainted!
    Hero's Turn!
    -------------------
    Hero Party's State:
    Garl Glittergold: HP=100; MP=100
    Available Items & Spells:
    No items in inventory for Garl Glittergold
    Segojan Earthcaller: HP=100; MP=900
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    Andrealphus: HP=200
    Pick an action to choose for Hero Garl Glittergold
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    a
    Which monster would you like to attack? Or go back? [-1]
    Andrealphus [0]: HP = 200
    0
    Hero Garl Glittergold attacked Monster Andrealphus for 650 damage!
    Monster Andrealphus has fainted!
    You've successfully defeated all the monsters!
    Alive party members gained 800 gold
    Garl GlittergoldGold: (2500->3300)
    Segojan EarthcallerGold: (2500->3300)
    Non-fainted hero Garl Glittergold gains back with 10% of their max HP [500] & MP [100] HP: 100->150MP: 100->110
    Non-fainted hero Segojan Earthcaller gains back with 10% of their max HP [500] & MP [900] HP: 100->150MP: 900->990
    Fainted hero Eunoia Cyn revives with 50% of their max HP [500] & MP [400]HP: 0->250MP: 400->400
    The whole party gained 6 exp
    Hero Eunoia Cyn leveled up! (1->2)
    Max Health: (500->525)
    Max Mana: (400->420)
    Strength: (700->735)
    Dexterity: (800->840)
    Agility: (800->840)
    Since this hero is a warrior, they gain extra strength and agility!
    Strength: (735->771)
    Agility: (840->882)
    Hero Garl Glittergold leveled up! (1->2)
    Max Health: (500->525)
    Max Mana: (100->105)
    Strength: (600->630)
    Dexterity: (500->525)
    Agility: (500->525)
    Since this hero is a paladin, they gain extra strength and dexterity!
    Strength: (630->661)
    Dexterity: (525->551)
    Hero Segojan Earthcaller leveled up! (1->2)
    Max Health: (500->525)
    Max Mana: (900->945)
    Strength: (800->840)
    Dexterity: (500->525)
    Agility: (500->525)
    Since this hero is a sorcerer, they gain extra dexterity and agility!
    Dexterity: (525->551)
    Agility: (525->551)
    Input any key to continue

    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | M | H | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a COMMON space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    a
    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | H |   | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a MARKET space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    m
    ----------------
    Item 0
    Required Level 3
    Name: Breastplate
    Cost: 350
    Required Level: 3
    Damage Reduction: 600
    ----------------
    Item 1
    Name: Magic Potion
    Required Level: 2
    Cost: 350
    Required Level: 2
    Attribute Increase: 100
    Attributes Affected: Mana
    ----------------
    Item 2
    Name: Arctic Storm
    Required Level: 6
    Cost: 700
    Required Level: 6
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    ----------------
    Item 3
    Name: Dagger
    Required Level: 1
    Cost: 200
    Required Level: 1
    Damage Amplification: 250
    Required Hands: 1
    Will hero Eunoia Cyn (Gold: 2500) like to buy [B], sell [S], move on to the next hero [N] or exit [E]?
    b
    ----------------
    Item 0
    Required Level 3
    Name: Breastplate
    Cost: 350
    Required Level: 3
    Damage Reduction: 600
    ----------------
    Item 1
    Name: Magic Potion
    Required Level: 2
    Cost: 350
    Required Level: 2
    Attribute Increase: 100
    Attributes Affected: Mana
    ----------------
    Item 2
    Name: Arctic Storm
    Required Level: 6
    Cost: 700
    Required Level: 6
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    ----------------
    Item 3
    Name: Dagger
    Required Level: 1
    Cost: 200
    Required Level: 1
    Damage Amplification: 250
    Required Hands: 1
    Which item (or none [-1]) would you like to buy?
    Hero Eunoia Cyn has 2500 gold.
    0
    ----------------
    Item 0
    Name: Magic Potion
    Required Level: 2
    Cost: 350
    Required Level: 2
    Attribute Increase: 100
    Attributes Affected: Mana
    ----------------
    Item 1
    Name: Arctic Storm
    Required Level: 6
    Cost: 700
    Required Level: 6
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    ----------------
    Item 2
    Name: Dagger
    Required Level: 1
    Cost: 200
    Required Level: 1
    Damage Amplification: 250
    Required Hands: 1
    Will hero Garl Glittergold (Gold: 3300) like to buy [B], sell [S], move on to the next hero [N] or exit [E]?
    b
    ----------------
    Item 0
    Name: Magic Potion
    Required Level: 2
    Cost: 350
    Required Level: 2
    Attribute Increase: 100
    Attributes Affected: Mana
    ----------------
    Item 1
    Name: Arctic Storm
    Required Level: 6
    Cost: 700
    Required Level: 6
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    ----------------
    Item 2
    Name: Dagger
    Required Level: 1
    Cost: 200
    Required Level: 1
    Damage Amplification: 250
    Required Hands: 1
    Which item (or none [-1]) would you like to buy?
    Hero Garl Glittergold has 3300 gold.
    1
    ----------------
    Item 0
    Name: Magic Potion
    Required Level: 2
    Cost: 350
    Required Level: 2
    Attribute Increase: 100
    Attributes Affected: Mana
    ----------------
    Item 1
    Name: Dagger
    Required Level: 1
    Cost: 200
    Required Level: 1
    Damage Amplification: 250
    Required Hands: 1
    Will hero Segojan Earthcaller (Gold: 3300) like to buy [B], sell [S], move on to the next hero [N] or exit [E]?
    e
    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | H |   | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a MARKET space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    e
    Eunoia Cyn's Inventory: 
    [0] Name: Breastplate
    Cost: 350
    Damage Reduction: 600
    Which item would you like to equip? Or none [-1]
    0
    Eunoia Cyn (lvl 2) is underleveled for the item! Required Level: 3
    Garl Glittergold's Inventory: 
    [0] Name: Arctic Storm
    Cost: 700
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    Which item would you like to equip? Or none [-1]
    -1
    No items in inventory for Segojan Earthcaller
    Input any key to continue

    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | H |   | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a MARKET space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    d
    The party runs into an encounter!
    Hero's Turn!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=250; MP=400
    Available Items & Spells:
    Eunoia Cyn's Inventory: 
    [0] Name: Breastplate
    Cost: 350
    Damage Reduction: 600
    Garl Glittergold: HP=150; MP=110
    Available Items & Spells:
    Garl Glittergold's Inventory: 
    [0] Name: Arctic Storm
    Cost: 700
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    Segojan Earthcaller: HP=150; MP=990
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    Cyrrollalee: HP=700
    Phaarthurnax: HP=600
    Merrshaullk: HP=1000
    Pick an action to choose for Hero Eunoia Cyn
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    a
    Which monster would you like to attack? Or go back? [-1]
    Cyrrollalee [0]: HP = 700
    Phaarthurnax [1]: HP = 600
    Merrshaullk [2]: HP = 1000
    0
    Hero Eunoia Cyn attacked Monster Cyrrollalee for 370 damage!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=250; MP=400
    Available Items & Spells:
    Eunoia Cyn's Inventory: 
    [0] Name: Breastplate
    Cost: 350
    Damage Reduction: 600
    Garl Glittergold: HP=150; MP=110
    Available Items & Spells:
    Garl Glittergold's Inventory: 
    [0] Name: Arctic Storm
    Cost: 700
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    Segojan Earthcaller: HP=150; MP=990
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    Cyrrollalee: HP=330
    Phaarthurnax: HP=600
    Merrshaullk: HP=1000
    Pick an action to choose for Hero Garl Glittergold
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    s
    //If you're going actually going through this, this print was made before spell level check
    //was added. This should work as expected now, where the user is not allowed to use the spell.
    Which spell would you like to cast? Or go back? [-1]
    Available spells for Hero Garl Glittergold
    Spell [0]
    Name: Arctic Storm
    Cost: 700
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    Which monster would you like to attack? Or go back? [-1]
    Cyrrollalee [0]: HP = 330
    Phaarthurnax [1]: HP = 600
    Merrshaullk [2]: HP = 1000
    0
    Hero Garl Glittergold cast Arctic Storm at Monster Cyrrollalee for 400 damage!
    Monster Cyrrollalee has fainted!
    -------------------
    Hero Party's State:
    Eunoia Cyn: HP=250; MP=400
    Available Items & Spells:
    Eunoia Cyn's Inventory: 
    [0] Name: Breastplate
    Cost: 350
    Damage Reduction: 600
    Garl Glittergold: HP=150; MP=110
    Available Items & Spells:
    Garl Glittergold's Inventory: 
    [0] Name: Arctic Storm
    Cost: 700
    Damage: 800
    Mana Cost: 300
    Spell Type: Ice
    Segojan Earthcaller: HP=150; MP=990
    Available Items & Spells:
    No items in inventory for Segojan Earthcaller
    -------------------
    Monster Party's State:
    Phaarthurnax: HP=600
    Merrshaullk: HP=1000
    Pick an action to choose for Hero Segojan Earthcaller
    Attack [A], Cast Spell [S], Use Potion [P], Equip Armor/Weapon [E], Check Party Information [I], Run Away! [R]
    r
    The party managed to run away!
    The party doesn't gain anything
    Input any key to continue

    +---+---+---+---+---+---+---+---+
    |   |   | M |   | X | M |   | X |
    +---+---+---+---+---+---+---+---+
    | X |   | M |   | M |   |   | X |
    +---+---+---+---+---+---+---+---+
    | M | M |   | M | M |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   | X |   | X | M | H | M | M |
    +---+---+---+---+---+---+---+---+
    | M | M |   |   | M | X |   | M |
    +---+---+---+---+---+---+---+---+
    | M |   | M | M | M | X | X |   |
    +---+---+---+---+---+---+---+---+
    | M |   |   |   | X |   |   |   |
    +---+---+---+---+---+---+---+---+
    |   |   |   |   | M | X |   |   |
    +---+---+---+---+---+---+---+---+
    You are currently standing on a COMMON space.
    Up [W], Left [A], Down [S], Right [D], Equip [E], Enter Market [M], See Party Information [I]
    q
    Thanks for playing!