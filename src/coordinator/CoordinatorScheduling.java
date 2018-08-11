package coordinator;

import java.util.concurrent.TimeUnit;

public class CoordinatorScheduling {
	
	/* TODO classe responsavel pela exclusão do coordenador*/
	
	public CoordinatorScheduling() {
		new Thread(coordinator()).start();
	}
	
	public Runnable coordinator() {
		return new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					Coordinator.pool().remove();
					try {
						Thread.sleep(TimeUnit.SECONDS.toMillis(100));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}

}
