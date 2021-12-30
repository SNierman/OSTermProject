package finalProject;

public class WorkTimeCounter {
	
	private int workTimeRemaining;
	
	public WorkTimeCounter () {

		this.workTimeRemaining = 0;
	}

	public int getWorkTimeRemaining() {
		return workTimeRemaining;
	}

	public void addOptimalJob() {
		
		 this.workTimeRemaining += 2;
	}
	
	public void removeOptimalJob() {
		
		if(this.workTimeRemaining >= 2) {
			
			this.workTimeRemaining -= 2;
		}
		
		else {
			throw new RuntimeException("Work Time remaining can not be negative.");
		}
		 
	}
	
	public void addNonOptimalJob() {
		
		 this.workTimeRemaining += 10;
	}
	
	public void removeNonOptimalJob() {
		
		if(this.workTimeRemaining >= 10) {
		 this.workTimeRemaining -= 10;
		 
		}
		
		else {
			throw new RuntimeException("Work Time remaining can not be negative.");
		}
	}

}
