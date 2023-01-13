public class Wizard extends Character{
    Wizard() {
        super("Wizard", 9, 10);
    }

    @Override
    public void armor() {}

    @Override
    public void weapon() {
        System.out.println("I can hit monsters with my staff.");
    }

    @Override
    public void maneuvers() {}

    @Override
    public void rages() {}

    @Override
    public void sneaky() {}

    @Override
    public void survival() {}

    @Override
    public void lock_picking() {}

    @Override
    public void spell_casting() {
        System.out.println("My real power is in my spells!");
    }

    @Override
    public void healer() {}

    @Override
    public void solver() {
        System.out.println("I am the best problem solver in the party!");
    }
}
