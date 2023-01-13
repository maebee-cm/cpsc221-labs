// A character that uses martial weapons
public abstract class MartialCharacter extends Character {
    MartialCharacter(String name, int hp, int ac) {
        super(name, hp, ac);
    }
    @Override
    public void weapon() {
        System.out.println("I wield martial weapons against monsters");
    }

    // No martial characters have these traits

    @Override
    public void lock_picking() {}

    @Override
    public void spell_casting() {}

    @Override
    public void healer() {}

    @Override
    public void solver() {}
}
