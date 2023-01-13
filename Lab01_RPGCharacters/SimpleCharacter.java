// Uses simple weapons
public abstract class SimpleCharacter extends Character {

    SimpleCharacter(String name, int hp, int ac) {
        super(name, hp, ac);
    }

    @Override
    public void weapon() {
        System.out.println("I kill monsters with simple weapons.");
    }


    // No characters that use simple weapons have these traits
    @Override
    public void maneuvers() {}

    @Override
    public void rages() {}

    @Override
    public void survival() {}

    @Override
    public void solver() {}
}
