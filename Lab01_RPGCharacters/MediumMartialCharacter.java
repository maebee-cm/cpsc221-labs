// A character that uses martial weapons and wears medium armor
public abstract class MediumMartialCharacter extends MartialCharacter {
    MediumMartialCharacter(String name, int hp, int ac) {
        super(name, hp, ac);
    }

    @Override
    public void armor() {
        System.out.println("I wear medium armor");
    }
}
