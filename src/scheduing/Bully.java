package scheduing;

import java.util.Comparator;

import processes.Processes;

public class Bully implements Comparator<Processes>{

	@Override
	public int compare(Processes arg0, Processes arg1) {
		return arg0.getId().compareTo(arg1.getId());
	}


}
