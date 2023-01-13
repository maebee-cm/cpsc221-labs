public class Ranger extends Character {
    Ranger() {
        super(13, 15, new BowAndArrowBehavior());
    }

    @Override
    public String toString() {
        return "Quick, Stealthy Ranger";
    }
}