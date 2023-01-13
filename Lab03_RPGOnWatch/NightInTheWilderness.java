import java.util.ArrayList;

public class NightInTheWilderness implements LongRest {
	ArrayList<OnWatch> watchers;
	Encounter encounter;

	NightInTheWilderness() {
		watchers = new ArrayList<OnWatch>();
	}

	public void setEncounter(Encounter encounter) {
		if (this.encounter != encounter) {
			this.encounter = encounter;
			somethingHappened();
		}
	}

	@Override
	public void stayAwake(OnWatch watcher) {
		if (watchers.indexOf(watcher) < 0) {
			watchers.add(watcher);
		}
	}

	@Override
	public void goToSleep(OnWatch watcher) {
		watchers.remove(watcher);
	}

	@Override
	public void somethingHappened() {
		for(OnWatch watcher: watchers) {
			watcher.observeEncounter(encounter);
		}
	}
}