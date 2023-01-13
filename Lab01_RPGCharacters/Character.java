public abstract class Character {
    private final String name;
    private final int hp;
    private final int ac;

    Character(String name, int hp, int ac) {
        this.name = name;
        this.hp = hp;
        this.ac = ac;
    }

    public abstract void armor();

    public abstract void weapon();

    public abstract void maneuvers();

    public abstract void rages();

    public abstract void sneaky();

    public abstract void survival();

    public abstract void lock_picking();

    public abstract void spell_casting();

    public abstract void healer();

    public abstract void solver();

    public String toString() {
        return "I am a " + name + ".\nI have " +  hp + " HPs and " + ac + " AC";
    }
}
