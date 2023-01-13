public class Rogue extends SimpleCharacter{
    Rogue() {
        super("rogue", 11, 16);
    }

    @Override
    public void armor() {
        System.out.println("I wear light armor!");
    }

    @Override
    public void sneaky() {
        System.out.println("I am very sneaky!");
    }

    @Override
    public void lock_picking() {
        System.out.println("Picking locks is my specialty!");
    }

    @Override
    public void spell_casting() {
    }

    @Override
    public void healer() {
    }
}
