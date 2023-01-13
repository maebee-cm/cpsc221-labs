public class Ranger extends MediumMartialCharacter{
    Ranger() {
        super("ranger", 13, 15);
    }

    @Override
    public void maneuvers() {}

    @Override
    public void rages() {}

    @Override
    public void sneaky() {
        System.out.println("I am very sneaky!");
    }

    @Override
    public void survival() {
        System.out.println("I will survive in the wild!");
    }
}
