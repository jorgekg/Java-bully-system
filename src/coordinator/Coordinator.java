package coordinator;

import java.util.ArrayList;

import processes.Processes;

public class Coordinator {
	
	private static Coordinator _pool;
	private Processes _process;
	
	/* TODO singleton coordenador */
	public static Coordinator pool() {
		if (_pool == null) {
			_pool = new Coordinator();
			new CoordinatorScheduling();
		}
		return _pool;
	}
	
	/* TODO requisição para o coordenador */
	public boolean request() {
		if (_process != null && !_process.isInactive()) {
			return true;
		}
		return false;
	}
	
	/* TODO adicionar novo processo coordenador*/
	public void add(Processes proc) {
		_process = proc;
	}
	
	
	public Processes get() {
		return _process;
	}
	
	public void remove() {
		if (_process != null) {
			System.out.println("Removendo o coordenador " + _process.getId());
			_process = null;
		} else {
			System.out.println("Nenhum coordenador para remover");
		}
	}
}
