package br.com.discover.estudosNathan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.estudosNathan.domain.Pedido;
import br.com.discover.estudosNathan.repositories.PedidoRepository;
import br.com.discover.estudosNathan.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	// Automaticamente instanciada
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. id: " + id
					+ " , Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

}
