public abstract class Character implements OnWatch, DisplayAttitude {
	protected Encounter encounter = Encounter.Nothing;
	protected final String DEFAULT_ATTITUDE;
	protected boolean isAsleep = false;
	private final LongRest rest;

	public Character(LongRest rest, String defaultAttitude) {
		this.rest = rest;
		DEFAULT_ATTITUDE = defaultAttitude;
	}

	public void goToSleep() {
		rest.goToSleep(this);
		isAsleep = true;
	}

	public void stayAwakeForWatch() {
		rest.stayAwake(this);
		isAsleep = false;
	}

	public void observeEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
}