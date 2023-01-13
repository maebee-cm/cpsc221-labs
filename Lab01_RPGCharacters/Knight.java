public class Knight extends MartialCharacter{
    Knight() {
        super("knight", 17, 17);
    }

    @Override
    public void armor() {
        System.out.println("I wear heavy armor!");
    }

    @Override
    public void maneuvers() {
        System.out.println("I got some pretty cool fighting moves!");
    }

    @Override
    public void rages() {}

    @Override
    public void sneaky() {}

    @Override
    public void survival() {}
}
