public class Ranger extends Character {
	public Ranger(LongRest rest) {
		super(rest, "The stars are beautiful.");
	}

	public String toString() {
		return "Quick, Stealthy Ranger";
	}

	@Override
	public void display() {
		if (!super.isAsleep) {
			System.out.println(switch (encounter) {
				case Nothing -> DEFAULT_ATTITUDE;
				case Squirrel -> "The simple life of a squirrel is marvelous.";
				case Goblin -> "Goblins are such a distortion of nature.";
				case Troll -> "I hate trolls! Time to light up my arrows.";
				case Dragon -> "My arrows cannot penetrate the dragon scales!";
			});
		}
		else {
			System.out.println("Sleeping the night away.");
		}
	}
}