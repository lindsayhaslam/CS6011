package Adventure;

public class Garage extends Room{

    private boolean locked_=true;
    public Garage() {
        super("Garage", "A garage with an unknown car");
        Item stool = new Item( "stool", "Something to hoist you into higher places...");
        Item plaque=new Item("plaque", "This room was created by Corinne MSD2024, enjoy");
        items_.add(stool);
        items_.add(plaque);
    }

    public void playerEntered() {
        if( locked_ ) {
            System.out.println( "You see a locked car, which requires a fob..." );
        }
    }

    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // unlock, use
        if( cmd.equals( "unlock" ) && attr.equals( "car") ) {

            boolean hasFob = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "fob" ) ) {
                    hasFob = true;
                    break;
                }
            }
            if( hasFob ) {
                System.out.println( "The car beeps to unlock and then turns on.");
                locked_ = false;
                endGame();

            }
            else {
                System.out.println( "You need a fob to unlock the car." );
            }
            return true;
        }
        return false;
    }

    public void endGame(){
        System.out.println("You get in the car and ride off into the sunset... until you run out of gas, of course.");
    }
}

