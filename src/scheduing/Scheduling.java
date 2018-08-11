package scheduing;

import java.util.concurrent.TimeUnit;

import processes.Processes;

public class Scheduling {

	public Scheduling() {
		new Thread(scheduler()).start();
		new Thread(request()).start();
		new Thread(inactivate()).start();
	}
	
	private Runnable scheduler() {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
					Processes p = new Processes();
					Schedule.pool().add(p);
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(30));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
	}
	
	private Runnable request() {
		return new Runnable() {	
			@Override
			public void run() {
				while (true) {
					Schedule.pool().request();
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(25));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	private Runnable inactivate() {
		return new Runnable() {
			@Override
			public void run() {
				while (true) {
					Schedule.pool().inactivity();
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(80));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}
}
