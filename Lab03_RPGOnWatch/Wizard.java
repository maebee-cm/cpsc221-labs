public class Wizard extends Character {
	public Wizard(LongRest rest) {
		super(rest, "Reading my spell book.");
	}

	public String toString() {
		return "Mysterious, Arcane Wizard";
	}

	@Override
	public void display() {
		if (!super.isAsleep) {
			System.out.println(switch (encounter) {
				case Nothing -> DEFAULT_ATTITUDE;
				case Squirrel -> "I could transform that squirrel into a raven.";
				case Goblin -> "I hope that goblin doesn't see me.";
				case Troll -> "Oh no, a troll! I wish I had prepared my fireball spell!";
				case Dragon -> "See ya, I'm outta here!";
			});
		}
		else {
			System.out.println("Sleeping the night away.");
		}
	}
}