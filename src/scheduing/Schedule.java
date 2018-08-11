package scheduing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import coordinator.Coordinator;
import processes.Processes;

public class Schedule {
	
	private static Schedule _pool;
	private ArrayList<Processes> _processes;
	
	public static Schedule pool() {
		if (_pool == null) {
			_pool = new Schedule();
			_pool._processes = new ArrayList<>();
		}
		return _pool;
	}
	
	private void hud() {
		int qtAtivos = 0;
		int qtInativos = 0;
		if (_pool._processes != null) {
			for (Processes p : _pool._processes) {
				if (p.isInactive()) {
					qtInativos ++;
				} else {
					qtAtivos ++;
				}
			}
		}
		System.out.println("Processo ativos: " + qtAtivos + " -- Processo inativos: " + qtInativos);
	}
	
	public Integer getIndex() {
		Random rand = new Random();
		int id = rand.nextInt(Integer.MAX_VALUE);
		if (_processes != null && _processes.size() >= id) {
			Processes p = _processes.get(id);
			if (p != null) {
				p.setInactive(false);
				return null;
			} else {
				return id;
			}
		} else {
			return id;
		}
	}
	
	public void add(Processes p) {
		Integer id = getIndex();
		if (id != null) {
			p.setId(id);
			p.setInactive(false);
			System.out.println("Processo " + p.getId() + " foi criado");
			_processes.add(p);
			hud();
		} else {
			System.out.println("Processo " + id + " ativado");
		}
	}
	
	public void inactivity() {
		Random rand = new Random();
		if (_processes.size() > 0) {
			int id = rand.nextInt(_processes.size());
			if (id > 0 && id < _processes.size()) {
				Processes p = _processes.get(id);
				if (p == Coordinator.pool().get()) {
					 inactivity();
				} else {
					System.out.println("Processo " + p.getId() + " inativado");
					p.setInactive(true);
				}
			}
		} else {
			System.out.println("Nenhum processo ativo para inativar!");
		}
	}
	
	public void request() {
		if (_processes != null) {
			Random rand = new Random();
			if (_processes.size() > 0) {
				int id = rand.nextInt(_processes.size());
				if (id >= 0 && id <= _processes.size()) {
					if (!_processes.get(id).sendRequest()) {
						if (Coordinator.pool().get() != null) {
							System.out.println("Coordenador " + Coordinator.pool().get().getId() + " não está respondendo");
						} else {
							bully();
						}
					} else {
						System.out.println("Processo " + _processes.get(id).getId() + " enviou requisição");
					}
				}
			}
		} else {
			System.out.println("Nenhum processo ativo para enviar a requisição!");
		}
	}
	
	public void bully() {
		Bully bully = new Bully();
		Collections.sort(_processes, bully);
		for(Processes p : _processes) {
			if (!p.isInactive()) {
				System.out.println("Processo " + _processes.get(0).getId() + " é o novo coordenador");
				Coordinator.pool().add(_processes.get(0));
				break;
			}
		}
	}

}
