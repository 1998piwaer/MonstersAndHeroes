What do I do with making separate boards, separate grids, separate settings, etc??
    Would this mean making a LoVSpace Interface which implements Space interface which makes LoV specific spaces?
    Same thing for Hero, before HeroParty would implement all coordinate related things (1 coordinate), now there is multiple coordinates, with monsters being completely different.
        Should this be some sort of adapter, making a separate class for each case feels like it breaks the purpose of the design patterns