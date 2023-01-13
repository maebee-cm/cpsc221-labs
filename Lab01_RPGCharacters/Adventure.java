public class Adventure {
    public static void main(String[] args) {
        Knight knight = new Knight();
        Barbarian barbarian = new Barbarian();
        Ranger ranger = new Ranger();
        Rogue rogue = new Rogue();
        Cleric cleric = new Cleric();
        Wizard wizard = new Wizard();

        System.out.println(knight);
        knight.armor();
        knight.weapon();
        knight.maneuvers();

        System.out.println("\n"+barbarian);
        barbarian.armor();
        barbarian.weapon();
        barbarian.rages();

        System.out.println("\n"+ranger);
        ranger.armor();
        ranger.weapon();
        ranger.sneaky();
        ranger.survival();

        System.out.println("\n"+rogue);
        rogue.armor();
        rogue.weapon();
        rogue.sneaky();
        rogue.lock_picking();

        System.out.println("\n"+cleric);
        cleric.armor();
        cleric.weapon();
        cleric.spell_casting();
        cleric.healer();

        System.out.println("\n"+wizard);
        wizard.armor();
        wizard.weapon();
        wizard.spell_casting();
        wizard.solver();
    }
}
