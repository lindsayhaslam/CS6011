//Created by Sarah Bateman
package Adventure;
import java.util.Timer;

//I have a dog treat that Lindsay Haslam uses in her code and I need a stool that Corinne Jones has in her code
public class Elevator extends Room {
    boolean hasStool = false;

    //room has a dog treat and plaque with my info
    public Elevator() {
        super("Elevator", "An unstable and out of order elevator.");
        Item treat = new Item("Dog treat", "A dog biscuit that is taped to the ceiling. Don't ask.");
        items_.add(treat);
        Item plaque = new Item("Plaque", "Reads: Created by Sarah Bateman - MSD 2024.");
        items_.add(plaque);
    }

    //overriding the getItem method so that it will only get the dog treat if they have a stool
    @Override
    public Item getItem(String name) {
        for (Item item : Adventure.inventory) {
            if (item.getName().equals("stool")) {
                hasStool = true;
                break;
            }
        }
        for (Item item : items_) {
            if (hasStool) {
                if (item.getName().equals(name)) {
                    items_.remove(item);
                    return item;
                }
            } else {
                System.out.println("You are not tall enough to reach the treat.");
            }
        }
        return null;
    }

}