public class Knight extends Character {
    Knight() {
        super(17, 17, new SwordBehavior());
    }

    @Override
    public String toString() {
        return "Knight in Shining Armor";
    }
}