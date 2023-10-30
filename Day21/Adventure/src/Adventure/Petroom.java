//Created by Lindsay Haslam
package Adventure;
public class Petroom extends Room {

    private boolean unlocked_ = true;
    boolean hungryDog = true;

    public Petroom() {
        super("petroom", "a room with a hungry three-headed dog.");
        Item fob = new Item("fob", "A random fob that must go to something.");
        Item plaque=new Item("Plaque", "This room was created by Lindsay Haslam MSD2024, enjoy");
        items_.add(fob);
        items_.add(plaque);
    }

        @Override
        public void playerEntered () {
            if (unlocked_) {
                System.out.println("You hear heavy breathing and classical music...");
            }
        }

        @Override
        public boolean handleCommand (String[]subcommands ){

            if (subcommands.length <= 1) {
                return false;
            }
            String cmd = subcommands[0];
            String attr = subcommands[1];

            // unlock, use
            if (cmd.equals("give") && attr.equals("Dog treat")) {

                boolean hasTreat = false;
                for (Item item : Adventure.inventory) {
                    if (item.getName().equals("Dog treat")) {
                        hasTreat = true;
                        break;
                    }
                }
                if (hasTreat) {
                    System.out.println("You gave the dog a treat.");
                    hungryDog = false;
                } else {
                    System.out.println("You don't have a treat. You could die.");
                }
                return true;
            }
            return false;
        }
    }
