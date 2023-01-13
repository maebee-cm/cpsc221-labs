public class Barbarian extends Character {
    Barbarian() {
        super(21, 13, new AxeBehavior());
    }

    @Override
    public String toString() {
        return "Fur-clad Raging Barbarian";
    }
}