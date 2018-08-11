package processes;

import coordinator.Coordinator;
import scheduing.Schedule;

public class Processes {
	
	private Integer id;
	private boolean inactive;
	
	public boolean sendRequest() {
		return Coordinator.pool().request();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	
}
