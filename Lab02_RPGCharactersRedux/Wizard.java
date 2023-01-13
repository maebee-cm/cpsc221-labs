public class Wizard extends Character{
    Wizard() {
        super(9, 10, new MagicStaffBehavior());
    }

    @Override
    public String toString() {
        return "Mysterious, Arcane Wizard";
    }
}