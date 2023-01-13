public class Cleric  extends SimpleCharacter {
    Cleric() {
        super("cleric", 14, 17);
    }

    @Override
    public void armor() {
        System.out.println("I wear medium armor!");
    }

    @Override
    public void sneaky() {
    }

    @Override
    public void lock_picking() {
    }

    @Override
    public void spell_casting() {
        System.out.println("My real power is in my spells!");
    }

    @Override
    public void healer() {
        System.out.println("My friends rely upon my medical skills!");
    }
}
