interface LongRest {
	void stayAwake(OnWatch watcher);
	void goToSleep(OnWatch watcher);
	void somethingHappened();
}