public class Barbarian extends MediumMartialCharacter{
    Barbarian() {
        super("barbarian", 21, 13);
    }

    @Override
    public void maneuvers() {}

    @Override
    public void rages() {
        System.out.println("When I get angry, I fight better!");
    }

    @Override
    public void sneaky() {}

    @Override
    public void survival() {}
}
