package br.com.discover.estudosNathan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.estudosNathan.domain.Cliente;
import br.com.discover.estudosNathan.repositories.ClienteRepository;
import br.com.discover.estudosNathan.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	// Automaticamente instanciada
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id
					+ " , Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
