 /*
  * ItemFactory.java
  * by Simon Kye (simonkye@bu.edu)
  * 3/30/2024
  *
  * Utilizes Factory pattern
  * Has a simple createItem method. Since the return type is Item,
  * all a market has to do is add factories that implement ItemFactory
  * and make the factories add to its catalog, which is a list of items.
  */

public interface ItemFactory {
    Item createItem();
}