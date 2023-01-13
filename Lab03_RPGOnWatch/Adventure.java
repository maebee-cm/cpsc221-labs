public class Adventure {
	public static void main(String[] args) {
		NightInTheWilderness night = new NightInTheWilderness();
		Character[] partyMembers = new Character[4];

		partyMembers[0] = new Knight(night);
		partyMembers[1] = new Barbarian(night);
		partyMembers[2] = new Ranger(night);
		partyMembers[3] = new Wizard(night);

		System.out.println("--- First Watch ---");
		partyMembers[0].stayAwakeForWatch();
		partyMembers[1].stayAwakeForWatch();
		partyMembers[2].goToSleep();
		partyMembers[3].goToSleep();
		night.setEncounter(Encounter.Troll);

		for (Character member : partyMembers) {
			System.out.println("* " + member);
			member.display();
			System.out.println();
		}

		System.out.println("--- Second Watch ---");
		partyMembers[0].goToSleep();
		partyMembers[1].stayAwakeForWatch();
		partyMembers[2].stayAwakeForWatch();
		partyMembers[3].goToSleep();
		night.setEncounter(Encounter.Squirrel);

		for (Character member : partyMembers) {
			System.out.println("* " + member);
			member.display();
			System.out.println();
		}

		System.out.println("--- Third Watch ---");
		partyMembers[0].goToSleep();
		partyMembers[1].goToSleep();
		partyMembers[2].stayAwakeForWatch();
		partyMembers[3].stayAwakeForWatch();
		night.setEncounter(Encounter.Goblin);

		for (Character member : partyMembers) {
			System.out.println("* " + member);
			member.display();
			System.out.println();
		}

		System.out.println("--- Fourth Watch ---");
		partyMembers[0].stayAwakeForWatch();
		partyMembers[1].goToSleep();
		partyMembers[2].goToSleep();
		partyMembers[3].stayAwakeForWatch();
		night.setEncounter(Encounter.Dragon);

		for (Character member : partyMembers) {
			System.out.println("* " + member);
			member.display();
			System.out.println();
		}

	}
}