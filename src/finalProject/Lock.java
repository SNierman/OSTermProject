package finalProject;

public class Lock {
	boolean usingReader;
	public Lock() {
		usingReader = false;
	}
	public boolean isUsingReader() {
		return usingReader;
	}
	public void doneUsingReader() {
		usingReader = false;
	}
	public void wantUsingReader() {
		usingReader = true;
	}
	
}
