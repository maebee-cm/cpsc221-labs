public class Adventure {
    public static void main(String[] args) {
        Character[] partyMembers = new Character[4];
        partyMembers[0] = new Knight();
        partyMembers[1] = new Barbarian();
        partyMembers[2] = new Ranger();
        partyMembers[3] = new Wizard();

        for(Character character: partyMembers) {
            System.out.println(character);
            character.fight();
            System.out.println();
        }

        System.out.println("The Dragon Attacks...\n");
        partyMembers[0].setWeapon(new NoneBehavior());
        partyMembers[1].takeDamage(100);
        partyMembers[2].takeDamage(50);

        for(Character character: partyMembers) {
            System.out.println(character);
            character.fight();
            System.out.println();
        }
    }
}