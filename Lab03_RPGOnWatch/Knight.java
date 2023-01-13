public class Knight extends Character {
	public Knight(LongRest rest) {
		super(rest, "Enjoying some quiet time polishing my sword.");
	}

	public String toString() {
		return "Knight in Shining Armor";
	}

	@Override
	public void display() {
		if (!super.isAsleep) {
			System.out.println(switch (encounter) {
				case Nothing -> DEFAULT_ATTITUDE;
				case Squirrel -> "I hope the squirrel doesn't encounter an owl.";
				case Goblin -> "I bet I could scare the goblin away with a creepy noise.";
				case Troll -> "Now it's time to prove my mettle on this troll.";
				case Dragon -> "I don't like this dragon... I've been burnt before!";
			});
		}
		else {
			System.out.println("Sleeping the night away.");
		}
	}
}