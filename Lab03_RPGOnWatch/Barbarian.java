public class Barbarian extends Character {
	public Barbarian(LongRest rest) {
		super(rest, "Staring into the fire... feeling hungry.");
	}

	public String toString() {
		return "Fur-clad Raging Barbarian";
	}

	@Override
	public void display() {
		if (!super.isAsleep) {
			System.out.println(switch (encounter) {
				case Nothing -> DEFAULT_ATTITUDE;
				case Squirrel -> "Squirrel would make a good snack.";
				case Goblin -> "I could slice that goblin in two with one blow.";
				case Troll -> "Bet I could dice up troll faster than it regenerates.";
				case Dragon -> "Wonder how bad the dragon's fire hurts!";
			});
		}
		else {
			System.out.println("Sleeping the night away.");
		}
	}
}